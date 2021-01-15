package com.romychsa.favorito_mascota.Adaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilUsuarioAdaptador extends RecyclerView.Adapter<PerfilUsuarioAdaptador.PerfilUsuarioViewHolder> {
    ArrayList<Mascota> mascotas;


    public PerfilUsuarioAdaptador(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;

    }

    @NonNull
    @Override
    public PerfilUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_usuario, parent, false);
        return new PerfilUsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilUsuarioViewHolder perfilUsuarioViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.get ()
                .load (mascota.getFoto())
                .placeholder (R.drawable.ic_action_dog)
                .error(R.drawable.ic_action_dog)
                .into(perfilUsuarioViewHolder.imgMascota);

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }



    public static class PerfilUsuarioViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgMascota;

        public PerfilUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascota = (ImageView)itemView.findViewById(R.id.imgMascota);
        }
    }
}
