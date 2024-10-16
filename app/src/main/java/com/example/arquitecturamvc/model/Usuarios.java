package com.example.arquitecturamvc.model;

import android.graphics.Bitmap;

public class Usuarios {
    public Usuarios(String nombre, String correo, String clave, int id_usuario, Bitmap imagen) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.id_usuario = id_usuario;
        this.imagen = imagen;
    }
    private String nombre;
    private String correo;
    private String clave;
    private int id_usuario;
    private Bitmap imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

}
