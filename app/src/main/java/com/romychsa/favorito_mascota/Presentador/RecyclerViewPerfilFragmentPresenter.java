package com.romychsa.favorito_mascota.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.romychsa.favorito_mascota.Fragment.IPerfilFragment;
import com.romychsa.favorito_mascota.Fragment.PerfilFragment;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.RestApi.EndPointsAPI;
import com.romychsa.favorito_mascota.RestApi.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.RestApi.model.MascotaResponse;
import com.romychsa.favorito_mascota.db.ConstructorMascotas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewPerfilFragmentPresenter implements IRecyclerViewFragmentPresenterPerfil {
    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> PERRO;
    private ArrayList<Mascota> GATO;


    public RecyclerViewPerfilFragmentPresenter(IPerfilFragment iPerfilFragment, Context context) {
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;
       //ObtenerMediosRecientesGato();
       ObtenerMediosRecientesPerro();

    }


    @Override
    public void mostrarMascotas() {
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(PERRO));
        iPerfilFragment.generarGridLayout();
    }

    @Override
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

    @Override
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
