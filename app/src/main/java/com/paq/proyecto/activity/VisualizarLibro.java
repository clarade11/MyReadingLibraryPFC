package com.paq.proyecto.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.paq.proyecto.adapter.LibroLibraryAdapter;
import com.paq.proyecto.adapter.LibroLibraryGeneralAdapter;
import com.paq.proyecto.adapter.Seguridad;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Memories;
import com.paq.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class VisualizarLibro extends AppCompatActivity {

    ImageView imageVisualizarLibro;
    TextView tvTituloLibro;
    EditText tvSipnopsis,tvISBN,tvPrecio, tvPaginasLeidas;
    //RecyclerView recyclerVisualizarLibroID;
    RatingBar ratingBar;
    ImageButton btGuardarCambios;
    DBHelper DB;
    Usuario usuario;
    ArrayList<Memories> listaMemories;
    Libro libro;

    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_libro);

        asociacion();

        escuchaPulsaciones();

        vista();

    }


    //llenar la vista con los recuerdos
    private void vista() {

        listaMemories = new ArrayList<Memories>();
        //recyclerVisualizarLibroID.setLayoutManager(new LinearLayoutManager(this));

        llenarCampos();
        llenarLista();

        LibroLibraryGeneralAdapter adapter = new LibroLibraryGeneralAdapter(listaMemories,VisualizarLibro.this);//llenamos el adapter con la lista llena
        //recyclerVisualizarLibroID.setAdapter(adapter); //metemos el adaptador que acabamos de llenar


    }

    //llenamos los campos del libro
    private void llenarCampos() {

        //imageVisualizarLibro
        tvTituloLibro.setText(libro.getTitulo());
        if(libro.getDescripcion()!=null) {
            tvSipnopsis.setText(libro.getDescripcion());
        } else {
            tvSipnopsis.setText("Sipnopsis no indicada");
        }
        if(libro.getCodigoBarras()!=null){
            tvISBN.setText(libro.getCodigoBarras());
        } else {
            tvISBN.setText("Codigo de barras no indicado");
        }

        if(String.valueOf(libro.getPrecio())!=null){
            tvPrecio.setText(Double.toString(libro.getPrecio()));
        } else {
            tvPrecio.setText("Precio no indicado");
        }

        if(String.valueOf(libro.getNumPaginas())!=null){
            tvPaginasLeidas.setText(libro.getNumPaginas());
        }else{
        tvPaginasLeidas.setText("No comenzado");}


        if(libro.getPuntuacion()!=0.0){
            double puntuacion = (double)libro.getPuntuacion();
            ratingBar.setRating((float) puntuacion );
        } else {
            ratingBar.setRating(0);
        }
        if(libro.getFotoID()!=null){
            Glide.with(VisualizarLibro.this)
                    .load(libro.getFotoID())
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
                    .into(imageVisualizarLibro);
        }



    }

    //llenamos la lista del recyclerview
    private void llenarLista() {
        //cogemos id del libro
        Integer id = libro.getIdLibro();
        //cogemos memories del libro
        List<Memories> lista = DB.getAllMemoriesDeUsuarioLibro(usuario.getIdUsuario(), id);
        //relleno el arraylist con esa lista
        listaMemories = (ArrayList<Memories>) lista;
    }

    //si se realizan cambios en el libro
    private void escuchaPulsaciones() {
        imageVisualizarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(VisualizarLibro.this);
                dialogo.setTitle("Cambiar portada de libro");

                final EditText fotoNueva = new EditText(VisualizarLibro.this);
                fotoNueva.setInputType(InputType.TYPE_CLASS_TEXT);
                dialogo.setView(fotoNueva);

                //botones del alert
                dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        foto = Seguridad.introduccionURL(fotoNueva.getText().toString().trim(),VisualizarLibro.this) ;

                        libro.setFotoID(foto);
                        DB.actualizarLibro(libro);
                        if(!libro.getFotoID().equals("")){
                            Glide.with(VisualizarLibro.this)
                                    .load(libro.getFotoID())
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
                                    .into(imageVisualizarLibro);
                        } else{
                            imageVisualizarLibro.setVisibility(View.INVISIBLE);
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
        });


        btGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Libro libroModificado = new Libro();

                libroModificado.setComprado(libro.getComprado());
                libroModificado.setIdUsuario(usuario.getIdUsuario());
                libroModificado.setTienda(libro.getTienda());
                libroModificado.setPuntuacion(Double.parseDouble(String.valueOf(ratingBar.getRating())));
                libroModificado.setDescripcion(tvSipnopsis.getText().toString().trim());
                libroModificado.setEditorial(libro.getEditorial());
                libroModificado.setPrecio(Double.valueOf(tvPrecio.getText().toString().trim()));
                libroModificado.setIdLibro(libro.getIdLibro());
                libroModificado.setCodigoBarras(tvISBN.getText().toString().trim());
                libroModificado.setFotoID(libro.getFotoID());
                libroModificado.setTitulo(tvTituloLibro.getText().toString().trim());
                libroModificado.setAutor(libro.getAutor());
                libroModificado.setNumPaginas(tvPaginasLeidas.getText().toString().trim());


                DB.actualizarLibro(libroModificado);

                Intent i = new Intent(VisualizarLibro.this, NavDrawer.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();


            }
        });
    }
    private void asociacion() {

        imageVisualizarLibro = (ImageView) findViewById(R.id.imageVisualizarLibro);
        tvTituloLibro = (TextView) findViewById(R.id.tvTituloLibro);
        tvSipnopsis = (EditText) findViewById(R.id.tvSipnopsis);
        tvISBN = (EditText) findViewById(R.id.tvISBN);
        tvPrecio = (EditText) findViewById(R.id.tvPrecio);
        tvPaginasLeidas = (EditText) findViewById(R.id.tvPaginasLeidas);
//        recyclerVisualizarLibroID = (RecyclerView) findViewById(R.id.recyclerVisualizarLibroID);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        btGuardarCambios = (ImageButton) findViewById(R.id.btGuardarCambiosLibro);

        DB = new DBHelper(VisualizarLibro.this);
        usuario = MainActivity.usuarioObjeto;
        libro = LibroLibraryAdapter.libro;


    }


}