package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.LibroLibraryAdapter;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;
import com.example.proyecto.fragments.Library;

import java.util.ArrayList;
import java.util.List;

public class VisualizarLibro extends AppCompatActivity {

    ImageView imageVisualizarLibro;
    TextView tvTituloLibro,tvSipnopsis,tvISBN,tvPrecio, tvPaginasLeidas;
    RecyclerView recyclerVisualizarLibroID;
    RatingBar ratingBar;

    DBHelper DB;
    Usuario usuario;
    ArrayList<Memories> listaMemories;
    Libro libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_libro);

        asociacion();

        vista();

    }

    private void vista() {

        listaMemories = new ArrayList<com.example.proyecto.clasesObjeto.Memories>();
        recyclerVisualizarLibroID.setLayoutManager(new LinearLayoutManager(this));

        llenarCampos();
        llenarLista();

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
            tvPrecio.setText(String.valueOf(libro.getPrecio()));
        } else {
            tvPrecio.setText("Precio no indicado");
        }
        tvPaginasLeidas.setText("No comenzado");

        //tvPaginasLeidas.setText(String.valueOf(listaMemories.get));

        if(libro.getPuntuacion()!=0.0){
            double puntuacion = (double)libro.getPuntuacion();
            ratingBar.setRating((float) puntuacion );
        } else {
            ratingBar.setRating(0);
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


    private void asociacion() {

        imageVisualizarLibro = (ImageView) findViewById(R.id.imageVisualizarLibro);
        tvTituloLibro = (TextView) findViewById(R.id.tvTituloLibro);
        tvSipnopsis = (TextView) findViewById(R.id.tvSipnopsis);
        tvISBN = (TextView) findViewById(R.id.tvISBN);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        tvPaginasLeidas = (TextView) findViewById(R.id.tvPaginasLeidas);
        recyclerVisualizarLibroID = (RecyclerView) findViewById(R.id.recyclerVisualizarLibroID);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);

        DB = new DBHelper(VisualizarLibro.this);
        usuario = MainActivity.usuarioObjeto;
        libro = LibroLibraryAdapter.libro;


    }


}