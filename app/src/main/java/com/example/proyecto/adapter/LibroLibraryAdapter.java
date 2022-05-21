package com.example.proyecto.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.activity.VisualizarLibro;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LibroLibraryAdapter extends RecyclerView.Adapter<LibroLibraryAdapter.LibroViewHolder> {

    ArrayList<Libro> listaLibros;
    public static Libro libro;
    Context mcontext;


    public LibroLibraryAdapter(ArrayList<Libro> listaLibros, Context context){
        this.listaLibros=listaLibros;
        mcontext=context;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listlibrary,null,false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroLibraryAdapter.LibroViewHolder holder, int position) {
        holder.tituloLibrary.setText(listaLibros.get(position).getTitulo());

        if (listaLibros.get(position).getFotoID().equals("")) {
            holder.imageLibrary.setImageResource(R.drawable.negro);
        } else{
            //si tiene url de la imagen
            Glide.with(holder.itemView.getContext())
                    .load(listaLibros.get(position).getFotoID())
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
                    .into(holder.imageLibrary);


        }


    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder {

        TextView tituloLibrary;
        ImageView imageLibrary;
        DBHelper DB;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloLibrary=(TextView) itemView.findViewById(R.id.tituloLibrary);
            imageLibrary=(ImageView) itemView.findViewById(R.id.imageLibrary);

            pulsarLibro(itemView);
        }

        private void pulsarLibro(View itemView) {
            DB = new DBHelper(itemView.getContext());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    alertBorrado();

                    return true;
                }

                private void alertBorrado() {
                    //CREAMOS DIALOGO, BBDD Y USUARIO
                    DB = new DBHelper(itemView.getContext());
                    Usuario usuario = MainActivity.usuarioObjeto;
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(itemView.getContext());
                    dialogo.setTitle("¿Desea borrar el libro? Esto borrará todos sus recuerdos");

                    dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            String titulo = tituloLibrary.getText().toString().trim();

                            Integer idLibro = DB.getIDLibro(titulo, usuario.getIdUsuario());
                            //Toast.makeText(itemView.getContext(), "ID LIBRO SELECCIONADO"+idLibro, Toast.LENGTH_SHORT).show();

                            Libro libro = DB.getLibro(idLibro);

                            List<Memories> recuerdosDelLibro = DB.getAllMemoriesDeUsuarioLibro(usuario.getIdUsuario(), idLibro);

                            for(int x=0;x<recuerdosDelLibro.size();x++){
                                DB.borrarMemorie(recuerdosDelLibro.get(x));
                                //System.out.println(recuerdosDelLibro.get(x).getFrase());
                            }

                            DB.borrarLibro(libro);
                            imageLibrary.setVisibility(View.GONE);
                            tituloLibrary.setVisibility(View.GONE);


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

            itemView.setOnClickListener(new View.OnClickListener() {
                Usuario usuario = MainActivity.usuarioObjeto;
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(),VisualizarLibro.class));

                    //hacer objeto libro para recogerlo en pantalla visualizar libro
                    Integer idLibro = DB.getIDLibro(tituloLibrary.getText().toString().trim(), usuario.getIdUsuario());

                    libro = DB.getLibro(idLibro);

                }
            });


        }


    }

}
