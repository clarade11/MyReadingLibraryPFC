package com.example.proyecto.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.clasesObjeto.Libro;

import java.util.ArrayList;

public class LibroWishListAdapter extends RecyclerView.Adapter<LibroWishListAdapter.LibroViewHolder> {

    ArrayList<Libro> listaLibros;

    public LibroWishListAdapter(ArrayList<Libro> listaLibros){
        this.listaLibros=listaLibros;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listwishlist,null,false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroWishListAdapter.LibroViewHolder holder, int position) {
        holder.tituloWishList.setText(listaLibros.get(position).getTitulo());
        holder.precioWishList.setText(Double.toString(listaLibros.get(position).getPrecio()));
        holder.descripcionWishList.setText(listaLibros.get(position).getDescripcion());
        holder.imageWishList.setImageURI(Uri.parse(listaLibros.get(position).getFotoID()));

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class LibroViewHolder extends RecyclerView.ViewHolder {

        TextView tituloWishList,precioWishList,descripcionWishList;
        ImageView imageWishList;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloWishList=(TextView) itemView.findViewById(R.id.tituloWishList);
            precioWishList=(TextView) itemView.findViewById(R.id.precioWishList);
            descripcionWishList=(TextView) itemView.findViewById(R.id.descripcionWishList);
            imageWishList=(ImageView) itemView.findViewById(R.id.imageWishList);
        }



    }
}
