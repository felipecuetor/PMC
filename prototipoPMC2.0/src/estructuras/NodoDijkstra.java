/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoDijkstra.java,v 1.6 2008/10/11 22:04:09 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Clase utilizada en el calculo de caminos m�nimos usando el algoritmo de Dijkstra
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class NodoDijkstra<K, V extends IVertice<K>, A extends IArco> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante que indica costo m�nimo indefinido
	 */
	public final static int INDEFINIDO = Integer.MAX_VALUE;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Costo del camino m�nimo del punto inicial hasta el v�rtice representado en este nodo
     */
    private int costoMinimo;

    /**
     * V�rtice representado en el nodo
     */
    private Vertice<K, V, A> vertice;

    /**
     * Nodo anterior en el camino m�nimo
     */
    private NodoDijkstra<K, V, A> predecesor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nodo con el v�rtice especificado
     * @param pVertice V�rtice que va a ser representado en el nodo
     */
    public NodoDijkstra( Vertice<K, V, A> pVertice )
    {
        costoMinimo = INDEFINIDO;
        vertice = pVertice;
        predecesor = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor del costo m�nimo entre el v�rtice representado en el nodo y su predecesor
     */
    public int darCostoMinimo( )
    {
        return costoMinimo;
    }

    /**
     * Asigna el costo m�nimo entre el v�rtice representado en el nodo y su predecesor
     * @param costo El costo m�nimo entre el predecesor y el v�rtice
     * @param anterior El predecesor del v�rtice
     */
    public void asignarCostoMinimo( int costo, NodoDijkstra<K, V, A> anterior )
    {
        costoMinimo = costo;
        predecesor = anterior;
    }

    /**
     * Retorna el v�rtice representa en el nodo
     * @return V�rtice representado en el nodo
     */
    public Vertice<K, V, A> darVertice( )
    {
        return vertice;
    }

    /**
     * Retorna el predecesor del v�rtice representado en el nodo
     * @return El predecesor
     */
    public NodoDijkstra<K, V, A> darPredecesor( )
    {
        return predecesor;
    }
}
