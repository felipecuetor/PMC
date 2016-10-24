/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 29, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

/**
 * Excepción lanzada cuando el arco especificado no existe
 */
public class ArcoNoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador del vértice desde donde debería salir el arco
     */
    private Object origen;

    /**
     * Identificador del vértice hasta donde debería llegar el arco
     */
    private Object destino;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de error
     * @param idDesde Identificador del vértice desde donde sale el arco
     * @param idHasta Identificador del vértice hasta donde llega el arco
     */
    public ArcoNoExisteException( String mensaje, Object idDesde, Object idHasta )
    {
        super( mensaje );
        origen = idDesde;
        destino = idHasta;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el identificador del vértice desde donde debe salir el arco
     * @return identificador del vértice desde donde debe salir el arco
     */
    public Object darOrigen( )
    {
        return origen;
    }

    /**
     * Devuelve el identificador del vértice hasta donde debe llegar el arco
     * @return identificador del vértice hasta donde debe llegar el arco
     */
    public Object darDestino( )
    {
        return destino;
    }
}
