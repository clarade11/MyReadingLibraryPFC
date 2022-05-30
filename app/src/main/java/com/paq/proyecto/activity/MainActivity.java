package com.paq.proyecto.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.paq.proyecto.adapter.Validacion;
import com.paq.proyecto.basedatos.DBHelper;

public class MainActivity extends AppCompatActivity {

    public static com.paq.proyecto.clasesObjeto.Usuario usuarioObjeto;

    //variables locales
    Button btEntrar;
    EditText edUsuario, edContrasena;
    TextView enlaceRegistrar, errorInicio;
    ImageView logo;
    //Cursor
    private Cursor cursor;
    //base dato
    DBHelper DB;
    Validacion validacion;


    //SHARED PREFERENCES
    public static  final String MyPREFERENCES = "MyPrefs";
    public static final String Usuario = "usuario";
    public static final String Contrasena = "contrasena";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DB = new DBHelper(MainActivity.this);
        validacion = new Validacion(MainActivity.this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);


        asociacion();

        Glide.with(MainActivity.this)
                .load(R.drawable.azul_transparente)
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


        //¿Hay ya información?
        String sharedUsuario =sharedpreferences.getString(Usuario, "");
        System.out.println(sharedUsuario);

        if(sharedUsuario!=null){
            String sharedContrasena =sharedpreferences.getString(Contrasena, "");
            System.out.println(sharedContrasena);
            edUsuario.setText(sharedUsuario);
            edContrasena.setText(sharedContrasena);
        }

        navegar();

    }


    //asociar el design con java
    private void asociacion() {
        btEntrar = (Button) findViewById(R.id.btEntrar);
        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edContrasena = (EditText) findViewById(R.id.edContrasena);
        enlaceRegistrar = (TextView) findViewById(R.id.enlaceRegistrar);
        logo=(ImageView) findViewById(R.id.imageViewInicio) ;

        errorInicio = (TextView) findViewById(R.id.errorInicio);
    }

    //NAVEGAR A APP

    public void navegar() {
        enlaceRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent i = new Intent(MainActivity.this, Registrar.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar();
            }
        });
    }

    /**
     * Verificamos que los campos son correctos
     */
    private void verificar() {
        if (!validacion.isEditTextUsuario(edUsuario,  "ERROR")) {
            return ;
        }
        if (!validacion.isEditText(edContrasena,  "ERROR")) {
            return;
        }
        if (DB.existeUsuarioContrasena(edUsuario.getText().toString().trim()
                , edContrasena.getText().toString().trim())) {

            Intent i = new Intent(MainActivity.this, NavDrawer.class);

            //crea objeto usuario para luego utilizarlo en otras clases
            usuarioObjeto= DB.getUsuario(edUsuario.getText().toString().trim());

            //sharedPreferences para tener iniciado sesion siempre desde el mismo dispositivo
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Usuario,edUsuario.getText().toString().trim());
            editor.putString(Contrasena,edContrasena.getText().toString().trim());
            editor.commit();


            //mensaje para aclarar
            Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();

            startActivity(i);
            finish();

            vaciar();
        } else {
            errorInicio.setVisibility(View.VISIBLE);
        }
    }
    /**
     * Metodo para vaciar los campos de texto
     */
    private void vaciar() {
        edUsuario.setText(null);
        edContrasena.setText(null);
    }


}