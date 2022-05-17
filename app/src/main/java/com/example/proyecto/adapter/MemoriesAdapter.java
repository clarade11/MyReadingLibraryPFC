package com.example.proyecto.adapter;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.Usuario;
import java.util.ArrayList;
import java.util.List;

public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.MemoriesViewHolder> {

    ArrayList<Memories> listamemories;
    Integer id = 0;
    public static ArrayList<String> urlID = new ArrayList<>();


    public MemoriesAdapter(ArrayList<Memories> listamemories) {
        this.listamemories = listamemories;
    }

    @Override
    public MemoriesAdapter.MemoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmemories, null, false);
        return new MemoriesAdapter.MemoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoriesViewHolder holder, int position) {
        //el id libro tiene que ser cambiado por el nombre del libro
        ArrayList<String> nombresLibros = com.example.proyecto.fragments.Memories.nombreLibros;


        if (listamemories.get(position).getDescripcion() != null) {
            holder.descripcionMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));
            holder.descripcionMemories.setText(listamemories.get(position).getDescripcion());
        } else if (listamemories.get(position).getDescripcion() == null) {
            holder.descripcionMemories.setVisibility(View.GONE);
            holder.descripcionMemoriesLibro.setVisibility(View.GONE);
        }
        if (listamemories.get(position).getFrase() != null) {
            holder.fraseMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));
            holder.fraseMemories.setText(listamemories.get(position).getFrase());
        } else if (listamemories.get(position).getFrase() == null) {
            holder.fraseMemories.setVisibility(View.GONE);
            holder.fraseMemoriesLibro.setVisibility(View.GONE);
        }
        if (listamemories.get(position).getImagen() != null) {
            //System.out.println(listamemories.get(position).getImagen() + "------------IMAGEN");

            holder.idImagen.setText(String.valueOf(id).trim());
            urlID.add(listamemories.get(position).getImagen().trim());
            id++;


            Glide.with(holder.itemView.getContext())
                    .load(listamemories.get(position).getImagen().trim())
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
                    .into(holder.imagenMemories);
            holder.imagenMemoriesLibro.setText(String.valueOf(nombresLibros.get(position)));

        } else if (listamemories.get(position).getImagen() == null) {
            holder.imagenMemories.setVisibility(View.GONE);
            holder.imagenMemoriesLibro.setVisibility(View.GONE);
            holder.idImagen.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return listamemories.size();
    }

    public static class MemoriesViewHolder extends RecyclerView.ViewHolder {

        TextView imagenMemoriesLibro, fraseMemoriesLibro, fraseMemories, descripcionMemoriesLibro, descripcionMemories, idImagen;
        ImageView imagenMemories;
        DBHelper DB;
        ArrayList<String> url = MemoriesAdapter.urlID;

        public MemoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMemoriesLibro = (TextView) itemView.findViewById(R.id.imagenMemoriesLibro);
            fraseMemoriesLibro = (TextView) itemView.findViewById(R.id.fraseMemoriesLibro);
            fraseMemories = (TextView) itemView.findViewById(R.id.fraseMemories);
            descripcionMemoriesLibro = (TextView) itemView.findViewById(R.id.descripcionMemoriesLibro);
            descripcionMemories = (TextView) itemView.findViewById(R.id.descripcionMemories);
            idImagen = (TextView) itemView.findViewById(R.id.imagenID);
            imagenMemories = (ImageView) itemView.findViewById(R.id.imagenMemories);

            pulsarItem(itemView);
        }

        //borrar elemento pulsando largo
        private void pulsarItem(View itemView) {

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    alertBorrado();

                    return true;
                }

                private void alertBorrado() {
                    //CREAMOS DIALOGO, BBDD Y USUARIO
                    DB = new DBHelper(itemView.getContext());
                    Usuario usuario = MainActivity.usuarioObjeto;
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(itemView.getContext());
                    dialogo.setTitle("¿Desea borrar el recuerdo?");

                    dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            //Toast.makeText(itemView.getContext(), "Pulsado", Toast.LENGTH_SHORT).show();
                            String frase = fraseMemories.getText().toString().trim();
                            String tituloLibroFrase = fraseMemoriesLibro.getText().toString().trim();
                            String foto = idImagen.getText().toString().trim();
                            String tituloImagen = imagenMemoriesLibro.getText().toString().trim();
                            String descripcion = descripcionMemories.getText().toString().trim();
                            String tituloDescripcion = descripcionMemoriesLibro.getText().toString().trim();

                            List<Libro> lista = DB.getAllLibrosDeUsuario(usuario.getIdUsuario());

                            //creamos objeto con los campos pulsados
                            //System.out.println(frase+" "+tituloLibroFrase+" "+tituloImagen+" "+descripcion+" "+tituloDescripcion);
                            Integer idLibro = 0;
                            for (int x = 0; x < lista.size(); x++) {
                                if (tituloDescripcion != null && (!tituloDescripcion.equals("")) && tituloDescripcion.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();

                                } else if (tituloImagen != null&& (!tituloImagen.equals("")) && tituloImagen.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                } else if (tituloLibroFrase != null && (!tituloLibroFrase.equals("")) && tituloLibroFrase.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                    System.out.println("ID LIBRO INSERTADO " + lista.get(x).getIdLibro());
                                }
                            }
                            //System.out.println("IDLIBRO PULSADO"+idLibro);

                            //url de la foto seleccionada SEGURIDAD
                            String imagenURL=null;
                            if(foto.equals("")){
                                imagenURL=null;
                            }else if(foto.isEmpty()){
                                imagenURL=null;
                            }else if (foto != null) {
                                imagenURL = url.get(Integer.parseInt(foto));
                            } else {
                                imagenURL=null;
                            }

                            //SEGURIDAD STRINGS
                            String fraseBBDD=null;
                            String descripcionBBDD=null;

                            fraseBBDD = seguridad(fraseBBDD, frase);
                            descripcionBBDD=seguridad(descripcionBBDD,descripcion);


                            Memories memo = new Memories(fraseBBDD, descripcionBBDD, null, null, imagenURL, idLibro, usuario.getIdUsuario());


                            //comparamos los recuerdos de la bbdd con el pulsado para borrarlo
                            List<Memories> listamemoriesdeusuario = DB.getAllMemoriesDeUsuario(usuario.getIdUsuario());
                            Memories moment ;
                            Integer idRecuerdo = 0;

                            for (int a = 0; a < listamemoriesdeusuario.size(); a++) {
                                System.out.println("DENTRO DEL FOR ID DEL MOMENTO RECORRIDO" + listamemoriesdeusuario.get(a).getIdMemories());

                                if (memo.getIdLibro() == listamemoriesdeusuario.get(a).getIdLibro()) {
                                    System.out.println("IDLIBRO COINCIDE");
                                    //arreglar esto

                                    if (memo.getFrase() != null && listamemoriesdeusuario.get(a).getFrase() != null && (!memo.getFrase().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieFrase(usuario.getIdUsuario(), memo.getFrase().trim());

                                    } else if (memo.getDescripcion() != null && listamemoriesdeusuario.get(a).getDescripcion() != null && (!memo.getDescripcion().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieDescripcion(usuario.getIdUsuario(), memo.getDescripcion().trim());

                                    } else if (memo.getImagen() != null && listamemoriesdeusuario.get(a).getImagen() != null && (!memo.getImagen().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieImagen(usuario.getIdUsuario(), memo.getImagen().trim());

                                    }
                                }

                            }
                            System.out.println(idRecuerdo + "----> ID RECUERDO");
                            moment = DB.getMemories(idRecuerdo);

                            System.out.println("ID DEL MEMORIE DE LA BASE DE DATOS" + moment.getIdMemories());
                            DB.borrarMemorie(moment);

                            Toast.makeText(itemView.getContext(), "Recuerdo borrado", Toast.LENGTH_SHORT).show();

                            //hacemos invisibles
                            imagenMemoriesLibro.setVisibility(View.GONE);
                            fraseMemoriesLibro.setVisibility(View.GONE);
                            fraseMemories.setVisibility(View.GONE);
                            descripcionMemoriesLibro.setVisibility(View.GONE);
                            descripcionMemories.setVisibility(View.GONE);
                            imagenMemories.setVisibility(View.GONE);
                            idImagen.setVisibility(View.GONE);

                        }

                        private String seguridad(String bbdd, String comprobar) {

                            if(comprobar.isEmpty()){
                                bbdd=null;
                            } else if(comprobar.equals("")){
                                bbdd=null;
                            } else if(comprobar!=null){
                                bbdd=comprobar;
                            }

                            return bbdd;
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
                @Override
                public void onClick(View v) {
                    alertModificar();
                }
                String imagenParaEditar=null;
                private void alertModificar() {
                    //Toast.makeText(itemView.getContext(), "Pulsado", Toast.LENGTH_SHORT).show();

                    Usuario usuario = MainActivity.usuarioObjeto;
                    DB = new DBHelper(itemView.getContext());

                    //cogemos string del elemento pulsado
                    String fraseParaEditar = fraseMemories.getText().toString().trim();
                    String descripcionParaEditar = descripcionMemories.getText().toString().trim();

                    String id=idImagen.getText().toString().trim();


                    if(id!=null && (!id.isEmpty()) && (!id.equals(""))){
                        imagenParaEditar=url.get(Integer.parseInt(id));
                    }



                    if (fraseParaEditar != null || descripcionParaEditar != null || imagenParaEditar!=null) {

                        //Creamos dialogo
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(itemView.getContext());
                        dialogo.setTitle("Modificar recuerdo");

                        final EditText edTextoModificado = new EditText(itemView.getContext());
                        edTextoModificado.setInputType(InputType.TYPE_CLASS_TEXT);
                        if (fraseParaEditar != null && (!fraseParaEditar.equals(""))) {
                            edTextoModificado.setText(fraseParaEditar);
                        } else if (descripcionParaEditar != null && (!descripcionParaEditar.equals(""))) {
                            edTextoModificado.setText(descripcionParaEditar);
                        } else if(imagenParaEditar != null && (!imagenParaEditar.equals(""))){
                            edTextoModificado.setText(imagenParaEditar);
                        }
                        dialogo.setView(edTextoModificado);

                        //botones del alert
                        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (fraseParaEditar != null && (!fraseParaEditar.equals("")) ) {
                                    System.out.println("FRASE ANTGUA --" + fraseParaEditar);
                                    String frase = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemorieFrase(usuario.getIdUsuario(), fraseParaEditar);
                                    Memories moment = DB.getMemories(id);
                                    moment.setFrase(frase);
                                    DB.actualizarMemorie(moment);
                                    fraseMemories.setText(frase);
                                } else if (descripcionParaEditar != null && (!descripcionParaEditar.equals(""))) {
                                    System.out.println("DESCRIPCION ANTGUA --" + descripcionParaEditar);
                                    String descripcion = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemorieDescripcion(usuario.getIdUsuario(), descripcionParaEditar);
                                    Memories moment2 = DB.getMemories(id);
                                    moment2.setDescripcion(descripcion);
                                    DB.actualizarMemorie(moment2);
                                    descripcionMemories.setText(descripcion);
                                } else if (imagenParaEditar != null && (!imagenParaEditar.equals("")) ) {
                                    System.out.println("IMAGEN ANTGUA --" + imagenParaEditar);
                                    String imagen = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemorieImagen(usuario.getIdUsuario(), imagenParaEditar);
                                    Memories moment3 = DB.getMemories(id);
                                    moment3.setImagen(imagen);
                                    DB.actualizarMemorie(moment3);
                                    Toast.makeText(itemView.getContext(), "Actualizado con éxito, entre y salga de los recuerdos", Toast.LENGTH_SHORT).show();

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
                }
            });
        }


    }
}
