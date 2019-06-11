package com.example.proyecto_final;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater=null;
    Context contexto;
    String[][]Datos;
    int[]datosImg;
    public Adaptador(Context conexto,String[][]datos,int[]imagenes) {
        this.contexto=conexto;
        this.Datos=datos;
        this.datosImg=imagenes;

        inflater=(LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista=inflater.inflate(R.layout.elemento_lista,null);
        TextView titulo =(TextView)vista.findViewById(R.id.titulo);
        TextView empresa=(TextView)vista.findViewById(R.id.descripcion);
        //TextView fandom=(TextView)vista.findViewById(R.id.fandom);

        ImageView imagen=(ImageView)vista.findViewById(R.id.imgGrup);

        titulo.setText(Datos[i][0]);
        empresa.setText(Datos[i][1]);
        //fandom.setText(Datos[i][2]);
        imagen.setImageResource(datosImg[i]);
        imagen.setTag(i);

        return vista;
    }
}
