package com.ananasco.yuristurkenboom_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.List;

public class ItemShowActivity extends AppCompatActivity {

    String origin;
    String itemName;
    String description;
    Double price;
    String imgUrl;
    JSONObject obj;

    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_show);

        order = new Order(this);
        origin = getIntent().getStringExtra("origin");
        try {
            obj = new JSONObject(getIntent().getStringExtra("object"));
            itemName = obj.getString("name");
            description = obj.getString("description");
            price = Double.parseDouble(obj.getString("price"));
            imgUrl = obj.getString("image_url");
        }
        catch (JSONException e) {
            System.out.println(e);
            description = "Er is een fout opgetreden";
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(itemName.substring(0, 1).toUpperCase()
                + itemName.substring(1));

        System.out.println("WTF");
        TextView descView = findViewById(R.id.description);
        TextView priceView = findViewById(R.id.price);

        descView.setText(description);
        priceView.setText(price.toString());
        getImage(imgUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MenuListActivity.class);
                intent.putExtra("category", origin);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateImage(Bitmap bmp){
        ImageView imgView = findViewById(R.id.imgView);
        if (bmp != null){
            imgView.setImageBitmap(bmp);
        }
    }

    public void getImage(String url){
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        updateImage(response);
                    }
                },
                0, // Image width
                0, // Image height
                ImageView.ScaleType.FIT_CENTER, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with Volley");
                updateImage(null);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(imageRequest);
    }

    public void addToOrder(View view) {
        order.addItem(itemName, 1);
        order.addItem(itemName, 1);
        System.out.println(order.getItemAmount(itemName));
    }
}
