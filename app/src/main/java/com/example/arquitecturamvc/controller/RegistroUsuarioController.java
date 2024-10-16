package com.example.arquitecturamvc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.arquitecturamvc.model.ConexionSQLiteHelper;
import com.example.arquitecturamvc.model.Utilidades;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RegistroUsuarioController {
    private Context context;
    private ConexionSQLiteHelper conn;

    public RegistroUsuarioController(Context context) {
        this.context = context;
        this.conn = new ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1);
    }

    public void registrarUsuario(String nombreUsuario, String correoUsuario, String claveUsuario, Bitmap imagenUsuario, int idEmpresa) {
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_USUARIO, nombreUsuario);
        values.put(Utilidades.CAMPO_CORREO, correoUsuario);
        values.put(Utilidades.CAMPO_CLAVE, claveUsuario);

        if (imagenUsuario != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagenUsuario.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            values.put(Utilidades.CAMPO_IMAGEN, byteArray);

        }
        values.put(Utilidades.CAMPO_ID_EMPRESA, idEmpresa);
        long result = db.insert(Utilidades.TABLA_USUARIOS, null, values);

        if (result == -1) {
            Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public ArrayList<String[]> obtenerEmpresas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<String[]> listaEmpresas = new ArrayList<>();

        String[] campos = {Utilidades.CAMPO_ID_EMPRESA, Utilidades.CAMPO_RAZON_SOCIAL};
        Cursor cursor = db.query(Utilidades.TABLA_EMPRESAS, campos, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String[] empresa = new String[2];
            empresa[0] = cursor.getString(0);
            empresa[1] = cursor.getString(1);
            listaEmpresas.add(empresa);
        }

        cursor.close();
        db.close();

        return listaEmpresas;
    }
}
