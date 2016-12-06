package git.ujaen.es.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("sesion", 0);

        String sesionid = settings.getString("SESION-ID", "");
        String expires = settings.getString("EXPIRES", "0000-00-00-00-00-00");
        System.out.println(expires);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date fecha = null;
        Date fechaactual = new Date();

        try {
            fecha = dateFormat.parse(expires);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            fechaactual = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Fecha actual "+fechaactual+ " Fecha "+fecha);
        if (fechaactual.after(fecha)) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment f = fm.findFragmentById(R.id.main_frame);
            if (f == null) {//Si no es null es que había un fragmento
                AuthFragment au = AuthFragment.newInstance("","");
                ft.add(R.id.main_frame, au);
                ft.addToBackStack(null);
                ft.commit();

            }
        } else {
            Intent intent= new Intent(this,Main2Activity.class);
            startActivity(intent);
        }

        //Listview de la actividad 1, con explicación, autenticación, e historial de usuarios
        listview();
    }
    public void listview(){
        //String para listview con los títulos de los fragmentos
        final String[] opciones = { "Explicación", "Autenticación", "Historial de usuarios"};

        //Creamos el adaptador
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones);

        //Encontramos ListView del fragmento
        final ListView listView = (ListView) findViewById(R.id.list_view);
        //Establecemos adaptador para ese ListView
        listView.setAdapter(adaptador);

        //Establecemos un escuchador de click en los items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**Método al hacer click en un item de la lista
             *
             * @param a     Adaptador del ListView
             * @param v     Vista
             * @param position  Posición del item clickado
             * @param id    id del item
             */
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id){
                //Llamamos al Gestor de fragmentos
                FragmentManager fm = getSupportFragmentManager();
                //Comenzamos la transacción de fragmentos
                FragmentTransaction ft = fm.beginTransaction();
                //Encontramos el fragmento principal de la aplicación
                Fragment f = fm.findFragmentById(R.id.main_frame);

                //Switch para los distintos casos de los items del ListView. La entrada es la posición del item clickado
                switch (position) {

                    //Caso de posición 0, que es del fragmento de explicación
                    case 0:
                        //Se inicializa una nueva instancia del fragmento de explicación
                        Explanation e = Explanation.newInstance();
                        //Se añade fragmento de explicación
                        ft.replace(R.id.main_frame, e);
                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();
                        break;

                    //Caso de posición 1, que es el fragmento de autenticación
                    case 1:
                        //Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
                        AuthFragment au = AuthFragment.newInstance("", "");
                        //Reemplazamos el fragmento ya existente por el de autenticación
                        ft.replace(R.id.main_frame, au);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();
                        break;

                    //Caso de posición 2, que es el fragmento del historial de login
                    case 2:
                        Historial h = Historial.newInstance();
                        //Reemplazamos el fragmento ya existente por el del historial
                        ft.replace(R.id.main_frame, h);
                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();
                        break;
                }

            }
        });
    }

}




