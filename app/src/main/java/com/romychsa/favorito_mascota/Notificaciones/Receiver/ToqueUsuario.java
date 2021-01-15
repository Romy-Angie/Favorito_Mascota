package com.romychsa.favorito_mascota.Notificaciones.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.romychsa.favorito_mascota.Activity.ConfigCuenta;
import com.romychsa.favorito_mascota.Activity.MainActivity;
import com.romychsa.favorito_mascota.Notificaciones.EndPoints;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToqueUsuario extends BroadcastReceiver {
    private static String ID_CAT = "-MQT_hZZQ3DNrtQFFBVY";
    private static String ID_DOG = "-MQT_jYRIF2aI-mOaR7m";

    private static String ID_DISPOSITIVO = ID_CAT;

    public static String CAT ="estrella.chsa";
    public static String DOG ="anmy.dev";
    public static String MASCOTA_RECEPTOR= CAT;
    public static String MASCOTA_EMISOR= DOG;

    @Override
    public void onReceive(Context context, Intent intent) {
            String ACTION_KEY = "TOQUE_ANIMAL";
            String ACTION_VER_MIPERFIL = "VER_MI_PERFIL";
            String ACTION_FOLLOW = "SEGUIR_USUARIO";
            String ACTION_VER_PERFILUSUARIO = "PERFIL_USUARIO";

            String accion = intent.getAction();

            if(ACTION_KEY.equals(accion)){
                toqueAnimal();
                Toast.makeText(context, "Diste un toque a "+ MASCOTA_RECEPTOR, Toast.LENGTH_SHORT).show();
            }

    }



    public void toqueAnimal(){
        Log.d("TOQUE_MASCOTA", "true");
        final UsuarioResponse usuarioResponse = new UsuarioResponse(ID_DISPOSITIVO, "123", MASCOTA_RECEPTOR);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPoints endPoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endPoints.toqueAMascota(usuarioResponse.getId(), MASCOTA_EMISOR);
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
