package com.romychsa.favorito_mascota.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.romychsa.favorito_mascota.Adaptador.MascotaAdaptador;
import com.romychsa.favorito_mascota.Adaptador.PerfilMascotaAdaptador;
import com.romychsa.favorito_mascota.Pojo.Mascota;
import com.romychsa.favorito_mascota.Presentador.IRecyclerViewFragmentPresenter;
import com.romychsa.favorito_mascota.Presentador.IRecyclerViewFragmentPresenterPerfil;
import com.romychsa.favorito_mascota.Presentador.RecyclerViewFragmentPresenter;
import com.romychsa.favorito_mascota.Presentador.RecyclerViewPerfilFragmentPresenter;
import com.romychsa.favorito_mascota.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PerfilFragment extends Fragment implements IPerfilFragment{

    public static ArrayList<Mascota> mascotasperfil;
    private RecyclerView listafotosMiMascotas;
    private IRecyclerViewFragmentPresenterPerfil presenter;
    private String nombreUsuario;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            nombreUsuario = getArguments().getString("nombre", "");
        }
        else {
            nombreUsuario = "";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        CircularImageView circularImageView = (CircularImageView) v.findViewById(R.id.circularImageView);
// Set Color
        circularImageView.setCircleColor(Color.BLACK);
// or with gradient
        circularImageView.setBorderColorStart(Color.rgb(161, 24, 109));
        circularImageView.setBorderColorEnd(Color.rgb(244, 76, 181));
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);
// Add Shadow with default param
        circularImageView.setShadowRadius(30f);
        circularImageView.setShadowColor(Color.BLACK);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.BOTTOM);

        listafotosMiMascotas = (RecyclerView)v.findViewById(R.id.rvMiMascota);
        //GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
       // glm.setOrientation(LinearLayoutManager.VERTICAL);
        presenter = new RecyclerViewPerfilFragmentPresenter(this, getContext());

        //listafotosMiMascotas.setLayoutManager(glm);
        //inicializarListaContactos();
        //inicializarAdaptador();
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listafotosMiMascotas.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3 );
        listafotosMiMascotas.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PerfilMascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        PerfilMascotaAdaptador adaptador = new PerfilMascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilMascotaAdaptador adaptador) {
        listafotosMiMascotas.setAdapter(adaptador);
    }



/*
    public PerfilMascotaAdaptador adaptador;
    public void inicializarAdaptador(){
        adaptador = new PerfilMascotaAdaptador(mascotas, getActivity());
        // MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        listafotosMiMascotas.setAdapter(adaptador);
    }
*/

/*
    public void inicializarListaContactos(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(1,"Lucho",R.drawable.pet1, 9));
        mascotas.add(new Mascota(1,"Lucho",R.mipmap.l1, 2));
        mascotas.add(new Mascota(1,"Lucho",R.mipmap.l2, 2));
        mascotas.add(new Mascota(1,"Lucho",R.mipmap.l3, 5));
        mascotas.add(new Mascota(1, "Lucho",R.mipmap.l4,5 ));
        mascotas.add(new Mascota(1, "Lucho",R.mipmap.l5, 2));
        mascotas.add(new Mascota(1,"Lucho",R.mipmap.l6, 4));
        mascotas.add(new Mascota(1, "Lucho",R.mipmap.l7, 5));
        mascotas.add(new Mascota(1, "Lucho",R.mipmap.l8, 5));
    }
*/
}
