package com.example.proyecto_final;

import android.content.Intent;
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

        nom = (TextView) findViewById(R.id.txtnom);
        /*org = (TextView) findViewById(R.id.txtOrganizador);
        lugar = (TextView) findViewById(R.id.txtLugar);
        fecHora = (TextView) findViewById(R.id.txtFecHor);
        costo = (TextView) findViewById(R.id.txtCosto);
        desc = (TextView) findViewById(R.id.txtDescD);
        cat = (TextView) findViewById(R.id.txtCateD);

        nom.setText("Clausura Cbtis72");
        org.setText("Centro de Bachillerato Tecnologico, Industrial y de Servicios No.72");
        lugar.setText("Domos Cecilio Chi");
        fecHora.setText("04 de Julio, 7:00pm");
        costo.setText("Gratuito");
        desc.setText("Cierre de ciclo escolar");
        cat.setText("Social");*/
        String nombre = getIntent().getStringExtra("nombre_evento");
        //obtenerDatosDetalles(nombre);
        morph = (FloatingActionsMenu) findViewById(R.id.menu_fab);
        final View uno, dos, tres;
        uno = findViewById(R.id.btnGps);
        dos = findViewById(R.id.btnPart);
        tres = findViewById(R.id.btnDelete);
        tres.setVisibility(View.GONE);
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ya formas parte", Toast.LENGTH_SHORT).show();
                dos.setVisibility(View.GONE);
                tres.setVisibility(View.VISIBLE);
            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ya no :v", Toast.LENGTH_SHORT).show();
                tres.setVisibility(View.GONE);
                dos.setVisibility(View.VISIBLE);
            }
        });
    }

    /*private void obtenerDatosDetalles(String nombre){
        String URL = "http://localhost/ws/getoneevent.php?nombre="+nombre;
        JsonArrayRequest request= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
           /* @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for(int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(1);
                        nom.setText(jsonObject.getString("nombreevento"));
                        /*
                        * lugar.setText(jsonObject.getString("ubicacion"));
                        *
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }*/

}
