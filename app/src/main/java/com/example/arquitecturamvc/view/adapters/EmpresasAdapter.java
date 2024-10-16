package com.example.arquitecturamvc.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.model.Empresas;

import java.util.List;

public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.EmpresasHolder> implements View.OnClickListener{
    List<Empresas> listaEmpresas;
    private View.OnClickListener listener;
    public EmpresasAdapter(List <Empresas> listaEmpresas){
        this.listaEmpresas=listaEmpresas;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public EmpresasAdapter.EmpresasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_empresas, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        vista.setOnClickListener(this);
        return new EmpresasAdapter.EmpresasHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresasAdapter.EmpresasHolder holder, int position) {
        holder.txtId.setText(String.valueOf(listaEmpresas.get(position).getId_empresa()));
        holder.txtRazonSocial.setText(listaEmpresas.get(position).getRazonSocial().toString());
        holder.txtRut.setText(listaEmpresas.get(position).getRut().toString());
        if(listaEmpresas.get(position).getImagen_empresa()!=null){
            holder.imagenEmpresa.setImageBitmap(listaEmpresas.get(position).getImagen_empresa());
        }else{
            holder.imagenEmpresa.setImageResource(R.drawable.empresa);
        }

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public class EmpresasHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtRazonSocial, txtRut;
        private ImageView imagenEmpresa;
        public EmpresasHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.txt_id_empresa);
            txtRazonSocial=itemView.findViewById(R.id.txt_razon_social);
            txtRut=itemView.findViewById(R.id.txt_rut);
            imagenEmpresa=itemView.findViewById(R.id.imagen_empresa);
        }
    }
}

