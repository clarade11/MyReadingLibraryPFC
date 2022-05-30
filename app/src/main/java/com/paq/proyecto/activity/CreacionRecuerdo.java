package com.paq.proyecto.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.paq.proyecto.adapter.Seguridad;
import com.paq.proyecto.adapter.Validacion;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Memories;
import com.paq.proyecto.clasesObjeto.Usuario;

import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class CreacionRecuerdo extends AppCompatActivity {
    Spinner spinnerLibro, spinnerTipo;
    ImageView imageview;
    EditText textRecuerdo;
    Button btCrear;

    Button pickColor;
    View vistacolor;
    Integer colordefecto=0;


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

                listener();

        textRecuerdo.setVisibility(View.VISIBLE);

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
                Integer colorFrase=null;
                Integer colorDescripcion=null;
                Integer colorImagen=null;
                Integer colorPositivo=null;
                Integer colorNegativo=null;


                //recogemos los datos de la pantalla

                if (tipoRecuerdo.equals("Frase")) {
                    frase = textRecuerdo.getText().toString().trim();
                    if(colordefecto!=0){
                        colorFrase=colordefecto;
                    } else{
                        colorFrase=-1;
                    }
                } else if (tipoRecuerdo.equals("Texto")) {
                    descripcion = textRecuerdo.getText().toString().trim();
                    if(colordefecto!=0){
                        colorDescripcion=colordefecto;
                    } else{
                        colorDescripcion=-1;
                    }
                } else if (tipoRecuerdo.equals("Url de imagen")) {

                    imagenBBDD = Seguridad.introduccionURL(textRecuerdo.getText().toString().trim(),CreacionRecuerdo.this);
                        Glide.with(CreacionRecuerdo.this)
                                .load(imagenBBDD)
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
                        if(colordefecto!=0){
                        colorImagen=colordefecto;
                    } else{
                            colorImagen=-1;
                        }

                } else if(tipoRecuerdo.equals("Comentario positivo")){
                    positivo=textRecuerdo.getText().toString().trim();
                    if(colordefecto!=0){
                        colorPositivo=colordefecto;
                    } else{
                        colorPositivo=-1;
                    }
                }else if(tipoRecuerdo.equals("Comentario negativo")){
                    negativo=textRecuerdo.getText().toString().trim();
                    if(colordefecto!=0){
                        colorNegativo=colordefecto;
                    }else{
                        colorNegativo=-1;
                    }
                }

                Integer idLibro = DB.getIDLibro(tituloLibro, usuario.getIdUsuario());


                //creamos recuerdo

                Memories memo =
                        new Memories(frase,String.valueOf(colorFrase),null,null, imagenBBDD,String.valueOf(colorImagen)
                                , descripcion,String.valueOf(colorDescripcion), positivo,String.valueOf(colorPositivo), negativo,String.valueOf(colorNegativo),null,
                                 usuario.getIdUsuario(),idLibro);
                DB.insertMemorie(memo);

                System.out.println("color elegido ->"+colordefecto);

                //mensaje correcto
                Toast.makeText(CreacionRecuerdo.this, "Recuerdo creado", Toast.LENGTH_SHORT).show();

                //cerramos pestaña
                Intent i = new Intent(CreacionRecuerdo.this, NavDrawer.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();

            }
        });

        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorpickerDialogo();

            }


        });
        }

    private void colorpickerDialogo() {
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this, colordefecto,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // leave this function body as
                        // blank, as the dialog
                        // automatically closes when
                        // clicked on cancel button
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        // change the mDefaultColor to
                        // change the GFG text color as
                        // it is returned when the OK
                        // button is clicked from the
                        // color picker dialog
                        colordefecto = color;

                        // now change the picked color
                        // preview box to mDefaultColor
                        vistacolor.setBackgroundColor(colordefecto);
                    }
                });
        colorPickerDialogue.show();
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

            if(listaLibros.get(i).getComprado()==1 && listaLibros.get(i).getTitulo()!=null){
                    z++;
            }

        }
        String[] array = new String[z];
        z=0;
        for (int i = 0; i < listaLibros.size(); i++) {
            if(listaLibros.get(i).getComprado()==1 && listaLibros.get(i).getTitulo()!=null){
                array[z] = listaLibros.get(i).getTitulo();
                z++;
            }

        }
        return array;

    }

    private void asociacion() {
        spinnerLibro = (Spinner) findViewById(R.id.spinnerLibro);
        spinnerTipo = (Spinner) findViewById(R.id.spinner2);

        textRecuerdo = (EditText) findViewById(R.id.textRecuerdo);
        btCrear = (Button) findViewById(R.id.buttonCrearRecuerdo);
        imageview= (ImageView) findViewById(R.id.imageViewRecuerdo);

        pickColor = (Button) findViewById(R.id.pickColor);
        vistacolor=(View) findViewById(R.id.preview_selected_color);
    }
}