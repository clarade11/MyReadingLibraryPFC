package com.example.proyecto.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class Registrar extends AppCompatActivity {
    //variables locales
    Button btRegistrar;
    EditText edRegistrarUsuario, edRegistrarContrasena, edRegistrarContrasenaRepetida, edRegistrarTelefono;
    ImageView logo;
    DBHelper DB;
    Validacion validacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        DB = new DBHelper(Registrar.this);
        validacion = new Validacion(Registrar.this);
        asociacion();
        navegar();
    }

    private void asociacion() {
        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        edRegistrarUsuario = (EditText) findViewById(R.id.edRegistrarUsuario);
        edRegistrarContrasena = (EditText) findViewById(R.id.edRegistrarContrasena);
        edRegistrarContrasenaRepetida = (EditText) findViewById(R.id.edRegistrarContrasenaRepetida);
        edRegistrarTelefono = (EditText) findViewById(R.id.edRegistrarTelefono);
        logo = (ImageView) findViewById(R.id.imageViewRegistrar);



        Glide.with(Registrar.this)
                .load(R.drawable.blanco_transparente)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                })
                .into(logo);
    }

    /**
     * Verificamos que los campos son correctos
     */
    public void registrar() {
        if (!validacion.isEditTextUsuario(edRegistrarUsuario,  "ERROR")) {
            return ;
        }
        if (!validacion.isEditText(edRegistrarTelefono,  "ERROR")) {
            return;
        }
        if (!validacion.isEditTextContrasena(edRegistrarContrasena,edRegistrarContrasenaRepetida,  "ERROR")) {
            return ;
        }
        if (!DB.existeUsuario(edRegistrarUsuario.getText().toString().trim())) {
            Usuario usuario = new Usuario();
            usuario.setUsuario(edRegistrarUsuario.getText().toString().trim());
            usuario.setContrasena(edRegistrarContrasena.getText().toString().trim());
            usuario.setTelefono(edRegistrarTelefono.getText().toString().trim());;
            usuario.setNombre("Edite perfil para añadir el nombre");
            usuario.setApellidos("Edite perfil para añadir el apellido");

            DB.insertUsuario(usuario);

            vaciar();
        } else {
            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();

        }
    }

    private void navegar() {
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
                //volvemos
                Intent intent = new Intent(Registrar.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * Metodo para vaciar los campos de texto
     */
    private void vaciar() {
        edRegistrarTelefono.setText(null);
        edRegistrarContrasena.setText(null);
        edRegistrarContrasenaRepetida.setText(null);
        edRegistrarUsuario.setText(null);
    }

}