package com.example.proyecto_final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class splash extends Activity {
    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 4000; // 3 segundos
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();


                if (networkInfo!=null && networkInfo.isConnected()){
                    Intent intent = new Intent(splash.this, ActivityPrincipal.class);
                    Toast.makeText(getApplicationContext(),"Conectado.",Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),"No hay conexión a internet, prueba más tarde.",Toast.LENGTH_SHORT).show();


                }


                finish();
            };
        }, DURACION_SPLASH);
    }


    }

