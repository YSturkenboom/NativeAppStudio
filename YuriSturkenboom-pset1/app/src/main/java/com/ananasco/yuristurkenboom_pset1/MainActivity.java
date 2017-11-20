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

// In this Activity the CheckBox objects' ContentDescription is used to give the CheckBoxes a sort
// of id. This is probably a crime, but for the time it was the best I could do, and I didn't want
// to hardcode absolutely everything. (future lesson on custom attribs?)
public class MainActivity extends AppCompatActivity {

    // These three maps are used to establish a relation between the data representation, the
    // CheckBoxes themselves and the associated ImageViews. I was hoping to avoid repeated code.
    // In the end this was probably more trouble than it's worth, and caused me a lot of hassle,
    // because I didn't have the experience with Java required to debug my own code...
    Map<CharSequence, ImageView> nameToImViewMap = new HashMap<CharSequence, ImageView>();
    Map<CharSequence, Boolean> nameToCheckedBoolMap = new HashMap<CharSequence, Boolean>();
    Map<CharSequence, CheckBox> nameToCheckBoxMap = new HashMap<CharSequence, CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // It seems intuitive that nameToImViewMap and nameToCheckBoxMap should be static since
        // they shouldn't change, but I haven't yet figured out how to properly do that, and the
        // assignment was already taking too long.
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
        Set<CharSequence> names = nameToCheckedBoolMap.keySet();

        // iterate over all checkbox names
        for (CharSequence name : names) {
            outState.putBoolean(name + "_checked", nameToCheckedBoolMap.get(name));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        Set<CharSequence> names = nameToCheckBoxMap.keySet();

        // iterate over all checkbox names
        for (CharSequence name : names){
            Boolean checked =  outState.getBoolean(name + "_checked");
            CheckBox cB = nameToCheckBoxMap.get(name);
            ImageView imView = nameToImViewMap.get(name);
            nameToCheckedBoolMap.put(name, checked);
            update(cB, imView, checked);
        }
    }

    // Updates the visual elements - the checkbox tick and the corresponding imageView of the
    // Mr. PotatoHead part.
    public void update(CheckBox cB, ImageView imV, Boolean checked){
        cB.setChecked(checked);
        if (checked) {
            imV.setVisibility(View.VISIBLE);
        }
        else {
            imV.setVisibility(View.INVISIBLE);
        }
    }
}
