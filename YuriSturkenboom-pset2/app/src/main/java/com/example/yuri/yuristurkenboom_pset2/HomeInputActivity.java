package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

//TODO: saveinstancestate, textfield overflow, screen size, rotationlayouts, nicer button selection
public class HomeInputActivity extends AppCompatActivity {

    Story currentStory;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_input);

        rnd = new Random();

        // the default story is simple
        InputStream is = getResources().openRawResource(R.raw.madlib0_simple);
        currentStory = new Story(is);

        update();
    }

    public void goToThinkingStoryActivity(View view){
        Intent intent = new Intent(this, ThinkingStoryActivity.class);
        intent.putExtra("storyString", currentStory.toString());
        startActivity(intent);
        finish();
    }

    // This method is triggered when the button is pressed to enter a word. The word is added to
    // the data representation.
    public void enterText(View view){
        TextView textField = findViewById(R.id.textField);
        String word = textField.getText().toString();

        if (!word.equals("")) {
            currentStory.fillInPlaceholder(word);
        }

        // clear text field for easy reentering
        textField.setText("");

        Toast t = Toast.makeText(getApplicationContext(), "Keep going!", Toast.LENGTH_SHORT);
        t.show();

        update();
    }

    public void update(){
        TextView wordsLeft = findViewById(R.id.wordsLeft);
        if(currentStory.getPlaceholderRemainingCount() == 0){
            wordsLeft.setText("All done!");
        }
        else {
            wordsLeft.setText("Only " + currentStory.getPlaceholderRemainingCount() + " words left!");
        }
        TextView requiredWord = findViewById(R.id.requiredWord);
        requiredWord.setText("Word required: " + currentStory.getNextPlaceholder());
        //TextView wordsSoFar = findViewById(R.id.wordsSoFar);
        //requiredWord.setText("Word required: " + currentStory.);
    }

    public void selectRandomStory(View view) {
        int randInt = rnd.nextInt(4);
        System.out.println(randInt);
        switch (randInt) {
            case 0:
                selectStory0(view);
                break;
            case 1:
                selectStory1(view);
                break;
            case 2:
                selectStory2(view);
                break;
            case 3:
                selectStory3(view);
                break;
            case 4:
                selectStory4(view);
                break;
        }
    }

    public void selectStory0(View view){
        InputStream is = getResources().openRawResource(R.raw.madlib0_simple);
        currentStory = new Story(is);
        update();
    }

    public void selectStory1(View view){
        InputStream is = getResources().openRawResource(R.raw.madlib1_tarzan);
        currentStory = new Story(is);
        update();
    }

    public void selectStory2(View view){
        InputStream is = getResources().openRawResource(R.raw.madlib2_university);
        currentStory = new Story(is);
        update();
    }

    public void selectStory3(View view){
        InputStream is = getResources().openRawResource(R.raw.madlib3_clothes);
        currentStory = new Story(is);
        update();
    }

    public void selectStory4(View view){
        InputStream is = getResources().openRawResource(R.raw.madlib4_dance);
        currentStory = new Story(is);
        update();
    }
}
