package git.ujaen.es.practica2;

/**La clase Mensaje define los mensajes que se van a utilizar en el protocolo
 * Tendrán una cabecera y un campo de datos, que se forma por un entero y una cadena de caracteres.
 * Se implementarán también los métodos
 */

public class Mensaje {
    public static final String AUTH = "AUTH";
    public static final String POST = "POST";
    public static final String GRPH = "GRPH";
    public static final String CRLF = "\r\n";
    String header = "";
    Datos data = null;
    public Mensaje (int opcion, Datos data){
        switch (opcion){
            case 1:
                this.header = AUTH;
                break;
            case 2:
                this.header= POST;
                break;
            case 3:
                this.header = GRPH;
                break;
        }
        this.data=data;
    }

    public byte[] toByteArray(){
        String m = header+""+data.toString()+CRLF;
        return m.getBytes();
    }
    public String toArrayByte(Byte[] bytes){
        String m = bytes.toString();
        return m;
    }

}
