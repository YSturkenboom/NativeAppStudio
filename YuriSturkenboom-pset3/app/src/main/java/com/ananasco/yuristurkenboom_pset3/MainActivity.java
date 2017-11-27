package com.ananasco.yuristurkenboom_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Vread stuff from JSON, layouts, activity nav., implement assignment, non-anonymous listeners

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    List<String> categoriesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categoriesArray);

        listView.setAdapter(arrayAdapter);
        doVolleyStuff();
        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                String destination = o.toString();
                navigateToMenu("categories", destination);
            }
        });
    }

    public void goToOrder(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        //intent.putExtra("origin", "main");
        //intent.putExtra("category", destination);
        startActivity(intent);
        finish();
    }

    private void navigateToMenu(String context, String destination){
        Intent intent = new Intent(this, MenuListActivity.class);
        intent.putExtra("origin", "main");
        intent.putExtra("category", destination);
        startActivity(intent);
        finish();
    }

    private void updateList(List<String> listString) {
        arrayAdapter.clear();
        arrayAdapter.addAll(listString);
        arrayAdapter.notifyDataSetChanged();
    }

    // Note: all stuff that needs to be done async needs to be in the listener!
    private void doVolleyStuff(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String location = "https://resto.mprog.nl/";
        String endpoint = "categories";
        String url = location + endpoint;

        final TextView mTextView = findViewById(R.id.text);

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

    // assumed input array in an object: {'bla': ['whatever','stuff']}
    private List<String> parseJSON(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arr = object.getJSONArray("categories");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }
}
