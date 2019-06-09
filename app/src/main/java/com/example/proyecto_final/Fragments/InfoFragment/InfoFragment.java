package com.example.proyecto_final.Fragments.InfoFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.Context;
import android.widget.AdapterView;

//import com.google.android.gms.ads.MobileAds;

import com.example.proyecto_final.R;
import com.example.proyecto_final.singleHorizontal;
import com.example.proyecto_final.singleVertical;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    private RecyclerView recyclerView;
   // Adaptador adaptador;
    private ArrayList<Object> objects=new ArrayList<>();
    private ListView lista;
   /* String[][]Datos={
            {"¡¡¡UN LITRO DE ACEITE CONTAMINA CERCA DE UN MILLON DE LITROS DE AGUA!!!","Aunque no cocinemos muchos alimentos fritos en aceite, cuando lo hacemos, normalmente tiramos el aceite usado en la pileta de la cocina o en algún otro resumidero, ¿verdad?. Ese es uno de los mayores errores que podemos cometer "},
            {"¿EL VIDRIO SE RECICLA?","El vidrio es 100% reciclable Puede reciclarse infinitamente, jamás pierde sus propiedades "},
            {"¡¡UN GRAN PASO PARA LAS MATEMATICAS!!"," Los mayas emplearon las matemáticas y fueron una de las primeras civilizaciones en utilizar el “cero” en el mundo"},
            {"MÉXICO UN PAIS MULTICULTURAL","En el país se hablan alrededor de 287 idiomas, es el país hispanohablante más poblado, así como el séptimo país con mayor diversidad lingüística en el mundo: el estado reconoce como lengua nacional al español junto a 67 lenguas indígenas propias de la nación. "},
            {"¿POR QUÉ LOS DEPORTISTAS DE RAZA NEGRA NO SON BUENOS EN NATACIÓN?","Las personas de raza negra no destacan en las competencias de natación debido a su constitución física; es decir, cuentan con una mayor proporción de fibra muscular, más adaptada para una reacción explosiva que para el esfuerzo continuo que requiere la natación."},
            {"¿QUIÉN ANOTÓ EL PRIMERO GOL EN UN MUNDIAL?","Fue Lucien Laurent, jugador de la selección francesa que participó en los primeros tres certámenes internacionales de fútbol (1930, 1934 y 1938). El gol entró a los 19 minutos en un partido contra México, el 13 de julio de 1930 en Uruguay. El resultado final fue 4-1 a favor de Francia."},
            {"FACEBOOK","Cada día se utilizan 4.7 billones de minutos en Facebook."},
            {"¿MAS DE LA MITAD DE USUAROS DE FACEBOOK EN MEXICO SON MUJERES?","En México las mujeres casi duplican a los hombres en numero de usuarios en las redes sociales (62.3%)de los usuarios mexicanos son mujeres frente a un 37.5 % que son usuarios hombres,                                                                                             mmmmm\n" +
                    " "}

    };*/
    //int[] datosImg={R.drawable.gota_oli,R.drawable.reciclaje_vidrio,R.drawable.mayas,R.drawable.idiomas,R.drawable.natacion_no,R.drawable.primer_gol,R.drawable.face,R.drawable.user};
    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //codigo para anuncios
       // MobileAds.getInitializationStatus();
        // MobileAds.initialize(this,"YOUR_ADMOB_APP_ID");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);

        //lista.setAdapter(new Adaptador(getContext(),Datos,datosImg));

        return view;
    }

    private ArrayList<Object> getObject(){
        objects.add(getVerticalData().get(0));
        objects.add(getHorizontalData().get(0));
        return objects;
    }

    public static ArrayList<singleVertical>getVerticalData(){
        ArrayList<singleVertical> singleVerticals=new ArrayList<>();
        singleVerticals.add(new singleVertical("¡¡¡UN LITRO DE ACEITE CONTAMINA CERCA DE UN MILLON DE LITROS DE AGUA!!!","Aunque no cocinemos muchos alimentos fritos en aceite, cuando lo hacemos, normalmente tiramos el aceite usado en la pileta de la cocina o en algún otro resumidero, ¿verdad?. Ese es uno de los mayores errores que podemos cometer ",R.drawable.gota_oli));
        singleVerticals.add(new singleVertical("¿EL VIDRIO SE RECICLA?","El vidrio es 100% reciclable Puede reciclarse infinitamente, jamás pierde sus propiedades ",R.drawable.reciclaje_vidrio));
        singleVerticals.add(new singleVertical("¡¡UN GRAN PASO PARA LAS MATEMATICAS!!"," Los mayas emplearon las matemáticas y fueron una de las primeras civilizaciones en utilizar el “cero” en el mundo",R.drawable.mayas));
        singleVerticals.add(new singleVertical("MÉXICO UN PAIS MULTICULTURAL","En el país se hablan alrededor de 287 idiomas, es el país hispanohablante más poblado, así como el séptimo país con mayor diversidad lingüística en el mundo: el estado reconoce como lengua nacional al español junto a 67 lenguas indígenas propias de la nación. ",R.drawable.idiomas));
        singleVerticals.add(new singleVertical("¿POR QUÉ LOS DEPORTISTAS DE RAZA NEGRA NO SON BUENOS EN NATACIÓN?","Las personas de raza negra no destacan en las competencias de natación debido a su constitución física; es decir, cuentan con una mayor proporción de fibra muscular, más adaptada para una reacción explosiva que para el esfuerzo continuo que requiere la natación.",R.drawable.natacion_no));
        singleVerticals.add(new singleVertical("¿QUIÉN ANOTÓ EL PRIMERO GOL EN UN MUNDIAL?","Fue Lucien Laurent, jugador de la selección francesa que participó en los primeros tres certámenes internacionales de fútbol (1930, 1934 y 1938). El gol entró a los 19 minutos en un partido contra México, el 13 de julio de 1930 en Uruguay. El resultado final fue 4-1 a favor de Francia.",R.drawable.primer_gol));
        singleVerticals.add(new singleVertical("FACEBOOK","Cada día se utilizan 4.7 billones de minutos en Facebook.",R.drawable.face));
        singleVerticals.add(new singleVertical("¿MAS DE LA MITAD DE USUAROS DE FACEBOOK EN MEXICO SON MUJERES?","En México las mujeres casi duplican a los hombres en numero de usuarios en las redes sociales (62.3%)de los usuarios mexicanos son mujeres frente a un 37.5 % que son usuarios hombres",R.drawable.user));
    return singleVerticals;
    }

    public static ArrayList<singleHorizontal>getHorizontalData(){
       ArrayList<singleHorizontal>singleHorizontals=new ArrayList<>();
       singleHorizontals.add(new singleHorizontal(R.drawable.gota_oli,"¡¡¡UN LITRO DE ACEITE CONTAMINA CERCA DE UN MILLON DE LITROS DE AGUA!!!","Aunque no cocinemos muchos alimentos fritos en aceite, cuando lo hacemos, normalmente tiramos el aceite usado en la pileta de la cocina o en algún otro resumidero, ¿verdad?. Ese es uno de los mayores errores que podemos cometer "));
       singleHorizontals.add(new singleHorizontal(R.drawable.reciclaje_vidrio,"¿EL VIDRIO SE RECICLA?","El vidrio es 100% reciclable Puede reciclarse infinitamente, jamás pierde sus propiedades"));
       singleHorizontals.add(new singleHorizontal(R.drawable.mayas,"¡¡UN GRAN PASO PARA LAS MATEMATICAS!!","Los mayas emplearon las matemáticas y fueron una de las primeras civilizaciones en utilizar el “cero” en el mundo"));
       singleHorizontals.add(new singleHorizontal(R.drawable.idiomas,"MÉXICO UN PAIS MULTICULTURAL","En el país se hablan alrededor de 287 idiomas, es el país hispanohablante más poblado, así como el séptimo país con mayor diversidad lingüística en el mundo: el estado reconoce como lengua nacional al español junto a 67 lenguas indígenas propias de la nación. "));
       singleHorizontals.add(new singleHorizontal(R.drawable.natacion_no,"¿POR QUÉ LOS DEPORTISTAS DE RAZA NEGRA NO SON BUENOS EN NATACIÓN?","Las personas de raza negra no destacan en las competencias de natación debido a su constitución física; es decir, cuentan con una mayor proporción de fibra muscular, más adaptada para una reacción explosiva que para el esfuerzo continuo que requiere la natación."));
       singleHorizontals.add(new singleHorizontal(R.drawable.primer_gol,"¿QUIÉN ANOTÓ EL PRIMERO GOL EN UN MUNDIAL?","Fue Lucien Laurent, jugador de la selección francesa que participó en los primeros tres certámenes internacionales de fútbol (1930, 1934 y 1938). El gol entró a los 19 minutos en un partido contra México, el 13 de julio de 1930 en Uruguay. El resultado final fue 4-1 a favor de Francia."));
       singleHorizontals.add(new singleHorizontal(R.drawable.face,"FACEBOOK","\"Cada día se utilizan 4.7 billones de minutos en Facebook."));
       singleHorizontals.add(new singleHorizontal(R.drawable.user,"¿MAS DE LA MITAD DE USUAROS DE FACEBOOK EN MEXICO SON MUJERES?","En México las mujeres casi duplican a los hombres en numero de usuarios en las redes sociales (62.3%)de los usuarios mexicanos son mujeres frente a un 37.5 % que son usuarios hombres"));
       return  singleHorizontals;
    }

}
