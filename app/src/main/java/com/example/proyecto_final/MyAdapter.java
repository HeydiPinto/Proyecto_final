package com.example.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private static LayoutInflater inflater= null;
    Context contexto;
    String[][] datos;

    public MyAdapter(Context conexto, String[][]datos){
        this.contexto= conexto;
        this.datos=datos;
        inflater=(LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent){
        final View vista=inflater.inflate(R.layout.list_item,null);
        ImageView fondo = (ImageView) vista.findViewById(R.id.fondo);
        TextView nom=(TextView) vista.findViewById(R.id.nom);
        TextView desc=(TextView) vista.findViewById(R.id.ide);
        nom.setText(datos[i][0]);
        desc.setText(datos[i][1]);

        switch (datos[i][1]){
            case "Ecologico":
                fondo.setImageResource(R.color.ecologico);
                break;
            case "Social":
                fondo.setImageResource(R.color.social);
                break;
            case "Cultural":
                fondo.setImageResource(R.color.cultural);
                break;
        }

        return vista;
    }


    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
