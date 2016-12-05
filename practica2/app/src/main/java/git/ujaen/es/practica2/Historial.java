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

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**Fragmento para listar el historial de autenticaciones
 *
 * Created by Pablo on 5/12/2016.
 */

public class Historial extends Fragment {
    /**Método para crear una nueva instancia del fragmento del historial
     *
     * @return fragmento de explicación
     */
    public static Historial newInstance(){
        Historial fragment = new Historial();
        return fragment;
    }



    /**Método al que se llama al crear la vista
     *
     * @param inflater Necesario para inflar el fragmento con la vista
     * @param container Contenedor de la vista
     * @param savedInstanceState Instancia de los parámetros guardados tras un recreado del fragmento (no lo vamos a utilizar)
     * @return la vista
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_log, container, false);


        //TODO el adaptador bien
        String[] opciones = {"Explicación", "Autenticación", "Historial de usuarios"};

        View rootView = inflater.inflate(R.layout.fragment_log,container, false);
        ListView drawerListView = (ListView)rootView.findViewById(R.id.listview);

        System.out.println("Llegamos aquí");
        drawerListView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, opciones));

        return v;


//-----------------------------------------------------------
        //     String[] lista = new String[]{"hola"};
/*
        try {
            FileInputStream os;
            try {
                os = getContext().openFileInput("historial");
                DataInputStream dos = new DataInputStream(os);
                int n= dos.available();

                System.out.println("Bytes: "+n);

                lista = new String[n/2];
                System.out.println("Longitud: "+lista.length);

                int contador=0;
                int i=0;
                while(dos.available() > 0 && contador<10) {
                    lista[i]=dos.readUTF();
                    i++;
                    contador++;
                }
                dos.close();
                os.close();

                }catch(FileNotFoundException f) {
                    f.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Creamos el adaptador
  //      ArrayAdapter adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lista);

  //      ListView listView;
        //Encontramos ListView del fragmento
  //       listView = (ListView) findViewById(R.id.listview1);
        //Establecemos adaptador para ese ListView
  //      listView.setAdapter(adaptador);
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_log,container,false);
        String[] datasource={"fff","dasds"};
        ArrayAdapter<String> adapter =  new ArrayAdapter<String> (getActivity(),android.R.layout.simple_list_item_1, datasource);
        ListView listView = (ListView) getView().findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        return rootview;
    }*/

    }
}
