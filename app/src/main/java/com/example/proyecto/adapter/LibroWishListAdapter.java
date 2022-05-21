package com.example.proyecto.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.example.proyecto.activity.CreacionRecuerdo;
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.activity.Perfil;
import com.example.proyecto.activity.Registrar;
import com.example.proyecto.activity.VerWishList;
import com.example.proyecto.activity.VisualizarLibro;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibroWishListAdapter extends RecyclerView.Adapter<LibroWishListAdapter.LibroViewHolder> {

    ArrayList<Libro> listaLibros;
    DBHelper DB;
    public static Libro libroWishlist;
    Usuario usuario = MainActivity.usuarioObjeto;

    public LibroWishListAdapter(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listwishlist, null, false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroWishListAdapter.LibroViewHolder holder, int position) {
        holder.tituloWishList.setText(listaLibros.get(position).getTitulo());
        holder.precioWishList.setText(Double.toString(listaLibros.get(position).getPrecio()));
        holder.descripcionWishList.setText(listaLibros.get(position).getDescripcion());
        if(listaLibros.get(position).getFotoID().trim().equals("")){
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.no_photo)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                         public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                     }

                        @Override
                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             return false; }

            })
            .into(holder.imageWishList);
        } else{
            Glide.with(holder.itemView.getContext())
                    .load(listaLibros.get(position).getFotoID().trim())
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
                    .into(holder.imageWishList);
}


    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class LibroViewHolder extends RecyclerView.ViewHolder {

        TextView tituloWishList, precioWishList, descripcionWishList;
        ImageView imageWishList;
        Button btAleatorio;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloWishList = (TextView) itemView.findViewById(R.id.tituloWishList);
            precioWishList = (TextView) itemView.findViewById(R.id.precioWishList);
            descripcionWishList = (TextView) itemView.findViewById(R.id.descripcionWishList);
            imageWishList = (ImageView) itemView.findViewById(R.id.imageWishList);
            btAleatorio = (Button) itemView.findViewById(R.id.btAleatorio);

            pulsarLibro();

        }



        //cualquier pulsación a libro
        private void pulsarLibro() {
            DB = new DBHelper(itemView.getContext());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), VerWishList.class));

                    Integer idLibro = DB.getIDLibro(tituloWishList.getText().toString().trim(), usuario.getIdUsuario());
                    libroWishlist = DB.getLibro(idLibro);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    alertPasoVentana();

                    return true;

                }

                private void alertPasoVentana() {
                    //CREAMOS DIALOGO, BBDD Y USUARIO
                    DB = new DBHelper(itemView.getContext());
                    Usuario usuario = MainActivity.usuarioObjeto;
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(itemView.getContext());
                    dialogo.setTitle("¿Añadirlo a la librería?");

                    dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            Integer comprado = 1;
                            String tituloWishlist = tituloWishList.getText().toString().trim();
                            Integer id = DB.getIDLibro(tituloWishlist, usuario.getIdUsuario());
                            Libro libroWishlist = DB.getLibro(id);
                            libroWishlist.setComprado(comprado);
                            DB.actualizarLibro(libroWishlist);
                            Toast.makeText(itemView.getContext(), "Añadido a la librería", Toast.LENGTH_SHORT).show();

                            tituloWishList.setVisibility(View.GONE);
                            precioWishList.setVisibility(View.GONE);
                            descripcionWishList.setVisibility(View.GONE);
                            imageWishList.setVisibility(View.GONE);


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
        }


    }
}
