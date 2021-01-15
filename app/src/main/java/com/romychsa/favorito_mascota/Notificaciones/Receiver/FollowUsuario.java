package com.romychsa.favorito_mascota.Notificaciones.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.romychsa.favorito_mascota.Notificaciones.EndPoints;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUsuario extends BroadcastReceiver {

    private static String ID_CAT = "-MQT_hZZQ3DNrtQFFBVY";
    private static String ID_DOG = "-MQT_jYRIF2aI-mOaR7m";

    private static String ID_DISPOSITIVO = ID_CAT;

    public static String CAT ="estrella.chsa";
    public static String DOG ="anmy.dev";
    public static String MASCOTA_RECEPTOR= DOG;
    public static String MASCOTA_EMISOR= CAT;

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_FOLLOW = "SEGUIR_USUARIO";

        String accion = intent.getAction();

        if(ACTION_FOLLOW.equals(accion)){
            followUsuario();
            Toast.makeText(context, "Estas siguiendo a "+ MASCOTA_EMISOR , Toast.LENGTH_SHORT).show();
        }
    }

    public void followUsuario(){
        Log.d("FOLLOW_MASCOTA", "true");
        final UsuarioResponse usuarioResponse = new UsuarioResponse(ID_DISPOSITIVO, "123", MASCOTA_EMISOR);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPoints endPoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endPoints.followMascota(usuarioResponse.getId(), MASCOTA_RECEPTOR);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                Log.d("ANIMAL_FIREBASE", usuarioResponse1.getNombre());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
