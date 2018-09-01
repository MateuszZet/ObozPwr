package com.example.mateusz.obozpwr;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //username textView
        TextView txtUsername = (TextView)findViewById(R.id.username);
        txtUsername.setText(user.getEmail());

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
                else{

                    Log.i("AuthStateChanged", "User is signed in with uid: " + user.getUid() + "User is signed in with Email: " + user.getEmail() );
                }
            }
        };

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

        //content of buttons listeners should be in some kind of function bacause is quite redundant

        //############################ BUTTONS + #######################################

        final Button button = (Button)  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                if(isEmpty(editText) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                            historia.child("kompania").push().setValue(item);
                            kompania.setValue(val_long2 + val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

       final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(isEmpty(editText2) == false) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                        View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                        mBuilder.setView(mView);
                        final AlertDialog dialog = mBuilder.create();
                        dialog.show();

                        final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                        Button button = (Button)  mView.findViewById(R.id.btnOk);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //value from editText
                                String val = editText2.getText().toString();
                                Long val_long = Long.parseLong(val);

                                //value from txtView
                                String val2 = textView2.getText().toString();
                                Long val_long2 = Long.parseLong(val2);

                                Calendar c = Calendar.getInstance();
                                History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                                historia.child("ksieciunie").push().setValue(item);
                                ksieciunie.setValue(val_long2 + val_long);
                                dialog.dismiss();
                            }
                        });
                        Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                }


        });

       final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText3) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText3.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView3.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                            historia.child("ksiezniczki").push().setValue(item);
                            ksiezniczki.setValue(val_long2 + val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

       final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText4) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText4.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView4.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                            historia.child("ogry").push().setValue(item);
                            ogry.setValue(val_long2 + val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

       final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText5) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText5.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView5.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                            historia.child("pinokio").push().setValue(item);
                            pinokio.setValue(val_long2 + val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

       final Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText6) == false) {

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText6.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView6.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),val_long);
                            historia.child("smosiolki").push().setValue(item);
                            smosiolki.setValue(val_long2 + val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                }
            }
        });

        //######################  BUTTONS -  ##############################

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText) == false) {

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("kompania").push().setValue(item);
                            kompania.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }
            }
        });

        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText2) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText2.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView2.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("ksieciunie").push().setValue(item);
                            ksieciunie.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });

        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText3) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText3.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView3.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("ksiezniczki").push().setValue(item);
                            ksiezniczki.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });

        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText4) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText4.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView4.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("ogry").push().setValue(item);
                            ogry.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });
        final Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText5) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText5.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView5.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("pinokio").push().setValue(item);
                            pinokio.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });
        final Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editText6) == false) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_desc,null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    final EditText opis = (EditText) mView.findViewById(R.id.editTxtOpis);

                    Button button = (Button)  mView.findViewById(R.id.btnOk);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //value from editText
                            String val = editText6.getText().toString();
                            Long val_long = Long.parseLong(val);

                            //value from txtView
                            String val2 = textView6.getText().toString();
                            Long val_long2 = Long.parseLong(val2);

                            Calendar c = Calendar.getInstance();
                            History item = new History(sdf.format(c.getTime()),opis.getText().toString(),0-val_long);
                            historia.child("smosiolki").push().setValue(item);
                            smosiolki.setValue(val_long2 - val_long);
                            dialog.dismiss();
                        }
                    });
                    Button button2 = (Button)  mView.findViewById(R.id.btnAnuluj);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });

        //button WYLOGUJ
        final Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signOut();
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

    //sign out method
    public void signOut() {
        auth.signOut();
        Toast.makeText(MainActivity.this, "WYLOGOWANIE", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}



