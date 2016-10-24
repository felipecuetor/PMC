/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Vertice.java,v 1.2 2008/09/30 18:54:45 alf-mora Exp $
 * Universidad de los Andes (Bogotï¿½ - Colombia)
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n 
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

import java.util.*;


/**
 * Representa un vértice del grafo
 * 
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Vertice<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento contenido en el vértice
     */
    private V infoVertice;

    /**
     * Lista de arcos hacia los sucesores de éste vértice
     */
    private ArrayList<Arco<K, V, A>> predecesores;

    /**
     * Lista de arcos hacia los sucesores de éste vértice
     */
    private ArrayList<Arco<K, V, A>> sucesores;

    /**
     * Marca del nodo
     */
    private boolean marcado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del vértice
     * @param pInfoVertice Elemento contenido en el vértice
     */
    public Vertice( V pInfoVertice )
    {
        infoVertice = pInfoVertice;
        sucesores = new ArrayList<Arco<K, V, A>>( );
        predecesores = new ArrayList<Arco<K, V, A>>( );
        marcado = false;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el Id del vértice
     * @return Identificador del vértice
     */
    public K darId( )
    {
        return infoVertice.darId( );
    }

    /**
     * Devuelve la informaciï¿½n del vértice
     * @return Informaciï¿½n del vértice
     */
    public V darInfoVertice( )
    {
        return infoVertice;
    }

    /**
     * Devuelve los arcos hacia los sucesores del vértice
     * @return Arcos hacia los sucesores del vértice
     */
    public ArrayList<Arco<K, V, A>> darSucesores( )
    {
        return sucesores;
    }

    /**
     * Devuelve los arcos hacia los predecesores del vértice
     * @return Arcos hacia los predecesores del vértice
     */
    public ArrayList<Arco<K, V, A>> darPredecesores( )
    {
        return predecesores;
    }

    /**
     * Devuelve el arco (si existe) hacia el vértice especificado. Devuelve null si no existe.
     * @param idDestino Identificador del vértice destino
     * @return Arco hacia el vértice especificado, null si no existe
     */
    public Arco<K, V, A> darArco( K idDestino )
    {
        // Busca secuencialmente el arco
        for( int i = 0; i < sucesores.size( ); i++ )
        {
            Arco<K, V, A> arco = sucesores.get( i );
            if( idDestino.equals( arco.darVerticeDestino( ).darId( ) ) )
            {
                return arco;
            }
        }
        return null;
    }

    /**
     * Devuelve la marca del vértice
     * @return Indica si el vértice se encuentra marcado
     */
    public boolean marcado( )
    {
        return marcado;
    }

    /**
     * Marca el vértice
     */
    public void marcar( )
    {
        marcado = true;
    }

    /**
     * Elimina la marca del vértice
     */
    public void desmarcar( )
    {
        marcado = false;
    }

    /**
     * Elimina un arco del vértice
     * @param idDestino Identificador del vértice destino del arco que se quiere eliminar
     * @throws ArcoNoExisteException Excepción generada cuando el arco no existe
     */
    public void eliminarArco( K idDestino ) throws ArcoNoExisteException
    {
        Arco<K, V, A> arco = darArco( idDestino );
        if( arco == null )
        {
            throw new ArcoNoExisteException( "El arco no existe", darId( ), idDestino );
        }
        sucesores.remove( arco );
        arco.darVerticeDestino( ).eliminarArcoPredecesor( arco );
    }

    /**
     * Elimina un arco de los predecesores del vértice
     * @param arco Arco a eliminar
     */
    private void eliminarArcoPredecesor( Arco<K, V, A> arco )
    {
        predecesores.remove( arco );
    }

    /**
     * Agrega un arco al vértice
     * @param arco Arco a agregar al vértice
     * @throws ArcoYaExisteException Excepción generada cuando ya hay un arco hacia el mismo vértice
     */
    public void agregarArco( Arco<K, V, A> arco ) throws ArcoYaExisteException
    {
        K idDestino = arco.darVerticeDestino( ).darId( );
        if( esSucesor( idDestino ) )
        {
            throw new ArcoYaExisteException( "El arco ya existe", darId( ), idDestino );
        }
        sucesores.add( arco );
        arco.darVerticeDestino( ).agregarArcoPredecesor( arco );
    }

    /**
     * Agrega un arco al vértice
     * @param arco Arco a agregar al vértice
     * @throws ArcoYaExisteException Excepción generada cuando ya hay un arco hacia el mismo vértice
     */
    private void agregarArcoPredecesor( Arco<K, V, A> arco ) throws ArcoYaExisteException
    {
        predecesores.add( arco );
    }

    /**
     * Elimina todos los arcos del vértice
     */
    public void eliminarArcos( )
    {
        sucesores.clear( );
    }

    /**
     * Verifica si el arco especificado es sucesor de éste
     * @param idDestino Identificador del vértice destino
     * @return True si es sucesor, False si no
     */
    public boolean esSucesor( K idDestino )
    {
        return darArco( idDestino ) != null;
    }

    /**
     * Devuelve el número de sucesores del vértice
     * @return Número de sucesores del vértice
     */
    public int darNumeroSucesores( )
    {
        return sucesores.size( );
    }

    /**
     * Devuelve el número de predecesores del vértice
     * @return Número de predecesores del vértice
     */
    public int darNumeroPredecesores( )
    {
        return predecesores.size( );
    }

    /**
     * Indica si hay un camino simple del vértice actual al vértice que se recibe como parámetro
     * @param destino Vértice destino de la búsqueda
     * @return True si existe, False si no
     */
    public boolean hayCamino( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return true;
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) && vert.hayCamino( destino ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve el camino mas corto al vértice especificado
     * @param destino Vértice destino
     * @return Camino mas corto hacia el vértice especificado
     */
    public Camino<K, V, A> darCaminoMasCorto( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return new Camino<K, V, A>( this.infoVertice );
        else
        {
            marcar( );
            ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
            Camino<K, V, A> camino = null;
            Arco<K, V, A> arcoEnCamino = null;
            for( int i = 0; i < sucesores.size( ); i++ )
            {
                Arco<K, V, A> arco = sucesores.get( i );
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    Camino<K, V, A> cam = vert.darCaminoMasCorto( destino );
                    if( cam != null )
                    {
                        if( camino == null || cam.darLongitud( ) < camino.darLongitud( ) )
                        {
                            camino = cam;
                            arcoEnCamino = arco;
                        }
                    }
                }
            }
            desmarcar( );
            if( camino == null )
                return null;
            else
            {
                camino.agregarArcoComienzo( darInfoVertice( ), arcoEnCamino.darInfoArco( ) );
                return camino;
            }
        }
    }

    /**
     * Devuelve el camino mas barato hacia el vértice especificado
     * @param destino Vértice destino
     * @return Camino mas barato al vértice especificado
     */
    public Camino<K, V, A> darCaminoMasBarato( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return new Camino<K, V, A>( this.infoVertice );
        else
        {
            marcar( );
            ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
            Camino<K, V, A> camino = null;
            Arco<K, V, A> arcoEnCamino = null;
            for( int i = 0; i < sucesores.size( ); i++ )
            {
                Arco<K, V, A> arco = sucesores.get( i );
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    Camino<K, V, A> cam = vert.darCaminoMasBarato( destino );
                    if( cam != null )
                    {
                        if( camino == null || cam.darCosto( ) + arco.darPeso( ) < camino.darCosto( ) + arcoEnCamino.darPeso( ) )
                        {
                            camino = cam;
                            arcoEnCamino = arco;
                        }
                    }
                }
            }
            desmarcar( );
            if( camino == null )
                return null;
            else
            {
                camino.agregarArcoComienzo( darInfoVertice( ), arcoEnCamino.darInfoArco( ) );
                return camino;
            }
        }
    }

    /**
     * Indica si hay un ciclo que parte del vértice actual
     * 
     * @return True si existe un ciclo, False si no
     */
    public boolean hayCiclo( )
    {
        ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
        for( Arco<K, V, A> arco : sucesores )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( vert.hayCamino( this ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si hay un camino de Hamilton que pasa por el vértice actual, teniendo en cuenta que en dicho camino<br>
     *  ya se ha pasado por un cierto número de vértice (longActual) y que debe pasar por todos lo vértices del grafo (ordenGrafo)
     * @param longActual Longitud actual del camino
     * @param ordenGrafo Orden del grafo
     * @return True si existe, False si no
     */
    public boolean hayCaminoHamilton( int longActual, int ordenGrafo )
    {
        longActual++;
        if( longActual == ordenGrafo )
            return true;
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) && vert.hayCaminoHamilton( longActual, ordenGrafo ) )
                {
                    return true;
                }
            }
            desmarcar( );
            return false;
        }
    }

    /**
     * Indica si hay un camino de Euler que pasa por el vértice actual, teniendo en cuenta que en dicho camino ya se ha pasado
     *  por un cierto número de arcos (longActual) y que debe pasar por todos lo arcos del grafo (nArcos)
     * @param longitudActual Longitud actual del camino
     * @param nArcos Número de arcos en el grafo
     * @param matriz Matriz de adyacencia utilizada para marcar arcos
     * @return <code>true</code> si el camino existe, <code>false</code> en caso contrario
     */
    public boolean hayCaminoEuler( int longitudActual, int nArcos, MatrizAdyacencia<K, V, A> matriz )
    {
        if( longitudActual == nArcos )
            return true;
        else
        {
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                try
                {
                    if( !matriz.marcado( darId( ), arco.darVerticeDestino( ).darId( ) ) )
                    {
                        matriz.marcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        if( arco.darVerticeDestino( ).hayCaminoEuler( longitudActual + 1, nArcos, matriz ) )
                            return true;
                        matriz.desmarcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                    }
                }
                catch( ArcoNoExisteException e )
                {
                    // Esto no debería suceder
                }
                catch( VerticeNoExisteException e )
                {
                    // Esto no debería suceder
                }
            }
            return false;
        }
    }

    /**
     * Devuelve el camino de Hamilton
     * @param hamilton Camino de Hamilton
     * @param ordenGrafo Orden del grafo
     * @return True si existe, False si no
     */
    public boolean darCaminoHamilton( Camino<K, V, A> hamilton, int ordenGrafo )
    {
        if( hamilton.darLongitud( ) + 1 == ordenGrafo )
        {
            return true;
        }
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    hamilton.agregarArcoFinal( arco.darVerticeDestino( ).darInfoVertice( ), arco.darInfoArco( ) );
                    if( vert.darCaminoHamilton( hamilton, ordenGrafo ) )
                        return true;
                    hamilton.eliminarUltimoArco( );
                }
            }
            desmarcar( );
        }
        return false;
    }

    /**
     * Devuelve un camino de Euler.
     * @param camino Variable en la que se construirá el camino
     * @param nArcos Número de arcos en el grafo
     * @param matriz Matriz de adyacencia utilizada para marcar arcos.
     * @return <code>true</code> si existe un camino de Euler o <code>false</code> de lo contrario
     */
    public boolean darCaminoEuler( Camino<K, V, A> camino, int nArcos, MatrizAdyacencia<K, V, A> matriz )
    {
        if( camino.darLongitud( ) == nArcos )
            return true;
        else
        {
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                try
                {
                    if( !matriz.marcado( darId( ), arco.darVerticeDestino( ).darId( ) ) )
                    {
                        matriz.marcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        camino.agregarArcoFinal( arco.darVerticeDestino( ).darInfoVertice( ), arco.darInfoArco( ) );
                        if( arco.darVerticeDestino( ).darCaminoEuler( camino, nArcos, matriz ) )
                            return true;
                        matriz.desmarcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        camino.eliminarUltimoArco( );
                    }
                }
                catch( ArcoNoExisteException e )
                {
                    // Esto no deberÃ­a suceder
                }
                catch( VerticeNoExisteException e )
                {
                    // Esto no deberÃ­a suceder
                }
            }
            return false;
        }
    }

    /**
     * Indica si hay un ciclo de Hamilton que pasa por el vértice actual, teniendo en cuenta que en dicho camino ya se ha pasado por un<br>
     *  cierto número de vertices (longActual) y que debe pasar por todos los vertices del grafo.
     * @param longitudActual Longitud actual del camino
     * @param ordenGrafo Número de vertices en el grafo.
     * @param K Id del vértice desde el que se origino el ciclo.
     * @return <code>true</code> si el camino existe, <code>false</code> en caso contrario
     */
    public boolean hayCicloHamilton( int longActual, int ordenGrafo, K origenCiclo )
    {
        longActual++;
        if( longActual == ordenGrafo && esSucesor( origenCiclo ) )
            return true;
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) && vert.hayCicloHamilton( longActual, ordenGrafo, origenCiclo ) )
                {
                    return true;
                }
            }
            desmarcar( );
            return false;
        }
    }

    /**
     * Indica si hay un ciclo de Euler que pasa por el vértice actual, teniendo en cuenta que en dicho camino ya se ha pasado por un cierto<br>
     *  número de arcos (longActual) y que debe pasar por todos lo arcos del grafo (nArcos)
     * @param longActual Número de arcos recorridos hasta el momento
     * @param nArcos Número de arcos en el grafo
     * @param matriz Matriz de adyacencia utilizada para marcar los arcos
     * @param origenCiclo Id del vertice del cual partio el ciclo
     * @return <code>true</code> si se encuentre un ciclo de Euler o <code>false</code> en caso contrario
     */
    public boolean hayCicloEuler( int longActual, int nArcos, MatrizAdyacencia<K, V, A> matriz, K origenCiclo )
    {
        try
        {
            if( longActual + 1 == nArcos && esSucesor( origenCiclo ) && !matriz.marcado( darId( ), origenCiclo ) )
                return true;
            else
            {
                for( Arco<K, V, A> arco : darSucesores( ) )
                {

                    if( !matriz.marcado( darId( ), arco.darVerticeDestino( ).darId( ) ) )
                    {
                        matriz.marcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        if( arco.darVerticeDestino( ).hayCicloEuler( longActual + 1, nArcos, matriz, origenCiclo ) )
                            return true;
                        matriz.desmarcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                    }

                }
                return false;
            }
        }
        catch( ArcoNoExisteException e )
        {
            // Esto no deberia suceder
        }
        catch( VerticeNoExisteException e )
        {
            // Esto no deberia suceder
        }
        return false;
    }

    /**
     * Retorna un ciclo hamiltoniano que pase por este vértice
     * @param hamilton Camino formado hasta ahora
     * @param ordenGrafo Orden del grafo
     * @return <code>true</code> Si existe un ciclo hamiltoniano o <code>false</code> en caso contrario
     */
    public boolean darCicloHamilton( Camino<K, V, A> hamilton, int ordenGrafo )
    {
        // Obtener el primer nodo del camino
        V origenCamino = hamilton.darOrigen( );

        if( hamilton.darLongitud( ) + 1 == ordenGrafo && esSucesor( origenCamino.darId( ) ) )
        {
            // Agregar el arco que cierra el camino
            hamilton.agregarArcoFinal( darArco( origenCamino.darId( ) ).darVerticeDestino( ).darInfoVertice( ), darArco( origenCamino.darId( ) ).darInfoArco( ) );
            return true;
        }
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    hamilton.agregarArcoFinal( arco.darVerticeDestino( ).darInfoVertice( ), arco.darInfoArco( ) );
                    if( vert.darCicloHamilton( hamilton, ordenGrafo ) )
                        return true;
                    hamilton.eliminarUltimoArco( );
                }
            }
            desmarcar( );
        }
        return false;
    }

    /**
     * Retorna un ciclo de euler que pasa por este vértice
     * @param euler Camino formado hasta ahora
     * @param nArcos Número de arcos en el grafo
     * @param matriz Matriz de adyacencia utilizada para marcar arcos
     * @return <code>true</code> si existe el ciclo de euler o <code>false</code> en caso contrario
     */
    public boolean darCicloEuler( Camino<K, V, A> euler, int nArcos, MatrizAdyacencia<K, V, A> matriz )
    {
        // Obtener el primer nodo del camino
        K origenCiclo = euler.darOrigen( ).darId( );

        // Calcular la longitud actual de camino
        int longActual = euler.darLongitud( );

        try
        {
            if( longActual + 1 == nArcos && esSucesor( origenCiclo ) && !matriz.marcado( darId( ), origenCiclo ) )
            {
                euler.agregarArcoFinal( darArco( origenCiclo ).darVerticeDestino( ).darInfoVertice( ), darArco( origenCiclo ).darInfoArco( ) );
                return true;
            }
            else
            {
                for( Arco<K, V, A> arco : darSucesores( ) )
                {

                    if( !matriz.marcado( darId( ), arco.darVerticeDestino( ).darId( ) ) )
                    {
                        matriz.marcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        euler.agregarArcoFinal( arco.darVerticeDestino( ).darInfoVertice( ), arco.darInfoArco( ) );
                        if( arco.darVerticeDestino( ).darCicloEuler( euler, nArcos, matriz ) )
                            return true;
                        matriz.desmarcarArco( darId( ), arco.darVerticeDestino( ).darId( ) );
                        euler.eliminarUltimoArco( );
                    }

                }
                return false;
            }
        }
        catch( ArcoNoExisteException e )
        {
            // Esto no deberia suceder
        }
        catch( VerticeNoExisteException e )
        {
            // Esto no deberia suceder
        }
        return false;
    }

    /**
     * Incluye en el cálculo de caminos mínimos el vértice actual
     * @param minimos El objeto sobre el que se está haciendo el calculo
     * @return El objeto en el que se están calculando los caminos mínimos
     */
    public CaminosMinimos<K, V, A> dijkstra( CaminosMinimos<K, V, A> minimos )
    {
        Vertice<K, V, A> vert = minimos.darSiguienteVertice( );
        while( vert != null )
        {
            minimos.recalcularCaminosEspeciales( vert );
            vert = minimos.darSiguienteVertice( );
        }
        return minimos;
    }

    /**
     * Construye un iterador con el recorrido por profundidad que parte del vértice.
     * @param itera Iterador en donde se agregarán los elementos del recorrido por profundidad que parte del vértice.
     */
    public void darRecorridoProfundidad( IteradorSimple<Vertice<K, V, A>> itera )
    {
        try
        {
            itera.agregar( this );
        }
        catch( IteradorException e )
        {
            // Nunca deberï¿½a ocurrir esta excepciï¿½n
        }
        marcar( );
        for( Arco<K, V, A> arco : darSucesores( ) )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( !vert.marcado( ) )
            {
                vert.darRecorridoProfundidad( itera );
            }
        }
    }

    /**
     * Completa un arbol parcial de recubrimiento con los vértices no marcados del grafo que sean sucesores de este vértice
     * @param arbolPR Grafo que representa el ábol parcial de recubrimiento
     */
    public void darArbolParcialRecubrimiento( GrafoDirigido<K, V, A> arbolPR )
    {
        marcar( );
        for( Arco<K, V, A> sucesor : sucesores )
        {
            if( !sucesor.darVerticeDestino( ).marcado( ) )
                try
                {
                    arbolPR.agregarArco( darId( ), sucesor.darVerticeDestino( ).darId( ), sucesor.darInfoArco( ) );
                    sucesor.darVerticeDestino( ).darArbolParcialRecubrimiento( arbolPR );
                }
                catch( VerticeNoExisteException e )
                {
                    // Esto no deberia suceder
                }
                catch( ArcoYaExisteException e )
                {
                    // Esto no deberÃ­a suceder
                }
        }
    }

    /**
     * Marca todos los vértices adyacentes al vértice.
     */
    public void marcarAdyacentes( )
    {
        for( Arco<K, V, A> sucesor : sucesores )
        {
            if( !sucesor.darVerticeDestino( ).marcado( ) )
            {
                sucesor.darVerticeDestino( ).marcar( );
                sucesor.darVerticeDestino( ).marcarAdyacentes( );
            }
        }
        for( Arco<K, V, A> predecesor : predecesores )
        {
            if( !predecesor.darVerticeOrigen( ).marcado( ) )
            {
                predecesor.darVerticeOrigen( ).marcar( );
                predecesor.darVerticeOrigen( ).marcarAdyacentes( );
            }
        }
    }

}
