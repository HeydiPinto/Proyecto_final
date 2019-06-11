package com.example.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    private final int DURACION_SPLASH = 4000;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){
                    Intent i = new Intent(SplashScreen.this, Login.class);
                    Toast.makeText(getApplicationContext(), "Conexión Estable", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No hay conexión a Internet\n       Prueba más tarde", Toast.LENGTH_SHORT).show();
                }
            finish();}
        }, DURACION_SPLASH);
    }
}
