package com.example.proyecto.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyecto.R;
import com.example.proyecto.activity.MainActivity;
import com.example.proyecto.activity.VisualizarLibro;
import com.example.proyecto.adapter.LibroLibraryAdapter;
import com.example.proyecto.basedatos.DBHelper;
import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Library#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Library extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button visualizarLibro;
    RecyclerView recyclerLibraryId;
    ArrayList<Libro> listaLibros;
    Usuario usuario;
    DBHelper DB;

    public Library() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Library.
     */
    // TODO: Rename and change types and number of parameters
    public static Library newInstance(String param1, String param2) {
        Library fragment = new Library();
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
        View view =inflater.inflate(R.layout.fragment_library, container, false);
        DB = new DBHelper(getContext());

        usuario = MainActivity.usuarioObjeto;
        listaLibros=new ArrayList<>();
        recyclerLibraryId=view.findViewById(R.id.recyclerLibraryId);
        recyclerLibraryId.setLayoutManager(new GridLayoutManager(getContext(),2));

        llenarLista();

        LibroLibraryAdapter adapter = new LibroLibraryAdapter(listaLibros);//llenamos el adapter con la lista llena
        recyclerLibraryId.setAdapter(adapter); //metemos el adaptador que acabamos de llenar


        navegar(view);

        return view;
    }

    //crear objetos o recibir objetos para llenar la lista
    private void llenarLista() {
//        listaLibros.add(new Libro(null,"libro 1","yo","5555555855",
//        "micasa",7.80,"hola mundo",0,null,"teirico",0));
//        listaLibros.add(new Libro(null,"libro 2","yo","5555555855",
//                "micasa",7.80,"hola mundo",0,null,"teirico",0));
//        listaLibros.add(new Libro(null,"libro 3","yo","5555555855",
//                "micasa",7.80,"hola mundo",0,null,"teirico",0));
//        listaLibros.add(new Libro(null,"libro 4","yo","5555555855",
//                "micasa",7.80,"hola mundo",0,null,"teirico",0));
        //bbdd
        List<Libro> lista= DB.getAllLibrosDeUsuario(usuario.getIdUsuario());
        listaLibros=(ArrayList<Libro>) lista;

    }

    //NAVEGAR A APP
    public void navegar(View view){
        visualizarLibro = (Button)view.findViewById(R.id.btLibroSeleccionado);

        visualizarLibro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finish();
                Intent intent = new Intent(getActivity(), VisualizarLibro.class);
                startActivity(intent);

            }
        });
    }




}