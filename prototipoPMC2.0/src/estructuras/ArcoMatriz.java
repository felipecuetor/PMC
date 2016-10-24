/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan E. Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package estructuras;

/**
 * Representa una posición en una matriz de adyacencia
 */
public class ArcoMatriz<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
    /**
     * Marca del arco
     */
    private boolean marcado;

    /**
     * Arco
     */
    private Arco<K,V,A> arco;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
	/**
	 * Construye una posición de una matriz de ayacencia a partir de un
	 * <code>Arco</code>
	 * @param arco Información del arco
	 */
	public ArcoMatriz(Arco<K,V,A> arco) {
		marcado = false;
		this.arco = arco;
	}

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Retorna la marca del arco
	 * @return <code>true</code> si el arco está marcado o <code>false</code>
	 *         en caso contrario
	 */
	public boolean marcado() {
		return marcado;
	}

	/**
	 * Marca el arco
	 */
	public void marcar() {
		marcado = true;
	}

	/**
	 * Elimina la marca del vertice
	 */
	public void desmarcar() {
		marcado = false;
	}

	/**
	 * Retorna el arco contenido
	 * @return La información de arco contenido
	 */
	public Arco<K,V,A> darArco() {
		return arco;
	}

}
