package com.example.arquitecturamvc.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.controller.RegistroEmpresaController;
import com.example.arquitecturamvc.model.ImagenHelper;

public class RegistroEmpresaActivity extends AppCompatActivity {
    private EditText txtRazonSocial, txtRut;
    private ImageView ivImagenEmpresa;
    private ImagenHelper imagenHelper;
    private RegistroEmpresaController registroEmpresaController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empresa);
        txtRazonSocial = findViewById(R.id.txt_razon_social);
        txtRut = findViewById(R.id.etRut);
        ivImagenEmpresa = findViewById(R.id.imagen_empresa);
        registroEmpresaController = new RegistroEmpresaController(this);
        imagenHelper = new ImagenHelper(this, ivImagenEmpresa);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagenHelper.manejarResultado(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ImagenHelper.REQUEST_CAMERA_PERMISSION) {
            imagenHelper.abrirCamara();
        }
    }
    public void selecionarImagen(View view) {
        imagenHelper.verificarPermisosYSeleccionarImagen();
    }
    public void registrar(View view) {
        String razonSocial = txtRazonSocial.getText().toString();
        String rut = txtRut.getText().toString();
        Bitmap imagenSeleccionada = imagenHelper.getImagenSeleccionada();

        if (razonSocial.isEmpty() || rut.isEmpty()) {
            return;
        }
        registroEmpresaController.registrarEmpresa(razonSocial, rut, imagenSeleccionada);
    }
}
