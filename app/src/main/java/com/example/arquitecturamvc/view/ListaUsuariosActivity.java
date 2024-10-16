package com.example.arquitecturamvc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.controller.ListaUsuariosController;
import com.example.arquitecturamvc.model.Usuarios;
import com.example.arquitecturamvc.view.adapters.UsuariosAdapter;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUsuarios;
    private UsuariosAdapter usuariosAdapter;
    private ListaUsuariosController listaUsuariosController;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        recyclerViewUsuarios = findViewById(R.id.recycler_view_usuarios);
        StaggeredGridLayoutManager lmStaggerdHorizontal = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerViewUsuarios.setLayoutManager(lmStaggerdHorizontal);
        recyclerViewUsuarios.setHasFixedSize(true);
        listaUsuariosController = new ListaUsuariosController(this);

        List<Usuarios> listaUsuarios = listaUsuariosController.obtenerUsuarios();

        usuariosAdapter = new UsuariosAdapter(listaUsuarios);

        usuariosAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerViewUsuarios.getChildAdapterPosition(v);
                Usuarios usuarioSeleccionado = listaUsuarios.get(position);
                Toast.makeText(getApplicationContext(),
                        "Seleccionaste: " + usuarioSeleccionado.getNombre(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        recyclerViewUsuarios.setAdapter(usuariosAdapter);
    }

}