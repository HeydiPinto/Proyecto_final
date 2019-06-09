package com.example.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater=null;
    private Context contexto;
    ArrayList<Object>items;
    int[]datosImg;
    public Adaptador(Context contexto, String[][]datos, int[]imagenes) {
        this.contexto=contexto;
        //this.Datos=datos;
        this.datosImg=imagenes;

        inflater=(LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }

    //public Adaptador(InfoFragment infoFragment, String[][] datos, int[] datosImg) {
    //}


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
        TextView descripcion=(TextView)vista.findViewById(R.id.descripcion);

        ImageView imagen=(ImageView)vista.findViewById(R.id.imgGrup);

       // titulo.setText(Datos[i][0]);
       // descripcion.setText(Datos[i][1]);
        imagen.setImageResource(datosImg[i]);
        imagen.setTag(i);

        return vista;

    }
}
