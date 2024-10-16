package com.example.arquitecturamvc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_EMPRESAS);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        insertarEmpresaAdministrador(db);
        insertarUsuarioAdministrador(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.CREAR_TABLA_EMPRESAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.CREAR_TABLA_USUARIOS);
        onCreate(db);
    }
    private void insertarEmpresaAdministrador(SQLiteDatabase db) {
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CAMPO_RAZON_SOCIAL, "Administradores");
        valores.put(Utilidades.CAMPO_RUT, "1-9");

        db.insert(Utilidades.TABLA_EMPRESAS, null, valores);
    }
    private void insertarUsuarioAdministrador(SQLiteDatabase db) {
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CAMPO_NOMBRE_USUARIO, "Administrador");
        valores.put(Utilidades.CAMPO_CORREO, "admin@gmail.com");
        valores.put(Utilidades.CAMPO_CLAVE, "admin123");
        valores.put(Utilidades.CAMPO_ID_EMPRESA, 1);

        db.insert(Utilidades.TABLA_USUARIOS, null, valores);
    }
}
