package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ThinkingStoryActivity extends AppCompatActivity {

    Handler delayHandler = new Handler();
    String storyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinking_story);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        storyString = getIntent().getStringExtra("storyString");

        // Adapted from https://stackoverflow.com/questions/41664409/wait-for-5-seconds
        delayHandler.postDelayed(new Runnable() {
            public void run() {
                goToStoryShowActivity();
            }
        }, 3000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                System.out.println(NavUtils.getParentActivityName(this));
                Intent intent = new Intent(this, HomeInputActivity.class);

                // This is important, because if we try to go back to the Home activity without clearing
                // the handler, it will live on and execute the code after the delay that was intended to
                // only be executed in this Activity.
                if (delayHandler != null) {
                    delayHandler.removeCallbacksAndMessages(null);
                }

                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToStoryShowActivity(){
        Intent intent = new Intent(this, StoryShowActivity.class);
        intent.putExtra("storyString", storyString);
        startActivity(intent);
        finish();
    }

    public void goToHomeActivity(View view){

        // This is important, because if we try to go back to the Home activity without clearing
        // the handler, it will live on and execute the code after the delay that was intended to
        // only be executed in this Activity.
        if (delayHandler != null) {
            delayHandler.removeCallbacksAndMessages(null);
        }

        Intent intent = new Intent(this, HomeInputActivity.class);
        startActivity(intent);
        finish();
    }
}
