package com.ananasco.yuristurkenboom_pset3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

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
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yuri on 27-11-17.
 */

public class Order {

    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public Order(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("order", MODE_PRIVATE);
    }

    public Map<String, ?> getAllItems() {
        return prefs.getAll();
    }

    public void addItem(String name, int amount) {
        int existingAmount = getItemAmount(name);
        editor = prefs.edit();
        if (amount > 0) {
            editor.putInt(name, existingAmount + amount);
        } else {
            editor.putInt(name, 1);
        }
        editor.apply();
    }

    public void removeItem(String name, int amount) {
        int existingAmount = getItemAmount(name);
        editor = prefs.edit();
        if (amount > 1) {
            editor.putInt(name, existingAmount - amount);
        } else if (amount == 1) {
            editor.remove(name);
        }
        editor.apply();
    }

    public int getItemAmount(String name) {
        return prefs.getInt(name, 0);
    }
}

