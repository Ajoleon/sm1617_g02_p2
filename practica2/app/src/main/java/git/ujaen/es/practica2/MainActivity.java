package git.ujaen.es.practica2;

import android.content.SharedPreferences;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


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
            if (fechaactual.after(fecha)) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                //TODO necesito que no entre al fragmento
                Fragment f = fm.findFragmentById(R.id.main_frame);
                if (f == null) {//Si no es null es que había un fragmento
                    AuthFragment au = AuthFragment.newInstance("", "");
                    ft.add(R.id.main_frame, au);
                    ft.addToBackStack(null);
                    ft.commit();
                    //}
                }
            }else{
                System.out.println("he entrado");
            }




        }

    }


