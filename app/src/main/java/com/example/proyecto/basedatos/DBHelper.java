package com.example.proyecto.basedatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyecto.clasesObjeto.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "MyReadingDiary.db";

    // User table name
    private static final String TABLE_USUARIO = "usuario";

    // User Table Columns names
    private static final String COLUMN_IDUSUARIO = "idUsuario";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CONTRASENA = "contrasena";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDOS = "apellidos";
    private static final String COLUMN_TELEFONO = "telefono";

    // create table sql query
    private String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + "("
            + COLUMN_IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USUARIO + " TEXT,"+ COLUMN_CONTRASENA + " TEXT,"
            + COLUMN_NOMBRE + " TEXT," + COLUMN_APELLIDOS + " TEXT," +COLUMN_TELEFONO + " TEXT" + ")";

    // drop table sql query
    private String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_TABLE_USUARIO);
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

        Integer id = usuario.getIdUsuario()+1;


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
}
