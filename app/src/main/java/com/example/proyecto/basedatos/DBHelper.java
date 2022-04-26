package com.example.proyecto.basedatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyecto.clasesObjeto.Libro;
import com.example.proyecto.clasesObjeto.Memories;
import com.example.proyecto.clasesObjeto.RelacionUsuarioLibro;
import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "MyReadingDiary.db";

    // table name
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_MEMORIES = "memories";
    private static final String TABLE_LIBROS = "libros";
    private static final String TABLE_LIBRO_USUARIO = "libro_usuario";

    // usuario
    private static final String COLUMN_IDUSUARIO = "idUsuario";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CONTRASENA = "contrasena";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDOS = "apellidos";
    private static final String COLUMN_TELEFONO = "telefono";

    //recuerdos
    private static final String COLUMN_IDMEMORIES = "idmemories";
    private static final String COLUMN_FRASE = "frase";
    private static final String COLUMN_FRASE_COLOR = "frase_color";
    private static final String COLUMN_PUNTUACION = "puntuacion";
    private static final String COLUMN_PUNTUACION_COLOR = "puntuacion_color";
    private static final String COLUMN_IMAGEN = "imagen";
    private static final String COLUMN_IMAGEN_COLOR = "imagen_color";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_DESCRIPCION_COLOR = "descripcion_color";
    private static final String COLUMN_POSITIVO = "positivo";
    private static final String COLUMN_POSITIVO_COLOR = "positivo_color";
    private static final String COLUMN_NEGATIVO = "negativo";
    private static final String COLUMN_NEGATIVO_COLOR = "negativo_color";
    private static final String COLUMN_PAGINAS_LEIDAS = "paginas_leidas";
    private static final String COLUMN_ID_USUARIO = "idUsuario";
    private static final String COLUMN_ID_LIBRO = "idLibro";

    //libro
    private static final String COLUMN_IDLIBRO = "idLibro";
    private static final String COLUMN_FOTO = "foto";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_AUTOR = "autor";
    private static final String COLUMN_CODIGOBARRAS = "codigo_barras";
    private static final String COLUMN_EDITORIAL = "editorial";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_DESCRIPCION_LIBRO = "descripcion";
    private static final String COLUMN_COMPRADO = "comprado";
    private static final String COLUMN_PUNTUACION_LIBRO = "puntuacion";
    private static final String COLUMN_TIENDA = "tienda";
    private static final String COLUMN_USUARIO_FK2 = "idUsuario";

    //tabla relacion
    private static final String COLUMN_ID_LIBRO_USUARIO = "idLibroUsuario";
    private static final String COLUMN_USUARIO_FK = "idUsuario";
    private static final String COLUMN_LIBRO_FK = "idLibro";

    // create table sql query
    //HAY QUE PONER FK Y TABLA LIBRO_USUARIO
    private String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + "("
            + COLUMN_IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USUARIO + " TEXT,"
            + COLUMN_CONTRASENA + " TEXT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_APELLIDOS + " TEXT,"
            + COLUMN_TELEFONO + " TEXT" + ");";

    private String CREATE_TABLE_MEMORIES = "CREATE TABLE " + TABLE_MEMORIES + "("
            + COLUMN_IDMEMORIES + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FRASE + " TEXT,"
            + COLUMN_FRASE_COLOR + " TEXT,"
            + COLUMN_PUNTUACION + " TEXT,"
            + COLUMN_PUNTUACION_COLOR + " TEXT,"
            + COLUMN_IMAGEN + " TEXT,"
            + COLUMN_IMAGEN_COLOR + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT,"
            + COLUMN_DESCRIPCION_COLOR + " TEXT,"
            + COLUMN_POSITIVO + " TEXT,"
            + COLUMN_POSITIVO_COLOR + " TEXT,"
            + COLUMN_NEGATIVO + " TEXT,"
            + COLUMN_NEGATIVO_COLOR + " TEXT,"
            + COLUMN_PAGINAS_LEIDAS + " TEXT,"
            + COLUMN_ID_USUARIO + " INTEGER,"
            + COLUMN_ID_LIBRO + " INTEGER,"
            + " FOREIGN KEY ("+COLUMN_ID_USUARIO+") REFERENCES "+TABLE_USUARIO+"("+COLUMN_IDUSUARIO+"),"
            + " FOREIGN KEY ("+COLUMN_ID_LIBRO+") REFERENCES "+TABLE_LIBROS+"("+COLUMN_IDLIBRO+")"
            + ");";

    private String CREATE_TABLE_LIBROS = "CREATE TABLE " + TABLE_LIBROS + "("
            + COLUMN_IDLIBRO + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOTO + " TEXT,"+COLUMN_FRASE_COLOR + " TEXT,"
            + COLUMN_TITULO + " TEXT,"
            + COLUMN_AUTOR + " TEXT,"
            + COLUMN_CODIGOBARRAS + " TEXT,"
            + COLUMN_EDITORIAL + " TEXT,"
            + COLUMN_PRECIO + " REAL,"
            + COLUMN_DESCRIPCION_LIBRO + " TEXT,"
            + COLUMN_COMPRADO + " INTEGER,"
            + COLUMN_USUARIO_FK2 + " INTEGER,"
            + COLUMN_PUNTUACION_LIBRO + " REAL,"
            + COLUMN_TIENDA + " TEXT,"
            + " FOREIGN KEY ("+COLUMN_USUARIO_FK2+") REFERENCES "+TABLE_USUARIO+"("+COLUMN_IDUSUARIO+")"
            + ");";



    private String CREATE_TABLE_LIBRO_USUARIO = "CREATE TABLE " + TABLE_LIBRO_USUARIO + "("
            + COLUMN_ID_LIBRO_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USUARIO_FK + " INTEGER,"
            + COLUMN_LIBRO_FK + " INTEGER,"
            + " FOREIGN KEY ("+COLUMN_USUARIO_FK+") REFERENCES "+TABLE_USUARIO+"("+COLUMN_IDUSUARIO+"),"
            + " FOREIGN KEY ("+COLUMN_LIBRO_FK+") REFERENCES "+TABLE_LIBROS+"("+COLUMN_IDLIBRO+")"
            + ")";

    // drop table sql query
    private String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO;
    private String DROP_TABLE_MEMORIES = "DROP TABLE IF EXISTS " + TABLE_MEMORIES;
    private String DROP_TABLE_LIBRO_USUARIO = "DROP TABLE IF EXISTS " + TABLE_LIBRO_USUARIO;
    private String DROP_TABLE_LIBROS = "DROP TABLE IF EXISTS " + TABLE_LIBROS;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_LIBROS);
        db.execSQL(CREATE_TABLE_MEMORIES);
        db.execSQL(CREATE_TABLE_LIBRO_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_TABLE_USUARIO);
        db.execSQL(DROP_TABLE_LIBROS);
        db.execSQL(DROP_TABLE_MEMORIES);
        db.execSQL(DROP_TABLE_LIBRO_USUARIO);
        // Create tables again
        onCreate(db);
    }
    /**
     * Metodo para crear usuario
     *
     * @param usuario
     */
    public void insertUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USUARIO, usuario.getUsuario());
        values.put(COLUMN_CONTRASENA, usuario.getContrasena());
        values.put(COLUMN_NOMBRE, usuario.getNombre());
        values.put(COLUMN_APELLIDOS, usuario.getApellidos());
        values.put(COLUMN_TELEFONO, usuario.getTelefono());

        // Inserting Row
        db.insert(TABLE_USUARIO, null, values);
        db.close();
        System.out.println("Usuario insertado");
    }
    /**
     * Este método es para buscar a todos los usuarios y devolver la lista de registros de usuarios.
     *
     * @return list
     */
    @SuppressLint("Range")
    public List<Usuario> getAllUsuarios() {

        String[] columnas = {
                COLUMN_IDUSUARIO,
                COLUMN_USUARIO,
                COLUMN_CONTRASENA,
                COLUMN_NOMBRE,
                COLUMN_APELLIDOS,
                COLUMN_TELEFONO
        };

        String orden =
                COLUMN_IDUSUARIO + " ASC";
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        SQLiteDatabase db = this.getReadableDatabase();

         //SELECT idusuario,usuario,contraseña,nombre,apellidos,telefono FROM ususuario ORDER BY idusuario;

        Cursor cursor = db.query(TABLE_USUARIO, //Table to query
                columnas,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                orden); //The sort order
        // cogemos todos los datos y los metemos en la lista
        if (cursor.moveToFirst()) {
            do {
                Usuario usuarioClase = new Usuario();
                usuarioClase.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IDUSUARIO))));
                usuarioClase.setUsuario(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO)));
                usuarioClase.setContrasena(cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASENA)));
                usuarioClase.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
                usuarioClase.setApellidos(cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDOS)));
                usuarioClase.setTelefono(cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO)));
                // Adding user record to list
                listaUsuario.add(usuarioClase);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listaUsuario;
    }
    /**
     * Metodo para actualizar usuario
     *
     * @param usuario
     */
    public void actualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IDUSUARIO, usuario.getIdUsuario());
        values.put(COLUMN_USUARIO, usuario.getUsuario());
        values.put(COLUMN_CONTRASENA, usuario.getContrasena());
        values.put(COLUMN_NOMBRE, usuario.getNombre());
        values.put(COLUMN_APELLIDOS, usuario.getApellidos());
        values.put(COLUMN_TELEFONO, usuario.getTelefono());

        db.update(TABLE_USUARIO, values, COLUMN_IDUSUARIO + " = ?",
                new String[]{String.valueOf(usuario.getIdUsuario())});
        db.close();
    }
    /**
     * Metodo para borrar usuario
     *
     * @param usuario
     */
    public void borrarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USUARIO, COLUMN_IDUSUARIO + " = ?",
                new String[]{String.valueOf(usuario.getIdUsuario())});
        db.close();
    }
    /**
     * Metodo para ver si usuario existe
     *
     * @param usuario
     * @return true/false
     */
    public boolean existeUsuario(String usuario) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_IDUSUARIO
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USUARIO + " = ?";
        // selection argument
        String[] selectionArgs = {usuario};
        // query user table with condition

        Cursor cursor = db.query(TABLE_USUARIO, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    /**
     * Usuario existe o no
     *
     * @param usuario
     * @param contrasena
     * @return true/false
     */
    public boolean existeUsuarioContrasena(String usuario, String contrasena) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_IDUSUARIO
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USUARIO + " = ?" + " AND " + COLUMN_CONTRASENA + " = ?";
        // selection arguments
        String[] selectionArgs = {usuario, contrasena};
        // query user table with conditions

        Cursor cursor = db.query(TABLE_USUARIO, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Devuelve OBJETO Usuario
     *
     * @param usuario
     * @return true/false
     */
    @SuppressLint("Range")
    public Usuario getUsuario(String usuario) {
        // array of columns to fetch
        String[] column = {
                COLUMN_IDUSUARIO,
                COLUMN_USUARIO,
                COLUMN_CONTRASENA,
                COLUMN_NOMBRE,
                COLUMN_APELLIDOS,
                COLUMN_TELEFONO
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USUARIO + " = ?";
        // selection argument
        String[] selectionArgs = {usuario};
        // query user table with condition

        Cursor cursor = db.query(TABLE_USUARIO, //Table to query
                column,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        Usuario usuarioObjeto = new Usuario();
        if (cursor.moveToFirst()) {
            do {

                usuarioObjeto.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IDUSUARIO))));
                usuarioObjeto.setUsuario(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO)));
                usuarioObjeto.setContrasena(cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASENA)));
                usuarioObjeto.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
                usuarioObjeto.setApellidos(cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDOS)));
                usuarioObjeto.setTelefono(cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO)));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return usuarioObjeto;
    }

    //LIBRO

    /**
     * Metodo para crear libro
     *
     * @param libro
     */
    public void insertLibro(Libro libro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FOTO, libro.getFotoID());
        values.put(COLUMN_TITULO, libro.getTitulo());
        values.put(COLUMN_AUTOR, libro.getAutor());
        values.put(COLUMN_CODIGOBARRAS, libro.getCodigoBarras());
        values.put(COLUMN_EDITORIAL, libro.getEditorial());
        values.put(COLUMN_PRECIO, libro.getPrecio());
        values.put(COLUMN_DESCRIPCION_LIBRO, libro.getDescripcion());
        values.put(COLUMN_COMPRADO, libro.getComprado());
        values.put(COLUMN_PUNTUACION_LIBRO, libro.getPuntuacion());
        values.put(COLUMN_TIENDA, libro.getTienda());
        values.put(COLUMN_USUARIO_FK2, libro.getIdUsuario());

        // Inserting Row
        db.insert(TABLE_LIBROS, null, values);

        db.close();
        System.out.println("Libro insertado");
    }
    /**
     * Este método es para buscar a todos los libros y devolver la lista de registros de libros.
     *
     * @return list
     */
    @SuppressLint("Range")
    public List<Libro> getAllLibros() {

        String[] columnas = {
                COLUMN_ID_LIBRO,
                COLUMN_FOTO,
                COLUMN_TITULO,
                COLUMN_AUTOR,
                COLUMN_CODIGOBARRAS,
                COLUMN_EDITORIAL,
                COLUMN_PRECIO,
                COLUMN_DESCRIPCION_LIBRO,
                COLUMN_COMPRADO,
                COLUMN_PUNTUACION_LIBRO,
                COLUMN_TIENDA,
                COLUMN_USUARIO_FK2
        };

        String orden =
                COLUMN_ID_LIBRO + " ASC";
        List<Libro> lista = new ArrayList<Libro>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LIBROS, //Table to query
                columnas,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                orden); //The sort order
        // cogemos todos los datos y los metemos en la lista
        if (cursor.moveToFirst()) {
            do {
                Libro libroClase = new Libro();
                libroClase.setIdLibro(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_LIBRO))));
                libroClase.setFotoID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FOTO))));
                libroClase.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_TITULO)));
                libroClase.setAutor(cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR)));
                libroClase.setCodigoBarras(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGOBARRAS)));
                libroClase.setEditorial(cursor.getString(cursor.getColumnIndex(COLUMN_EDITORIAL)));
                libroClase.setPrecio(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO)));
                libroClase.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_LIBRO)));
                libroClase.setComprado(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_COMPRADO))));
                libroClase.setPuntuacion(cursor.getDouble(cursor.getColumnIndex(COLUMN_PUNTUACION_LIBRO)));
                libroClase.setTienda(cursor.getString(cursor.getColumnIndex(COLUMN_TIENDA)));
                libroClase.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_FK2))));

                // Adding user record to list
                lista.add(libroClase);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lista;
    }
    /**
     * Metodo para actualizar libro
     *
     * @param libro
     */
    public void actualizarLibro(Libro libro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID_LIBRO, libro.getIdLibro());
        values.put(COLUMN_FOTO, libro.getFotoID());
        values.put(COLUMN_TITULO, libro.getTitulo());
        values.put(COLUMN_AUTOR, libro.getAutor());
        values.put(COLUMN_CODIGOBARRAS, libro.getCodigoBarras());
        values.put(COLUMN_EDITORIAL, libro.getEditorial());
        values.put(COLUMN_PRECIO, libro.getPrecio());
        values.put(COLUMN_DESCRIPCION_LIBRO, libro.getDescripcion());
        values.put(COLUMN_COMPRADO, libro.getComprado());
        values.put(COLUMN_PUNTUACION_LIBRO, libro.getPuntuacion());
        values.put(COLUMN_TIENDA, libro.getTienda());
        values.put(COLUMN_USUARIO_FK2, libro.getIdUsuario());

        db.update(TABLE_LIBROS, values, COLUMN_ID_LIBRO + " = ?",
                new String[]{String.valueOf(libro.getIdLibro())});
        db.close();
    }
    /**
     * Metodo para borrar usuario
     *
     * @param libro
     */
    public void borrarLibro(Libro libro) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LIBROS, COLUMN_ID_LIBRO + " = ?",
                new String[]{String.valueOf(libro.getIdLibro())});
        db.close();
    }
    /**
     * Metodo para ver si usuario existe
     *
     * @param libro
     * @param usuario
     * @return true/false
     */
    public boolean existeLibro(String libro,Integer usuario) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_TITULO,
                COLUMN_USUARIO_FK2
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_TITULO + " = ?"  + " AND " + COLUMN_USUARIO_FK2 + " = ?";
        // selection argument
        String[] selectionArgs = {libro,String.valueOf(usuario)};
        // query user table with condition

        Cursor cursor = db.query(TABLE_LIBROS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Devuelve OBJETO Libro
     *
     * @param idLibro
     * @return true/false
     */
    @SuppressLint("Range")
    public Libro getLibro(Integer idLibro) {
        // array of columns to fetch
        String[] column = {
                COLUMN_ID_LIBRO,
                COLUMN_FOTO,
                COLUMN_TITULO,
                COLUMN_AUTOR,
                COLUMN_CODIGOBARRAS,
                COLUMN_EDITORIAL,
                COLUMN_PRECIO,
                COLUMN_DESCRIPCION_LIBRO,
                COLUMN_COMPRADO,
                COLUMN_PUNTUACION_LIBRO,
                COLUMN_TIENDA,
                COLUMN_USUARIO_FK2
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_ID_LIBRO + " = ?";
        // selection argument
        String[] selectionArgs = {String.valueOf(idLibro)};
        // query user table with condition

        Cursor cursor = db.query(TABLE_LIBROS, //Table to query
                column,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        Libro libroObjeto = new Libro();
        if (cursor.moveToFirst()) {
            do {
                libroObjeto.setIdLibro(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_LIBRO))));
                libroObjeto.setFotoID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FOTO))));
                libroObjeto.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_TITULO)));
                libroObjeto.setAutor(cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR)));
                libroObjeto.setCodigoBarras(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGOBARRAS)));
                libroObjeto.setEditorial(cursor.getString(cursor.getColumnIndex(COLUMN_EDITORIAL)));
                libroObjeto.setPrecio(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO)));
                libroObjeto.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));
                libroObjeto.setComprado(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_COMPRADO))));
                libroObjeto.setPuntuacion(cursor.getDouble(cursor.getColumnIndex(COLUMN_PUNTUACION)));
                libroObjeto.setTienda(cursor.getString(cursor.getColumnIndex(COLUMN_TIENDA)));
                libroObjeto.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_FK2))));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return libroObjeto;
    }

    /**
     * Devuelve idLibro del pinchado o buscado
     *
     * @param titulo
     * @param idUsuario
     * @return true/false
     */
    @SuppressLint("Range")
    public Integer getIDLibro (String titulo, Integer idUsuario){

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID_LIBRO,
                COLUMN_TITULO,
                COLUMN_USUARIO_FK2
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USUARIO_FK2 + " = ?" + " AND " + COLUMN_TITULO + " = ?";
        // selection argument
        String[] selectionArgs = {String.valueOf(idUsuario),titulo};
        // query user table with condition

        Cursor cursor = db.query(TABLE_LIBROS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order


        Integer id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_LIBRO)));

        return id;
    }

    //memories

    /**
     * Metodo para crear momento
     *
     * @param moment
     */
    public void insertMemorie(Memories moment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FRASE, moment.getFrase());
        values.put(COLUMN_FRASE_COLOR, moment.getFraseColor());
        values.put(COLUMN_PUNTUACION, moment.getPuntuacion());
        values.put(COLUMN_PUNTUACION_COLOR, moment.getPuntuacionColor());
        values.put(COLUMN_IMAGEN, moment.getImagen());
        values.put(COLUMN_IMAGEN_COLOR, moment.getImagenColor());
        values.put(COLUMN_DESCRIPCION, moment.getDescripcion());
        values.put(COLUMN_DESCRIPCION_COLOR, moment.getDescripcionColor());
        values.put(COLUMN_POSITIVO, moment.getPositivo());
        values.put(COLUMN_POSITIVO_COLOR, moment.getPositivoColor());
        values.put(COLUMN_NEGATIVO, moment.getNegativo());
        values.put(COLUMN_NEGATIVO_COLOR, moment.getNegativoColor());
        values.put(COLUMN_PAGINAS_LEIDAS, moment.getPaginasLeidas());
        values.put(COLUMN_ID_USUARIO, moment.getIdUsuario());
        values.put(COLUMN_ID_LIBRO, moment.getIdMemories());

        // Inserting Row
        db.insert(TABLE_MEMORIES, null, values);

        db.close();
        System.out.println("Recuerdo insertado");
    }
    /**
     * Este método es para buscar a todos los momentos y devolver la lista de registros de memories.
     *
     * @return list
     */
    @SuppressLint("Range")
    public List<Memories> getAllMemories() {

        String[] columnas = {
                COLUMN_IDMEMORIES,
                COLUMN_FRASE,
                COLUMN_FRASE_COLOR,
                COLUMN_PUNTUACION,
                COLUMN_PUNTUACION_COLOR,
                COLUMN_IMAGEN,
                COLUMN_IMAGEN_COLOR,
                COLUMN_DESCRIPCION,
                COLUMN_DESCRIPCION_COLOR,
                COLUMN_POSITIVO,
                COLUMN_POSITIVO_COLOR,
                COLUMN_NEGATIVO,
                COLUMN_NEGATIVO_COLOR,
                COLUMN_PAGINAS_LEIDAS,
                COLUMN_ID_USUARIO,
                COLUMN_ID_LIBRO
        };

        String orden =
                COLUMN_IDMEMORIES + " ASC";
        List<Memories> lista = new ArrayList<Memories>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEMORIES, //Table to query
                columnas,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                orden); //The sort order
        // cogemos todos los datos y los metemos en la lista
        if (cursor.moveToFirst()) {
            do {
                Memories momentClase = new Memories();
                momentClase.setIdMemories(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IDMEMORIES))));
                momentClase.setFrase(cursor.getString(cursor.getColumnIndex(COLUMN_FRASE)));
                momentClase.setFraseColor(cursor.getString(cursor.getColumnIndex(COLUMN_FRASE_COLOR)));
                momentClase.setPuntuacion(cursor.getString(cursor.getColumnIndex(COLUMN_PUNTUACION)));
                momentClase.setPuntuacionColor(cursor.getString(cursor.getColumnIndex(COLUMN_PUNTUACION_COLOR)));
                momentClase.setImagen(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))));
                momentClase.setImagenColor(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN_COLOR)));
                momentClase.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));
                momentClase.setDescripcionColor(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_COLOR)));
                momentClase.setPositivo(cursor.getString(cursor.getColumnIndex(COLUMN_POSITIVO)));
                momentClase.setPositivoColor(cursor.getString(cursor.getColumnIndex(COLUMN_POSITIVO_COLOR)));
                momentClase.setNegativo(cursor.getString(cursor.getColumnIndex(COLUMN_NEGATIVO)));
                momentClase.setNegativoColor(cursor.getString(cursor.getColumnIndex(COLUMN_NEGATIVO_COLOR)));
                momentClase.setPaginasLeidas(cursor.getString(cursor.getColumnIndex(COLUMN_PAGINAS_LEIDAS)));
                momentClase.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_FK))));
                momentClase.setIdLibro(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LIBRO_FK))));
                // Adding user record to list
                lista.add(momentClase);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lista;
    }
    /**
     * Metodo para actualizar libro
     *
     * @param moment
     */
    public void actualizarMemorie(Memories moment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IDMEMORIES, moment.getIdMemories());
        values.put(COLUMN_FRASE, moment.getFrase());
        values.put(COLUMN_FRASE_COLOR, moment.getFraseColor());
        values.put(COLUMN_PUNTUACION, moment.getPuntuacion());
        values.put(COLUMN_PUNTUACION_COLOR, moment.getPuntuacionColor());
        values.put(COLUMN_IMAGEN, moment.getImagen());
        values.put(COLUMN_IMAGEN_COLOR, moment.getImagenColor());
        values.put(COLUMN_DESCRIPCION, moment.getDescripcion());
        values.put(COLUMN_DESCRIPCION_COLOR, moment.getDescripcionColor());
        values.put(COLUMN_POSITIVO, moment.getPositivo());
        values.put(COLUMN_POSITIVO_COLOR, moment.getPositivoColor());
        values.put(COLUMN_NEGATIVO, moment.getNegativo());
        values.put(COLUMN_NEGATIVO_COLOR, moment.getNegativoColor());
        values.put(COLUMN_PAGINAS_LEIDAS, moment.getPaginasLeidas());
        values.put(COLUMN_ID_USUARIO, moment.getIdUsuario());
        values.put(COLUMN_ID_LIBRO, moment.getIdMemories());

        db.update(TABLE_MEMORIES, values, COLUMN_IDMEMORIES + " = ?",
                new String[]{String.valueOf(moment.getIdMemories())});
        db.close();
    }
    /**
     * Metodo para borrar recuerdo
     *
     * @param moment
     */
    public void borrarMemorie(Memories moment) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MEMORIES, COLUMN_IDMEMORIES + " = ?",
                new String[]{String.valueOf(moment.getIdMemories())});
        db.close();
    }
    /**
     * Metodo para ver si memorie existe
     *
     * @param moment
     * @return true/false
     */
    public boolean existeMemorie(Memories moment) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_IDMEMORIES
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_IDMEMORIES + " = ?";
        // selection argument
        String[] selectionArgs = {String.valueOf(moment.getIdMemories())};
        // query user table with condition

        Cursor cursor = db.query(TABLE_MEMORIES, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Devuelve OBJETO Memories
     *
     * @param idMoment
     * @return true/false
     */
    @SuppressLint("Range")
    public Memories getMemories(Integer idMoment) {
        // array of columns to fetch
        String[] column = {
                COLUMN_IDMEMORIES,
                COLUMN_FRASE,
                COLUMN_FRASE_COLOR,
                COLUMN_PUNTUACION,
                COLUMN_PUNTUACION_COLOR,
                COLUMN_IMAGEN,
                COLUMN_IMAGEN_COLOR,
                COLUMN_DESCRIPCION,
                COLUMN_DESCRIPCION_COLOR,
                COLUMN_POSITIVO,
                COLUMN_POSITIVO_COLOR,
                COLUMN_NEGATIVO,
                COLUMN_NEGATIVO_COLOR,
                COLUMN_PAGINAS_LEIDAS,
                COLUMN_ID_USUARIO,
                COLUMN_ID_LIBRO
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_IDMEMORIES + " = ?";
        // selection argument
        String[] selectionArgs = {String.valueOf(idMoment)};
        // query user table with condition

        Cursor cursor = db.query(TABLE_MEMORIES, //Table to query
                column,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        Memories momentClase = new Memories();
        if (cursor.moveToFirst()) {
            do {

                momentClase.setIdMemories(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IDMEMORIES))));
                momentClase.setFrase(cursor.getString(cursor.getColumnIndex(COLUMN_FRASE)));
                momentClase.setFraseColor(cursor.getString(cursor.getColumnIndex(COLUMN_FRASE_COLOR)));
                momentClase.setPuntuacion(cursor.getString(cursor.getColumnIndex(COLUMN_PUNTUACION)));
                momentClase.setPuntuacionColor(cursor.getString(cursor.getColumnIndex(COLUMN_PUNTUACION_COLOR)));
                momentClase.setImagen(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))));
                momentClase.setImagenColor(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN_COLOR)));
                momentClase.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));
                momentClase.setDescripcionColor(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_COLOR)));
                momentClase.setPositivo(cursor.getString(cursor.getColumnIndex(COLUMN_POSITIVO)));
                momentClase.setPositivoColor(cursor.getString(cursor.getColumnIndex(COLUMN_POSITIVO_COLOR)));
                momentClase.setNegativo(cursor.getString(cursor.getColumnIndex(COLUMN_NEGATIVO)));
                momentClase.setNegativoColor(cursor.getString(cursor.getColumnIndex(COLUMN_NEGATIVO_COLOR)));
                momentClase.setPaginasLeidas(cursor.getString(cursor.getColumnIndex(COLUMN_PAGINAS_LEIDAS)));
                momentClase.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_FK))));
                momentClase.setIdLibro(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LIBRO_FK))));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return momentClase;
    }
    

    //relacion libro y usuario
    /**
     * Metodo para crear libro y usuario en relacion
     *
     * @param IDlibro
     *  @param IDusuario
     */
    public void grabarLibroAUsuario(Integer IDlibro, Integer IDusuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USUARIO_FK, IDusuario);
        values.put(COLUMN_LIBRO_FK, IDlibro);

        // Inserting Row
        db.insert(TABLE_LIBRO_USUARIO, null, values);
        db.close();
        System.out.println("Relacion insertada");
    }

    @SuppressLint("Range")
    public RelacionUsuarioLibro obtenerLibrosDeUsuario(Integer idUsuario) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID_LIBRO_USUARIO,
                COLUMN_USUARIO_FK,
                COLUMN_LIBRO_FK
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USUARIO_FK + " = ?";
        // selection arguments
        String[] selectionArgs = {String.valueOf(idUsuario)};
        // query user table with conditions

        Cursor cursor = db.query(TABLE_LIBRO_USUARIO, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        RelacionUsuarioLibro relacion = new RelacionUsuarioLibro();
        if (cursor.moveToFirst()) {
            do {

                relacion.setIdLibroUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_LIBRO_USUARIO))));
                relacion.setIdUsuario(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_FK))));
                relacion.setIdLibro(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LIBRO_FK))));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return relacion;
    }


}
