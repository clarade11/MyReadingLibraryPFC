package com.example.proyecto.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.adapter.LibroWishListAdapter;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //mis parametros
    RecyclerView recyclerLibros;
    ArrayList<Libro> listaLibros;
    ArrayList<Libro> lista;
    DBHelper DB;
    Usuario usuario;
    Button btAleatorio;

    public WishList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishList.
     */
    // TODO: Rename and change types and number of parameters
    public static WishList newInstance(String param1, String param2) {
        WishList fragment = new WishList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        DB = new DBHelper(getContext());
        usuario= MainActivity.usuarioObjeto;


        lista=new ArrayList<Libro>();
        recyclerLibros=view.findViewById(R.id.recyclerId);
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        LibroWishListAdapter adapter = new LibroWishListAdapter(lista);//llenamos el adapter con la lista llena
        recyclerLibros.setAdapter(adapter); //metemos el adaptador que acabamos de llenar

        pulsarAleatorio(view);

        return view;
    }

    //pulsamos botón aleatoria
    private void pulsarAleatorio(View view) {

        btAleatorio= (Button) view.findViewById(R.id.btAleatorio);


        btAleatorio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
                dialogo.setTitle("¡Libro aleatorio que debes comprar!");

                if(lista.size()!=0) {
                    Random random = new Random();
                    int n = random.nextInt(lista.size() + 0);

                    int idlibroaleatorio = lista.get(n).getIdLibro();
                    Libro aleatorio = DB.getLibro(idlibroaleatorio);

                    String tienda = aleatorio.getTienda();

                    if (tienda.equals("Tienda donde está disponible no indicado") || tienda.equals("")) {
                        tienda = "No indicado";
                    }


                    final TextView tv = new TextView(getContext());
                    tv.setText("El libro aleatorio escogido es: " + aleatorio.getTitulo() + " disponible en: " + tienda);
                    tv.setPadding(15, 15,
                            15, 15);
                    dialogo.setView(tv);
                }
                else{
                    final TextView tv = new TextView(getContext());
                    tv.setText("No hay libros en tu lista de deseo");
                    tv.setPadding(15, 15,
                            15, 15);
                    dialogo.setView(tv);
                }


                dialogo.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();

                    }
                });
                dialogo.show();
            }
        });




    }

    //metodo para crear objetos
    private void llenarLista() {
//                    //base de datos
        List<Libro> listabbdd =  DB.getAllLibrosDeUsuario(usuario.getIdUsuario());

        for(int i = 0; i<listabbdd.size();i++){
            if(listabbdd.get(i).getComprado()==0) {
                lista.add(listabbdd.get(i));
            }
        }


    }
}