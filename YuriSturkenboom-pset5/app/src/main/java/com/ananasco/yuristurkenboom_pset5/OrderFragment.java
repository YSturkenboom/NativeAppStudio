package com.ananasco.yuristurkenboom_pset5;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by yuri on 1-12-17.
 */

public class OrderFragment extends DialogFragment {

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
}
