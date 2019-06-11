package com.example.proyecto_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsistirEvento extends AppCompatActivity {
    public static String TAG = DetallesActivity.class.getSimpleName();
    ListView listView;
    private List<String> date;
    String nombre;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistir_evento);

        listView=(ListView)findViewById(R.id.listView1);

        date = new ArrayList<String>();

        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);String d = preferences.getString("nombre","");
        nombre = preferences.getString("nombreGuardado","");

        datoslista("http://puntosingular.mx/nave/getlisteventbyuser.php");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date);

        listView.setAdapter(adapter);


    }

    private void datoslista(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                //tamaño=response.length();

                for (int i = 0; i < response.length(); i++) {
                    try{
                        jsonObject=response.getJSONObject(i);

                        date.add(jsonObject.getString("nombre_event"));

                    }
                    catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }){@Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<>();
            map.put("nombre", nombre);
            return map;
        }
        };

        requestQueue = (RequestQueue) Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }



}
