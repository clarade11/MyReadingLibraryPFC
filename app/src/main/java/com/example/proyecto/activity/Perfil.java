package com.example.proyecto.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.clasesObjeto.Usuario;

public class Perfil extends AppCompatActivity {

    Usuario usuario;
    ImageView imagePerfil;
    TextView tvPerfilUsuario;
    TextView tvPerfilNombreApellido;
    TextView cambiarContrasena;
    TextView cambiarTelefono;
    TextView tvPerfilTelefono;

    Validacion validacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        asociacion();

        colocarDatos();

        navegar();

    }

    private void navegar() {
        cambiarTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //anuncio en el que poder escribir
            }
        });
        cambiarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, CambiarContrasena.class); //clase nuestra,clase a la que viajar
                startActivity(i);
            }
        });
    }

    //ponemos los datos en pantalla
    private void colocarDatos() {
        usuario = MainActivity.usuarioObjeto;
        imagePerfil.setImageResource(R.drawable.negro);
        tvPerfilUsuario.setText(usuario.getUsuario());
        tvPerfilNombreApellido.setText(usuario.getNombre()+" "+usuario.getApellidos());
        tvPerfilTelefono.setText(usuario.getTelefono());

    }

    //asociamos diseño con variables
    private void asociacion() {

        imagePerfil = (ImageView) findViewById(R.id.imagePerfil);
        tvPerfilUsuario = (TextView) findViewById(R.id.tvPerfilUsuario);
        tvPerfilNombreApellido = (TextView) findViewById(R.id.tvPerfilNombreApellido);
        cambiarContrasena = (TextView) findViewById(R.id.cambiarContrasena);
        cambiarTelefono = (TextView) findViewById(R.id.cambiarTelefono);
        tvPerfilTelefono = (TextView) findViewById(R.id.tvPerfilTelefono);

    }

}