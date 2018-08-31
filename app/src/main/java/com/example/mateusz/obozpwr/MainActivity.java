package com.example.mateusz.obozpwr;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calendar for date and time

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        //database reference
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference kompania = database.getReference("kompania");
        final DatabaseReference ksieciunie = database.getReference("ksieciunie");
        final DatabaseReference ksiezniczki = database.getReference("ksiezniczki");
        final DatabaseReference ogry = database.getReference("ogry");
        final DatabaseReference pinokio = database.getReference("pinokio");
        final DatabaseReference smosiolki = database.getReference("smosiolki");
        final DatabaseReference historia = database.getReference("historia");

        //textViews
        final TextView textView = (TextView) findViewById(R.id.textView);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        final TextView textView5 = (TextView) findViewById(R.id.textView5);
        final TextView textView6 = (TextView) findViewById(R.id.textView6);

        //editText
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);


        //buttons +
        Button button = (Button)  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                if(isEmpty(editText) == false) {
                    //value from editText
                    String val = editText.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView.getText().toString();
                    Long val_long2 = Long.parseLong(val2);
                    Calendar c = Calendar.getInstance();
                    History item = new History(sdf.format(c.getTime()),"Opis",val_long);
                    historia.child("kompania").push().setValue(item);
                    kompania.setValue(val_long + val_long2);
                }
            }
        });
       final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(isEmpty(editText2) == false) {
                        //value from editText
                        String val = editText2.getText().toString();
                        Long val_long = Long.parseLong(val);

                        //value from txtView
                        String val2 = textView2.getText().toString();
                        Long val_long2 = Long.parseLong(val2);

                        ksieciunie.setValue(val_long + val_long2);
                    }
                }


        });
       final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText3) == false) {
                    //value from editText
                    String val = editText3.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView3.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    ksiezniczki.setValue(val_long + val_long2);
                }
            }
        });
       final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText4) == false) {
                    //value from editText
                    String val = editText4.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView4.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    ogry.setValue(val_long + val_long2);
                }
            }
        });
       final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText5) == false) {
                    //value from editText
                    String val = editText5.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView5.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    pinokio.setValue(val_long + val_long2);
                }
            }
        });
       final Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText6) == false) {
                    //value from editText
                    String val = editText6.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView6.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    smosiolki.setValue(val_long + val_long2);
                }
            }
        });



        // BUTTONS -
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText) == false) {
                    //value from editText
                    String val = editText.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView.getText().toString();
                    Long val_long2 = Long.parseLong(val2);
                    Calendar c = Calendar.getInstance();
                    History item = new History(sdf.format(c.getTime()),"Opis",0-val_long);
                    historia.child("kompania").push().setValue(item);
                    kompania.setValue(val_long2 - val_long);
                }
            }
        });
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText2) == false) {
                    //value from editText
                    String val = editText2.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView2.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    ksieciunie.setValue(val_long2 - val_long);
                }

            }
        });
        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText3) == false) {
                    //value from editText
                    String val = editText3.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView3.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    ksiezniczki.setValue(val_long2 - val_long);
                }

            }
        });
        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText4) == false) {
                    //value from editText
                    String val = editText4.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView4.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    ogry.setValue(val_long2 - val_long);
                }

            }
        });
        final Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText5) == false) {
                    //value from editText
                    String val = editText5.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView5.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    pinokio.setValue(val_long2 - val_long);
                }

            }
        });
        final Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText6) == false) {
                    //value from editText
                    String val = editText6.getText().toString();
                    Long val_long = Long.parseLong(val);

                    //value from txtView
                    String val2 = textView6.getText().toString();
                    Long val_long2 = Long.parseLong(val2);

                    smosiolki.setValue(val_long2 - val_long);
                }

            }
        });




        ////VALUE LISTENERS////

        // Read from the database
        kompania.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);

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
                textView6.setText(Long.toString(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }


}
