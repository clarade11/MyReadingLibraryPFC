package com.example.proyecto.clasesObjeto;

import android.media.Image;

import java.util.Objects;

public class Libro {

    Integer fotoID;
    String titulo;
    String autor;
    String codigoBarras;
    String editorial;
    Double precio;
    String descripcion;
    Boolean comprado;
    Double puntuacion;
    String tienda;

    public Libro(Integer fotoID,String titulo,String autor,String codigoBarras,String editorial,Double precio,String descripcion,Boolean comprado,Double puntuacion,String tienda ){
        this.fotoID=fotoID;
        this.titulo=titulo;
        this.autor=autor;
        this.codigoBarras=codigoBarras;
        this.editorial=editorial;
        this.precio=precio;
        this.descripcion=descripcion;
        this.comprado=comprado;
        this.puntuacion=puntuacion;
        this.tienda=tienda;
    }

    public Integer getFotoID(){
        return fotoID;
    }

    public void setFotoID(Integer fotoID) {
        this.fotoID = fotoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Boolean getComprado() {
        return comprado;
    }

    public Double getPrecio() {
        return precio;
    }


    public String getCodigoBarras() {
        return codigoBarras;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTienda() {
        return tienda;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }


    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(fotoID, libro.fotoID) && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor) && Objects.equals(codigoBarras, libro.codigoBarras) && Objects.equals(editorial, libro.editorial) && Objects.equals(precio, libro.precio) && Objects.equals(descripcion, libro.descripcion) && Objects.equals(comprado, libro.comprado)  && Objects.equals(puntuacion, libro.puntuacion) && Objects.equals(tienda, libro.tienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fotoID, titulo, autor, codigoBarras, editorial, precio, descripcion, comprado, puntuacion, tienda);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "fotoID=" + fotoID +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", editorial='" + editorial + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", comprado=" + comprado +
                ", puntuacion=" + puntuacion +
                ", tienda='" + tienda + '\'' +
                '}';
    }
}


