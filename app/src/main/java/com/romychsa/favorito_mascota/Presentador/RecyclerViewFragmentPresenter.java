package com.romychsa.favorito_mascota.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.romychsa.favorito_mascota.Fragment.IRecyclerViewFragment;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.RestApi.EndPointsAPI;
import com.romychsa.favorito_mascota.RestApi.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.RestApi.model.MascotaResponse;
import com.romychsa.favorito_mascota.db.ConstructorMascotas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragment iRecyclerViewFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas2;
    private ArrayList<Mascota> mascotas1;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragment iRecyclerViewFragment, Context context) {
        this.iRecyclerViewFragment = iRecyclerViewFragment;
        this.context = context;
        //obtenerMascotasBaseDatos();
          ObtenerMediosRecientes();

    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas1 = constructorMascotas.obtenerDatos();
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        iRecyclerViewFragment.inicializarAdaptadorRV(iRecyclerViewFragment.crearAdaptador(mascotas1));
        iRecyclerViewFragment.generarLinearLayoutVertical();

    }

    @Override
    public void ObtenerMediosRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsAPI endPointsAPI = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
     Call<MascotaResponse> mascotaResponseCall = endPointsAPI.getRecentMedia();


        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas1 = mascotaResponse.getMascotas();
                mostrarMascotas();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso durante el establecimiento de conexión", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXIÓN", t.toString());

            }
        });
/*
        Call<MascotaResponse> mascotaResponseCall1 = endPointsAPI.getRecentMedia1();

        mascotaResponseCall1.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas2 = mascotaResponse.getMascotas();
                    for(Mascota m : mascotas2){
                        mascotas1.add(m);
                    }
                mostrarMascotas();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso durante el establecimiento de conexión", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXIÓN", t.toString());
            }
        });

*/
    }
}
