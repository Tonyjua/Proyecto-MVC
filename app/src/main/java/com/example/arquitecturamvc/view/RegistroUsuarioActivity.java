package com.example.arquitecturamvc.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.controller.RegistroUsuarioController;
import com.example.arquitecturamvc.model.ImagenHelper;

import java.util.ArrayList;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText txtNombreUsuario, txtCorreoUsuario, txtClaveUsuario;
    private Spinner spinnerEmpresas;
    private ImageView ivImagenUsuario;
    private ImagenHelper imagenHelper;
    private RegistroUsuarioController registroUsuarioController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        registroUsuarioController = new RegistroUsuarioController(this);
        txtNombreUsuario = findViewById(R.id.txt_nombre_usuario);
        txtCorreoUsuario = findViewById(R.id.txt_correo_usuario);
        txtClaveUsuario = findViewById(R.id.txt_clave_usuario);
        spinnerEmpresas = findViewById(R.id.spinner_empresas);
        ivImagenUsuario = findViewById(R.id.imagen_usuario);
        imagenHelper = new ImagenHelper(this, ivImagenUsuario);
        cargarSpinnerEmpresas();
    }

    private void cargarSpinnerEmpresas() {
        ArrayList<String[]> listaEmpresas = registroUsuarioController.obtenerEmpresas();
        ArrayList<String> empresasMostrar = new ArrayList<>();

        for (String[] empresa : listaEmpresas) {
            empresasMostrar.add(empresa[1]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empresasMostrar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresas.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagenHelper.manejarResultado(requestCode, resultCode, data);
    }

    private void registrarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().toString();
        String correoUsuario = txtCorreoUsuario.getText().toString();
        String claveUsuario = txtClaveUsuario.getText().toString();
        Bitmap imagenUsuario = imagenHelper.getImagenSeleccionada();
        int idEmpresaSeleccionada = obtenerIdEmpresaSeleccionada();
        if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || claveUsuario.isEmpty()) {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imagenUsuario == null) {
            Toast.makeText(this, "Debe seleccionar una imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        registroUsuarioController.registrarUsuario(nombreUsuario, correoUsuario, claveUsuario, imagenUsuario, idEmpresaSeleccionada);
    }

    private int obtenerIdEmpresaSeleccionada() {
        int posicion = spinnerEmpresas.getSelectedItemPosition();
        ArrayList<String[]> listaEmpresas = registroUsuarioController.obtenerEmpresas();
        return Integer.parseInt(listaEmpresas.get(posicion)[0]); // Devuelve el ID de la empresa seleccionada
    }
    public void registrar(View view) {
        registrarUsuario();
    }

    public void seleccionarImagen(View view) {
        imagenHelper.verificarPermisosYSeleccionarImagen();
    }
}
