package git.ujaen.es.practica2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences settings = getSharedPreferences("sesion", 0);

        String sesionid = settings.getString("SESION-ID", "");
        String expires = settings.getString("EXPIRES", "0000-00-00-00-00-00");

        //Llamamos al Gestor de fragmentos
        FragmentManager fm = getSupportFragmentManager();
        //Comenzamos la transacción de fragmentos
        FragmentTransaction ft = fm.beginTransaction();
        //Encontramos el fragmento principal de la aplicación
        Fragment f = fm.findFragmentById(R.id.main_frame);

        //Si antes no había ningún fragmento
        if(f==null){
            /**Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
             *
             * @see AuthFragment.newInstance() Método donde se crea la nueva instancia del fragmento de autenticación
             */
            PasoParam p = PasoParam.newInstance();
            //Añadimos el fragmento al main_frame
            ft.add(R.id.main_frame, p);
            //Añadimos null a la pila hacia atrás
            ft.addToBackStack(null);
            //Ejecuta la transacción de fragmentos
            ft.commit();

        }

        listview1();
    }

    public void listview1(){
        //String para listview con los títulos de los fragmentos
        final String[] opciones = { "Explicación", "Toma de medidas", "Desconexión"};

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
                        //if(autenticado) {
                        //Se inicializa una nueva instancia del fragmento de explicación
                        Explanation e = Explanation.newInstance();

                        //Se añade fragmento de explicación
                        ft.replace(R.id.main_frame, e);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();

                        //Establecemos autenticado a false
                        //    autenticado = false;
                        //}

                        break;

                    //Caso de posición 1,
                    case 1:
                        //Si no se ha accedido al fragmento de autenticado, para que no haya
                        //un bug en el recreado del fragmento el cambio a la otra vista lo realice
                        //if(!autenticado){
                        //Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
                        PasoParam p = PasoParam.newInstance();
                        //Reemplazamos el fragmento al main_frame
                        ft.replace(R.id.main_frame, p);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();

                        //Establecemos autenticado a true porque ha entrado en el fragmento de autenticación
                        //    autenticado = true;
                        //}

                        break;
                    case 2:

                        SharedPreferences settings = getSharedPreferences("sesion", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.clear();
                        editor.commit();

                        AuthFragment au = AuthFragment.newInstance("", "");
                        //Reemplazamos el fragmento ya existente por el de autenticación
                        ft.replace(R.id.main_frame, au);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();

                        //Para el desplegable en caso de abrirlo tras desconectar sin cerrar la aplicación
                        listview2();
                        break;
                }

                //Obtenemos texto del item en la posición clickada
                //String texto = String.valueOf(a.getItemAtPosition(position));

                //Mostramos tostada con el texto y la posición
                //Toast.makeText(MainActivity.this, texto +", con posicion: "+ position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Para el desplegable en caso de abrirlo tras desconectar sin cerrar la aplicación
    public void listview2(){
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
                        //if(autenticado) {
                        //Se inicializa una nueva instancia del fragmento de explicación
                        Explanation e = Explanation.newInstance();

                        //Se añade fragmento de explicación
                        ft.replace(R.id.main_frame, e);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();

                        //Establecemos autenticado a false
                        //    autenticado = false;
                        //}

                        break;

                    //Caso de posición 1,
                    case 1:
                        //Si no se ha accedido al fragmento de autenticado, para que no haya
                        //un bug en el recreado del fragmento el cambio a la otra vista lo realice
                        //if(!autenticado){
                        //Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
                        AuthFragment au = AuthFragment.newInstance("", "");
                        //Reemplazamos el fragmento ya existente por el de autenticación
                        ft.replace(R.id.main_frame, au);

                        //Añadimos null a la pila hacia atrás
                        ft.addToBackStack(null);
                        //Ejecuta la transacción de fragmentos
                        ft.commit();

                        //Establecemos autenticado a true porque ha entrado en el fragmento de autenticación
                        //    autenticado = true;
                        //}

                        break;
                    case 2:
                        Intent intent = new Intent(v.getContext(), Main3Activity.class);
                        startActivity(intent);
                        break;
                }

                //Obtenemos texto del item en la posición clickada
                //String texto = String.valueOf(a.getItemAtPosition(position));

                //Mostramos tostada con el texto y la posición
                //Toast.makeText(MainActivity.this, texto +", con posicion: "+ position, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
