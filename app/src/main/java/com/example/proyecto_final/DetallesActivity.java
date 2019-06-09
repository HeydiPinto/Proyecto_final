package com.example.proyecto_final;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesActivity extends AppCompatActivity {

    Button btnGps, btnPart;
    TextView nom, org, lugar, fecHora, costo, desc, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        nom = (TextView) findViewById(R.id.txtNom);
        org = (TextView) findViewById(R.id.txtOrganizador);
        lugar = (TextView) findViewById(R.id.txtLugar);
        fecHora = (TextView) findViewById(R.id.txtFecHor);
        costo = (TextView) findViewById(R.id.txtCosto);
        desc = (TextView) findViewById(R.id.txtDescD);
        cat = (TextView) findViewById(R.id.txtCateD);
        btnPart = (Button) findViewById(R.id.btnPart);
        //btnGps = (Button) findViewById(R.id.btnGps);

        nom.setText("Clausura Cbtis72");
        org.setText("Centro de Bachillerato Tecnologico, Industrial y de Servicios No.72");
        lugar.setText("Cbtis72");
        fecHora.setText("04 de Julio, 7:00pm");
        costo.setText("Gratuito");
        desc.setText("Cierre de ciclo escolar");
        cat.setText("Social");

        btnPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ya eres Parte!", Toast.LENGTH_SHORT).show();
            }
        });

        /*btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "GPS", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
