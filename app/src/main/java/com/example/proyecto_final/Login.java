package com.example.proyecto_final;

import android.content.Intent;
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

public class Login extends AppCompatActivity {

    Button btnRegistro, btnLogin;
    EditText txtUsuario, txtPassword;
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtPassword = (EditText) findViewById(R.id.contraLogin);
        txtUsuario = (EditText) findViewById(R.id.emailLogin);
        btnLogin = (Button) findViewById(R.id.btnIngresar);
        btnRegistro = (Button) findViewById(R.id.btnNuevo);
        rq = Volley.newRequestQueue(this);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                existe();
            }
        });
    }

    public void existe(){
        if (vacio(txtUsuario) || vacio(txtPassword)){

        }else{
            String url2="http://puntosingular.mx/nave/login.php";
            StringRequest request= new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = null;
                            try {
                                jsonObject = jsonArray.getJSONObject(0);
                                if (jsonObject.getString("success") == "true"){
                                    Intent in = new Intent(getApplicationContext(), ActivityPrincipal.class);
                                    startActivity(in);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
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
                    map.put("user", txtUsuario.getText().toString());
                    map.put("pass", txtPassword.getText().toString());
                    return map;
                }
            };
            rq.add(request);
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
