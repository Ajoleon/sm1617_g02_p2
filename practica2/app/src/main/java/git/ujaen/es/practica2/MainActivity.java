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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;


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

        Button boton = (Button)findViewById(R.id.auth_button_send);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
                Socket s = new Socket (InetAddress.getByName("hhtp://www4.ujaen.es"),80);
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.print("GET /~jccuevas/ssmm/autentica.php?user=user&pass=12345 HTTP/1.1\r\nHost: www4.ujaen.es \r\n\r\n");
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String t;
                while((t = br.readLine()) != null){
                    Log.d("Prueba",t);
                }
                br.close();
                s.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }




}


