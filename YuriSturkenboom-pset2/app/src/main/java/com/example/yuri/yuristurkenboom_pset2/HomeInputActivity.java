package com.example.yuri.yuristurkenboom_pset2;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

//TODO: V saveinstancestate, V textfield overflow, V screen size, V rotationlayouts,
//TODO: V nicer button selection, fix warnings if possible, V back button behaviour
public class HomeInputActivity extends AppCompatActivity {

    Story currentStory;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_input);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.texts_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long whatever) {
                selectStory((String) parent.getItemAtPosition(position), getCurrentFocus());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                System.out.println("Nothing selected");
            }
        });

        rnd = new Random();

        // the default story is simple
        selectStory("Simple", getCurrentFocus());
        update();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("currentStory", currentStory);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        try {
            currentStory = (Story) outState.getSerializable("currentStory");
        }
        catch (Exception e) {
            System.out.println("No story loaded, probably arrived here from another activity");
        }
    }

    public void goToThinkingStoryActivity(View view){
        if (currentStory.getPlaceholderRemainingCount() == 0) {
            Intent intent = new Intent(this, ThinkingStoryActivity.class);
            intent.putExtra("storyString", currentStory.toString());
            startActivity(intent);
            finish();
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "Please enter some more words", Toast.LENGTH_LONG);
            t.show();
        }
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

        if (currentStory.getPlaceholderRemainingCount() > 0) {
            Toast t = Toast.makeText(getApplicationContext(), "Keep going!", Toast.LENGTH_LONG);
            t.show();
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "All done!", Toast.LENGTH_SHORT);
            t.show();
        }

        update();
    }

    public void update(){
        TextView wordsLeft = findViewById(R.id.wordsLeft);
        TextView requiredWord = findViewById(R.id.requiredWord);

        if(currentStory.getPlaceholderRemainingCount() == 0){
            wordsLeft.setText("All done!");
            requiredWord.setText("Done!");
        }
        else {
            wordsLeft.setText("Only " + currentStory.getPlaceholderRemainingCount() + " words left!");
            requiredWord.setText("Word required: " + currentStory.getNextPlaceholder());
        }


        requiredWord.setText("Word required: " + currentStory.getNextPlaceholder());
    }

    public void selectRandomStory(View view) {
        int randInt = rnd.nextInt(4);
        System.out.println(randInt);
        switch (randInt) {
            case 0:
                selectStory("Simple", view);
                break;
            case 1:
                selectStory("Tarzan", view);
                break;
            case 2:
                selectStory("University", view);
                break;
            case 3:
                selectStory("Clothes", view);
                break;
            case 4:
                selectStory("Dance", view);
                break;
        }
    }

    public void selectStory(String selection, View _){
        int rawId;
        switch (selection) {
            case "Simple":
                rawId = R.raw.madlib0_simple;
                break;
            case "Tarzan":
                rawId = R.raw.madlib1_tarzan;
                break;
            case "University":
                rawId = R.raw.madlib2_university;
                break;
            case "Clothes":
                rawId = R.raw.madlib3_clothes;
                break;
            default:
                rawId = R.raw.madlib4_dance;
                break;
        }
        InputStream is = getResources().openRawResource(rawId);
        currentStory = new Story(is);
        currentStory.setHtmlMode(true);
        update();
    }
}
