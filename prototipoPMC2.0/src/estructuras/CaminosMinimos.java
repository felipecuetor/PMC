/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CaminosMinimos.java,v 1.10 2008/10/11 22:04:33 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abr 21, 2006
 * Autor: Juan Erasmo Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

import java.util.*;


/**
 * Clase utilizada para el cálculo de caminos mínimos desde un vértice origen usando el algoritmo de Dijkstra
 */
public class CaminosMinimos<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Vértice origen del camino
     */
    private Vertice<K, V, A> origen;

    /**
     * Conjunto de vértices que representan el grafo al que pertenece el vértice origen
     */
    private HashMap<K, NodoDijkstra<K, V, A>> nodos;

    /**
     * Lista de vértices que aún no han sido marcados (no se han incluido en el cálculo del camino mínimo)
     */
    private ArrayList<Vertice<K, V, A>> sinMarcar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase
     * @param pOrigen Vértice a partir del cual se van a calcular los caminos mínimos
     * @param pVertices Vértices que hacen parte del grafo al que pertenece el vértice origen
     */
    public CaminosMinimos( Vertice<K, V, A> pOrigen, Collection<Vertice<K, V, A>> pVertices )
    {
        // Guarda el origen de todos los caminos mínimos
        origen = pOrigen;
        // Crea la estructura que representa la información del camino mínimo asociada con cada vértice
        nodos = new HashMap<K, NodoDijkstra<K, V, A>>( );
        // Crea la estructura que representa el conjunto de vértices sin marcar
        sinMarcar = new ArrayList<Vertice<K, V, A>>( );
        NodoDijkstra<K, V, A> nodoInicial = null;
        // Recorre los vértices del grafo inicializando las estructuras
        for( Vertice<K, V, A> vert : pVertices )
        {
            NodoDijkstra<K, V, A> nodo = new NodoDijkstra<K, V, A>( vert );
            nodos.put( vert.darId( ), nodo );
            if( !vert.darId( ).equals( origen.darId( ) ) )
                sinMarcar.add( vert );
            else
                nodoInicial = nodo;
        }
        // Inicializa la estructura de caminos mínimos, definiendo el costo del camino especial hacia todos los sucesores del vértice de partida
        for( Arco<K, V, A> arco : origen.darSucesores( ) )
        {
            asignarCosto( arco.darVerticeDestino( ).darId( ), arco.darPeso( ), nodoInicial );
        }
        // Inicializa el costo del camino especial al vértice de origen como de costo 0
        asignarCosto( origen.darId( ), 0, null );
        origen.marcar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Asigna el costo mínimo entre los dos vértices especificados
     * @param idVertice El identificador del vértice destino
     * @param costo El costo entre los dos vértices
     * @param anterior El vértices origen
     */
    public void asignarCosto( K idVertice, int costo, NodoDijkstra<K, V, A> anterior )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( idVertice );
        nodo.asignarCostoMinimo( costo, anterior );
    }

    /**
     * Localiza el vértice no marcado con costo de camino especial mínimo, cuyo costo sea distinto de NodoDijkstra.INDEFINIDO, y lo marca. Si no lo encuentra retorna null
     * @return El siguiente vértice no marcado. Si no se encuentra se retornó null
     */
    public Vertice<K, V, A> darSiguienteVertice( )
    {
        Vertice<K, V, A> menorV = null;
        NodoDijkstra<K, V, A> menorN = null;

        // Recorrer los vertices sin marcar
        for( Vertice<K, V, A> vert : sinMarcar )
        {
            NodoDijkstra<K, V, A> nodo = nodos.get( vert.darId( ) );
            // Si no he inicializado el menor vertice, y el nodo en el que estoy tiene un costo minimo != indefinido
            if( menorV == null && nodo.darCostoMinimo( ) != NodoDijkstra.INDEFINIDO )
            {
                menorV = vert;
                menorN = nodo;
            }

            else if( menorN != null && nodo.darCostoMinimo( ) < menorN.darCostoMinimo( ) )
            {
                menorV = vert;
                menorN = nodo;
            }
        }

        if( menorV != null )
        {
            sinMarcar.remove( menorV );
            menorV.marcar( );
        }
        return menorV;
    }

    /**
     * Recalcula todos los caminos especiales de la siguiente manera: para cada sucesor no marcado se debe decidir si resulta mejor el camino antes calculado o si es
     * preferible utilizar el camino especial hasta el vértice que se acaba de incluir (y que llega como parámetro) y luego utilizar el arco que los une.
     * @param nuevoVert Nuevo vértice que se acaba de incluir en el camino
     */
    public void recalcularCaminosEspeciales( Vertice<K, V, A> nuevoVert )
    {
        NodoDijkstra<K, V, A> nuevoNodo = nodos.get( nuevoVert.darId( ) );
        int costoANuevoVert = nuevoNodo.darCostoMinimo( );
        for( Arco<K, V, A> arco : nuevoVert.darSucesores( ) )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( !vert.marcado( ) )
            {
                NodoDijkstra<K, V, A> nodo = nodos.get( vert.darId( ) );
                if( nodo.darCostoMinimo( ) > costoANuevoVert + arco.darPeso( ) )
                    nodo.asignarCostoMinimo( costoANuevoVert + arco.darPeso( ), nuevoNodo );
            }
        }
    }

    /**
     * Retorna el costo del camino mínimo que parte del origen y llega al vértice que se recibe como parámetro. Si no existe dicho camino, retorna -1.
     * @param vertice Vértice destino
     * @return Costo del camino mínimo desde el nodo origen al nodo destino. Si no existe el camino se retorna -1
     */
    public int darCostoCamino( Vertice<K, V, A> vertice )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( vertice.darId( ) );
        int costo = nodo.darCostoMinimo( );
        return costo == NodoDijkstra.INDEFINIDO ? -1 : costo;
    }

    /**
     * Devuelve los vértices por los cuales pasa el camino mínimo que lleva del origen al vértice que se recibe como parámetro.
     * @param vertice El vértice destino
     * @return Iterador sobre los vértices
     */
    public Iterador<Vertice<K, V, A>> darCaminoMinimo( Vertice<K, V, A> vertice )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( vertice.darId( ) );
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( nodos.size( ) );
        try
        {
            while( nodo != null )
            {
                itera.insertar( nodo.darVertice( ) );
                nodo = nodo.darPredecesor( );
            }
        }
        catch( Exception e )
        {
            // Nunca debería aparecer esta excepción
            e.printStackTrace( );
        }
        return itera;
    }
}
