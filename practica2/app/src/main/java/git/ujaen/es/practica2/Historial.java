package git.ujaen.es.practica2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**Fragmento para listar el historial de autenticaciones
 *
 * Created by Pablo on 5/12/2016.
 */

public class Historial extends Fragment {
    ListView mLeadsList;
    ArrayAdapter<String> mLeadsAdapter;

    /**
     * Método para crear una nueva instancia del fragmento del historial
     *
     * @return fragmento de explicación
     */
    public static Historial newInstance() {
        Historial fragment = new Historial();
        return fragment;
    }


    /**
     * Método al que se llama al crear la vista
     *
     * @param inflater           Necesario para inflar el fragmento con la vista
     * @param container          Contenedor de la vista
     * @param savedInstanceState Instancia de los parámetros guardados tras un recreado del fragmento (no lo vamos a utilizar)
     * @return la vista
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        EstableceAdapter(v);

        return v;

    }

    public void EstableceAdapter(View fragment) {

        String[] leadsNames;
        String [] linea = null;

        linea = leerArchivo();
        if(linea!=null) {

            if(linea.length<=3) {
                //Establezco el tamaño del adaptador
                leadsNames = new String[linea.length];

                //Recorro linea y lo voy metiendo en el adaptador
                for (int i = 0; i < linea.length; i++) {
                    try {
                        leadsNames[i] = linea[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }

                mLeadsList = (ListView) fragment.findViewById(R.id.listview);
                mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, leadsNames);
                mLeadsList.setAdapter(mLeadsAdapter);
            }else{
                ultimosLogin();
                linea = leerArchivo();
                leadsNames = new String[linea.length];

                //Recorro linea y lo voy metiendo en el adaptador
                for (int i = 0; i < linea.length; i++) {
                    try {
                        leadsNames[i] = linea[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                mLeadsList = (ListView) fragment.findViewById(R.id.listview);
                mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, leadsNames);
                mLeadsList.setAdapter(mLeadsAdapter);
            }
        }
    }

    public String[] leerArchivo(){

        InputStreamReader archivo = null;
        try {
            archivo = new InputStreamReader(getContext().openFileInput("historial"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Creamos un objeto buffer, en el que iremos almacenando el contenido
        // del archivo.
        String texto = "";
        try{
            BufferedReader br = new BufferedReader(archivo);
            texto = "";
            try {
                while(br.ready()){
                    texto = texto + br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Leído: "+texto);
        }catch(NullPointerException e){}

        if(!texto.equals("")) {
            String[] linea = new String[]{};
            for (int i = 0; i < texto.length(); i++) {
                linea = texto.split("&&");
            }
            return linea;

        }else{
            return null;
        }

    }
    public void ultimosLogin(){

        //Cojo el texto con todos las lineas que hay en el archivo
        //Las separo en el array linea
        String [] linea = null;
        linea = leerArchivo();

        OutputStreamWriter osw=null;
        try {
            osw = new OutputStreamWriter(getContext().openFileOutput("historial", MODE_PRIVATE));
            osw.write("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=linea.length-3; i<linea.length;i++){

            try{
                osw.append(linea[i]);
                System.out.println("Archivo "+linea[i]);
                osw.append("&&");
            }catch(Exception e){ }
        }

        try {
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

