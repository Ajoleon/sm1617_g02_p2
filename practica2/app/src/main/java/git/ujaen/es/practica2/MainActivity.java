package git.ujaen.es.practica2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.net.*;


public class MainActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        Fragment f = fm.findFragmentById(R.id.main_frame);
        if(f==null){//Si no es null es que había un fragmento antes
            AuthFragment au = AuthFragment.newInstance("pepe", "12345");
            ft.add(R.id.main_frame, au);
            ft.addToBackStack(null);

        }

        ft.commit();
        final Autentication a=new Autentication("","","",0);

        Button boton = (Button)findViewById(R.id.auth_button_send1);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {//Hace falta un botón en el activity main
                Autenticar aut = new Autenticar();
                aut.execute(a);
            }
        });


    }
    class Sesion{
        public String mSessionId="";
        public String mExpires="";
    }
    class Autenticar extends AsyncTask<Autentication,Void,Sesion>{

        @Override
        protected void onPreExecute() {
            Autentication datos = null;
            super.onPreExecute();
        }

        @Override
        protected Sesion doInBackground(Autentication... params) {
            try {

                URL url = new URL("http://www4.ujaen.es");
                Socket socket = new Socket(url.getHost(),80);
                //PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
               InputStreamReader is =  new InputStreamReader(socket.getInputStream());

                os.write(new String("GET /~jccuevas/ssmm/autentica.php?user=user&pass=12345 HTTP/1.1\r\nhost:www4.ujaen.es\r\n\r\n"));
                os.flush();
                BufferedReader in = new BufferedReader(is);
                String inputline;
                while((inputline = in.readLine()) != null){
                    Log.d("Prueba",inputline);
                    System.out.println(inputline);
                }
                os.close();
                in.close();
                socket.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }




}


