package com.example.mateusz.obozpwr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference kompania = database.getReference("kompania");
        DatabaseReference ksieciunie = database.getReference("ksieciunie");
        DatabaseReference ksiezniczki = database.getReference("ksiezniczki");
        DatabaseReference ogry = database.getReference("ogry");
        DatabaseReference pinokio = database.getReference("pinokio");
        DatabaseReference smosiolki = database.getReference("smosiolki");


        // buttons +
        Button button = (Button) findViewById(R.id.button);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button11 = (Button) findViewById(R.id.button11);

        // buttons -
        Button button2 = (Button) findViewById(R.id.button2);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button10 = (Button) findViewById(R.id.button10);
        Button button12 = (Button) findViewById(R.id.button12);


        // Read from the database
        kompania.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        // Read from the database
        ksieciunie.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView2 = (TextView) findViewById(R.id.textView2);
                textView2.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        // Read from the database
        ksiezniczki.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView3 = (TextView) findViewById(R.id.textView3);
                textView3.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        // Read from the database
        ogry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView4 = (TextView) findViewById(R.id.textView4);
                textView4.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        // Read from the database
        pinokio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView5 = (TextView) findViewById(R.id.textView5);
                textView5.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        // Read from the database
        smosiolki.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                TextView textView6 = (TextView) findViewById(R.id.textView6);
                textView6.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }





}
