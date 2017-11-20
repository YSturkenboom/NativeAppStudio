package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        storyString = getIntent().getStringExtra("storyString");
        showResult();
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
