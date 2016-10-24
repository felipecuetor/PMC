/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Camino.java,v 1.7 2008/04/19 03:58:41 jua-gome Exp $
 * Universidad de los Andes (Bogotï¿½ - Colombia)
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abril 14, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;


/**
 * Representa un camino en un grafo
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Camino<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los arcos del camino
     */
    private Lista<A> arcos;

    /**
     * Lista con los vértices del camino
     */
    private Lista<V> vertices;

    /**
     * Origen del camino
     */
    private V origen;

    /**
     * Costo total del camino
     */
    private int costo;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del camino
     * @param origen Vértice de origen del camino
     */
    public Camino( V origen )
    {
        // Inicializar los atributos del camino
        vertices = new Lista<V>( );
        arcos = new Lista<A>( );
        costo = 0;
        this.origen = origen;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un arco al final del camino
     * @param arco Arco a agregar
     */
    public void agregarArcoFinal( V vertice, A arco )
    {
        arcos.agregar( arco );
        vertices.agregar( vertice );
        costo += arco.darPeso( );
    }

    /**
     * Agrega un arco al comienzo del camino.
     * @param nuevoOrigen Nuevo origen del camino
     * @param arco Arco que va del nuevo origen al antiguo origen del camino
     */
    public void agregarArcoComienzo( V nuevoOrigen, A arco )
    {
        arcos.insertar( arco, 0 );
        vertices.insertar( origen, 0 );
        origen = nuevoOrigen;
        costo += arco.darPeso( );
    }

    /**
     * Concatena todos los arcos del camino especificado al final del camino
     * @param camino Camino a concatenar
     */
    public void concatenar( Camino<K, V, A> camino )
    {
        // Agregar los arcos y vertices del camino a concatenar ignorando el origen del camino ingresado por parámetro
        for( int i = 0; i < camino.arcos.darLongitud( ); i++ )
            agregarArcoFinal( camino.vertices.darElemento( i ), camino.arcos.darElemento( i ) );
    }

    /**
     * Elimina el último arco
     */
    public void eliminarUltimoArco( )
    {
        if( arcos.darLongitud( ) >= 1 )
        {
            A arco = arcos.darElemento( arcos.darLongitud( ) - 1 );
            arcos.eliminar( arcos.darLongitud( ) - 1 );
            vertices.eliminar( vertices.darLongitud( ) - 1 );
            costo -= arco.darPeso( );
        }
    }

    /**
     * Reinicia el camino conservando el origen
     */
    public void reiniciar( )
    {
        arcos.vaciar( );
        vertices.vaciar( );
        costo = 0;
    }

    /**
     * Devuelve la longitud del camino
     * @return Longitud del camino
     */
    public int darLongitud( )
    {
        return arcos.darLongitud( );
    }

    /**
     * Devuelve el costo del camino
     * @return Costo del camino
     */
    public int darCosto( )
    {
        return costo;
    }

    /**
     * Devuelve los vértices por los cuales pasa el camino
     * @return Iterador sobre los vértices
     */
    public Iterador<V> darSecuenciaVertices( )
    {
        // Crear una lista auxiliar y agregarle el origen
        Lista<V> aux = new Lista<V>( );
        aux.agregar( origen );

        // Poblara la lista auxiliar con los vértices del camino
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            aux.agregar( vertices.darElemento( i ) );
        }

        // Retornar el iterador
        return aux.darIterador( );
    }

    /**
     * Retorna el origen del camino
     * @return El vertice desde el que se origina el camino
     */
    public V darOrigen( )
    {
        return origen;
    }
}
