package com.example.arquitecturamvc.model;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class ImagenHelper {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_GALLERY_PICK = 2;
    public static final int REQUEST_CAMERA_PERMISSION = 100;

    private Activity activity;
    private ImageView imageView;
    private Bitmap imagenSeleccionada;

    public ImagenHelper(Activity activity, ImageView imageView) {
        this.activity = activity;
        this.imageView = imageView;
    }

    public void verificarPermisosYSeleccionarImagen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
            } else {
                mostrarOpcionesImagen();
            }
        } else {
            mostrarOpcionesImagen();
        }
    }

    private void mostrarOpcionesImagen() {
        new AlertDialog.Builder(activity)
                .setTitle("Seleccionar Imagen")
                .setItems(new CharSequence[]{"Tomar Foto", "Seleccionar de Galería"}, (dialog, which) -> {
                    if (which == 0) {
                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                        } else {
                            abrirCamara();
                        }
                    } else {
                        abrirGaleria();
                    }
                }).show();
    }

    public void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void abrirGaleria() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(pickPhoto, REQUEST_GALLERY_PICK);
    }

    public void manejarResultado(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            try {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    imagenSeleccionada = (Bitmap) data.getExtras().get("data");
                } else if (requestCode == REQUEST_GALLERY_PICK) {
                    imagenSeleccionada = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), data.getData());
                }
                imageView.setImageBitmap(imagenSeleccionada);
            } catch (IOException e) {
                mostrarMensajeError("Error al cargar la imagen.");
            }
        }
    }

    public Bitmap getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    private void mostrarMensajeError(String mensaje) {
        new AlertDialog.Builder(activity)
                .setTitle("Error")
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }
}
