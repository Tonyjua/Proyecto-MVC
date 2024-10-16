package com.example.arquitecturamvc.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arquitecturamvc.model.ConexionSQLiteHelper;
import com.example.arquitecturamvc.model.Utilidades;

public class LoginController {
    private ConexionSQLiteHelper conexionSQLiteHelper;

    public LoginController(Context context) {
        this.conexionSQLiteHelper = new ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1);
    }

    public boolean validarLogin(String correo, String clave) {
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        String[] parametros = {correo, clave};
        String[] campos = {Utilidades.CAMPO_ID_USUARIO};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos, Utilidades.CAMPO_CORREO + "=? AND " + Utilidades.CAMPO_CLAVE + "=?", parametros, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                cursor.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }
}
