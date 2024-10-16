package com.example.arquitecturamvc.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.arquitecturamvc.model.ConexionSQLiteHelper;
import com.example.arquitecturamvc.model.Usuarios;
import com.example.arquitecturamvc.model.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosController {
    private Context context;
    private ConexionSQLiteHelper dbHelper;

    public ListaUsuariosController(Context context) {
        this.context = context;
        this.dbHelper = new ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1);
    }

    public List<Usuarios> obtenerUsuarios() {
        List<Usuarios> listaUsuarios = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] campos = {Utilidades.CAMPO_ID_USUARIO, Utilidades.CAMPO_NOMBRE_USUARIO, Utilidades.CAMPO_CORREO, Utilidades.CAMPO_IMAGEN};

        Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idUsuario = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String correo = cursor.getString(2);

                byte[] imagenBlob = null;
                if (!cursor.isNull(3)) {
                    imagenBlob = cursor.getBlob(3);
                }
                Bitmap imagenBitmap = null;

                if (imagenBlob != null) {
                    imagenBitmap = BitmapFactory.decodeByteArray(imagenBlob, 0, imagenBlob.length);
                }

                Usuarios usuario = new Usuarios(nombre, correo, null, idUsuario, imagenBitmap);
                listaUsuarios.add(usuario);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaUsuarios;
    }
}
