package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.List;

public class CreacionLibro extends AppCompatActivity {

    Button btCrearLibro;
    CheckBox checkBox;
    EditText edTienda, edDescripcion, edCodigoBarras, edPrecio, edEditorial, edAutor, edTitulo;
    EditText imagenLibro;

    Usuario usuario;

    //Cursor
    private Cursor cursor;
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


        terminar();
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
        if(edTitulo.getText().toString().trim()!=null){
            if(!DB.existeLibro(edTitulo.getText().toString().trim(),usuario.getIdUsuario())){
                Integer comprado=1;
                if(checkBox.isChecked()){
                    comprado=0;
                }

                Libro libro = new Libro(
                        imagenLibro.getText().toString().trim(),
                        edTitulo.getText().toString().trim(),
                        edAutor.getText().toString().trim(),
                        edCodigoBarras.getText().toString().trim(),
                        edEditorial.getText().toString().trim(),
                        //Double.parseDouble(edPrecio.getText().toString().trim()),
                        1.5,
                        edDescripcion.getText().toString().trim(),
                        comprado,
                        0.0,
                        edTienda.getText().toString().trim(),
                        usuario.getIdUsuario());
                DB.insertLibro(libro);
            }
        }

        List<Libro> libros = DB.getAllLibros();
        for(int i =0;i<libros.size();i++){
            System.out.println(libros.get(i).getTitulo());
            ;            }

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
    }
}