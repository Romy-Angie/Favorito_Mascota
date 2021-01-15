package com.romychsa.favorito_mascota.Adaptador;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.romychsa.favorito_mascota.Activity.Contacto;
import com.romychsa.favorito_mascota.Activity.MainActivity;
import com.romychsa.favorito_mascota.Notificaciones.EndPoints;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.model.UsuarioResponse;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.R;
import com.romychsa.favorito_mascota.RestApi.deserilizador.MascotaDeserializador;
import com.romychsa.favorito_mascota.RestApi.deserilizador.MascotaDeserializadorPerfil;
import com.romychsa.favorito_mascota.db.ConstructorMascotas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;
    int likesMascota;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas,parent, false);
        return new MascotaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        //Se agarra un arraylist de objetos mascota y de cada uno se saca la foto y nombre.
        final Mascota mascota = mascotas.get(position);
        Picasso.get ()
                .load (mascota.getFoto())
                .placeholder (R.drawable.ic_action_dog)
                .error(R.drawable.ic_action_dog)
                .into(mascotaViewHolder.imgMascota);
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvRanking.setText(String.valueOf(mascota.getRanking()));
        MainActivity.mascotas = mascotas;
        mascotaViewHolder.btnHuesoblanco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(activity, "Me gusta " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                int ranking = mascota.getRanking() +1;
                String urlfoto = mascota.getFoto();
                mascotaViewHolder.tvRanking.setText(String.valueOf(ranking));

                MainActivity mainActivity = new MainActivity();
                //mainActivity.lanzarNotificacionLike(urlfoto);
                mainActivity.toqueAnimal(v);
            }
        });

        //mascotaViewHolder.imgMascota.setImageResource(mascota.getFoto());
        //mascotaViewHolder.tvRanking.setText(String.valueOf(mascota.getRanking()));

       //Darle like a la mascota
      /* mascotaViewHolder.btnHuesoblanco.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(activity, "Me gusta " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

               ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
               constructorMascotas.darLike(mascota);
               mascotaViewHolder.tvRanking.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));

           }
       });*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }


    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMascota;
        private TextView tvNombre;
        private TextView tvRanking;
        private ImageButton btnHuesoblanco;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascota =(ImageView) itemView.findViewById(R.id.imgMascota);
            tvNombre =(TextView)itemView.findViewById(R.id.tvNombre);
            tvRanking =(TextView)itemView.findViewById(R.id.tvRanking);
            btnHuesoblanco =(ImageButton)itemView.findViewById(R.id.btnHuesoblanco);

        }


    }


}

