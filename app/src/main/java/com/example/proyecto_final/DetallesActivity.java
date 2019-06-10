package com.example.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetallesActivity extends AppCompatActivity {

    private FloatingActionsMenu morph;
    TextView nom, org, lugar, fecHora, costo, desc, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        nom = (TextView) findViewById(R.id.txtNom);
        org = (TextView) findViewById(R.id.txtOrg);
        lugar = (TextView) findViewById(R.id.txtLugar);
        fecHora = (TextView) findViewById(R.id.txtFecHor);
        costo = (TextView) findViewById(R.id.txtCosto);
        desc = (TextView) findViewById(R.id.txtDescD);
        cat = (TextView) findViewById(R.id.txtCateD);



        morph = (FloatingActionsMenu) findViewById(R.id.menu_fab);
        final View btnUbicacion, btnParticipar, btnCancelacion;
        btnUbicacion = findViewById(R.id.btnGps);
        btnParticipar = findViewById(R.id.btnPart);
        btnCancelacion = findViewById(R.id.btnDelete);
        btnCancelacion.setVisibility(View.GONE);

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);

                SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                String datosGuardados = preferences.getString("nombreGuardado", "");

                if (datosGuardados.length() == 0){
                    Toast.makeText(getApplicationContext(), "Sin valor", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Valor: "+datosGuardados, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ya formas parte", Toast.LENGTH_SHORT).show();
                btnParticipar.setVisibility(View.GONE);
                btnCancelacion.setVisibility(View.VISIBLE);
            }
        });
        btnCancelacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ya no :v", Toast.LENGTH_SHORT).show();
                btnCancelacion.setVisibility(View.GONE);
                btnParticipar.setVisibility(View.VISIBLE);
            }
        });
    }

    /*private void obtenerDatosDetalles(String nombre){
        String URL = "http://localhost/ws/getoneevent.php?nombre="+nombre;
        JsonArrayRequest request= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
           @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for(int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(1);
                        nom.setText(jsonObject.getString("nombreevento"));
                        lugar.setText(jsonObject.getString("ubicacion"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }*/
}
