package com.example.arquitecturamvc.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.controller.LoginController;

public class MainActivity extends AppCompatActivity {
    private EditText etCorreo, etClave;
    private LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCorreo = findViewById(R.id.etCorreo);
        etClave = findViewById(R.id.etClave);
        loginController = new LoginController(this);
    }
    public void Ingresar(View view) {
        String correo = etCorreo.getText().toString();
        String clave = etClave.getText().toString();

        if (correo.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean resultado = loginController.validarLogin(correo, clave);
        if (resultado) {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Correo o clave incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}