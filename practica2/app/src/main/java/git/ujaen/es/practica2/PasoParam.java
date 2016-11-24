package git.ujaen.es.practica2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pablo on 24/11/2016.
 */

public class PasoParam extends Fragment {

    /**Método para crear una nueva instancia del fragmento de explicación
     *
     * @return fragmento de explicación
     */
    public static PasoParam newInstance(){
        PasoParam fragment = new PasoParam();
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
        //Infla  el contenedor con el fragmento de explicación
        View fragmento = inflater.inflate(R.layout.fragment_paso, container, false);

        //Recogemos los datos del intent que se habían enviado a esta actividad
        Bundle bundle= getActivity().getIntent().getExtras();
        if(bundle!=null){
            String usuario = bundle.getString("User");
            String clave = bundle.getString("Pass");
            String ip = bundle.getString("Ip");
            String puerto = bundle.getString("Port");

            TextView tv1 = (TextView)fragmento.findViewById(R.id.textView1);
            tv1.setText(usuario);
            TextView tv2 = (TextView)fragmento.findViewById(R.id.textView3);
            tv2.setText(clave);
            TextView tv3 = (TextView)fragmento.findViewById(R.id.textView4);
            tv3.setText(ip);
            TextView tv4 = (TextView)fragmento.findViewById(R.id.textView5);
            tv4.setText(puerto);

            Toast.makeText(getActivity(), "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }

        //Devuelve la vista
        return fragmento;
    }
}
