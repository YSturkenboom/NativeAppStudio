package com.ananasco.yuristurkenboom_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
        ListView listView = findViewById(R.id.listView);


         arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categoriesArray);

        listView.setAdapter(arrayAdapter);
        doVolleyStuff();
        arrayAdapter.notifyDataSetChanged();
    }
/*
    public void updateArrayAdapter(String[] list) {
        arrayAdapter.clear();

        for (String s : list) {
            arrayAdapter.insert(s, arrayAdapter.getCount());
        }

        arrayAdapter.notifyDataSetChanged();
    }
    */

    // Note: all stuff that needs to be done async needs to be in the listener!
    public void doVolleyStuff(){
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
                            categoriesArray.addAll(list);
                            System.out.println(categoriesArray);
                            mTextView.setText(categoriesArray.toString());

                        }
                        catch (JSONException e){
                            System.out.println("Malformed JSON Array");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // assumed input array in an object: {'bla': ['whatever','stuff']}
    public List<String> parseJSON(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arr = object.getJSONArray("categories");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }
}
