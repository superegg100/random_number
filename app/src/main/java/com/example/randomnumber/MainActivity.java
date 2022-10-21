package com.example.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int max_easy = 10;
    int max_normal = 50;
    int max_hard = 100;
    TextView rand_num, id_str;
    Button plusten;
    Button plusone;
    Button minusten;
    Button minusone;
    Button guess;
    TextView horl;
    int counter = 0;
    int random_int;
    int fcounter = 0;
    SharedPreferences sp;
    Dialog d;
    EditText fname, lname;
    Button save;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_str = findViewById(R.id.id_str);
        sp = getSharedPreferences("id_details", 0);
        String Strfname = sp.getString("fname", null);
        String Strlname = sp.getString("lname", null);
        if (Strfname != null && Strlname != null){
            id_str.setText("Good Luck " + Strfname + " " + Strlname + " !");
        }
        fname = findViewById(R.id.etFname);
        lname = findViewById(R.id.etLname);
        plusone = findViewById(R.id.button);
        plusten = findViewById(R.id.button4);
        minusten = findViewById(R.id.button2);
        minusone = findViewById(R.id.button5);
        rand_num = (TextView) findViewById(R.id.rand_num);
        horl = (TextView) findViewById(R.id.textView);
        guess = findViewById(R.id.guess);

        plusone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter += 1;
                rand_num.setText(String.valueOf(counter));
            }
        });
        plusten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter += 10;
                rand_num.setText(String.valueOf(counter));
            }
        });
        minusone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter -= 1;
                rand_num.setText(String.valueOf(counter));
            }
        });
        minusten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter -= 10;
                rand_num.setText(String.valueOf(counter));
            }
        });
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(random_int == 0) {
                    horl.setText("First Pick A Difficulty !!!!!!");
                }
                if (counter > random_int  && random_int != 0){
                    fcounter++;
                    horl.setText("guess lower");
                }
                if (counter < random_int  && random_int != 0){
                    fcounter++;
                    horl.setText("guess higher");
                }
                if (counter==random_int && random_int != 0){
                    fcounter++;
                    Toast.makeText(MainActivity.this, "it took you " + fcounter + " tries", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Win.class);
                    startActivity(intent);
                    random_int = 0;
                    fcounter = 0;
                    horl.setText("Pick A Difficulty");
                    rand_num.setText("0");
                    counter = 0;
                }
            }
        });

    }

    public void Setname(String first, String last){
        id_str.setText("Good Luck " + first + " " + last + " !");
    }

    public void createLoginDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.activity_login);
        d.setTitle("Login");
        d.setCancelable(true);
        fname = (EditText) d.findViewById(R.id.etFname);
        lname = (EditText) d.findViewById(R.id.etLname);
        save = (Button) d.findViewById(R.id.btnSubmit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (save == view) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("fname", fname.getText().toString());
                    editor.putString("lname", lname.getText().toString());
                    editor.commit();
                    d.dismiss();
                    Setname(fname.getText().toString(), lname.getText().toString());
                }

            }
        });
        d.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id==R.id.action_login){
            horl.setText("Start Guessing !");
            Toast.makeText(this, "you selected easy", Toast.LENGTH_SHORT).show();
            random_int = (int)Math.floor(Math.random()*(max_easy-1+1)+1);

        }
        else if (R.id.action_register == id){
            horl.setText("Start Guessing !");
            Toast.makeText(this, "you selected normal ", Toast.LENGTH_SHORT).show();
            random_int = (int)Math.floor(Math.random()*(max_normal-1+1)+1);

        }
        else if (R.id.action_start == id){
            horl.setText("Start Guessing !");
            Toast.makeText(this, "you selected hard ", Toast.LENGTH_SHORT).show();
            random_int = (int)Math.floor(Math.random()*(max_hard-1+1)+1);
        }
        else if (R.id.action_sp == id){
            createLoginDialog();
        }
        return true;

    }



}