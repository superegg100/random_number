package com.example.randomnumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Win extends AppCompatActivity {
    Button RESTART, EXIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Intent intent = getIntent();
        RESTART = findViewById(R.id.restart);
        EXIT = findViewById(R.id.exit);
        EXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(Win.this);
                alert.setTitle("EXIT");
                alert.setMessage("ARE YOU SURE YOU WANT TO EXIT?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        System.exit(0);
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Win.this,"I want to stay",Toast.LENGTH_LONG).show();
                    }
                });
                alert.create().show();
            }
        });
        RESTART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert=new AlertDialog.Builder(Win.this);
                alert.setTitle("RESTART");
                alert.setMessage("ARE YOU SURE YOU WANT TO RESTART?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();

                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alert.create().show();
            }
        });
    }
}
