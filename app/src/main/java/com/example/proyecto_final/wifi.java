package com.example.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class wifi extends AppCompatActivity {
    private final int DURACION_SPLASH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);


        new Handler().postDelayed(new Runnable(){
            public void run(){
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();


                if (networkInfo!=null && networkInfo.isConnected()){
                    Intent intent = new Intent(wifi.this, splash.class);
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
