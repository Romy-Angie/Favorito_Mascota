package com.romychsa.favorito_mascota.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.romychsa.favorito_mascota.Adaptador.MascotaAdaptador;
import com.romychsa.favorito_mascota.Adaptador.PageAdapter;
import com.romychsa.favorito_mascota.Adaptador.PerfilMascotaAdaptador;
import com.romychsa.favorito_mascota.Fragment.PerfilFragment;
import com.romychsa.favorito_mascota.Fragment.RecyclerViewFragment;
import com.romychsa.favorito_mascota.Notificaciones.EndPoints;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.adapter.RestApiAdapter;
import com.romychsa.favorito_mascota.Notificaciones.RestAPI.model.UsuarioResponse;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class MainActivity extends AppCompatActivity {

    public  static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  String username;
    public  String urlfoto;
    public static String CANAL_ID = "canal";
    private static final String TAG = "FIREBASE TOKEN";

    private static String ID_CAT = "-MQT_hZZQ3DNrtQFFBVY";
    private static String ID_DOG = "-MQT_jYRIF2aI-mOaR7m";

    private static String ID_DISPOSITIVO = ID_DOG;

    public static String CAT ="estrella.chsa";
    public static String DOG ="anmy.dev";
    public static String MASCOTA_RECEPTOR= DOG;
    public static String MASCOTA_EMISOR= CAT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);


        toolbar =(Toolbar)findViewById(R.id.toolbar);
        tabLayout =(TabLayout)findViewById(R.id.tabLayout);
        viewPager =(ViewPager)findViewById(R.id.viewPager);
        setUpViewPager();

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    public void lanzarNotificacionLike(String urlfoto){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        enviarRegistroLike(token, urlfoto);
                    }
                });
    }

    public void lanzarNotificacion(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        enviarRegistro(token);
                    }
                });
    }

    private void enviarRegistro(String token){

        Mascota mascota = mascotas.get(0);
        username = mascota.getNombre();
        urlfoto = mascota.getFoto();

        Log.d("TOKEN", token);
        Log.d("USER", username);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPoints endPoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endPoints.registrarUsuario(token, username, 1, urlfoto);


        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("USER_NAME", usuarioResponse.getNombre());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }


    private void enviarRegistroLike(String token, String urlfoto){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPoints endPoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endPoints.registrarUsuario(token, MASCOTA_RECEPTOR, 1, urlfoto);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("USER_NAME", usuarioResponse.getNombre());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CANAL_ID, name, importance);

            //opcional
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void toqueAnimal(View v){
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


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;

    }

     @Override
     public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.mContacto:
                Intent i = new Intent(this, Contacto.class);
                startActivity(i);
                break;
            case R.id.mAcerca:
                Intent j = new Intent(this, AcercaDe.class);
                startActivity(j);
                break;

            case R.id.mConfigCuenta:
                Intent h = new Intent(this, ConfigCuenta.class);
                startActivity(h);
                break;

            case R.id.mNotificacion:
               lanzarNotificacion();

                break;

            case R.id.action_favorito:
                Intent intent = new Intent(MainActivity.this, MascotaFavorita.class);

                /*
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("lista", mascotas);
                intent.putExtras(bundle);

                */
                startActivity(intent);
                break;

        }

         return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
 //Habilita los dos fragment (lista de mascotas y perfil de usuario)
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);

    }


    private void OpenFragment(){
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), agregarFragments());
        pageAdapter.getItem(1);
    }

}
