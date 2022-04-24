package com.example.proyecto.adapter;

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

public class LibroLibraryAdapter extends RecyclerView.Adapter<LibroLibraryAdapter.LibroViewHolder> {

    ArrayList<Libro> listaLibros;

    public LibroLibraryAdapter(ArrayList<Libro> listaLibros){
        this.listaLibros=listaLibros;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listlibrary,null,false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroLibraryAdapter.LibroViewHolder holder, int position) {
        holder.tituloLibrary.setText(listaLibros.get(position).getTitulo());
        holder.imageLibrary.setImageResource(listaLibros.get(position).getFotoID());

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder {

        TextView tituloLibrary;
        ImageView imageLibrary;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloLibrary=(TextView) itemView.findViewById(R.id.tituloLibrary);
            imageLibrary=(ImageView) itemView.findViewById(R.id.imageLibrary);
        }



    }

}
