package com.example.arquitecturamvc.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.arquitecturamvc.model.ConexionSQLiteHelper;
import com.example.arquitecturamvc.model.Empresas;
import com.example.arquitecturamvc.model.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpresasController {
    private Context context;
    private ConexionSQLiteHelper dbHelper;

    public ListaEmpresasController(Context context) {
        this.context = context;
        this.dbHelper = new ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1);
    }

    public List<Empresas> obtenerEmpresas() {
        List<Empresas> listaEmpresas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] campos = {Utilidades.CAMPO_ID_EMPRESA, Utilidades.CAMPO_RAZON_SOCIAL, Utilidades.CAMPO_RUT, Utilidades.CAMPO_IMAGEN_EMPRESA};

        Cursor cursor = db.query(Utilidades.TABLA_EMPRESAS, campos, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idEmpresa = cursor.getInt(0);
                String razonSocial = cursor.getString(1);
                String rut = cursor.getString(2);

                byte[] imagenBlob = null;
                if (!cursor.isNull(3)) {
                    imagenBlob = cursor.getBlob(3);
                }
                Bitmap imagenBitmap = null;

                if (imagenBlob != null) {
                    imagenBitmap = BitmapFactory.decodeByteArray(imagenBlob, 0, imagenBlob.length);
                }

                Empresas empresas = new Empresas(razonSocial, rut, idEmpresa, imagenBitmap);
                listaEmpresas.add(empresas);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaEmpresas;
    }

}