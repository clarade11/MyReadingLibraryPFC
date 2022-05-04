package com.example.proyecto.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Usuario;

public class Perfil extends AppCompatActivity {

    DBHelper DB;
    Usuario usuario;
    ImageView imagePerfil;
    TextView tvPerfilUsuario;
    TextView tvPerfilNombreApellido;
    TextView cambiarContrasena;
    TextView cambiarTelefono;
    TextView cerrarSesion;
    TextView tvPerfilTelefono;

    String telefono;
    String ps1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        DB = new DBHelper(Perfil.this);
        asociacion();

        colocarDatos();

        navegar();

    }

    private void navegar() {
        cambiarTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCambioTelefono();
            }
        });
        cambiarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertCambioContrasena();
            }
        });
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, MainActivity.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();
            }
        });
    }

    //alert para cambiar el telefono de perfil

    private void alertCambioTelefono() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Cambio de teléfono");

        final EditText telefonoNuevo = new EditText(Perfil.this);
        telefonoNuevo.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogo.setView(telefonoNuevo);

        //botones del alert
        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                telefono = telefonoNuevo.getText().toString().trim();
                usuario.setTelefono(telefono);
                DB.actualizarUsuario(usuario);
            }
        });

        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogo.show();


    }


    //ponemos los datos en pantalla
    private void colocarDatos() {
        usuario = MainActivity.usuarioObjeto;
        imagePerfil.setImageResource(R.drawable.negro);
        tvPerfilUsuario.setText(usuario.getUsuario());
        tvPerfilNombreApellido.setText(usuario.getNombre() + " " + usuario.getApellidos());
        tvPerfilTelefono.setText(usuario.getTelefono());

    }

    //asociamos diseño con variables
    private void asociacion() {

        imagePerfil = (ImageView) findViewById(R.id.imagePerfil);
        tvPerfilUsuario = (TextView) findViewById(R.id.tvPerfilUsuario);
        tvPerfilNombreApellido = (TextView) findViewById(R.id.tvPerfilNombreApellido);
        cambiarContrasena = (TextView) findViewById(R.id.cambiarContrasena);
        cambiarTelefono = (TextView) findViewById(R.id.cambiarTelefono);
        cerrarSesion = (TextView) findViewById(R.id.cerrarSesion);
        tvPerfilTelefono = (TextView) findViewById(R.id.tvPerfilTelefono);
    }

    //alert del cambio de contraseña del perfil
    private void alertCambioContrasena() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Cambio de contraseña");

        final EditText password1 = new EditText(Perfil.this);
        password1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        dialogo.setView(password1);

        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                ps1 = password1.getText().toString().trim();

                usuario.setContrasena(ps1);
                DB.actualizarUsuario(usuario);

            }
        });

        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogo.show();


    }

}