package com.example.proyecto_final;


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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventosFragment extends Fragment {

    FloatingActionButton fab;
    ListView lista;
    String [][] datos={
            {"Evento 1", "Ecologico"},
            {"Evento 2", "Social"},
            {"Evento 3", "Ecologico"},
            {"Evento 4", "Ecologico"},
            {"Evento 5", "Ecologico"},
            {"Evento 6", "Cultural"},
            {"Evento 7", "Cultural"},
            {"Evento 8", "Social"}
    };

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

        lista= (ListView) rootView.findViewById(R.id.listView);

        lista.setAdapter(new MyAdapter(getActivity().getApplicationContext(),datos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"Clicked: "+position, Toast.LENGTH_SHORT).show();
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
        final EditText categoria = view.findViewById(R.id.categoria);
        final EditText fecha_hora = view.findViewById(R.id.fecha_hora);
        final EditText desc = view.findViewById(R.id.desc);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.lbl_new_note_title));

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
                if (TextUtils.isEmpty(nomEvent.getText().toString()) || TextUtils.isEmpty(lugar.getText().toString()) || TextUtils.isEmpty(categoria.getText().toString()) || TextUtils.isEmpty(fecha_hora.getText().toString()) || TextUtils.isEmpty(desc.getText().toString())){
                    Toast.makeText(getContext(), "Campo Vacío!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    final String msg = "Nombre: "+nomEvent.getText().toString()
                            +"\nCategoria: "+categoria.getText().toString()
                            +"\nLugar: "+lugar.getText().toString()
                            +"\nFecha y hora: "+fecha_hora.getText().toString()
                            +"\nDescripción: "+desc.getText().toString();

                    final String[] destinatarios = {"santosflota8@gmail.com"};

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


}
