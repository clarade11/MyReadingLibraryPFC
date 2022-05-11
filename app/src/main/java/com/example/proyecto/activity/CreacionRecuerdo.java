package com.example.proyecto.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.example.proyecto.adapter.Validacion;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;


import java.util.List;

public class CreacionRecuerdo extends AppCompatActivity {
    Spinner spinnerLibro, spinnerTipo;
    ImageView imageview;
    EditText textRecuerdo;
    Button btCrear;
    ImageButton lapiz;

    List<String> librosSpinner;

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

        navegar();
    }

    private void navegar() {
        lapiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener();
//                if(spinnerTipo.getSelectedItem().equals("Imagen")){
                   // imagen.setVisibility(View.VISIBLE);
                   // textRecuerdo.setVisibility(View.GONE);
                    //cargarImagen();
//                } else {
                    textRecuerdo.setVisibility(View.VISIBLE);
                   // imagen.setVisibility(View.GONE);
                //}
            }
        });

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloLibro = spinnerLibro.getSelectedItem().toString();
                String tipoRecuerdo = spinnerTipo.getSelectedItem().toString();

                String frase = null;
                String descripcion = null;
                String imagenBBDD = null;
                String positivo = null;
                String negativo = null;

                //recogemos los datos de la pantalla

                if (tipoRecuerdo.equals("Frase")) {
                    frase = textRecuerdo.getText().toString().trim();
                } else if (tipoRecuerdo.equals("Texto")) {
                    descripcion = textRecuerdo.getText().toString().trim();
                } else if (tipoRecuerdo.equals("Url de imagen")) {
                    //bitmap
                    //https://androidstudiofaqs.com/tutoriales/guardar-una-imagen-android-studio
//                    if (imagen.getResources() != null) {
                        //System.out.println(imagenBBDD);
//                        imagen.buildDrawingCache();
//                        Bitmap bitmap = imagen.getDrawingCache(); //bitmap de la imagen
//
//
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
//                        byte[] blob = baos.toByteArray();
//                        imagenBBDD = new String(blob, java.nio.charset.StandardCharsets.UTF_8);

                        Glide.with(CreacionRecuerdo.this)
                                .load(textRecuerdo.getText().toString().trim())
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
                                .into(imageview);
                        imagenBBDD=textRecuerdo.getText().toString().trim();
//                    }

                } else if(tipoRecuerdo.equals("Comentario positivo")){
                    positivo=textRecuerdo.getText().toString().trim();
                }else if(tipoRecuerdo.equals("Comentario negativo")){
                    negativo=textRecuerdo.getText().toString().trim();
                }

                //Integer idLibro = DB.getIDLibro(tituloLibro, usuario.getIdUsuario());

                //cogemos el id del libro
                Integer idLibro = 100000;
                List<Libro> libros = DB.getAllLibrosDeUsuario(usuario.getIdUsuario());
                for (int i = 0; i < libros.size(); i++) {
                    System.out.println(tituloLibro);
                    if (libros.get(i).getTitulo().trim().equals(tituloLibro.trim()) ) {
                        idLibro = libros.get(i).getIdLibro();
                        System.out.println("ID LIBRO DEL RECUERDO CREADO------>"+idLibro);
                    }
                }

                //creamos recuerdo
                System.out.println("ID LIBRO DEL RECUERDO CREADO IBSERTADO EN BBDD------>"+idLibro);
                Memories memo =
                        new Memories(frase,null,null,null, imagenBBDD,null
                                , descripcion,null, positivo,null, negativo,null,null,
                                 usuario.getIdUsuario(),idLibro);
                DB.insertMemorie(memo);

                //mensaje correcto
                Toast.makeText(CreacionRecuerdo.this, "Recuerdo creado", Toast.LENGTH_SHORT).show();

                //cerramos pestaña
                Intent i = new Intent(CreacionRecuerdo.this, NavDrawer.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();

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

        String[] arrayTipo = {"Frase", "Texto", "Comentario positivo", "Comentario negativo", "Url de imagen"};

        spinnerTipo.setAdapter(new ArrayAdapter<String>(CreacionRecuerdo.this,
                android.R.layout.simple_spinner_dropdown_item, arrayTipo));

    }

    private String[] llenar(List<Libro> listaLibros) {

        int z=0;

        for (int i = 0; i < listaLibros.size(); i++) {
            //System.out.println(listaLibros.get(i).getTitulo());
            if(listaLibros.get(i).getComprado()==1 && listaLibros.get(i).getTitulo()!=null){

                    z++;
            }

        }
        String[] array = new String[z];
        z=0;
        for (int i = 0; i < listaLibros.size(); i++) {
            //System.out.println(listaLibros.get(i).getTitulo());
            if(listaLibros.get(i).getComprado()==1 && listaLibros.get(i).getTitulo()!=null){
                System.out.println(listaLibros.get(i).getTitulo());

                array[z] = listaLibros.get(i).getTitulo();
                z++;


            }

        }
        return array;

    }


    private void asociacion() {
        spinnerLibro = (Spinner) findViewById(R.id.spinnerLibro);
        spinnerTipo = (Spinner) findViewById(R.id.spinner2);

        //imageView2 = (ImageView) findViewById(R.id.imageView2);
        textRecuerdo = (EditText) findViewById(R.id.textRecuerdo);
        btCrear = (Button) findViewById(R.id.buttonCrearRecuerdo);
        lapiz = (ImageButton) findViewById(R.id.imageButtonLapiz);
        imageview= (ImageView) findViewById(R.id.imageViewRecuerdo);
    }
}