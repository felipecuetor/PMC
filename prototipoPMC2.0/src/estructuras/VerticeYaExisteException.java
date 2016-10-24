/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VerticeYaExisteException.java,v 1.2 2008/10/11 22:02:54 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

/**
 * Excepción utilizada para informar que el vértice especificado ya existe en el grafo
 */
public class VerticeYaExisteException extends Exception
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
     * Identificador del vértice que ya existe en el grafo
     */
    private Object idVertice;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de error
     * @param id Identificador del vértice que ya existe
     */
    public VerticeYaExisteException( String mensaje, Object id )
    {
        super( mensaje );
        idVertice = id;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el identificador del vértice que ya existe
     * @return identificador del vértice que ya existe
     */
    public Object darIdentificador( )
    {
        return idVertice;
    }
}
