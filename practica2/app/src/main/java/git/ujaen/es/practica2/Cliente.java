package git.ujaen.es.practica2;

/**Interfaz de Cliente en la que se declaran los métodos a implementar
 *
 */

public interface  Cliente {

    /**En esta clase, se trabaja con los atributos campo1 y campo2 y se devuelve un entero, que será la respuesta del servidor
     *
     * @return respuesta que, en función del valor, significará OK, ERROR en usuario, ERROR en clave
     */
    public int Autenticacion (int dni, int clave);

    /**En esta clase no se utilizarán los campos, simplemente, se utiliza para liberar la conexión
     *
     * @return respuesta que, en función del valor, significará OK o ERROR
     */
    public Boolean Liberacion();

    /**Aquí se debe implementar código para añadir la medida tomada a una nueva entrada de la base de datos
     * Devuelve código de OK ,ERROR, o alguna opción más si fuese necesaria
     *
     * @return respuesta
     */
    public int Tomarmuestra(int medida, int franja);

    /**Método en el que se trabaja con los campos para devolver una lista de las medidas
     *
     * @return lista que es una lista de enteros con las medidas para la franja indicada en el mensaje
     */
    public String[] Listar(int numerotomas, int franja);

}
