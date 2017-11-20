package com.ananasco.yuristurkenboom_pset1;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.BoolRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Map<CharSequence, ImageView> nameToImViewMap = new HashMap<CharSequence, ImageView>();
    Map<CharSequence, Boolean> nameToCheckedBoolMap = new HashMap<CharSequence, Boolean>();
    Map<CharSequence, CheckBox> nameToCheckBoxMap = new HashMap<CharSequence, CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameToImViewMap.put("Nose", (ImageView) findViewById(R.id.imViewNose));
        nameToImViewMap.put("Body", (ImageView) findViewById(R.id.imViewBody));
        nameToImViewMap.put("Hat", (ImageView) findViewById(R.id.imViewHat));
        nameToImViewMap.put("Eyebrows", (ImageView) findViewById(R.id.imViewEyebrows));
        nameToImViewMap.put("Eyes", (ImageView) findViewById(R.id.imViewEyes));
        nameToImViewMap.put("Glasses", (ImageView) findViewById(R.id.imViewGlasses));
        nameToImViewMap.put("Shoes", (ImageView) findViewById(R.id.imViewShoes));
        nameToImViewMap.put("Arms", (ImageView) findViewById(R.id.imViewArms));
        nameToImViewMap.put("Moustache", (ImageView) findViewById(R.id.imViewMoustache));
        nameToImViewMap.put("Mouth", (ImageView) findViewById(R.id.imViewMouth));

        nameToCheckedBoolMap.put("Nose", true);
        nameToCheckedBoolMap.put("Body", true);
        nameToCheckedBoolMap.put("Hat", true);
        nameToCheckedBoolMap.put("Eyebrows", true);
        nameToCheckedBoolMap.put("Eyes", true);
        nameToCheckedBoolMap.put("Glasses", true);
        nameToCheckedBoolMap.put("Shoes", true);
        nameToCheckedBoolMap.put("Arms", true);
        nameToCheckedBoolMap.put("Moustache", true);
        nameToCheckedBoolMap.put("Mouth", true);

        nameToCheckBoxMap.put("Nose", (CheckBox) findViewById(R.id.checkBoxNose));
        nameToCheckBoxMap.put("Body", (CheckBox) findViewById(R.id.checkBoxBody));
        nameToCheckBoxMap.put("Hat", (CheckBox) findViewById(R.id.checkBoxHat));
        nameToCheckBoxMap.put("Eyebrows", (CheckBox) findViewById(R.id.checkBoxEyebrows));
        nameToCheckBoxMap.put("Eyes", (CheckBox) findViewById(R.id.checkBoxEyes));
        nameToCheckBoxMap.put("Glasses", (CheckBox) findViewById(R.id.checkBoxGlasses));
        nameToCheckBoxMap.put("Shoes", (CheckBox) findViewById(R.id.checkBoxShoes));
        nameToCheckBoxMap.put("Arms", (CheckBox) findViewById(R.id.checkBoxArms));
        nameToCheckBoxMap.put("Moustache", (CheckBox) findViewById(R.id.checkBoxMoustache));
        nameToCheckBoxMap.put("Mouth", (CheckBox) findViewById(R.id.checkBoxMouth));
    }

    // This method toggles the checkbox boolean when tapped, and calls updates for the ImageViews
    // and CheckBox visuals.
    public void onCheckBoxTapped(View cBView) {
        CheckBox cB = (CheckBox) cBView;
        CharSequence name = cB.getContentDescription();
        ImageView imV = nameToImViewMap.get(name);

        // invert saved checkbox state (toggle)
        nameToCheckedBoolMap.put(name, !nameToCheckedBoolMap.get(name));
        update(cB, imV, nameToCheckedBoolMap.get(name));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Set names = nameToCheckedBoolMap.keySet();
        for (Object name : names) {
            outState.putBoolean(name + "_checked", nameToCheckedBoolMap.get(name));
            //outState.putSerializable("nameToImViewMap", (Serializable) nameToImViewMap);
            //outState.putSerializable("nameToCheckBoxMap", (Serializable) nameToCheckBoxMap);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        //nameToImViewMap = (Map<CharSequence, ImageView>) outState.get("nameToImViewMap");
        //nameToCheckBoxMap = (Map<CharSequence, CheckBox>) outState.get("nameToCheckBoxMap");

        Set<CharSequence> names = nameToCheckBoxMap.keySet();
        for (CharSequence name : names){
            Boolean checked =  outState.getBoolean(name + "_checked");
            System.out.println(name);
            CheckBox cB = nameToCheckBoxMap.get(name);
            ImageView imView = nameToImViewMap.get(name);
            nameToCheckedBoolMap.put(name, checked);
            update(cB, imView, checked);
        }
    }

    // Updates the visual elements, so the checkbox tick and the corresponding imageView of the
    // Mr. PotatoHead part.
    public void update(CheckBox cB, ImageView imV, Boolean checked){
        //System.out.println(cB.getId());
        cB.setChecked(checked);
        if (checked) {
            imV.setVisibility(View.VISIBLE);
        }
        else {
            imV.setVisibility(View.INVISIBLE);
        }
    }

    /*@protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        for (int i = 0; i < checkedList.length; i ++) {
            prefsEditor.putBoolean(""+i, checkedList[i]);
            System.out.println("Stored " + checkedList[i]);
        }
        prefsEditor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        for (int i = 0; i < checkedList.length; i ++) {
            checkedList[i] = prefs.getBoolean(""+i, false);
            System.out.println("Retrieved " + checkedList[i]);
        }
    }*/



    /*public void onCheckBoxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();


        String imageViewToBeChanged = "imView" + view.getContentDescription();


        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown" +  imageViewToBeChanged);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        View imViewToChange = findViewById(nameToImViewMap.get(view.getContentDescription()));

        if (checked) {
            imViewToChange.setVisibility(View.VISIBLE);
        }

        else {
            imViewToChange.setVisibility(View.INVISIBLE);
        }

    }*/
}
