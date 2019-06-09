package com.example.proyecto_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    EditText email, nombre, contrasena, fechaN;
    Button ingresar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        email = (EditText) findViewById(R.id.email);
        nombre = (EditText) findViewById(R.id.nombreu);
        contrasena = (EditText) findViewById(R.id.contra);
        fechaN = (EditText) findViewById(R.id.fechan);

        requestQueue = Volley.newRequestQueue(this);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!email.getText().toString().isEmpty())||(!nombre.getText().toString().isEmpty())||(!contrasena.getText().toString().isEmpty())||(!fechaN.getText().toString().isEmpty())){
                    enviarDatos();
                }else {
                    Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void enviarDatos() {
        String url = "http://puntosingular.mx/nave/ingresar.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación Exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>()
                        ;
                map.put("email", nombre.getText().toString());
                map.put("nombre_usuario", nombre.getText().toString());
                map.put("contra", contrasena.getText().toString());
                map.put("fecha_nacimiento", fechaN.getText().toString());
                return map;
            }
        };
        requestQueue.add(request);
    }
}
