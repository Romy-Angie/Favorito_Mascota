package com.romychsa.favorito_mascota.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.romychsa.favorito_mascota.Activity.PerfilUsuario;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.RestApi.EndPointsAPI;
import com.romychsa.favorito_mascota.RestApi.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.RestApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewPerfilUsuario  {

    private PerfilUsuario perfilUsuario;
    private Context context;
    private ArrayList<Mascota> PERRO;
    private ArrayList<Mascota> GATO;

    public RecyclerViewPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
        ObtenerMediosRecientesGato();
    }

    public void mostrarMascotas() {
        perfilUsuario.inicializarAdaptador(GATO);
        perfilUsuario.generarGridLayout();
    }


    public void ObtenerMediosRecientesPerro() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecentPerfil();
        EndPointsAPI endPointsAPI = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endPointsAPI.getRecentMedia();


        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                PERRO = mascotaResponse.getMascotas();
                mostrarMascotas();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso durante el establecimiento de conexión", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXIÓN", t.toString());

            }
        });
    }


    public void ObtenerMediosRecientesGato() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecentPerfil();
        EndPointsAPI endPointsAPI = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall1 = endPointsAPI.getRecentMedia1();


        mascotaResponseCall1.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                GATO = mascotaResponse.getMascotas();
                mostrarMascotas();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso durante el establecimiento de conexión", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXIÓN", t.toString());

            }
        });
    }


}
