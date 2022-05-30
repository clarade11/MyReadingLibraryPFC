package com.paq.proyecto.adapter;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.proyecto.R;
import com.paq.proyecto.activity.MainActivity;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Memories;
import com.paq.proyecto.clasesObjeto.Usuario;

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
    public MemoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmemories, null, false);
        return new MemoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoriesViewHolder holder, int position) {
        //el id libro tiene que ser cambiado por el nombre del libro
        //ArrayList<String> nombresLibros = Memories.nombreLibros;
        DBHelper DB = new DBHelper(holder.itemView.getContext());


        if (listamemories.get(position).getDescripcion() != null) {
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getIdLibro());
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getDescripcion());

            String titulo = DB.getTituloLibro(listamemories.get(position).getIdLibro());
            holder.descripcionMemoriesLibro.setText(titulo);

            holder.descripcionMemories.setText(listamemories.get(position).getDescripcion());
            if(listamemories.get(position).getDescripcionColor()!=null){

            holder.cv3.setCardBackgroundColor(Integer.parseInt(listamemories.get(position).getDescripcionColor()));}

        } else if (listamemories.get(position).getDescripcion() == null) {
            holder.descripcionMemories.setVisibility(View.GONE);
            holder.descripcionMemoriesLibro.setVisibility(View.GONE);
            holder.cv3.setVisibility(View.GONE);
        }
        if (listamemories.get(position).getFrase() != null) {

            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getIdLibro());
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getFrase());


            String titulo = DB.getTituloLibro(listamemories.get(position).getIdLibro());
            holder.fraseMemoriesLibro.setText(titulo);
            holder.fraseMemories.setText(listamemories.get(position).getFrase());

            if(listamemories.get(position).getFraseColor()!=null){
                holder.cv2.setCardBackgroundColor(Integer.parseInt(listamemories.get(position).getFraseColor()));}
        } else if (listamemories.get(position).getFrase() == null) {
            holder.fraseMemories.setVisibility(View.GONE);
            holder.fraseMemoriesLibro.setVisibility(View.GONE);
            holder.cv2.setVisibility(View.GONE);
        }
        if (listamemories.get(position).getImagen() != null) {
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getIdLibro());
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getImagen());

            holder.idImagen.setText(String.valueOf(id).trim());
            urlID.add(listamemories.get(position).getImagen().trim());
            id++;
            if(listamemories.get(position).getImagen().trim().equals("")){
                Glide.with(holder.itemView.getContext())
                        .load(R.drawable.no_photo)
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
            } else {
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
            }
            String titulo = DB.getTituloLibro(listamemories.get(position).getIdLibro());
            holder.imagenMemoriesLibro.setText(titulo);
            if(listamemories.get(position).getImagenColor()!=null){
                holder.cv1.setCardBackgroundColor(Integer.parseInt(listamemories.get(position).getImagenColor()));}

        } else if (listamemories.get(position).getImagen() == null) {
            holder.imagenMemories.setVisibility(View.GONE);
            holder.imagenMemoriesLibro.setVisibility(View.GONE);
            holder.idImagen.setVisibility(View.GONE);
            holder.cv1.setVisibility(View.GONE);
        } if (listamemories.get(position).getPositivo() != null) {
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getIdLibro());
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getPositivo());


            String titulo = DB.getTituloLibro(listamemories.get(position).getIdLibro());
            holder.positivoMemoriesLibro.setText(titulo);

            holder.positivoMemories.setText(listamemories.get(position).getPositivo());
            if(listamemories.get(position).getPositivoColor()!=null){

                holder.cv4.setCardBackgroundColor(Integer.parseInt(listamemories.get(position).getPositivoColor()));}

        } else if (listamemories.get(position).getPositivo() == null) {
            holder.positivoMemories.setVisibility(View.GONE);
            holder.positivoMemoriesLibro.setVisibility(View.GONE);
            holder.cv4.setVisibility(View.GONE);
        }if (listamemories.get(position).getNegativo() != null) {
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getIdLibro());
            System.out.println("IDLIBRO A BBDD"+listamemories.get(position).getNegativo());

            String titulo = DB.getTituloLibro(listamemories.get(position).getIdLibro());
            holder.negativoMemoriesLibro.setText(titulo);

            holder.negativoMemories.setText(listamemories.get(position).getNegativo());
            if(listamemories.get(position).getNegativoColor()!=null){

                holder.cv5.setCardBackgroundColor(Integer.parseInt(listamemories.get(position).getNegativoColor()));}

        } else if (listamemories.get(position).getNegativo() == null) {
            holder.negativoMemories.setVisibility(View.GONE);
            holder.negativoMemoriesLibro.setVisibility(View.GONE);
            holder.cv5.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return listamemories.size();
    }

    public static class MemoriesViewHolder extends RecyclerView.ViewHolder {

        TextView imagenMemoriesLibro, fraseMemoriesLibro, fraseMemories, descripcionMemoriesLibro, descripcionMemories, idImagen, negativoMemories, negativoMemoriesLibro, positivoMemories,positivoMemoriesLibro;
        ImageView imagenMemories;
        CardView cv1, cv2, cv3, cv4, cv5;
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
            positivoMemoriesLibro = (TextView) itemView.findViewById(R.id.positivoMemoriesLibro);
            positivoMemories = (TextView) itemView.findViewById(R.id.positivoMemories);
            negativoMemoriesLibro = (TextView) itemView.findViewById(R.id.negativoMemoriesLibro);
            negativoMemories = (TextView) itemView.findViewById(R.id.negativoMemories);
            cv1=(CardView) itemView.findViewById(R.id.card_view); //imagen
            cv2=(CardView) itemView.findViewById(R.id.card_view2); //frase
            cv3=(CardView) itemView.findViewById(R.id.card_view3); //descripcion
            cv4=(CardView) itemView.findViewById(R.id.card_view4); //positivo
            cv5=(CardView) itemView.findViewById(R.id.card_view5); //negativo

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
                            String frase = fraseMemories.getText().toString().trim();
                            String tituloLibroFrase = fraseMemoriesLibro.getText().toString().trim();
                            String foto = idImagen.getText().toString().trim();
                            String tituloImagen = imagenMemoriesLibro.getText().toString().trim();
                            String descripcion = descripcionMemories.getText().toString().trim();
                            String tituloDescripcion = descripcionMemoriesLibro.getText().toString().trim();
                            String positivo = positivoMemories.getText().toString().trim();
                            String tituloPositivo = positivoMemoriesLibro.getText().toString().trim();
                            String negativo = negativoMemories.getText().toString().trim();
                            String tituloNegativo = negativoMemoriesLibro.getText().toString().trim();

                            List<Libro> lista = DB.getAllLibrosDeUsuario(usuario.getIdUsuario());

                            Integer idLibro = 0;
                            for (int x = 0; x < lista.size(); x++) {
                                if (tituloDescripcion != null && (!tituloDescripcion.equals("")) && tituloDescripcion.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();

                                } else if (tituloImagen != null&& (!tituloImagen.equals("")) && tituloImagen.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                } else if (tituloLibroFrase != null && (!tituloLibroFrase.equals("")) && tituloLibroFrase.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                    System.out.println("ID LIBRO INSERTADO " + lista.get(x).getIdLibro());
                                }else if (tituloPositivo != null && (!tituloPositivo.equals("")) && tituloPositivo.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                }else if (tituloNegativo != null && (!tituloNegativo.equals("")) && tituloNegativo.equals(lista.get(x).getTitulo())) {
                                    idLibro = lista.get(x).getIdLibro();
                                }

                            }

                            lista.clear(); //limpiamos

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
                            String positivoBBDD=null;
                            String negativoBBDD=null;

                            fraseBBDD = seguridad(fraseBBDD, frase);
                            descripcionBBDD=seguridad(descripcionBBDD,descripcion);
                            positivoBBDD = seguridad(positivoBBDD, positivo);
                            negativoBBDD = seguridad(negativoBBDD, negativo);

                            Memories memo = new Memories(fraseBBDD, descripcionBBDD, positivoBBDD, negativoBBDD, imagenURL, idLibro, usuario.getIdUsuario());


                            //comparamos los recuerdos de la bbdd con el pulsado para borrarlo
                            List<Memories> listamemoriesdeusuario = DB.getAllMemoriesDeUsuario(usuario.getIdUsuario());
                            Memories moment ;
                            Integer idRecuerdo = 0;

                            for (int a = 0; a < listamemoriesdeusuario.size(); a++) {
                                System.out.println("DENTRO DEL FOR ID DEL MOMENTO RECORRIDO" + listamemoriesdeusuario.get(a).getIdMemories());

                                if (memo.getIdLibro() == listamemoriesdeusuario.get(a).getIdLibro()) {
                                    if (memo.getFrase() != null && listamemoriesdeusuario.get(a).getFrase() != null && (!memo.getFrase().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieFrase(usuario.getIdUsuario(), memo.getFrase().trim());

                                    } else if (memo.getDescripcion() != null && listamemoriesdeusuario.get(a).getDescripcion() != null && (!memo.getDescripcion().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieDescripcion(usuario.getIdUsuario(), memo.getDescripcion().trim());

                                    } else if (memo.getImagen() != null && listamemoriesdeusuario.get(a).getImagen() != null && (!memo.getImagen().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieImagen(usuario.getIdUsuario(), memo.getImagen().trim());

                                    }else if (memo.getPositivo() != null && listamemoriesdeusuario.get(a).getPositivo() != null && (!memo.getPositivo().equals(""))) {
                                        idRecuerdo = DB.getIDMemoriePositivo(usuario.getIdUsuario(), memo.getPositivo().trim());

                                    }else if (memo.getNegativo() != null && listamemoriesdeusuario.get(a).getNegativo() != null && (!memo.getNegativo().equals(""))) {
                                        idRecuerdo = DB.getIDMemorieNegativo(usuario.getIdUsuario(), memo.getNegativo().trim());

                                    }
                                }

                            }

                            listamemoriesdeusuario.clear();//limpiamos
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
                            positivoMemoriesLibro.setVisibility(View.GONE);
                            positivoMemories.setVisibility(View.GONE);
                            negativoMemoriesLibro.setVisibility(View.GONE);
                            negativoMemories.setVisibility(View.GONE);
                            imagenMemories.setVisibility(View.GONE);
                            idImagen.setVisibility(View.GONE);
                            cv1.setVisibility(View.GONE);
                            cv2.setVisibility(View.GONE);
                            cv3.setVisibility(View.GONE);
                            cv4.setVisibility(View.GONE);
                            cv5.setVisibility(View.GONE);

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
                    String positivoParaEditar = positivoMemories.getText().toString().trim();
                    String negativoParaEditar = negativoMemories.getText().toString().trim();

                    String id=idImagen.getText().toString().trim();


                    if(id!=null && (!id.isEmpty()) && (!id.equals(""))){
                        imagenParaEditar=url.get(Integer.parseInt(id));
                    }



                    if (fraseParaEditar != null || descripcionParaEditar != null || imagenParaEditar!=null || positivoParaEditar != null || negativoParaEditar != null) {

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
                        }else if(positivoParaEditar != null && (!positivoParaEditar.equals(""))){
                            edTextoModificado.setText(positivoParaEditar);
                        }else if(negativoParaEditar != null && (!negativoParaEditar.equals(""))){
                            edTextoModificado.setText(negativoParaEditar);
                        }
                        dialogo.setView(edTextoModificado);

                        //botones del alert
                        dialogo.setPositiveButton(R.string.actualizar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (fraseParaEditar != null && (!fraseParaEditar.equals("")) ) {
                                    String frase = edTextoModificado.getText().toString().trim();

                                    Integer id = DB.getIDMemorieFrase(usuario.getIdUsuario(), fraseParaEditar);
                                    Memories moment = DB.getMemories(id);
                                    moment.setFrase(frase);
                                    DB.actualizarMemorie(moment);
                                    fraseMemories.setText(frase);
                                } else if (descripcionParaEditar != null && (!descripcionParaEditar.equals(""))) {
                                    String descripcion = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemorieDescripcion(usuario.getIdUsuario(), descripcionParaEditar);
                                    Memories moment2 = DB.getMemories(id);
                                    moment2.setDescripcion(descripcion);
                                    DB.actualizarMemorie(moment2);
                                    descripcionMemories.setText(descripcion);
                                } else if (imagenParaEditar != null && (!imagenParaEditar.equals("")) ) {
                                    String imagen = edTextoModificado.getText().toString().trim();
                                    String url = Seguridad.introduccionURL(imagen, itemView.getContext());
                                    System.out.println(url +"--Zurl");
                                    Integer id = DB.getIDMemorieImagen(usuario.getIdUsuario(), imagenParaEditar);
                                    Memories moment3 = DB.getMemories(id);
                                    moment3.setImagen(url);

                                    DB.actualizarMemorie(moment3);
                                    Toast.makeText(itemView.getContext(), "Actualizado con éxito, entre y salga de los recuerdos", Toast.LENGTH_SHORT).show();

                                }else if (positivoParaEditar != null && (!positivoParaEditar.equals(""))) {
                                    String positivo = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemoriePositivo(usuario.getIdUsuario(), positivoParaEditar);
                                    Memories moment4 = DB.getMemories(id);
                                    moment4.setPositivo(positivo);
                                    DB.actualizarMemorie(moment4);
                                    positivoMemories.setText(positivo);
                                }else if (negativoParaEditar != null && (!negativoParaEditar.equals(""))) {
                                    String negativo = edTextoModificado.getText().toString().trim();
                                    Integer id = DB.getIDMemorieNegativo(usuario.getIdUsuario(), negativoParaEditar);
                                    Memories moment5 = DB.getMemories(id);
                                    moment5.setNegativo(negativo);
                                    DB.actualizarMemorie(moment5);
                                    negativoMemories.setText(negativo);
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
