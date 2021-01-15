package com.romychsa.favorito_mascota.Notificaciones;

import com.romychsa.favorito_mascota.Notificaciones.RestAPI.model.UsuarioResponse;
import com.romychsa.favorito_mascota.RestApi.ConstantesRestApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndPoints {
    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("token") String token, @Field("nombre") String nombre, @Field("ranking") int ranking, @Field("urlfoto") String urlfoto);


    @GET(ConstantesRestAPI.KEY_TOQUE_MASCOTA)
    Call<UsuarioResponse> toqueAMascota(@Path("id") String id, @Path("nombre") String nombre);

    @GET(ConstantesRestAPI.KEY_FOLLOW_MASCOTA)
    Call<UsuarioResponse> followMascota(@Path("id") String id, @Path("nombre") String nombre);
}
