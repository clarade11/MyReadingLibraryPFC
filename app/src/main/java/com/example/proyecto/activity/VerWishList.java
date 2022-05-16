package com.example.proyecto.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.example.proyecto.adapter.LibroLibraryGeneralAdapter;
import com.example.proyecto.adapter.LibroWishListAdapter;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;

public class VerWishList extends AppCompatActivity {

    DBHelper DB ;
    EditText tituloW,sipnopsisW,editorialW,autorW,tiendaW,
            precioW,codigoBarrasW;
    Button btGuardarCambios;
    ImageView imageView2;
    CheckBox comprado;

    Libro libro;

    Usuario usuario = MainActivity.usuarioObjeto;

    String foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_wish_list);

        asociacion();

        llenarCampos();

        editarImagen();

        guardar();

    }

    //alerta para cambiar la foto
    private void alertCambioFoto() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(VerWishList.this);
        dialogo.setTitle("Cambiar portada de libro");

        final EditText fotoNueva = new EditText(VerWishList.this);
        fotoNueva.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogo.setView(fotoNueva);

        //botones del alert
        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                foto = fotoNueva.getText().toString().trim();
                libro.setFotoID(foto);
                DB.actualizarLibro(libro);
                if(libro.getFotoID()!=null && (!libro.getFotoID().equals(""))){
                    Glide.with(VerWishList.this)
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
                            .into(imageView2);
                } else{
                    imageView2.setVisibility(View.INVISIBLE);
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

    //metodo para editar la imagen del libro
    private void editarImagen() {

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCambioFoto();
            }
        });

    }

    //llenamos los campos del libro
    private void llenarCampos() {

        //imageVisualizarLibro
        tituloW.setText(libro.getTitulo());
        if(libro.getDescripcion()!=null && (!libro.getDescripcion().equals(""))) {
            sipnopsisW.setText(libro.getDescripcion());
        }
        if(libro.getCodigoBarras()!=null && (!libro.getCodigoBarras().equals(""))){
            codigoBarrasW.setText(libro.getCodigoBarras());
        }
        if(String.valueOf(libro.getPrecio())!=null && (!String.valueOf(libro.getPrecio()).equals(""))){
            precioW.setText(String.valueOf(libro.getPrecio()));
        }
        if(libro.getAutor()!=null && (!libro.getAutor().equals(""))) {
            autorW.setText(libro.getAutor());
        }
        if(libro.getEditorial()!=null && (!libro.getEditorial().equals(""))) {
            editorialW.setText(libro.getEditorial());
        }
        if(libro.getTienda()!=null && (!libro.getTienda().equals(""))) {
            tiendaW.setText(libro.getTienda());
        }


        if(libro.getFotoID()!=null && (!libro.getFotoID().equals(""))){
            Glide.with(VerWishList.this)
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
                    .into(imageView2);
        }



    }

    private void guardar() {
        btGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer c=0;
                if (comprado.isChecked()) {
                    c = 1;
                }

                Libro libroModificado = new Libro();

                libroModificado.setComprado(c);
                libroModificado.setIdUsuario(usuario.getIdUsuario());
                libroModificado.setTienda(tiendaW.getText().toString().trim());
                libroModificado.setPuntuacion(0.0);
                libroModificado.setDescripcion(sipnopsisW.getText().toString().trim());
                libroModificado.setEditorial(editorialW.getText().toString().trim());
                libroModificado.setPrecio(Double.valueOf(precioW.getText().toString().trim()));
                libroModificado.setIdLibro(libro.getIdLibro());
                libroModificado.setCodigoBarras(codigoBarrasW.getText().toString().trim());
                libroModificado.setFotoID(libro.getFotoID());
                libroModificado.setTitulo(tituloW.getText().toString().trim());
                libroModificado.setAutor(autorW.getText().toString().trim());


                DB.actualizarLibro(libroModificado);

                Intent i = new Intent(VerWishList.this, NavDrawer.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                finish();


            }
        });
    }

    private void asociacion() {
        tituloW = (EditText) findViewById(R.id.tituloW);
        sipnopsisW = (EditText) findViewById(R.id.sipnopsisW);
        editorialW = (EditText) findViewById(R.id.editorialW);
        autorW = (EditText) findViewById(R.id.autorW);
        tiendaW = (EditText) findViewById(R.id.tiendaW);
        precioW = (EditText) findViewById(R.id.precioW);
        codigoBarrasW = (EditText) findViewById(R.id.codigoBarrasW);
        btGuardarCambios=(Button) findViewById(R.id.btGuardarCambios);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        comprado=(CheckBox) findViewById(R.id.checkboxW);

        DB=new DBHelper(VerWishList.this);
        libro= LibroWishListAdapter.libroWishlist;
    }
}