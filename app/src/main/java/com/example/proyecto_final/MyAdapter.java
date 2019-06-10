package com.example.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;


public class MyAdapter extends BaseAdapter {

    private static LayoutInflater inflater= null;
    Context contexto;
    String[][] datos;
    //int tamaño;
    //,int tamaño
    public MyAdapter(Context conexto, String[][]datos){
        this.contexto= conexto;
        this.datos=datos;
        //this.tamaño=tamaño;
        System.out.println("deep arr: " + Arrays.deepToString(datos));
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

        /*switch (datos[i][1]){
            case "Ecologico":
                fondo.setImageResource(R.color.ecologico);
                break;
            case "Social":
                fondo.setImageResource(R.color.social);
                break;
            case "Cultural":
                fondo.setImageResource(R.color.cultural);
                break;
        }*/

        return vista;
        //return null;
    }


    @Override
    public int getCount() {
        //return tamaño;
        /*if(tamaño==0){
            return 10;
        }else{
            return tamaño;
        }*/
       //return 10 ;

        //return 10;
        return 10;
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
