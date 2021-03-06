package com.paq.proyecto.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.paq.proyecto.activity.MainActivity;
import com.paq.proyecto.adapter.LibroLibraryAdapter;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Libro;
import com.paq.proyecto.clasesObjeto.Usuario;

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

        LibroLibraryAdapter adapter = new LibroLibraryAdapter(listaLibros,getContext());//llenamos el adapter con la lista llena
        recyclerLibraryId.setAdapter(adapter); //metemos el adaptador que acabamos de llenar



        return view;
    }

    //crear objetos o recibir objetos para llenar la lista
    private void llenarLista() {
        //bbdd
        List<Libro> lista= DB.getAllLibrosDeUsuario(usuario.getIdUsuario());
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getComprado()==1){
                listaLibros.add(lista.get(i));
            }
        }

    }





}