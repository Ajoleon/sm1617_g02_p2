package git.ujaen.es.practica2;

/**
 * Created by Pablo on 15/11/2016.
 */

public interface  Cliente {
    public int Autenticacion (int dni, int clave);
    public Boolean Liberacion();
    public int Tomarmuestra(int medida, int franja);
    public String[] Listar(int numerotomas, int franja);

}
