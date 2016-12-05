package git.ujaen.es.practica2;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthFragment} interface
 * to handle interaction events.
 * Use the {@link AuthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mUser = "";
    private String mPass = "";
    private Autentication mAutentica = new Autentication("","");
    private Sesion sesion = new Sesion("","");
    private EditText mEditUser = null;
    private EditText mEditPass = null;

    public AuthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @param pass Parameter 2.
     * @return A new instance of fragment AuthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthFragment newInstance(String user, String pass) {
        AuthFragment fragment = new AuthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {//Para que solo lo haga cuando no hay nada
            if (getArguments() != null) {
                mUser = getArguments().getString(ARG_PARAM1);
                mPass = getArguments().getString(ARG_PARAM2);
                mAutentica.setmUser(mUser);
                mAutentica.setmPass(mPass);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            Toast.makeText(getActivity(), "Cambio configuracion. ", Toast.LENGTH_SHORT).show();
            //Cuando se cambia la configuracion se queda toda actualizado
            mAutentica.setmUser(savedInstanceState.getString(ARG_PARAM1));
        }

        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_auth, container, false);

        redibuja(fragmento);

        Button boton = (Button) fragmento.findViewById(R.id.auth_button_send);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean online = isOnline();
                System.out.println("Está activa "+online);

                if(online) {

                    Autenticar aut = new Autenticar();

                    try {
                        final Autentication a = new Autentication(mAutentica.getmUser(), mAutentica.getmPass());
                        sesion = aut.execute(a).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    SharedPreferences settings = getActivity().getSharedPreferences("sesion", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("SESION-ID", sesion.getmSessionId());
                    editor.putString("EXPIRES", sesion.getmExpires());
                    editor.commit();

                    System.out.println("SESION-ID: " + sesion.getmSessionId());
                    System.out.println("EXPIRES: " + sesion.getmExpires());

                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    startActivity(intent);

                    //Para guardar el usuario y la contraseña en un archivo
                    try {
                        FileOutputStream os;
                        try {
                            os = getContext().openFileOutput("historial", MODE_PRIVATE);
                            DataOutputStream dos = new DataOutputStream(os);
                            System.out.println(mAutentica.getmUser()+" "+ mAutentica.getmPass());
                            dos.writeUTF(mAutentica.getmUser());
                            dos.writeUTF(mAutentica.getmPass());
                            dos.close();
                            os.close();
                        }catch(FileNotFoundException f){
                            f.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getActivity(), "Active la conexión a Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragmento;

    }


    //Método que redibuja las vistas
    private void redibuja(View fragmento){


        mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
        mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
        //Cuando pierde el foco, actualiza la variable
        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setmUser(mEditUser.getEditableText().toString());
                mAutentica.setmPass(mEditPass.getEditableText().toString());

            }
        });
        mEditUser.setText(mAutentica.getmUser());
        mEditPass.setText(mAutentica.getmPass());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_PARAM1, mAutentica.getmUser());
        outState.putString(ARG_PARAM2, mAutentica.getmPass());

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!=null && activeNetwork.isConnectedOrConnecting()){// obtener los datos
            return true;
        }else{// mostrar el error
            return false;
        }
    }

}
