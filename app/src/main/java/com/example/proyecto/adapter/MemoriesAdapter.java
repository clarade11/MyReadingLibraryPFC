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

        if(listamemories.get(position).getDescripcion()!=null){
            holder.descripcionMemoriesLibro.setText(String.valueOf(listamemories.get(position).getIdLibro()));
            holder.descripcionMemories.setText(listamemories.get(position).getDescripcion());
        } else {
            holder.descripcionMemories.invalidate();
            holder.descripcionMemoriesLibro.invalidate();
        }
        if(listamemories.get(position).getFrase()!=null) {
            holder.fraseMemoriesLibro.setText(String.valueOf(listamemories.get(position).getIdLibro()));
            holder.fraseMemories.setText(listamemories.get(position).getFrase());
        }
        if(listamemories.get(position).getImagen()!= null){
            holder.imagenMemoriesLibro.setText(String.valueOf(listamemories.get(position).getIdLibro()));
            holder.imagenMemories.setImageResource(listamemories.get(position).getImagen());
        }else {
            holder.imagenMemoriesLibro.invalidate();
            holder.imagenMemories.invalidate();
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
