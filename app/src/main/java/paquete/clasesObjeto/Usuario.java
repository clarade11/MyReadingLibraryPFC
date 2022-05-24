package paquete.clasesObjeto;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String telefono;

    public Usuario(String usuario,String contrasena,String nombre,String apellidos,String telefono){
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.telefono=telefono;
    }

    public Usuario(){

    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
