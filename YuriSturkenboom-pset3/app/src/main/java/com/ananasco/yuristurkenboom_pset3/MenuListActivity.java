package com.ananasco.yuristurkenboom_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class MenuListActivity extends AppCompatActivity {

    String origin;
    String category;
    ArrayAdapter<String> itemArrayAdapter;
    List<String> itemList = new ArrayList<>();
    Map<String, JSONObject> nameToJSONObjMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        origin =  getIntent().getStringExtra("origin");
        category = getIntent().getStringExtra("category");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category.substring(0, 1).toUpperCase()
                + category.substring(1));

        final ListView listView = findViewById(R.id.listView);

        itemArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itemList);

        listView.setAdapter(itemArrayAdapter);
        doVolleyStuff();
        itemArrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                String item = o.toString();
                navigateToItem(item, nameToJSONObjMap.get(item));
            }
        });
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

    public void navigateToItem(String itemString, JSONObject obj){
        Intent intent = new Intent(this, ItemShowActivity.class);
        intent.putExtra("item_name", itemString);
        intent.putExtra("origin", category);
        intent.putExtra("object", obj.toString());
        startActivity(intent);
        finish();
    }

    public void updateList(List<String> listString) {
        itemArrayAdapter.clear();
        itemArrayAdapter.addAll(listString);
        itemArrayAdapter.notifyDataSetChanged();
    }


    // Note: all stuff that needs to be done async needs to be in the listener!
    public void doVolleyStuff(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String location = "https://resto.mprog.nl/";
        String endpoint = "menu";
        String query = "?category=" + category;
        String url = location + endpoint + query;

        System.out.println(url);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            List<String> list = parseJSON(response);
                            updateList(list);
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
    public List<String> parseJSON(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arr = object.getJSONArray("items");
        List<String> itemList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = (JSONObject) arr.get(i);
            itemList.add(obj.getString("name"));

            // put in map for easy access later when we go to an item page, might as well
            nameToJSONObjMap.put(obj.getString("name"), obj);
        }
        return itemList;
    }
}
