package com.ananasco.itsaworkingtitle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.setValue("Hello, World!");
    }

    public void addToDB(View view) {
        EditText etMan = findViewById(R.id.man_et);
        EditText etModel = findViewById(R.id.model_et);
        EditText etPrice = findViewById(R.id.price_et);

        String manufacturer = etMan.getText().toString();
        String model = etModel.getText().toString();
        String price = etPrice.getText().toString();

        Car myCar = new Car(model, manufacturer, price);
        dbRef.child("car_park").setValue(myCar);
    }

    public void getFromDB(View view) {

        // Read from the database
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Car myCar = dataSnapshot.child("car_park").getValue(Car.class);

                ((TextView) findViewById(R.id.man_tv)).setText(myCar.manufacturer);
                ((TextView) findViewById(R.id.model_tv)).setText(myCar.model);
                ((TextView) findViewById(R.id.price_tv)).setText(myCar.price);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        dbRef.addValueEventListener(vel);
    }
}
