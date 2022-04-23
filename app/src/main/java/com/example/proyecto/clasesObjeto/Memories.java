package com.example.proyecto.clasesObjeto;

public class Memories {
    private int idMemories;
    private String frase;
    private String fraseColor;
    private String puntuacion;
    private String puntuacionColor;
    private String imagen;
    private String imagenColor;
    private String descripcion;
    private String descripcionColor;
    private String positivo;
    private String positivoColor;
    private String negativo;
    private String negativoColor;
    private String paginasLeidas;
    private Integer idUsuario;
    private Integer idLibro;

    public Memories(int idMemories, String frase, String fraseColor, String puntuacion, String puntuacionColor, String imagen, String imagenColor, String descripcion, String descripcionColor, String positivo, String positivoColor, String negativo, String negativoColor, String paginasLeidas,Integer idLibro,Integer idUsuario) {
        this.idMemories = idMemories;
        this.frase = frase;
        this.fraseColor = fraseColor;
        this.puntuacion = puntuacion;
        this.puntuacionColor = puntuacionColor;
        this.imagen = imagen;
        this.imagenColor = imagenColor;
        this.descripcion = descripcion;
        this.descripcionColor = descripcionColor;
        this.positivo = positivo;
        this.positivoColor = positivoColor;
        this.negativo = negativo;
        this.negativoColor = negativoColor;
        this.paginasLeidas = paginasLeidas;
        this.idUsuario = idUsuario;
        this.idLibro=idLibro;
    }

    public Memories(){

    }
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }


    public int getIdMemories() {
        return idMemories;
    }

    public void setIdMemories(int idMemories) {
        this.idMemories = idMemories;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getFraseColor() {
        return fraseColor;
    }

    public void setFraseColor(String fraseColor) {
        this.fraseColor = fraseColor;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPuntuacionColor() {
        return puntuacionColor;
    }

    public void setPuntuacionColor(String puntuacionColor) {
        this.puntuacionColor = puntuacionColor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenColor() {
        return imagenColor;
    }

    public void setImagenColor(String imagenColor) {
        this.imagenColor = imagenColor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionColor() {
        return descripcionColor;
    }

    public void setDescripcionColor(String descripcionColor) {
        this.descripcionColor = descripcionColor;
    }

    public String getPositivo() {
        return positivo;
    }

    public void setPositivo(String positivo) {
        this.positivo = positivo;
    }

    public String getPositivoColor() {
        return positivoColor;
    }

    public void setPositivoColor(String positivoColor) {
        this.positivoColor = positivoColor;
    }

    public String getNegativo() {
        return negativo;
    }

    public void setNegativo(String negativo) {
        this.negativo = negativo;
    }

    public String getNegativoColor() {
        return negativoColor;
    }

    public void setNegativoColor(String negativoColor) {
        this.negativoColor = negativoColor;
    }

    public String getPaginasLeidas() {
        return paginasLeidas;
    }

    public void setPaginasLeidas(String paginasLeidas) {
        this.paginasLeidas = paginasLeidas;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
