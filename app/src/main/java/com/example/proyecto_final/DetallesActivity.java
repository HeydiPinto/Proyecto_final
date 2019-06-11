package com.example.proyecto_final;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetallesActivity extends AppCompatActivity {
    public static String TAG = DetallesActivity.class.getSimpleName();
        RequestQueue requestQueue;
    private FloatingActionsMenu morph;
    TextView nom, org, lugar, fecHora, costo, descrip, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        nom = (TextView) findViewById(R.id.txtNom);
        org = (TextView) findViewById(R.id.txtOrg);
        lugar = (TextView) findViewById(R.id.txtLugar);
        fecHora = (TextView) findViewById(R.id.txtFecHor);
        costo = (TextView) findViewById(R.id.txtCosto);
        descrip = (TextView) findViewById(R.id.txtDescrip);
        cat = (TextView) findViewById(R.id.txtCateD);

        morph = (FloatingActionsMenu) findViewById(R.id.menu_fab);
        final View uno, dos, tres;
        uno = findViewById(R.id.btnGps);
        dos = findViewById(R.id.btnPart);
        tres = findViewById(R.id.btnDelete);
        tres.setVisibility(View.GONE);
        Intent i=getIntent();
        Bundle b=i.getExtras();
            String nomEve =b.getString("nombre_evento");

        obtenerDatosDetalles(nomEve);
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

    private void obtenerDatosDetalles(final String nombre){
        String URL = "http://puntosingular.mx/nave/getoneevent.php";
        /*StringRequest request= new StringRequest(Request.Method.POST, URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nom.setText(jsonObject.getString("nombreevento"));
                        lugar.setText(jsonObject.getString("ubicacion"));
                        fecHora.setText(jsonObject.getString("fecha"));
                        cat.setText(jsonObject.getString("tipo"));
                        org.setText(jsonObject.getString("organizacion"));
                        costo.setText(jsonObject.getString("costo"));
                        desc.setText(jsonObject.getString("descripcion"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nombre", nombre);
                return map;
            }
        };*/
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            jsonObject = jsonArray.getJSONObject(i);
                            nom.setText(jsonObject.getString("nombreevento"));
                            descrip.setText(jsonObject.getString("descripcion"));
                            lugar.setText(jsonObject.getString("ubicacion"));
                            fecHora.setText(jsonObject.getString("fecha"));
                            cat.setText(jsonObject.getString("tipo"));
                            org.setText(jsonObject.getString("organizacion"));
                            costo.setText(jsonObject.getString("costo"));
                            //desc.setText(jsonObject.getString("descripcion"));
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }){

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("nombre", nombre);
//                return map;
//            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nombre", nombre);
                return map;
            }
        };
        //requestQueue.add(request);
        requestQueue = (RequestQueue) Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
