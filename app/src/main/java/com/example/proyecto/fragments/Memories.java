package com.example.proyecto.fragments;

import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.adapter.LibroLibraryAdapter;
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


        return view;
    }

    private void llenarLista() {

        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories("frase 1","azul","5","amarillo",R.drawable.negro,"rosa","descripcion 1"
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",1,0));

        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories(null,"azul",null,"amarillo",null,"rosa","descripcion 2"
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",2,0));
        listaMemories.add(new com.example.proyecto.clasesObjeto.Memories("frase 3","azul","5","amarillo",R.drawable.negro,"rosa",null
                , "rojo","positivo 1","naranja","negativo 1","rojo","45",3,0));
    }
}