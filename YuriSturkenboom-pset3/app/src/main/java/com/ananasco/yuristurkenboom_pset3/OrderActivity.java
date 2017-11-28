package com.ananasco.yuristurkenboom_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    Order order;
    List<String> itemList = new ArrayList<>();
    ArrayAdapter itemArrayAdapter;
    Map<String, JSONObject> nameToJSONObjMap = new HashMap<>();
    Double finalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        finalPrice = 0.0;
        order = new Order(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order");

        final ListView listView = findViewById(R.id.listView);

        itemArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itemList);

        listView.setAdapter(itemArrayAdapter);
        doVolleyStuff();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // very insecure.
    private void updateOrder() {
        Map<String, ?> map = order.getAllItems();
        itemList.clear();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            try {
                System.out.println(nameToJSONObjMap.toString());
                int cost = nameToJSONObjMap.get(entry.getKey()).getInt("price");
                int amount = (Integer) entry.getValue();
                itemList.add(amount + "x " + entry.getKey() + ": $" + (cost * amount));
                finalPrice += cost * amount;
            }
            catch (JSONException e){
                System.out.println(e);
            }
        }
        itemArrayAdapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.total)).setText("$"+finalPrice);
    }

    public void submitOrder(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String location = "https://resto.mprog.nl/";
        String endpoint = "order";
        String url = location + endpoint;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://resto.mprog.nl/order",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            goToOrderTime(new JSONObject(response).getInt("preparation_time"));
                        }
                        catch (JSONException e){
                            System.out.println("If this happens, abandon all hope");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with Volley");
            }
        }
        ) {
            @Override
            @SuppressWarnings("unchecked")
            protected Map<String, String> getParams () {
                //return new HashMap<String, String>((Map)order.getAllItems());
                return new HashMap<String, String>();
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);;
    }

    public void goToOrderTime(int waitTime) {
        Intent intent = new Intent(this, OrderTimeActivity.class);
        intent.putExtra("wait_time", waitTime);
        startActivity(intent);
        finish();

    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Note: all stuff that needs to be done async needs to be in the listener!
    public void doVolleyStuff(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String location = "https://resto.mprog.nl/";
        String endpoint = "menu";
        String url = location + endpoint;

        System.out.println(url +  "HALLO");


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://resto.mprog.nl/menu",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("wtf");
                            parseJSON(response);
                            updateOrder();
                        }
                        catch (JSONException e){
                            System.out.println("Malformed JSON Array");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with Volley");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // different from the one in main, there has to be a better way to do this...
    public void parseJSON(String response) throws JSONException {
        System.out.println("HOI");
        JSONObject object = new JSONObject(response);
        JSONArray arr = object.getJSONArray("items");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = (JSONObject) arr.get(i);

            nameToJSONObjMap.put(obj.getString("name"), obj);
        }
    }
}
