package com.example.arquitecturamvc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.arquitecturamvc.model.ConexionSQLiteHelper;
import com.example.arquitecturamvc.model.Utilidades;

import java.io.ByteArrayOutputStream;

public class RegistroEmpresaController {
    private Context context;

    public RegistroEmpresaController(Context context) {
        this.context = context;
    }

    public boolean registrarEmpresa(String razonSocial, String rut, Bitmap imagenEmpresa) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_RAZON_SOCIAL, razonSocial);
        values.put(Utilidades.CAMPO_RUT, rut);

        if (imagenEmpresa != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagenEmpresa.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            values.put(Utilidades.CAMPO_IMAGEN_EMPRESA, byteArray);
        }

        long resultado = db.insert(Utilidades.TABLA_EMPRESAS, null, values);
        db.close();

        if (resultado == -1) {
            Toast.makeText(context, "Error al registrar empresa", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Empresa registrada correctamente", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}