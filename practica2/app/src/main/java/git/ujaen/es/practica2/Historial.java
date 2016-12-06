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
import java.util.ArrayList;

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

        redibuja(v);

        return v;

    }

    public void redibuja(View fragment) {

        String texto;
        texto = leerArchivo();

        String[] leadsNames = new String[]{};
        String [] linea = null;

        if(!texto.equals("")) {
            for (int i = 0; i < texto.length(); i++) {
                linea = texto.split("&&");
            }

            leadsNames = new String[linea.length];

            int j = 0;
            for (int i = 0; i < linea.length; i++) {
                try {
                    leadsNames[i] = linea[j];
                }catch(ArrayIndexOutOfBoundsException e){ }
                j = j + 1;
            }

            mLeadsList = (ListView) fragment.findViewById(R.id.listview);
            mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, leadsNames);
            mLeadsList.setAdapter(mLeadsAdapter);
        }
    }

    public String leerArchivo(){

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

        return texto;
    }
}

