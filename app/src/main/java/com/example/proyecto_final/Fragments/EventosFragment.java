package com.example.proyecto_final.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.DetallesActivity;
import com.example.proyecto_final.MyAdapter;
import com.example.proyecto_final.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {
        RequestQueue requestQueue;
    FloatingActionButton fab;
   // int tamaño;
    ListView lista;
    /*String [][] datos={
            {"Evento 1", "Ecologico"},
            {"Evento 2", "Social"},
            {"Evento 3", "Ecologico"},
            {"Evento 4", "Ecologico"},
            {"Evento 5", "Ecologico"},
            {"Evento 6", "Cultural"},
            {"Evento 7", "Cultural"},
            {"Evento 8", "Social"}
    };*/
    String [][] datos = new String[10][2];
    String[] opciones = {"Ecológico","Social","Cultural","Deportivo"};
    String cat;

    public EventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_eventos, container, false);

        FloatingActionButton fab = rootView.findViewById(R.id.sendEvent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoteDialog();
            }
        });
        datoslista("http://puntosingular.mx/nave/obtenerInfoLista.php");
        System.out.println("deep arr: " + Arrays.deepToString(datos));
        final MyAdapter adaptador = new MyAdapter(getActivity().getApplicationContext(),datos);

        lista= (ListView) rootView.findViewById(R.id.listView);
        lista.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adaptador
                TextView nom=(TextView) view.findViewById(R.id.nom);
                String textTitleList=nom.getText().toString();
                Intent i = new Intent(getActivity().getApplicationContext(), DetallesActivity.class);
                i.putExtra("nombre_evento", textTitleList);
                startActivity(i);
            }
        });

        return rootView;
    }

    private void showNoteDialog(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View view = layoutInflaterAndroid.inflate(R.layout.event_dialog,  null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(view);

        final EditText nomEvent = view.findViewById(R.id.nom);
        final EditText lugar = view.findViewById(R.id.lugar);
        final Spinner spinner = view.findViewById(R.id.categoria);
        final EditText fecha_hora = view.findViewById(R.id.fecha_hora);
        final EditText desc = view.findViewById(R.id.desc);
        final EditText org = view.findViewById(R.id.organizacion);
        final EditText costo = view.findViewById(R.id.costo);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.lbl_new_note_title));

        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_item, opciones));

        alertDialogBuilderUserInput.setCancelable(false)
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogBox, int id) {
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogBox, int id) {
                dialogBox.cancel();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Mostrar mensaje cuando no se ingresa texto
                if (TextUtils.isEmpty(nomEvent.getText().toString()) || TextUtils.isEmpty(lugar.getText().toString()) || TextUtils.isEmpty(fecha_hora.getText().toString()) || TextUtils.isEmpty(desc.getText().toString()) || TextUtils.isEmpty(costo.getText().toString()) || TextUtils.isEmpty(org.getText().toString())){
                    Toast.makeText(getContext(), "Campo Vacío!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    final String msg = "Nombre: "+nomEvent.getText().toString()
                            +"\nCategoria: "+spinner.getSelectedItem().toString()
                            +"\nOrganización: "+org.getText().toString()
                            +"\nLugar: "+lugar.getText().toString()
                            +"\nFecha y hora: "+fecha_hora.getText().toString()
                            +"\nCosto: "+costo.getText().toString()
                            +"\nDescripción: "+desc.getText().toString();

                    final String[] destinatarios = {"heydi.pinto@cbtis72.edu.mx, litzy.balam@cbtis72.edu.mx, zulema.jimenez@cbtis72.edu.mx6" +
                            ", santosflota8@gmail.com, cuxim2211@gmail.com"};

                    sendEmail(destinatarios, "Deseo crear un evento", msg);
                    alertDialog.dismiss();
                }

            }
        });
    }

    public void sendEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(intent, "Enviar E-Mail"));
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getContext(), "No se encontro aplicación de correo", Toast.LENGTH_LONG).show();
        }
    }

//obtengo los datos de la lista
    private void datoslista(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                 //tamaño=response.length();

                for (int i = 0; i < response.length(); i++) {
                    try{
                        jsonObject=response.getJSONObject(i);
                        for (int j = 0; j < 2; j++){
                            if(j==0){
                                datos[i][0] = jsonObject.getString("nombre_event");
                            }else if (j==1){
                                datos[i][1] = jsonObject.getString("tipo");
                            }
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );

         requestQueue = (RequestQueue) Volley.newRequestQueue(getContext());
         requestQueue.add(jsonArrayRequest);
    }


}
