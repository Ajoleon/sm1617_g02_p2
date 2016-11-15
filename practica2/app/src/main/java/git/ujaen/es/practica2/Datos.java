package git.ujaen.es.practica2;

/**
 * Created by Pablo on 15/11/2016.
 */

public class Datos {
    int campo1 = 0;//Las restricciones de estos valores, se harán cuando se capturen en la interfaz, ya que cada uno tiene unos limites diferentes.
    String campo2 = "";
    public Datos (int c1, String c2){
        this.campo1=c1;
        this.campo2=c2;
    }
    public String toString(){
        String c1="";
        String c2="";
        try{
            c1 = String.valueOf(campo1);
            c2 = campo2;
        }catch(NumberFormatException e){
            System.out.println("Error en los datos");
        }
        return c1+""+c2;
    }

}
