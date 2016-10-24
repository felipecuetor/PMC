/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: GrafoDirigido.java,v 1.3 2008/10/12 04:50:53 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * Autor: J. Villalobos - Abr 14, 2006
 * Autor: Juan Erasmo Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package estructuras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Representa un grafo dirigido
 * 
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class GrafoDirigido<K, V extends IVertice<K>, A extends IArco> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Constantes
    // ------------------------------------------------------------------
    /**
     * Constante que representa un valor infinito
     */
    public static final int INFINITO = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tabla de hashing con los vértices
     */
    private HashMap<K, Vertice<K, V, A>> vertices;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo grafo vacío
     */
    public GrafoDirigido( )
    {
        vertices = new HashMap<K, Vertice<K, V, A>>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica si el vértice con el identificador dado existe en el grafo
     * @param idVertice Identificador del vértice
     * @return <code>true</code> si el vértice con el identificador dado existe o <code>false</code> en caso contrario
     */
    public boolean existeVertice( K idVertice )
    {
        return vertices.get( idVertice ) != null;
    }

    /**
     * Retorna los vértices del grafo.
     * @return Los vértices del grafo.
     */
    public Lista<V> darVertices( )
    {
        // Crear la lista
        Lista<V> vs = new Lista<V>( );

        // Recorrer los vértices y poblar la lista
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            vs.agregar( v.darInfoVertice( ) );
        }

        // Retornar la lista
        return vs;
    }

    /**
     * Devuelve todos los vértices del grafo.
     * </p>
     * Este método retorna los objetos Vertice, propios de está implementación.
     * @return Vertices del grafo
     */
    public Collection<Vertice<K, V, A>> darObjetosVertices( )
    {
        return vertices.values( );
    }

    /**
     * Retorna el arco entre los vértices ingresados por parametros
     * @param idV1 id del primer vértice
     * @param idV2 id del segundo vértice
     * @return El arco entre los vértices ingresados por parametros
     * @throws Exception si alguno de los vértices ingresados por parametros no existe en el grafo
     * @throws Exception si no existe un arco entre esos vértices
     */
    public IArco darArco( K idVerticeOrigen, K idVerticeDestino ) throws Exception, Exception
    {
        // Busca el primer vértice y luego busca el arco
        Vertice<K, V, A> vertice = darObjetoVertice( idVerticeOrigen );
        if( existeVertice( idVerticeDestino ) )
        {
            Arco<K, V, A> arco = vertice.darArco( idVerticeDestino );
            if( arco == null )
                throw new Exception( "No existe un arco entre los vértices seleccionados");
            else
                return arco.darInfoArco( );
        }
        else
            throw new Exception( "Vértice destino no existe");
    }

    /**
     * Indica si existe un arco entre los vértices ingresados por parametros
     * @param idV1 id del primer vértice
     * @param idV2 id del segundo vértice
     * @return <code>true</code> si existe un arco entre los vértices ingresado o <code>false</code> en caso contrario.
     * @throws Exception si alguno de los vértices ingresados por parametros no existe en el grafo
     */
    public boolean existeArco( K idVerticeOrigen, K idVerticeDestino ) throws Exception
    {
        // Busca el primer vértice y luego busca el arco
        Vertice<K, V, A> vertice = darObjetoVertice( idVerticeOrigen );
        if( existeVertice( idVerticeDestino ) )
            return vertice.darArco( idVerticeDestino ) != null;
        else
            throw new Exception( "Vértice destino no existe");
    }

    /**
     * Devuelve todos los arcos del grafo
     * @return Arcos del grafo
     */
    public Lista<A> darArcos( )
    {
        Lista<A> arcos = new Lista<A>( );

        // Recorre todos los vértices buscando los arcos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Recorrer los arcos del vértice y poblar la lista arcos
            for( Arco<K, V, A> arco : vertice.darSucesores( ) )
                arcos.agregar( arco.darInfoArco( ) );
        }
        return arcos;

    }

    /**
     * Retorna todos los objetos usados para representar un arco dentro del grafo.
     * @return Todos los objetos usados para representar un arco dentro del grafo.
     */
    public ArrayList<Arco<K, V, A>> darObjetosArco( )
    {
        ArrayList<Arco<K, V, A>> arcos = new ArrayList<Arco<K, V, A>>( );

        // Recorre todos los vértices buscando los arcos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
            arcos.addAll( vertice.darSucesores( ) );

        // Retornar la lista
        return arcos;
    }

    /**
     * Devuelve los id de los vértice sucedores a un vértice ingresado por parámetro
     * @param idVertice Identificador del vértice
     * @return Los id de los vértice sucedores a un vértice ingresado por parámetro
     * @throws Exception Si el vértice especificado no existe
     */
    public Lista<V> darSucesores( K idVertice ) throws Exception
    {
        Lista<V> lista = new Lista<V>( );
        for( Arco<K, V, A> a : darObjetoVertice( idVertice ).darSucesores( ) )
        {
            lista.agregar( a.darVerticeDestino( ).darInfoVertice( ) );
        }
        return lista;
    }

    /**
     * Devuelve los id de los vértice predecesores a un vértice ingresado por parámetro
     * @param idVertice Identificador del vértice
     * @return Los id de los vértice predecesores a un vértice ingresado por parámetro
     * @throws Exception Si el vértice especificado no existe
     */
    public Lista<V> darPredecesores( K idVertice ) throws Exception
    {
        Lista<V> lista = new Lista<V>( );
        for( Arco<K, V, A> a : darObjetoVertice( idVertice ).darPredecesores( ) )
        {
            lista.agregar( a.darVerticeDestino( ).darInfoVertice( ) );
        }
        return lista;
    }

    /**
     * Crea un nuevo vértice en el grafo
     * @param elemento Elemento del vértice
     * @throws Exception Si el vértice que se quiere agregar ya existe
     */
    public void agregarVertice( V elemento ) throws Exception
    {
        if( existeVertice( elemento.darId( ) ) )
            throw new Exception( "Elemento ya existe");
        else
        {
            Vertice<K, V, A> vertice = new Vertice<K, V, A>( elemento );
            vertices.put( elemento.darId( ), vertice );
        }
    }

    /**
     * Elimina el vértice identificado con el Identificador especificado
     * @param idVertice Identificador del vértice
     * @throws Exception suando el vértice especificado no existe
     */
    public void eliminarVertice( K idVertice ) throws Exception
    {
        // Localiza el vértice en el grafo
        Vertice<K, V, A> vertice = darObjetoVertice( idVertice );
        // Elimina todos los arcos que salen del vértice
        vertice.eliminarArcos( );
        // Localiza en el grafo todos los arcos que llegan a este vértice y los
        // elimina
        for( Vertice<K, V, A> vert : vertices.values( ) )
        {
            try
            {
                vert.eliminarArco( vertice.darId( ) );
            }
            catch( Exception e )
            {
                // En caso de no existir no hace nada
            }
        }
        // Elimina el vértice
        vertices.remove( vertice.darId( ) );
    }

    /**
     * Agrega un nuevo arco al grafo
     * @param idVerticeOrigen Identificador del vértice desde donde sale el arco
     * @param idVerticeDestino Identificador del vértice hasta donde llega el arco
     * @param infoArco Elemento del arco
     * @throws Exception Si alguno de los vértices especificados no existe
     * @throws ArcoYaExisteException Si ya existe un arco entre esos dos vértices
     */
    public void agregarArco( K idVerticeOrigen, K idVerticeDestino, A infoArco ) throws Exception, Exception
    {
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darObjetoVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darObjetoVertice( idVerticeDestino );
        // Crea el arco y lo agrega
        Arco<K, V, A> arco = new Arco<K, V, A>( verticeOrigen, verticeDestino, infoArco );
        verticeOrigen.agregarArco( arco );
    }

    /**
     * Elimina el arco que existe entre dos vértices
     * @param idVerticeOrigen Identificador del vértice desde donde sale el arco
     * @param idVerticeDestino Identificador del vértice hasta donde llega el arco
     * @throws Exception Cuando el vértice de salida no existe
     * @throws Exception Cuando el arco no existe
     */
    public void eliminarArco( K idVerticeOrigen, K idVerticeDestino ) throws Exception, Exception
    {
        // Obtiene el vértice y elimina el arco
        Vertice<K, V, A> verticeOrigen = darObjetoVertice( idVerticeOrigen );
        verticeOrigen.eliminarArco( idVerticeDestino );
    }

    /**
     * Retorna el número de arcos que tiene el grafo
     * @return el número de arcos que tiene el grafo
     */
    public int darNArcos( )
    {
        return darArcos( ).darLongitud( );
    }

    /**
     * Devuelve el orden del grafo.
     * </p>
     * El orden de un grafo se define con el número de vértices que tiene este
     * @return Orden del grafo
     */
    public int darOrden( )
    {
        return vertices.size( );
    }

    /**
     * Verifica si existe un camino entre los dos vértices especificados
     * @param idVerticeOrigen Vértice de origen
     * @param idVerticeDestino Vértice de destino
     * @return <code>true</code> si hay camino entre los dos vértices especificados o <code>false</code> de lo contrario
     * @throws Exception Si no existe alguno de los dos vértices dados
     */
    public boolean hayCamino( K idVerticeOrigen, K idVerticeDestino ) throws Exception
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darObjetoVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darObjetoVertice( idVerticeDestino );
        return verticeOrigen.hayCamino( verticeDestino );
    }

    /**
     * Retorna el camino más corto (de menor longitud) entre el par de vértices especificados
     * @param idVerticeOrigen Vértice en el que inicia el camino
     * @param idVerticeDestino Vértice en el que termina el camino
     * @return El camino más corto entre el par de vértices especificados
     * @throws Exception Si alguno de los dos vértices no existe
     */
    public Camino<K, V, A> darCaminoMasCorto( K idVerticeOrigen, K idVerticeDestino ) throws Exception
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darObjetoVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darObjetoVertice( idVerticeDestino );
        // Le pide al vértice de origen que localice el camino
        return verticeOrigen.darCaminoMasCorto( verticeDestino );
    }

    /**
     * Retorna el camino más barato (de menor costo) entre el par de vértices especificados
     * @param idVerticeOrigen Vértice en el que inicia el camino
     * @param idVerticeDestino Vértice en el que termina el camino
     * @return El camino más barato entre el par de vértices especificados
     * @throws Exception Si alguno de los dos vértices no existe
     */
    public Camino<K, V, A> darCaminoMasBarato( K idVerticeOrigen, K idVerticeDestino ) throws Exception
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darObjetoVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darObjetoVertice( idVerticeDestino );
        // Le pide al vértice de origen que localice el camino
        return verticeOrigen.darCaminoMasBarato( verticeDestino );
    }

    /**
     * Indica si hay un ciclo en el grafo que pase por el vértice especificado
     * @param idVertice El identificador del vértice
     * @return <code>true</code> si existe el ciclo o <code>false</code> en caso contrario
     * @throws Exception Si el vértice especificado no existe
     */
    public boolean hayCiclo( K idVertice ) throws Exception
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el vértice
        Vertice<K, V, A> vertice = darObjetoVertice( idVertice );
        // Le pregunta al vértice de origen si a partir de él hay un ciclo
        return vertice.hayCiclo( );
    }

    /**
     * Indica si en el grafo hay camino hamiltoniano
     * @return true si hay camino hamiltoniano o false en caso contrario
     */
    public boolean hayCaminoHamilton( )
    {
        // Recorre todos los vértices del grafo buscando un camino de Hamilton
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Borra todas las marcas presentes en el grafo
            reiniciarMarcas( );
            if( vertice.hayCaminoHamilton( 0, darOrden( ) ) )
                return true;
        }
        return false;
    }

    /**
     * Indica si en el grafo hay ciclo hamiltoniano
     * @return true si hay ciclo hamiltoniano o false en caso contrario
     */
    public boolean hayCicloHamilton( )
    {
        // Como se trata de un ciclo no es necesario buscar en todos los nodos.
        // Con que se encuentre un solo nodo del que no pueda partir el ciclo
        // hamiltoniano podemos concluir que el grafo no tiene ciclos
        // hamiltonianos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            reiniciarMarcas( );
            if( vertice.hayCicloHamilton( 0, darOrden( ), vertice.darId( ) ) )
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Retorna el camino hamiltoniano que hay en el grafo
     * @param El camino hamiltoniano que hay en el grafo. En caso de que no haya se retorna null
     */
    public Camino<K, V, A> darCaminoHamilton( )
    {
        // Recorre todos los vértices del grafo buscando un camino de Hamilton
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Borra todas las marcas presentes en el grafo
            reiniciarMarcas( );
            Camino<K, V, A> hamilton = new Camino<K, V, A>( vertice.darInfoVertice( ) );
            if( vertice.darCaminoHamilton( hamilton, darOrden( ) ) )
                return hamilton;
        }
        return null;
    }

    /**
     * Retorna el ciclo hamiltoniano que hay en el grafo
     * @return El ciclo de Hamilton que hay en el grafo. En caso de que no haya se retorna null
     */
    public Camino<K, V, A> darCicloHamilton( )
    {
        // Como se trata de un ciclo no es necesario buscar en todos los nodos.
        // Con que se encuentre un solo nodo del que no pueda partir el ciclo
        // hamiltoniano podemos concluir que el grafo no tiene ciclos
        // hamiltonianos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            reiniciarMarcas( );
            Camino<K, V, A> ch = new Camino<K, V, A>( vertice.darInfoVertice( ) );
            if( vertice.darCicloHamilton( ch, darOrden( ) ) )
                return ch;
            else
                return null;
        }
        return null;
    }

    /**
     * Calcula todos los caminos mínimos desde el vértice dado hacia los demás vértices del grafo
     * @param idVertice El identificador del vértice
     * @return Los caminos mínimos desde el vértice especificado hacía los demás nodos
     * @throws Exception Si el vértice especificado no existe
     */
    public CaminosMinimos<K, V, A> dijkstra( K idVertice ) throws Exception
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el vértice
        Vertice<K, V, A> vertice = darObjetoVertice( idVertice );
        // Inicializa la estructura que va a permitir representar los caminos
        // mï¿½nimos que van
        // desde vértice dado a todos los demï¿½s vértices del grafo
        CaminosMinimos<K, V, A> minimos = new CaminosMinimos<K, V, A>( vertice, darObjetosVertices( ) );
        return vertice.dijkstra( minimos );
    }

    /**
     * Indica si el grafo es conexo
     * @return <code>true</code> si el grafo es conexo o <code>false</code> en caso contrario
     */
    public boolean esConexo( )
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        return contarComponentesConexos( ) <= 1 ? true : false;
    }

    /**
     * Indica si el grafo es fuertemente conexo
     * @return true si el grafo es fuertemente conexo o false en caso contrario
     */
    public boolean esFuertementeConexo( )
    {
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            for( Vertice<K, V, A> v2 : vertices.values( ) )
                try
                {
                    if( v != v2 && !hayCamino( v.darId( ), v2.darId( ) ) )
                        return false;
                }
                catch( Exception e )
                {
                    // Esto no deberÃ­a suceder
                }
        }
        return true;
    }

    /**
     * Indica si en el grafo no hay ciclos
     * @return <code>true</code> si en el grafo no hay ciclos o <code>false</code> en caso contrario
     */
    public boolean esAciclico( )
    {
        // Recorrer todos los vertices
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            try
            {
                reiniciarMarcas( );
                // Si se encuentra un ciclo el grafo no es aciclico
                if( hayCiclo( vertice.darId( ) ) )
                    return false;
            }
            catch( Exception e )
            {
                // Esto nunca va a ocurrir
            }
        }
        return true;
    }

    /**
     * Indica si el grafo es completo. Un grafo es completo si existe un arco entre cualquier pareja de vértices en el grafo.
     * @return <code>true</code> si el grafo es completo o <code>false</code> en caso contrario
     */
    public boolean esCompleto( )
    {
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            for( Vertice<K, V, A> v2 : vertices.values( ) )
            {
                if( !v.darId( ).equals( v2.darId( ) ) && ! ( v.esSucesor( v2.darId( ) ) || v2.esSucesor( v.darId( ) ) ) )
                    return false;
            }
        }
        return true;
    }

    /**
     * Retorna el recorrido plano sobre el árbol
     * @return El recorrido plano sobre el árbol
     */
    public Iterador<Vertice<K, V, A>> darRecorridoPlano( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            try
            {
                itera.agregar( v );
            }
            catch( Exception e )
            {
                // Nunca debe ocurrir esta excepciï¿½n
            }
        }
        return itera;
    }

    /**
     * Retorna el recorrido por profundidad sobre el grafo
     * @return El recorrido por profundidad sobre el grafo
     */
    public Iterador<Vertice<K, V, A>> darRecorridoProfundidad( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            if( !v.marcado( ) )
                v.darRecorridoProfundidad( itera );
        }
        return itera;
    }

    /**
     * Retorna el recorrido por niveles del grafo
     * @return El recorrido por niveles
     */
    public Iterador<Vertice<K, V, A>> darRecorridoNiveles( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        ColaEncadenada<Vertice<K, V, A>> frenteExploracion = new ColaEncadenada<Vertice<K, V, A>>( );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            if( !v.marcado( ) )
            {
                frenteExploracion.insertar( v );
                while( frenteExploracion.darLongitud( ) != 0 )
                {
                    try
                    {
                        Vertice<K, V, A> actual = frenteExploracion.tomarElemento( );
                        if( !actual.marcado( ) )
                        {
                            actual.marcar( );
                            itera.agregar( actual );
                            for( Arco<K, V, A> a : actual.darSucesores( ) )
                            {
                                Vertice<K, V, A> sucesor = a.darVerticeDestino( );
                                if( !sucesor.marcado( ) )
                                    frenteExploracion.insertar( sucesor );
                            }
                        }
                    }
                    catch( Exception e )
                    {
                        // Esta excepciï¿½n nunca deberï¿½a ocurrir
                    }
                }
            }
        }
        return itera;
    }

    /**
     * Retorna el grafo central del grafo
     * @return El grafo central del grafo o <code>null</code> en caso de que todos su vÃ©rtices tengan excentricidad infinita
     */
    public Vertice<K, V, A> darCentro( )
    {
        int menorExcentricidad = 0;
        Vertice<K, V, A> centro = null;

        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Calcular la excentricidad del vertice
            try
            {
                int excen = darExcentricidad( vertice.darId( ) );

                if( centro == null && excen != INFINITO )
                {
                    centro = vertice;
                    menorExcentricidad = excen;
                }
                else if( excen != INFINITO && menorExcentricidad > excen )
                {
                    centro = vertice;
                    menorExcentricidad = excen;
                }
            }
            catch( Exception e )
            {
                // Esto no deberÃ­a suceder
            }

        }
        return centro;
    }

    /**
     * Calcula la excentricidad de un vértice del grafo
     * @param idVertice Id del vertice
     * @return La excentricidad del vértice ingresado por parámetro, o Grafo.INFINITO en caso no poder alcanzar alguno de los vértices del grafo.
     * @throws Exception Si el vértice buscado no existe
     */
    public int darExcentricidad( K idVertice ) throws Exception
    {
        CaminosMinimos<K, V, A> cm = dijkstra( idVertice );

        Integer mayorPeso = null;
        for( Vertice<K, V, A> destino : vertices.values( ) )
        {
            // Obtener el costo del camino minimo de idVertice a destino
            int costo = cm.darCostoCamino( destino );

            if( costo == -1 )
                return INFINITO;
            if( mayorPeso == null )
                mayorPeso = costo;
            else if( mayorPeso < costo )
            {
                mayorPeso = costo;
            }
        }
        return mayorPeso.intValue( );
    }

    /**
     * El peso de un grafo es la suma de los pesos de todos sus arcos
     * @throws Exception 
     */
    public int darPeso( ) throws Exception
    {
        int peso = 0;
        Lista<A> arcos = darArcos( );
        for( int i = 0; i < arcos.darLongitud( ); i++ )
        {
            peso += arcos.darElemento( i ).darPeso( );
        }
        return peso;
    }

    /**
     * Retorna el árbol parcial de recubrimiento del grafo que parte del vértice dado
     * @param idVertice El identificador del vértice
     * @return El árbol de recubrimiento parcial del grafo que parte del vértice dado
     * @throws Exception Si el vertice buscado no existe;
     */
    public GrafoDirigido<K, V, A> darArbolParcialRecubrimiento( K idVertice ) throws Exception
    {
        Vertice<K, V, A> vertice = darObjetoVertice( idVertice );
        reiniciarMarcas( );

        GrafoDirigido<K, V, A> arbolPR = new GrafoDirigido<K, V, A>( );
        vertice.darArbolParcialRecubrimiento( arbolPR );
        return arbolPR;
    }


    /**
     * Cuenta el número de subgrafos que son conexos
     * @return El número de grafos que son conexos
     */
    public int contarComponentesConexos( )
    {
        int compConexos = 0;

        reiniciarMarcas( );

        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            if( !vertice.marcado( ) )
            {
                vertice.marcarAdyacentes( );
                compConexos++;
            }
        }
        return compConexos;
    }

    /**
     * Devuelve el vértice identificado con el identificador especificado
     * @param idVertice Identificador del vértice
     * @return Vértice buscado
     * @throws Exception Excepción generada cuando el vértice buscado no existe en el grafo
     */
    private Vertice<K, V, A> darObjetoVertice( K idVertice ) throws Exception
    {
        Vertice<K, V, A> vertice = vertices.get( idVertice );
        if( vertice == null )
        {
            throw new Exception( "El vértice buscado no existe en el grafo");
        }
        return vertice;
    }

    /**
     * Devuelve el vértice identificado con el identificador especificado
     * @param idVertice Identificador del vértice
     * @return Vértice buscado
     * @throws Exception Excepción generada cuando el vértice buscado no existe en el grafo
     */
    public V darVertice( K idVertice ) throws Exception
    {
        return darObjetoVertice( idVertice ).darInfoVertice( );
    }

    /**
     * Borra las marcas de todos los vértices del grafo
     */
    private void reiniciarMarcas( )
    {
        // Elimina todas las marcas presentes en los vértices del grafo
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            vertice.desmarcar( );
        }
    }

}
