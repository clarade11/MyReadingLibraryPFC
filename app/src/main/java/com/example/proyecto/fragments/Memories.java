package com.example.proyecto.fragments;

import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.MemoriesAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Memories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Memories extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerMemoriesId;
    ArrayList<com.example.proyecto.clasesObjeto.Memories> listaMemories;
    ArrayList<com.example.proyecto.clasesObjeto.Memories> listaFrases;
    ArrayList<com.example.proyecto.clasesObjeto.Memories> listaImagenes;
    ArrayList<com.example.proyecto.clasesObjeto.Memories> listaDescripciones;

    public Memories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Memories.
     */
    // TODO: Rename and change types and number of parameters
    public static Memories newInstance(String param1, String param2) {
        Memories fragment = new Memories();
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
        View view =inflater.inflate(R.layout.fragment_memories, container, false);

        listaMemories=new ArrayList<>();
        recyclerMemoriesId=view.findViewById(R.id.recyclerMemoriesId);
        recyclerMemoriesId.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        MemoriesAdapter adapter = new MemoriesAdapter(listaMemories);//llenamos el adapter con la lista llena
        recyclerMemoriesId.setAdapter(adapter); //metemos el adaptador que acabamos de llenar

//        //creamos un view
//
//        //creamos 3 adapter y en vez de meter lista completa, meter lista personalizada segun elemento
//
//        listaMemories=new ArrayList<>();
//        listaDescripciones=new ArrayList<>();
//        listaFrases=new ArrayList<>();
//        listaImagenes=new ArrayList<>();
//
//        recyclerMemoriesId=view.findViewById(R.id.recyclerMemoriesId);
//        recyclerMemoriesId.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        llenarLista();
//
//        clasificarListas();
//
//        MemoriesFrasesAdapter adapterFrase = new MemoriesFrasesAdapter(listaFrases);
//        recyclerMemoriesId.setAdapter(adapterFrase);
//        MemoriesImagenAdapter adapterImagen = new MemoriesImagenAdapter(listaImagenes);
//        recyclerMemoriesId.setAdapter(adapterImagen);
//        MemoriesDescripcionAdapter adapterDescripcion = new MemoriesDescripcionAdapter(listaDescripciones);
//        recyclerMemoriesId.setAdapter(adapterDescripcion);
//
//
//        //crear 3 layouts de listas

        return view;
    }

    private void clasificarListas() {

        for(int i=0;i<listaMemories.size();i++){
            if(listaMemories.get(i).getFrase()!=null){
                listaFrases.add(listaMemories.get(i));
            } else if(listaMemories.get(i).getDescripcion()!=null){
                listaDescripciones.add(listaMemories.get(i));
            } else if(listaMemories.get(i).getImagen()!=null){
                listaImagenes.add(listaMemories.get(i));
            }
        }


    }

    private void llenarLista() {

        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories("frase 1","azul","5","amarillo",R.drawable.negro,"rosa",null
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",1,0));

        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories(null,"azul",null,"amarillo",null,"rosa","descripcion 2"
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",2,0));
        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories(null,"azul","5","amarillo",R.drawable.negro,"rosa",null
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",3,0));
    }
}