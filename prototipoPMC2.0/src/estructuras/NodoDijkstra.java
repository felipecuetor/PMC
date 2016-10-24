/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoDijkstra.java,v 1.6 2008/10/11 22:04:09 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abr 21, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

import java.io.Serializable;

/**
 * Clase utilizada en el calculo de caminos mínimos usando el algoritmo de Dijkstra
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class NodoDijkstra<K, V extends IVertice<K>, A extends IArco> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante que indica costo mínimo indefinido
	 */
	public final static int INDEFINIDO = Integer.MAX_VALUE;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Costo del camino mínimo del punto inicial hasta el vértice representado en este nodo
     */
    private int costoMinimo;

    /**
     * Vértice representado en el nodo
     */
    private Vertice<K, V, A> vertice;

    /**
     * Nodo anterior en el camino mínimo
     */
    private NodoDijkstra<K, V, A> predecesor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nodo con el vértice especificado
     * @param pVertice Vértice que va a ser representado en el nodo
     */
    public NodoDijkstra( Vertice<K, V, A> pVertice )
    {
        costoMinimo = INDEFINIDO;
        vertice = pVertice;
        predecesor = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor del costo mínimo entre el vértice representado en el nodo y su predecesor
     */
    public int darCostoMinimo( )
    {
        return costoMinimo;
    }

    /**
     * Asigna el costo mínimo entre el vértice representado en el nodo y su predecesor
     * @param costo El costo mínimo entre el predecesor y el vértice
     * @param anterior El predecesor del vértice
     */
    public void asignarCostoMinimo( int costo, NodoDijkstra<K, V, A> anterior )
    {
        costoMinimo = costo;
        predecesor = anterior;
    }

    /**
     * Retorna el vértice representa en el nodo
     * @return Vértice representado en el nodo
     */
    public Vertice<K, V, A> darVertice( )
    {
        return vertice;
    }

    /**
     * Retorna el predecesor del vértice representado en el nodo
     * @return El predecesor
     */
    public NodoDijkstra<K, V, A> darPredecesor( )
    {
        return predecesor;
    }
}
