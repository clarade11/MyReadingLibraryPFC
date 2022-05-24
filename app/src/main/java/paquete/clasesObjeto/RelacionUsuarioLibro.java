package paquete.clasesObjeto;

public class RelacionUsuarioLibro {

    Integer idLibroUsuario;
    Integer idUsuario;
    Integer idLibro;

    public RelacionUsuarioLibro(Integer idLibroUsuario,Integer idUsuario,Integer idLibro){
        this.idLibroUsuario=idLibroUsuario;
        this.idUsuario=idUsuario;
        this.idLibro=idLibro;
    }

    public RelacionUsuarioLibro(){

    }

    public Integer getIdLibroUsuario(){
        return idLibroUsuario;
    }

    public void setIdLibroUsuario(Integer idLibroUsuario) {
        this.idLibroUsuario = idLibroUsuario;
    }
    public Integer getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdLibro(){
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

}
