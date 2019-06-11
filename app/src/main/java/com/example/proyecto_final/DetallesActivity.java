package com.example.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    RequestQueue rq;
    String idEvento, putUbicacion;
    double latitud, longitud;

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
        rq = Volley.newRequestQueue(this);

        morph = (FloatingActionsMenu) findViewById(R.id.menu_fab);
        final View btnMapa, btnParticipar, btnCancelar, btnVerificar;
        btnMapa = findViewById(R.id.btnGps);
        btnParticipar = findViewById(R.id.btnPart);
        btnCancelar = findViewById(R.id.btnDelete);
        btnVerificar = findViewById(R.id.btnCheck);

        btnCancelar.setVisibility(View.GONE);
        btnParticipar.setVisibility(View.GONE);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                existe(btnCancelar, btnParticipar);
                btnVerificar.setVisibility(View.GONE);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asistir();
            }
        });

        Intent i=getIntent();
        Bundle b=i.getExtras();
        final String nomEve =b.getString("nombre_evento");

        obtenerDatosDetalles(nomEve);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("la", latitud);
                i.putExtra("lo", longitud);
                i.putExtra("ubi", putUbicacion);
                startActivity(i);
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
                            idEvento = jsonObject.getString("id");
                            nom.setText(jsonObject.getString("nombreevento"));
                            descrip.setText(jsonObject.getString("descripcion"));
                            lugar.setText(jsonObject.getString("ubicacion"));
                            fecHora.setText(jsonObject.getString("fecha"));
                            cat.setText(jsonObject.getString("tipo"));
                            org.setText(jsonObject.getString("organizacion"));
                            costo.setText(jsonObject.getString("costo"));
                            //desc.setText(jsonObject.getString("descripcion"));
                            latitud = jsonObject.getDouble("latitud");
                            longitud = jsonObject.getDouble("longitud");
                            putUbicacion = jsonObject.getString("ubicacion");
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

    public void existe(final View bCancelar, final View bParticipar){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        final String usuarioGuardados = preferences.getString("nombreGuardado", "");

            String url2="http://puntosingular.mx/nave/getoneasistir.php";
            StringRequest request= new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = jsonArray.getJSONObject(0);
                            if (jsonObject.getString("success") == "true"){
                                Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                bCancelar.setVisibility(View.VISIBLE);
                                bParticipar.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                bCancelar.setVisibility(View.GONE);
                                bParticipar.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("id_evento", idEvento);
                    map.put("nombre_usuario", usuarioGuardados);
                    return map;
                }
            };
            rq.add(request);
    }

    public void asistir(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        final String usuarioGuardados = preferences.getString("nombreGuardado", "");

        String url2="http://puntosingular.mx/nave/agregarasistir.php";
        StringRequest request= new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.getString("success") == "true"){
                            Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_evento", idEvento);
                map.put("nombre_usuario", usuarioGuardados);
                return map;
            }
        };
        rq.add(request);
    }

    public void eliminar(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        final String usuarioGuardados = preferences.getString("nombreGuardado", "");

        String url2="http://puntosingular.mx/nave/eliminarasistir.php";
        StringRequest request= new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.getString("success") == "true"){
                            Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_evento", idEvento);
                map.put("nombre_usuario", usuarioGuardados);
                return map;
            }
        };
        rq.add(request);
    }

}
