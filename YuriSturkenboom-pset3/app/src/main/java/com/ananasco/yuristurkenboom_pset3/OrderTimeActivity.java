package com.ananasco.yuristurkenboom_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class OrderTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_time);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Estimated order time");

        //((TextView) findViewById(R.id.time)).setText(getIntent().getIntExtra("preparation_time", 0));
        int prepTime = getIntent().getIntExtra("wait_time", 0);
        TextView tV = findViewById(R.id.hallohallohallo);
        tV.setText(prepTime + " minutes");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

}
