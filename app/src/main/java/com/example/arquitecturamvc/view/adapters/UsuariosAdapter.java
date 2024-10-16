package com.example.arquitecturamvc.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arquitecturamvc.R;
import com.example.arquitecturamvc.model.Usuarios;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> implements View.OnClickListener{
    List<Usuarios> listaUsuarios;
    private View.OnClickListener listener;
    public UsuariosAdapter(List <Usuarios> listaUsuarios){
        this.listaUsuarios=listaUsuarios;
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
    public UsuariosAdapter.UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        vista.setOnClickListener(this);
        return new UsuariosAdapter.UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosAdapter.UsuariosHolder holder, int position) {
        holder.txtId.setText(String.valueOf(listaUsuarios.get(position).getId_usuario()));
        holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.txtCorreo.setText(listaUsuarios.get(position).getCorreo().toString());
        if(listaUsuarios.get(position).getImagen()!=null){
            holder.imagenUsuario.setImageBitmap(listaUsuarios.get(position).getImagen());
        }else{
            holder.imagenUsuario.setImageResource(R.drawable.avatar);
        }

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtNombre, txtCorreo;
        private ImageView imagenUsuario;
        public UsuariosHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.txt_id_usuario);
            txtNombre=itemView.findViewById(R.id.txt_nombre_usuario);
            txtCorreo=itemView.findViewById(R.id.txt_correo_usuario);
            imagenUsuario=itemView.findViewById(R.id.imagen_usuario);
        }
    }
}
