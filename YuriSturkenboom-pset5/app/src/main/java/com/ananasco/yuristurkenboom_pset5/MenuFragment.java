package com.ananasco.yuristurkenboom_pset5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;


/**

 */
public class MenuFragment extends ListFragment {

    ArrayAdapter<String> adapter;
    List<String> categoriesArray = new ArrayList<>();
    String category;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        RestoDataBase db = RestoDataBase.getInstance(getContext());
        db.addItem(l.getItemAtPosition(position).toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                categoriesArray);

        category = getArguments().get("category").toString();
        this.setListAdapter(adapter);
        doVolleyStuff();
        adapter.notifyDataSetChanged();
    }

    // Note: all stuff that needs to be done async needs to be in the listener!
    private void doVolleyStuff(){

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String location = "https://resto.mprog.nl/";
        String endpoint = "menu";
        String query = "?category=" + category;
        String url = location + endpoint + query;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            categoriesArray.clear();
                            categoriesArray.addAll(parseJSON(response));
                            adapter.notifyDataSetChanged();
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
    public List<String> parseJSON(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arr = object.getJSONArray("items");
        List<String> itemList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = (JSONObject) arr.get(i);
            itemList.add(obj.getString("name"));

            // put in map for easy access later when we go to an item page, might as well
            //nameToJSONObjMap.put(obj.getString("name"), obj);
        }
        return itemList;
    }
}
