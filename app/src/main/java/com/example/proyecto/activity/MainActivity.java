package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    //variables locales
    Button btEntrar;
    EditText edUsuario, edContrasena;
    TextView enlaceRegistrar, enlaceRecuperarContrasena;
    //Cursor
    private Cursor cursor;
    //base dato
    DBHelper DB;
    Validacion validacion;

    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(MainActivity.this);
        validacion = new Validacion(MainActivity.this);

        asociacion();

        /*Usuario admin = new Usuario();

        admin.setUsuario("admin");
        admin.setContrasena("admin");
        admin.setNombre("ola");
        admin.setApellidos("ola");
        admin.setTelefono("ola");

        DB.insertUsuario(admin);*/




        navegar();

    }


    //asociar el design con java
    private void asociacion() {
        btEntrar = (Button) findViewById(R.id.btEntrar);
        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edContrasena = (EditText) findViewById(R.id.edContrasena);
        enlaceRegistrar = (TextView) findViewById(R.id.enlaceRegistrar);
        enlaceRecuperarContrasena = (TextView) findViewById(R.id.enlaceRecuperarContrasena);
    }

    //NAVEGAR A APP

    public void navegar() {
        enlaceRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent i = new Intent(MainActivity.this, Registrar.class); //clase nuestra,clase a la que viajar
                startActivity(i);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, NavDrawer.class); //clase nuestra,clase a la que viajar
                startActivity(i);*/
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
            startActivity(i);

            vaciar();
        } else {
            //hay que poner aviso de error
            List<Usuario>usuarios = DB.getAllUsuarios();
            for(int i =0;i<usuarios.size();i++){
                System.out.println(usuarios.get(i).getUsuario());
;            }
            System.out.println("Usuario login:"+edUsuario.getText().toString().trim());
            System.out.println("contrasena login:"+edContrasena.getText().toString().trim());
            System.out.println("error btentrar");
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