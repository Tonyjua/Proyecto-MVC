package com.example.arquitecturamvc.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.arquitecturamvc.R;

public class PrincipalActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    public void registrarEmpresa(View view) {
        intent = new Intent(this, RegistroEmpresaActivity.class);
        startActivity(intent);
    }
    public void registrarUsuario(View view) {
        intent = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(intent);
    }
    public void listaEmpresas(View view) {
        intent = new Intent(this, ListaEmpresasActivity.class);
        startActivity(intent);
    }
    public void listaUsuarios(View view) {
        intent = new Intent(this, ListaUsuariosActivity.class);
        startActivity(intent);
    }
}