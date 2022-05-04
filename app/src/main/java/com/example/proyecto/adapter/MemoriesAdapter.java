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
import com.example.proyecto.activity.CreacionRecuerdo;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;

import java.util.ArrayList;

public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.MemoriesViewHolder>{

    ArrayList<Memories> listamemories;

    public MemoriesAdapter(ArrayList<Memories> listamemories){
        this.listamemories=listamemories;
    }

    @Override
    public MemoriesAdapter.MemoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmemories,null,false);
        return new MemoriesAdapter.MemoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoriesViewHolder holder, int position) {
        //el id libro tiene que ser cambiado por el nombre del libro
       ArrayList<String> nombresLibros=com.example.proyecto.fragments.Memories.nombreLibros;


        if(listamemories.get(position).getDescripcion()!=null){
            holder.descripcionMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));
            holder.descripcionMemories.setText(listamemories.get(position).getDescripcion());
        } else if(listamemories.get(position).getDescripcion()==null) {
            holder.descripcionMemories.setVisibility(View.GONE);
            holder.descripcionMemoriesLibro.setVisibility(View.GONE);
        }
        if(listamemories.get(position).getFrase()!=null) {
            holder.fraseMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));
            holder.fraseMemories.setText(listamemories.get(position).getFrase());
        }else if(listamemories.get(position).getFrase()==null) {
            holder.fraseMemories.setVisibility(View.GONE);
            holder.fraseMemoriesLibro.setVisibility(View.GONE);
        }
        if(listamemories.get(position).getImagen()!= null){
           // holder.imagenMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));
            //holder.imagenMemories.setImageURI(Uri.parse(listamemories.get(position).getImagen()));
            holder.imagenMemories.setVisibility(View.GONE);
            holder.imagenMemoriesLibro.setVisibility(View.GONE);
        }else if(listamemories.get(position).getImagen()==null) {
            holder.imagenMemories.setVisibility(View.GONE);
            holder.imagenMemoriesLibro.setVisibility(View.GONE);
        }



    }


    @Override
    public int getItemCount() {
        return listamemories.size();
    }

    public static class MemoriesViewHolder extends RecyclerView.ViewHolder {

        TextView imagenMemoriesLibro,fraseMemoriesLibro,fraseMemories,descripcionMemoriesLibro,descripcionMemories;
        ImageView imagenMemories;

        public MemoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMemoriesLibro=(TextView) itemView.findViewById(R.id.imagenMemoriesLibro);
            fraseMemoriesLibro=(TextView) itemView.findViewById(R.id.fraseMemoriesLibro);
            fraseMemories=(TextView) itemView.findViewById(R.id.fraseMemories);
            descripcionMemoriesLibro=(TextView) itemView.findViewById(R.id.descripcionMemoriesLibro);
            descripcionMemories=(TextView) itemView.findViewById(R.id.descripcionMemories);

            imagenMemories=(ImageView) itemView.findViewById(R.id.imagenMemories);
        }



    }
}
