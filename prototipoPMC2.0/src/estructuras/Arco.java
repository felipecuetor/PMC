/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arco.java,v 1.1 2008/04/19 03:54:50 jua-gome Exp $
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
 * Representa un arco del grafo
 * 
 * @param <K>
 *            Tipo del identificador de un vértice
 * @param <V>
 *            Tipo de datos del elemento del vértice
 * @param <A>
 *            Tipo de datos del elemento del arco
 */
public class Arco<K, V extends IVertice<K>, A extends IArco> {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Vértice desde el cual sale el arco
	 */
	private Vertice<K, V, A> origen;

	/**
	 * Vértice hacia el cual va el arco
	 */
	private Vertice<K, V, A> destino;

	/**
	 * Elemento en el arco
	 */
	private A infoArco;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del arco
	 * 
	 * @param pOrigen
	 *            Vértice desde el cual sale el arco
	 * @param pDestino
	 *            Vértice hacia donde se dirige el arco
	 * @param pInfoArco
	 *            Elemento en el arco
	 */
	public Arco(Vertice<K, V, A> pOrigen, Vertice<K, V, A> pDestino, A pInfoArco) {
		origen = pOrigen;
		destino = pDestino;
		infoArco = pInfoArco;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el elemento del arco
	 * 
	 * @return Elemento en el arco
	 */
	public A darInfoArco() {
		return infoArco;
	}

	/**
	 * Devuelve el vértice de destino del arco
	 * 
	 * @return vértice de destino del arco
	 */
	public Vertice<K, V, A> darVerticeDestino() {
		return destino;
	}

	/**
	 * Devuelve el vértice de origen del arco
	 * 
	 * @return vértice de origen del arco
	 */
	public Vertice<K, V, A> darVerticeOrigen() {
		return origen;
	}

	/**
	 * Devuelve el peso del arco
	 * 
	 * @return Peso del arco
	 */
	public int darPeso() {
		return infoArco.darPeso();
	}

	public String toString() {
		return infoArco.toString();
	}
}
