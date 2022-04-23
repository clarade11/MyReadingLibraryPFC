package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Usuario;

public class CambiarContrasena extends AppCompatActivity {

    Usuario usuario;
    DBHelper DB;

    EditText ps1;
    EditText ps2;
    Button btActualizar;
    Button btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);
        asociacion();

        DB = new DBHelper(CambiarContrasena.this);

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw1=ps1.getText().toString().trim();
                String psw2=ps2.getText().toString().trim();
                if(psw1.equals(psw2)) {
                    Intent i = new Intent(CambiarContrasena.this, Perfil.class); //clase nuestra,clase a la que viajar
                    startActivity(i);
                    usuario.setContrasena(psw1);
                    DB.actualizarUsuario(usuario);
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CambiarContrasena.this, Perfil.class); //clase nuestra,clase a la que viajar
                startActivity(i);

            }
        });



    }

    private void asociacion() {
        ps1 = (EditText) findViewById(R.id.ps1);
        ps2 = (EditText) findViewById(R.id.ps2);
        btActualizar = (Button) findViewById(R.id.btActualizar);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        usuario=MainActivity.usuarioObjeto;
    }
}