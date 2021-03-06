package com.paq.proyecto.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.paq.proyecto.adapter.Seguridad;
import com.paq.proyecto.adapter.Validacion;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Usuario;

import java.util.List;

public class CreacionLibro extends AppCompatActivity {




    Button btCrearLibro;
    ImageButton ayuda;
    CheckBox checkBox;
    EditText edTienda, edDescripcion, edCodigoBarras, edPrecio, edEditorial, edAutor, edTitulo;
    EditText imagenLibro;

    Usuario usuario;

    //base dato
    DBHelper DB;
    Validacion validacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_libro);

        DB = new DBHelper(CreacionLibro.this);
        validacion = new Validacion(CreacionLibro.this);

        asociacion();

        navegar();


        terminar();
    }

    private void navegar() {
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertAyuda();
            }
        });

    }

    //terminar el proceso de creacion
    private void terminar() {
        btCrearLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creacion();
            }
        });
    }

    //creacion libro
    private void creacion() {

        usuario = MainActivity.usuarioObjeto;
        if(edTitulo.getText().toString().trim()!=null && edTitulo.getText().toString().trim().length()>=1 && !(edTitulo.getText().toString().trim().equals(""))){
            if(!DB.existeLibro(edTitulo.getText().toString().trim(),usuario.getIdUsuario())){
                //si lo tiene comprado o no, checbox
                Integer comprado=1;
                if(checkBox.isChecked()){
                    comprado=0;
                }

                Double precio=0.0;
                if(edPrecio.getText().toString().trim()==null || edPrecio.getText().toString().trim().equals("")){
                    precio=0.0;
                } else if(edPrecio.getText().toString().trim().contains(",")){ //asegurarnos que el valor introducido es correcto
                    precio = Double.parseDouble(edPrecio.getText().toString().trim().replace(",","."));
                } else {
                    precio = Double.parseDouble(edPrecio.getText().toString().trim());
                }

                String seguridadImagen = Seguridad.introduccionURL(imagenLibro.getText().toString().trim(),CreacionLibro.this);

                Libro libro = new Libro(
                        seguridadImagen,
                        edTitulo.getText().toString().trim(),
                        edAutor.getText().toString().trim(),
                        edCodigoBarras.getText().toString().trim(),
                        edEditorial.getText().toString().trim(),
                        precio,
                        edDescripcion.getText().toString().trim(),
                        comprado,
                        0.0,
                        edTienda.getText().toString().trim(),
                        usuario.getIdUsuario());
                DB.insertLibro(libro);
            }
        } else {
            Toast.makeText(this, "Es obligatorio poner un t??tulo al libro", Toast.LENGTH_SHORT).show();
        }


        Intent i = new Intent(CreacionLibro.this, NavDrawer.class); //clase nuestra,clase a la que viajar
        startActivity(i);
        finish();

    }

    //asociamos elementos con la clase
    private void asociacion() {
        btCrearLibro = (Button) findViewById(R.id.btCrearLibro);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        edTienda=(EditText) findViewById(R.id.edTienda);
        edDescripcion=(EditText) findViewById(R.id.edDescripcion);
        edCodigoBarras=(EditText) findViewById(R.id.edCodigoBarras);
        edPrecio=(EditText) findViewById(R.id.edPrecio);
        edEditorial=(EditText) findViewById(R.id.edEditorial);
        edAutor=(EditText) findViewById(R.id.edAutor);
        edTitulo=(EditText) findViewById(R.id.edTitulo);
        imagenLibro=(EditText) findViewById(R.id.imagenLibro);
        ayuda=(ImageButton) findViewById(R.id.ayuda);

    }

    //alert para informar las novedades
    private void alertAyuda() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CreacionLibro.this);
        dialogo.setTitle("Pasos para a??adir una imagen");

        String texto1="1. Buscamos la imagen que deseemos a??adir.  ";
        String texto2="2. Escogemos la imagen.  ";
        String texto3="3. Dejamos pulsado encima de la imagen para que nos salgan distintas opciones  ";
        String texto4="4. Pulsamos la opci??n: Abrir imagen en una nueva pesta??a.  ";
        String texto5="5. Abrimos la p??sta??a nueva y copiamos la url. ??sta es la que se debe pegar en nuestro campo url.  ";





        dialogo.setMessage(texto1 + "\n" + texto2 + "\n"+texto3 + "\n"+texto4 + "\n"+texto5 + "\n");


        dialogo.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();

            }
        });
        dialogo.show();


    }
}