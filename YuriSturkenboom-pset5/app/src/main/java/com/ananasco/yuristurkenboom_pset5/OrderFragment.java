package com.ananasco.yuristurkenboom_pset5;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * Created by yuri on 1-12-17.
 */

public class OrderFragment extends DialogFragment implements View.OnClickListener {

    RestoDataBase db;
    RestoAdapter adapter;
    ListView listView;
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        return rootView;
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCancel:
                dismiss();
                break;
            case R.id.buttonPlace:
                placeOrder();
                break;

        }

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        //System.out.println("yolo");
        Cursor result = db.selectAll();
        adapter.swapCursor(result);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        Button b = (Button) view.findViewById(R.id.buttonCancel);
        b.setOnClickListener(this);
        b = (Button) view.findViewById(R.id.buttonPlace);
        b.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = RestoDataBase.getInstance(getContext());
        Cursor result = db.selectAll();
        adapter = new RestoAdapter(getContext(), result, R.layout.row_order);
        listView.setAdapter(adapter);
    }

    private void placeOrder(){
        Toast.makeText(getContext(), "Order placed!", Toast.LENGTH_SHORT).show();

    }

    // Note: all stuff that needs to be done async needs to be in the listener!
    private void doVolleyStuff(){

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String location = "https://resto.mprog.nl/";
        String endpoint = "order";
        String url = location + endpoint;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        try {
//                            //categoriesArray.clear();
//                            //categoriesArray.addAll(parseJSON(response));
//                            adapter.notifyDataSetChanged();
//                        }
//                        catch (JSONException e){
//                            System.out.println("Malformed JSON Array");
//                        }
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
}
