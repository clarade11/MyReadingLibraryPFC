package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navegar();
    }

    //NAVEGAR A APP
    public void navegar(){
        btEntrar = (Button) findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finish();
                Intent i  = new Intent( MainActivity.this, TabActivity.class); //clase nuestra,clase a la que viajar
                startActivity(i);
            }
        });
    }
}