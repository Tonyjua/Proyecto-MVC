package com.example.arquitecturamvc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.controller.ListaEmpresasController;
import com.example.arquitecturamvc.model.Empresas;
import com.example.arquitecturamvc.view.adapters.EmpresasAdapter;

import java.util.List;

public class ListaEmpresasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmpresas;
    private EmpresasAdapter empresasAdapter;
    private ListaEmpresasController listaEmpresasController;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresas);
        recyclerViewEmpresas = findViewById(R.id.recycler_view_empresa);
        StaggeredGridLayoutManager lmStaggerdHorizontal = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerViewEmpresas.setLayoutManager(lmStaggerdHorizontal);
        recyclerViewEmpresas.setHasFixedSize(true);
        listaEmpresasController = new ListaEmpresasController(this);
        List<Empresas> listaEmpresas = listaEmpresasController.obtenerEmpresas();
        empresasAdapter = new EmpresasAdapter(listaEmpresas);
        empresasAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerViewEmpresas.getChildAdapterPosition(v);
                Empresas empresaSeleccionado = listaEmpresas.get(position);
                Toast.makeText(getApplicationContext(),
                        "Seleccionaste: " + empresaSeleccionado.getRazonSocial(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewEmpresas.setAdapter(empresasAdapter);
    }

}