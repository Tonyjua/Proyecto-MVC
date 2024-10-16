package com.example.arquitecturamvc.model;

import android.graphics.Bitmap;

public class Empresas {
    private String razonSocial;
    private String rut;
    private int id_empresa;
    private Bitmap imagen_empresa;


    public Empresas(String razonSocial, String rut, int id_empresa, Bitmap imagen_empresa) {
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.id_empresa = id_empresa;
        this.imagen_empresa = imagen_empresa;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Bitmap getImagen_empresa() {
        return imagen_empresa;
    }

    public void setImagen_empresa(Bitmap imagen_empresa) {
        this.imagen_empresa = imagen_empresa;
    }



    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
