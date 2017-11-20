package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeInputActivity extends AppCompatActivity {

    ArrayList<String> words = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_input);
    }

    public void goToThinkingStoryActivity(View view){
        Intent intent = new Intent(this, ThinkingStoryActivity.class);
        intent.putExtra("words", words);
        startActivity(intent);
        finish();
    }

    // This method is triggered when the button is pressed to enter a word. The word is added to
    // the data representation.
    public void enterText(View view){
        TextView textView = findViewById(R.id.textField);
        String word = textView.getText().toString();
        words.add(word);
        TextView wordsSoFar = findViewById(R.id.textSoFar);
        //Toast toast = Toast.makeText(this, words.toString(), Toast.LENGTH_SHORT);
        //toast.show();
        wordsSoFar.setText(wordsSoFar.getText() + word);

        // clear text field for easy reentering
        wordsSoFar.setText("");
    }
}
