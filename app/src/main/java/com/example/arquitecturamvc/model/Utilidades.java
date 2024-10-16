package com.example.arquitecturamvc.model;

public class Utilidades {
    public static final String NOMBRE_BD="MVC.db";
    public static final String TABLA_USUARIOS="usuarios";
    public static final String TABLA_EMPRESAS="empresas";
    public static final String CAMPO_ID_USUARIO="id_usuario";
    public static final String CAMPO_NOMBRE_USUARIO="nombre_usuario";
    public static final String CAMPO_CLAVE="clave_usuario";
    public static final String CAMPO_CORREO="correo_usuario";
    public static final String CAMPO_IMAGEN="imagen_usuario";
    public static final String CAMPO_ID_EMPRESA="id_empresa";
    public static final String CAMPO_RAZON_SOCIAL="razon_social";
    public static final String CAMPO_RUT="rut_empresa";
    public static final String CAMPO_IMAGEN_EMPRESA="imagen_empresa";
    public static final String CREAR_TABLA_EMPRESAS="CREATE TABLE "
            +TABLA_EMPRESAS+"("+CAMPO_ID_EMPRESA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_RAZON_SOCIAL+" TEXT NOT NULL, "+CAMPO_RUT+" TEXT NOT NULL, "+CAMPO_IMAGEN_EMPRESA+" BLOB)";
    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE "
            +TABLA_USUARIOS+"("+CAMPO_ID_USUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_USUARIO+" TEXT NOT NULL, "+CAMPO_CORREO+" TEXT NOT NULL, "
            +CAMPO_CLAVE+" TEXT NOT NULL, "+CAMPO_IMAGEN+" BLOB,"+CAMPO_ID_EMPRESA+" INTEGER NOT NULL, FOREIGN KEY("
            +CAMPO_ID_EMPRESA+") REFERENCES "+TABLA_EMPRESAS+"("
            +CAMPO_ID_EMPRESA+") ON DELETE CASCADE ON UPDATE NO ACTION)";
}
