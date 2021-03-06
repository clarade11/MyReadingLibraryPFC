package com.paq.proyecto.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.paq.proyecto.activity.VisualizarLibro;
import com.paq.proyecto.clasesObjeto.Memories;

import java.util.ArrayList;

public class LibroLibraryGeneralAdapter extends RecyclerView.Adapter<LibroLibraryGeneralAdapter.LibroGeneralViewHolder>{
    ArrayList<Memories> listaMemories;
    Context mcontext;

    public LibroLibraryGeneralAdapter(ArrayList<Memories> listaMemories, VisualizarLibro visualizarLibro) {
        this.listaMemories=listaMemories;
        mcontext=visualizarLibro;
    }

    @NonNull
    @Override
    public LibroGeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmemorieslibrogeneral,null,false);
        return new LibroGeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroGeneralViewHolder holder, int position) {

        //poner imagen si existe
        if(listaMemories.get(position).getImagen()==null  || listaMemories.get(position).getImagen().equals("")  ){
            holder.imagenMemoriesVisualizarLibro.setVisibility(View.GONE);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(listaMemories.get(position).getImagen())
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
                    .into(holder.imagenMemoriesVisualizarLibro);
        }

        //poner descripcion si existe

        if(listaMemories.get(position).getDescripcion()==null || listaMemories.get(position).getDescripcion().equals("")){
            holder.descripcionMemoriesVisualizarLibro.setVisibility(View.GONE);

        }else {
            holder.descripcionMemoriesVisualizarLibro.setVisibility(View.VISIBLE);
            holder.descripcionMemoriesVisualizarLibro.setText(listaMemories.get(position).getDescripcion().trim());
            if(listaMemories.get(position).getDescripcionColor()!=null){
                holder.ldescripcion.setBackgroundColor(Integer.parseInt(listaMemories.get(position).getDescripcionColor()));}
        }

        //poner frase si existe

        if(listaMemories.get(position).getFrase()==null ){
            holder.fraseMemoriesVisualizarLibro.setVisibility(View.GONE);
        } else {
            holder.fraseMemoriesVisualizarLibro.setVisibility(View.VISIBLE);
            holder.fraseMemoriesVisualizarLibro.setText(listaMemories.get(position).getFrase().trim());
            if(listaMemories.get(position).getFraseColor()!=null){
                holder.lfrase.setBackgroundColor(Integer.parseInt(listaMemories.get(position).getFraseColor()));}
        }

        //poner positivo si existe

        if(listaMemories.get(position).getPositivo()==null || listaMemories.get(position).getDescripcion().equals("")){
            holder.positivoMemoriesVisualizarLibro.setVisibility(View.GONE);
        }else {
            holder.positivoMemoriesVisualizarLibro.setVisibility(View.VISIBLE);
            holder.positivoMemoriesVisualizarLibro.setText(listaMemories.get(position).getPositivo().trim());
            if(listaMemories.get(position).getPositivo()!=null){
                holder.lpositivo.setBackgroundColor(Integer.parseInt(listaMemories.get(position).getPositivoColor()));}
        }

        //poner negativo si existe

        if(listaMemories.get(position).getNegativo()==null || listaMemories.get(position).getDescripcion().equals("")){
            holder.negativoMemoriesVisualizarLibro.setVisibility(View.GONE);
        }else {
            holder.negativoMemoriesVisualizarLibro.setVisibility(View.VISIBLE);
            holder.negativoMemoriesVisualizarLibro.setText(listaMemories.get(position).getNegativo().trim());
            if(listaMemories.get(position).getNegativoColor()!=null){
                holder.lnegativo.setBackgroundColor(Integer.parseInt(listaMemories.get(position).getNegativoColor()));}
        }
    }

    @Override
    public int getItemCount() {
        return listaMemories.size();
    }

    public class LibroGeneralViewHolder extends RecyclerView.ViewHolder {

        ImageView imagenMemoriesVisualizarLibro;
        TextView fraseMemoriesVisualizarLibro, descripcionMemoriesVisualizarLibro,positivoMemoriesVisualizarLibro,negativoMemoriesVisualizarLibro;

        LinearLayout limagen,lfrase,ldescripcion,lpositivo,lnegativo;

        public LibroGeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            asociar();

        }

        private void asociar() {
            imagenMemoriesVisualizarLibro=(ImageView) itemView.findViewById(R.id.imagenMemoriesVisualizarLibro);
            fraseMemoriesVisualizarLibro=(TextView) itemView.findViewById(R.id.fraseMemoriesVisualizarLibro);
            descripcionMemoriesVisualizarLibro=(TextView) itemView.findViewById(R.id.descripcionMemoriesVisualizarLibro);
            positivoMemoriesVisualizarLibro=(TextView) itemView.findViewById(R.id.positivoMemoriesVisualizarLibro);
            negativoMemoriesVisualizarLibro=(TextView) itemView.findViewById(R.id.negativoMemoriesVisualizarLibro);

            lfrase = (LinearLayout) itemView.findViewById(R.id.lfrase);
            ldescripcion = (LinearLayout) itemView.findViewById(R.id.ldescripcion);
            limagen = (LinearLayout) itemView.findViewById(R.id.limagen);
            lpositivo = (LinearLayout) itemView.findViewById(R.id.lpositivo);
            lnegativo = (LinearLayout) itemView.findViewById(R.id.lnegativo);

        }
    }
}
