package com.paq.proyecto.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.paq.proyecto.activity.MainActivity;
import com.paq.proyecto.adapter.MemoriesAdapter;
import com.paq.proyecto.basedatos.DBHelper;
import com.paq.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

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

    //ARRAYLIST DE OTRAS CLASES
    //public static ArrayList<String> nombreLibros = new ArrayList<String>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerMemoriesId;
    ArrayList<com.paq.proyecto.clasesObjeto.Memories> listaMemories;

    DBHelper DB;
    Usuario usuario;

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
        View view = inflater.inflate(R.layout.fragment_memories, container, false);

        DB = new DBHelper(getContext());
        usuario = MainActivity.usuarioObjeto;

        listaMemories = new ArrayList<com.paq.proyecto.clasesObjeto.Memories>();
        recyclerMemoriesId = view.findViewById(R.id.recyclerMemoriesId);
        recyclerMemoriesId.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        MemoriesAdapter adapter = new MemoriesAdapter(listaMemories);//llenamos el adapter con la lista llena
        recyclerMemoriesId.setAdapter(adapter); //metemos el adaptador que acabamos de llenar


        return view;
    }


    private void llenarLista() {


        List<com.paq.proyecto.clasesObjeto.Memories> lista = DB.getAllMemoriesDeUsuario(usuario.getIdUsuario());

        for (int i = 0; i < lista.size(); i++) {
            listaMemories.add(lista.get(i));
//            //RECUPERAMOS EL TITULO A PARTIR DEL ID DEL LIBRO
//            Integer id = lista.get(i).getIdLibro();
//            System.out.println(id);
//            Libro libro = DB.getLibro(id);
//            System.out.println("Titulo libro del recuerdo : " + libro.getTitulo());
//            String titulo = libro.getTitulo();
//            System.out.println("VARIABLE TITULO: " + titulo);
//            nombreLibros.add(titulo);

        }
    }
}