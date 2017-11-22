package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class StoryShowActivity extends AppCompatActivity {

    String storyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        storyString = getIntent().getStringExtra("storyString");
        showResult();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                System.out.println(NavUtils.getParentActivityName(this));
                Intent intent = new Intent(this, HomeInputActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showResult() {
        TextView resultText = findViewById(R.id.textResult);
        resultText.setText(storyString);
    }

    public void goToHomeActivity(View view){
        Intent intent = new Intent(this, HomeInputActivity.class);
        startActivity(intent);
        finish();
    }
}
