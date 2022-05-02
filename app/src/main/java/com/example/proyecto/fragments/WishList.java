package com.example.proyecto.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.R;
import com.example.proyecto.adapter.LibroWishListAdapter;
import com.example.proyecto.clasesObjeto.Libro;

import java.util.ArrayList;

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

        listaLibros=new ArrayList<>();
        recyclerLibros=view.findViewById(R.id.recyclerId);
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        //hacer filtro de que si comprado true no puede pasar, pasaria en la libreria

        LibroWishListAdapter adapter = new LibroWishListAdapter(listaLibros);//llenamos el adapter con la lista llena
        recyclerLibros.setAdapter(adapter); //metemos el adaptador que acabamos de llenar

        return view;
    }

    //metodo para crear objetos
    private void llenarLista() {
                    //pruebas sin base de datos
        listaLibros.add(new Libro(null,"libro 1","yo","5555555855",
                "micasa",7.80,"hola mundo",0,null,"teirico",0));
        listaLibros.add(new Libro(null,"libro 2","yo","5555555855",
                "micasa",7.80,"hola mundo",0,null,"teirico",0));
        listaLibros.add(new Libro(null,"libro 3","yo","5555555855",
                "micasa",7.80,"hola mundo",0,null,"teirico",0));
        listaLibros.add(new Libro(null,"libro 4","yo","5555555855",
                "micasa",7.80,"hola mundo",0,null,"teirico",0));
                    //base de datos



    }
}