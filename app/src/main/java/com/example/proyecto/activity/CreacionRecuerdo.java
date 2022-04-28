package com.example.proyecto.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CreacionRecuerdo extends AppCompatActivity {
    Spinner spinnerLibro, spinnerTipo;
    ImageView imagen,imageView2;
    EditText textRecuerdo;
    Button btCrear;
    ImageButton lapiz;

    ArrayList uri;


    DBHelper DB;
    Validacion validacion;

    Usuario usuario = MainActivity.usuarioObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_recuerdo);

        DB = new DBHelper(CreacionRecuerdo.this);
        validacion = new Validacion(CreacionRecuerdo.this);

        asociacion();

        rellenarSpinners();

        lapiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener();
                if(spinnerTipo.getSelectedItem().equals("Imagen")){
                    imagen.setVisibility(View.VISIBLE);
                    textRecuerdo.setVisibility(View.GONE);
                    cargarImagen();
                } else {
                    textRecuerdo.setVisibility(View.VISIBLE);
                    imagen.setVisibility(View.GONE);
                }
            }
        });

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloLibro = spinnerLibro.getSelectedItem().toString();
                String tipoRecuerdo = spinnerTipo.getSelectedItem().toString();

                String frase = null;
                String descripcion =null;
                String imagenBBDD = null;
                String positivo = null;
                String negativo ="";


                if(tipoRecuerdo.equals("Frase")){
                    frase = textRecuerdo.getText().toString().trim();
                } else if(tipoRecuerdo.equals("Texto")){
                    descripcion = textRecuerdo.getText().toString().trim();
                } else if(tipoRecuerdo.equals("Imagen")){
                    //bitmap
                    //https://androidstudiofaqs.com/tutoriales/guardar-una-imagen-android-studio
                    if(imagen.getResources()!=null){
                        System.out.println(imagenBBDD);
                    }

                }
            }
        });
    }

    private void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione una"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }

    private void listener() {

        spinnerLibro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tituloSeleccionado = (String) spinnerLibro.getSelectedItem();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No seleccionaron nada
            }
        });
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tipo = (String) spinnerTipo.getSelectedItem();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No seleccionaron nada
            }
        });
    }

    private void rellenarSpinners() {
        //rellenamos spinner
        List<Libro> listaLibros = DB.getAllLibrosDeUsuario(usuario.getIdUsuario());

        String[] array = llenar(listaLibros);

        spinnerLibro.setAdapter(new ArrayAdapter<String>(CreacionRecuerdo.this,
                android.R.layout.simple_spinner_dropdown_item, array));

        String[] arrayTipo = {"Frase", "Texto","Imagen", "Comentario positivo", "Comentario negativo", "Imagen"};

        spinnerTipo.setAdapter(new ArrayAdapter<String>(CreacionRecuerdo.this,
                android.R.layout.simple_spinner_dropdown_item, arrayTipo));

    }

    private String[] llenar(List<Libro> listaLibros) {
        String[] array = new String[listaLibros.size()];

        for (int i = 0; i < listaLibros.size(); i++) {
            array[i] = (listaLibros.get(i).getTitulo());
        }

        return array;

    }


    private void asociacion() {
        spinnerLibro = (Spinner) findViewById(R.id.spinnerLibro);
        spinnerTipo = (Spinner) findViewById(R.id.spinner2);
        imagen = (ImageView) findViewById(R.id.imageButton2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        textRecuerdo = (EditText) findViewById(R.id.textRecuerdo);
        btCrear = (Button) findViewById(R.id.buttonCrearRecuerdo);
        lapiz = (ImageButton) findViewById(R.id.imageButtonLapiz);
    }
}