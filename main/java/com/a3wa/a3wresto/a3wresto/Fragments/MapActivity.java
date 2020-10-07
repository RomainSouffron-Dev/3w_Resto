package com.a3wa.a3wresto.a3wresto.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.a3wa.a3wresto.a3wresto.Model.Recette;
import com.a3wa.a3wresto.a3wresto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private Recette recette;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // getIntent()

        recette = (Recette) getIntent().getSerializableExtra("recette");

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // initialisation de la variable globale googleMap
        MapActivity.this.googleMap = googleMap;
        // instanciation de latLng (pour les coordonnées de localisation) et du zoom à appliquer
        LatLng latLng = new LatLng(recette.getRestaurant().getLat(), recette.getRestaurant().getLng());
        float zoom = 15;
        // pour afficher la map avec les données instanciées
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        // pour ajouter un marqueur pour le restaurant
        // position() : pour placer le marqueur au bon endroit (en fonction des coordonnées instanciées)
        // title() : pour ajouter un nom
        googleMap.addMarker(new MarkerOptions().position(latLng)   .title(recette.getRestaurant().getNom()));

    }
}
