package com.paq.proyecto.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Memories;
import com.paq.proyecto.clasesObjeto.Usuario;

import java.util.List;

public class Perfil extends AppCompatActivity {

    DBHelper DB;
    Usuario usuario;
    //ImageView imagePerfil;
    TextView tvPerfilUsuario;
    TextView tvPerfilNombre;
    TextView tvPerfilApellido;
    TextView cambiarContrasena;
    TextView cambiarTelefono;
    TextView cerrarSesion;
    TextView tvPerfilTelefono;
    TextView nlibros;
    TextView nrecuerdos;
    TextView novedades;

    String telefono;
    String ps1;
    String nombre;
    String apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        DB = new DBHelper(Perfil.this);
        asociacion();

        //colocar datos en los textview
        colocarDatos();

        //navegar en pantallas
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
        tvPerfilApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCambioApellido();
            }
        });
        tvPerfilNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCambioNombre();
            }
        });
        novedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertNovedades();
            }
        });
    }


    //alerta para cambiar el nombre
    private void alertCambioNombre() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Cambio de nombre");

        final EditText nombreNuevo = new EditText(Perfil.this);
        nombreNuevo.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogo.setView(nombreNuevo);

        //botones del alert
        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                nombre = nombreNuevo.getText().toString().trim();
                usuario.setNombre(nombre);
                DB.actualizarUsuario(usuario);
                tvPerfilNombre.setText(nombre);
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

    //alerta para cambiar el nombre
    private void alertCambioApellido() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Cambio de apellido");

        final EditText apellidoNuevo = new EditText(Perfil.this);
        apellidoNuevo.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogo.setView(apellidoNuevo);

        //botones del alert
        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                apellido = apellidoNuevo.getText().toString().trim();
                usuario.setApellidos(apellido);
                DB.actualizarUsuario(usuario);
                tvPerfilApellido.setText(apellido);
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
        //imagePerfil.setImageResource(R.drawable.negro);
        tvPerfilUsuario.setText(usuario.getUsuario());
        tvPerfilNombre.setText(usuario.getNombre());
        tvPerfilApellido.setText(usuario.getApellidos());
        tvPerfilTelefono.setText(usuario.getTelefono());

        contarlibrosyrecuerdos();

    }

    //contar libros y recuerdos del usuario en la bbdd
    private void contarlibrosyrecuerdos() {

        List<Libro> libros = DB.getAllLibrosDeUsuario(usuario.getIdUsuario());
        Integer numeroLibros = libros.size();

        List<Memories> recuerdos=DB.getAllMemoriesDeUsuario(usuario.getIdUsuario());
        Integer numeroRecuerdos = recuerdos.size();

        nlibros.setText(String.valueOf(numeroLibros));
        nrecuerdos.setText(String.valueOf(numeroRecuerdos));


    }

    //asociamos diseño con variables
    private void asociacion() {

        //imagePerfil = (ImageView) findViewById(R.id.imagePerfil);
        tvPerfilUsuario = (TextView) findViewById(R.id.tvPerfilUsuario);
        tvPerfilNombre = (TextView) findViewById(R.id.tvPerfilNombre);
        tvPerfilApellido = (TextView) findViewById(R.id.tvPerfilApellido);
        cambiarContrasena = (TextView) findViewById(R.id.cambiarContrasena);
        cambiarTelefono = (TextView) findViewById(R.id.cambiarTelefono);
        cerrarSesion = (TextView) findViewById(R.id.cerrarSesion);
        tvPerfilTelefono = (TextView) findViewById(R.id.tvPerfilTelefono);
        nrecuerdos = (TextView) findViewById(R.id.nrecuerdos);
        novedades = (TextView) findViewById(R.id.novedades);
        nlibros = (TextView) findViewById(R.id.nlibros);
    }

    //alert para informar las novedades
    private void alertNovedades() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Próximas novedades");

        String textoNovedad="My Reading Diary tiene el placer de informar que ¡Hay más contenido en camino!.";
        String t1=
                "En las próximas actualizaciones tendremos disponible que puedas seguir a otros usuarios, ver sus recuerdos, " +
                "podrás ponerte una foto de perfil, podrás subir tus propias fotos como recuerdo o como post ;), y mucho más. " +
                "¡My Reading Diary no va a parar de crecer!";



        dialogo.setMessage(textoNovedad+"\n"+t1);

        dialogo.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();

            }
        });
        dialogo.show();


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
                if(ps1.length()>=10) {
                    usuario.setContrasena(ps1);
                    DB.actualizarUsuario(usuario);
                } else {
                    Toast.makeText(Perfil.this, "Error", Toast.LENGTH_SHORT).show();
                }
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