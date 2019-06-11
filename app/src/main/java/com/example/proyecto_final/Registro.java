package com.example.proyecto_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    EditText email,nombre,contrasena,fechaN;
    Button registrar, iniciaSesion;
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        email=(EditText)findViewById(R.id.email);
        nombre=(EditText)findViewById(R.id.nomI);
        contrasena=(EditText)findViewById(R.id.contra);
        fechaN=(EditText)findViewById(R.id.fecha);

        registrar=(Button) findViewById(R.id.txtregistrar);
        iniciaSesion=(Button) findViewById(R.id.btnInicioSesion);
        rq = Volley.newRequestQueue(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });
        iniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
    public void enviarDatos(){

        if (vacio(email) || vacio(nombre) || vacio(contrasena) || vacio(fechaN)){

        }else{
            String url="http://puntosingular.mx/nave/ingresar.php";
            StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = jsonArray.getJSONObject(0);
                            if (jsonObject.getString("success") == "true"){
                                Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }else if (jsonObject.getString("success") == "false"){
                                Toast.makeText(getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(getApplicationContext(), ActivityPrincipal.class);
                                startActivity(in);
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
            }){ protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map= new HashMap<>();
                map.put("email",email.getText().toString());
                map.put("nombre_usuario", nombre.getText().toString());
                map.put("contra", contrasena.getText().toString());
                map.put("fecha_nacimiento", fechaN.getText().toString());
                return map;
            }

            };
            rq.add(request);

            //Guardar
            String nomUsuario = nombre.getText().toString();
            SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor objEditor = preferences.edit();
            objEditor.putString("nombreGuardado", nomUsuario);
            objEditor.commit();
        }
    }

    public boolean vacio(EditText et){
        if (TextUtils.isEmpty(et.getText().toString())){
            et.setError("Campo Vacío!");
            return true;
        }else {
            return false;
        }
    }
}
