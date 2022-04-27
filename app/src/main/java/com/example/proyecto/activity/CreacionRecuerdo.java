package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton imagen;
    EditText textRecuerdo;
    Button btCrear;
    ImageButton lapiz;

    List<String> listaTitulos;

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
                } else {
                    textRecuerdo.setVisibility(View.VISIBLE);
                    imagen.setVisibility(View.GONE);
                }
            }
        });





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
        imagen = (ImageButton) findViewById(R.id.imageButton2);
        textRecuerdo = (EditText) findViewById(R.id.textRecuerdo);
        btCrear = (Button) findViewById(R.id.buttonCrearRecuerdo);
        lapiz = (ImageButton) findViewById(R.id.imageButtonLapiz);
    }
}