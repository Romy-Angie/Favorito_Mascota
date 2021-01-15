package com.romychsa.favorito_mascota.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.romychsa.favorito_mascota.Adaptador.PerfilUsuarioAdaptador;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.Presentador.RecyclerViewPerfilUsuario;
import com.romychsa.favorito_mascota.R;

import java.util.ArrayList;


public class PerfilUsuario extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView perfilUsuario;
    //private IRecyclerViewFragmentPresenterPerfil presenter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);


        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.circularImageView);
        circularImageView.setCircleColor(Color.BLACK);
        circularImageView.setBorderColorStart(Color.rgb(161, 24, 109));
        circularImageView.setBorderColorEnd(Color.rgb(244, 76, 181));
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);
        circularImageView.setShadowRadius(30f);
        circularImageView.setShadowColor(Color.BLACK);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.BOTTOM);

        perfilUsuario = (RecyclerView)findViewById(R.id.rvMiMascota);
       // presenter = new RecyclerViewPerfilUsuario(this);
        RecyclerViewPerfilUsuario presenter = new RecyclerViewPerfilUsuario(this);

    }


    public void inicializarAdaptador(ArrayList<Mascota> mascotas){
        PerfilUsuarioAdaptador adaptador = new PerfilUsuarioAdaptador(mascotas);
        perfilUsuario.setAdapter(adaptador);
    }


    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        perfilUsuario.setLayoutManager(glm);
    }




}