/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorSimple.java,v 1.24 2008/09/30 16:06:59 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 5, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

/**
 * Implementación de un iterador simple. A través de este tipo de iterador sólo se pueden recorrer los elementos de la estructura de datos sobre la que se encuentra asociada
 * pero no ésta no puede ser modificada.
 * @param <T> Tipo de datos sobre los que se itera
 */
public class IteradorSimple<T> implements Iterador<T>
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;	

	/**
	 * Constante para posición del iterador por defecto
	 */
    private final static int NADA = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elementos sobre los que se está iterando
     */
    private T[] elems;

    /**
     * Posición que del próximo elemento a ser visitado
     */
    private int posActual;

    /**
     * La siguiente posición libre en elems. Corresponde en realidad al número de elementos sobre los que se está iterando
     */
    private int sigPosLibre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un iterador con el tamaño (capacidad) especificado. <br>
     * <b> post: </b> Se creó un iterador con la capacidad especificada.<br>
     * @param tamanio El tamaño que va a tener el iterador<br>
     */
    @SuppressWarnings("unchecked")
    public IteradorSimple( int tamanio )
    {
        elems = ( T[] )new Object[tamanio];
        sigPosLibre = 0;
        posActual = NADA;
    }

    // -----------------------------------------------------------------
    // Métodos: interface Iterador
    // -----------------------------------------------------------------
    /**
     * Indica si aún hay elementos sobre los cuales iterar. <br>
     * <b>post: </b> Se retornó true si aún no se han recorrido todos los elementos o false en caso contrario.
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( )
    {
        return elems.length > 0 && ( posActual + 1 ) < sigPosLibre;
    }

    /**
     * Retorna el elemento a ser visitado. <br>
     * <b>pre: </b> Aún existe al menos un elemento sobre el cual iterar. <br>
     * <b>post: </b> Se retornó el elemento a ser visitado. De no existir un siguiente se retorna null.
     * @return El elemento a ser visitado. De no existir un siguiente se retorna null.
     */
    public T darSiguiente( )
    {
        return haySiguiente( ) ? elems[ ++posActual ] : null;
    }

    /**
     * Retorna el último elemento visitado. <br>
     * <b>pre: </b> Aún existe al menos un elemento sobre el cual retroceder. <br>
     * <b>post: </b> Se retornó el último elemento visitado.
     * @return El último elemento visitado o null si no hay elementos sobre los cuales retroceder.
     */
    public T darAnterior( )
    {
        return hayAnterior( ) ? elems[ --posActual ] : null;
    }

    /**
     * Indica si aún hay elementos sobre los cuales retroceder. <br>
     * <b>post: </b> Se retornó true si aún hay elementos sobre los cuales retroceder o false en caso contrario.
     * @return True si aún hay elementos sobre los cuales retroceder o false en caso contrario
     */
    public boolean hayAnterior( )
    {
        return elems.length > 0 && posActual > 0;
    }

    /**
     * Sitúa el iterador de nuevo al inicio de la colección de datos con la que se encuentra asociado. <br>
     * <b>post: </b> El iterador se encuentra al inicio de la colección de datos con la que se encuentra asociada.
     */
    public void reiniciar( )
    {
        posActual = NADA;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo elemento al final del iterador. <br>
     * <b>post: </b> Se adicionó el elemento especificado en la última posición del iterador, y sigPosLibre= sigPosLibre+1.
     * @throws IteradorException Si el iterador no tiene capacidad para más elementos
     */
    public void agregar( T elem ) throws IteradorException
    {
        if( sigPosLibre <= elems.length - 1 )
        {
            elems[ sigPosLibre++ ] = elem;
        }
        else
            throw new IteradorException( "Límite del iterador alcanzado" );
    }

    /**
     * Inserta un nuevo elemento en la primera posición del iterador. <br>
     * <b>post: </b> Se adicionó el elemento especificado en la primera posición del iterador, y sigPosLibre= sigPosLibre+1. <br>
     * @throws IteradorException Si el iterador no tiene capacidad para más elementos
     */
    public void insertar( T elem ) throws IteradorException
    {
        if( sigPosLibre >= elems.length )
            throw new IteradorException( "Límite del iterador alcanzado" );
        // Abre espacio para el nuevo elemento
        for( int i = sigPosLibre; i > 0; i-- )
        {
            elems[ i ] = elems[ i - 1 ];
        }
        sigPosLibre++;
        elems[ 0 ] = elem;
    }

    /**
     * Convierte el iterador a un String. <br>
     * <b>post: </b> Se retornó la representación en String del iterador. El String tiene el formato "[numeroElementos]: e1-e2-e3..-en", donde e1, e2, ..., en son los
     * elementos del iterador y numeroElementos su tamaño.
     * @return La representación en String del iterador
     */
    @Override
    public String toString( )
    {
        String resp = "[" + sigPosLibre + "]:";
        for( int i = 0; i < sigPosLibre; i++ )
        {
            resp += elems[ i ] + "-";
        }
        return resp;
    }

    /**
     * Retorna la siguiente posición libre del iterador (número de elementos sobre los que se está iterando).<br>
     * <b>post: </b> Se retornó la siguente posición libre en el iterador.
     * @return La siguente posición libre en el iterador
     */
    public int darSigPosLibre( )
    {
        return sigPosLibre;
    }

    /**
     * Retorna la posición del próximo elemento a ser visitado. <br>
     * <b>post: </b> Se retornó la posición del próximo elemento a ser visitado.
     * @return La posición del próximo elemento a ser visitado
     */
    public int darPosActual( )
    {
        return posActual;
    }

    /**
     * Retorna el tamaño del iterador (número máximo de elementos que puede recorrer). <br>
     * <b>post: </b> Se retornó el tamaño del iterador.
     * @return El tamaño del iterador
     */
    public int darLongitud( )
    {
        return elems.length;
    }
}
