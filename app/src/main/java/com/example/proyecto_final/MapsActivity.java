package com.example.proyecto_final;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double latitud, longitud;
        String nom;
        mMap = googleMap;

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        latitud = b.getDouble("la");
        longitud = b.getDouble("lo");
        nom = b.getString("ubi");


        // Add a marker in Sydney and move the camera
        LatLng cbtis = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(cbtis).title("Marcador en "+nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cbtis, 14));
    }
}
