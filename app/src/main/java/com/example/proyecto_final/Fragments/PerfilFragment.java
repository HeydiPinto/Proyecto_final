package com.example.proyecto_final.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.ActivityPrincipal;
import com.example.proyecto_final.AsistirEvento;
import com.example.proyecto_final.R;
import com.example.proyecto_final.Registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    TextView nombre,eva;
    EditText fechaN,email,contra;
    Button iniciar;

    RequestQueue requestQueue;
    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_perfil, container, false);


        nombre=(TextView) view.findViewById(R.id.user);
        eva=(TextView) view.findViewById(R.id.eventosA);
        fechaN=(EditText) view.findViewById(R.id.fechanE);
        email=(EditText) view.findViewById(R.id.emailE);
        contra=(EditText) view.findViewById(R.id.contrasenaE);


        SharedPreferences preferences=getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);String d = preferences.getString("nombre","");
        String param = preferences.getString("nombreGuardado","");

        nombre.setText(param);

        encontrarUsuario("http://puntosingular.mx/nave/obtenerInfo.php?nombre_usuario="+nombre.getText());

        eva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), AsistirEvento.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void encontrarUsuario(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i=0; i< response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        contra.setText(jsonObject.getString("password"));
                        fechaN.setText(jsonObject.getString("nacimiento"));
                        email.setText(jsonObject.getString("email"));
                    }catch(JSONException e){

                    }

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

}
