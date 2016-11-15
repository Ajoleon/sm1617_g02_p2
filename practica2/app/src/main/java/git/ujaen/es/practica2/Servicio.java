package git.ujaen.es.practica2;

/**
 * Created by Pablo on 15/11/2016.
 */

public abstract class  Servicio {
    protected int campo1 = 0;
    protected String campo2 = "";

    public Servicio(int c1, String c2){
        this.campo1 = c1;
        this.campo2=c2;
    }
    public abstract int Autenticacion();//En esta clase, se trabaja con los atributos campo1 y campo2 y se devuelve un entero (a modo de respuesta)
    public abstract Boolean Liberacion();//Devuelve un Boolean para la respuesta
    public abstract int tomarMuestra();//Aquí se debe implementar código para añadir la medida tomada a una nueva entrada de la base de datos. Devuelve código de OK o Error.
    public abstract int[] Lista();//Devuelve un array de medidas para una franja concreta.

}
