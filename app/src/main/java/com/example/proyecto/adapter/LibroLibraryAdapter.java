package com.example.proyecto.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.activity.VisualizarLibro;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class LibroLibraryAdapter extends RecyclerView.Adapter<LibroLibraryAdapter.LibroViewHolder> {

    ArrayList<Libro> listaLibros;
    public static Libro libro;


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

        if (listaLibros.get(position).getFotoID() == null) {
            holder.imageLibrary.setImageResource(R.drawable.negro);
        } else{
            holder.imageLibrary.setImageURI(Uri.parse(listaLibros.get(position).getFotoID()));
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
