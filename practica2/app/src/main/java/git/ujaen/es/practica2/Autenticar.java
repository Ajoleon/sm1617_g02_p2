package git.ujaen.es.practica2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Antonio on 10/11/2016.
 */

public  class Autenticar extends AsyncTask<Autentication,Void,Sesion> {

    @Override
    protected void onPreExecute() {
        Autentication datos = null;
        super.onPreExecute();
    }

    @Override
    protected Sesion doInBackground(Autentication ... params) {
        try {
            Sesion sesion = new Sesion("","");
            String mUser = params[0].mUser;
            String mPass = params[0].mPass;
            String mIp = params[0].mIP;
            int mPort = params[0].mPort;
            URL url = new URL("http://www4.ujaen.es");
            Socket socket = new Socket(url.getHost(), 80);
            //PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
            InputStreamReader is = new InputStreamReader(socket.getInputStream());

            os.write(new String("GET /~jccuevas/ssmm/autentica.php?user=" + mUser + "&pass=" + mPass + " HTTP/1.1\r\nhost:www4.ujaen.es\r\n\r\n"));
            os.flush();
            BufferedReader in = new BufferedReader(is);
            String inputline;
            String [] linea = null;
            while ((inputline = in.readLine()) != null) {
                Log.d("Prueba", inputline);
                System.out.println(inputline);
                linea = inputline.split("&");
                for(int i = 0 ;i< linea.length;i++){
                    System.out.println(linea[i]);
                }
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