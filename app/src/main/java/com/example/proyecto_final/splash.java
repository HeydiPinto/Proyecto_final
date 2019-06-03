package com.example.proyecto_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends Activity {
    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 4000; // 3 segundos

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(splash.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }


    }
