/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Vertice.java,v 1.2 2008/09/30 18:54:45 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Representa un v�rtice del grafo
 * 
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Vertice<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento contenido en el v�rtice
     */
    private V infoVertice;

    /**
     * Lista de arcos hacia los sucesores de �ste v�rtice
     */
    private ArrayList<Arco<K, V, A>> predecesores;

    /**
     * Lista de arcos hacia los sucesores de �ste v�rtice
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
     * Constructor del v�rtice
     * @param pInfoVertice Elemento contenido en el v�rtice
     */
    public Vertice( V pInfoVertice )
    {
        infoVertice = pInfoVertice;
        sucesores = new ArrayList<Arco<K, V, A>>( );
        predecesores = new ArrayList<Arco<K, V, A>>( );
        marcado = false;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el Id del v�rtice
     * @return Identificador del v�rtice
     */
    public K darId( )
    {
        return infoVertice.darId( );
    }

    /**
     * Devuelve la informaci�n del v�rtice
     * @return Informaci�n del v�rtice
     */
    public V darInfoVertice( )
    {
        return infoVertice;
    }

    /**
     * Devuelve los arcos hacia los sucesores del v�rtice
     * @return Arcos hacia los sucesores del v�rtice
     */
    public ArrayList<Arco<K, V, A>> darSucesores( )
    {
        return sucesores;
    }

    /**
     * Devuelve los arcos hacia los predecesores del v�rtice
     * @return Arcos hacia los predecesores del v�rtice
     */
    public ArrayList<Arco<K, V, A>> darPredecesores( )
    {
        return predecesores;
    }

    /**
     * Devuelve el arco (si existe) hacia el v�rtice especificado. Devuelve null si no existe.
     * @param idDestino Identificador del v�rtice destino
     * @return Arco hacia el v�rtice especificado, null si no existe
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
     * Devuelve la marca del v�rtice
     * @return Indica si el v�rtice se encuentra marcado
     */
    public boolean marcado( )
    {
        return marcado;
    }

    /**
     * Marca el v�rtice
     */
    public void marcar( )
    {
        marcado = true;
    }

    /**
     * Elimina la marca del v�rtice
     */
    public void desmarcar( )
    {
        marcado = false;
    }

    /**
     * Elimina un arco del v�rtice
     * @param idDestino Identificador del v�rtice destino del arco que se quiere eliminar
     * @throws Exception Excepci�n generada cuando el arco no existe
     */
    public void eliminarArco( K idDestino ) throws Exception
    {
        Arco<K, V, A> arco = darArco( idDestino );
        if( arco == null )
        {
            throw new Exception( "El arco no existe");
        }
        sucesores.remove( arco );
        arco.darVerticeDestino( ).eliminarArcoPredecesor( arco );
    }

    /**
     * Elimina un arco de los predecesores del v�rtice
     * @param arco Arco a eliminar
     */
    private void eliminarArcoPredecesor( Arco<K, V, A> arco )
    {
        predecesores.remove( arco );
    }

    /**
     * Agrega un arco al v�rtice
     * @param arco Arco a agregar al v�rtice
     * @throws Exception Excepci�n generada cuando ya hay un arco hacia el mismo v�rtice
     */
    public void agregarArco( Arco<K, V, A> arco ) throws Exception
    {
        K idDestino = arco.darVerticeDestino( ).darId( );
        if( esSucesor( idDestino ) )
        {
            throw new Exception( "El arco ya existe");
        }
        sucesores.add( arco );
        arco.darVerticeDestino( ).agregarArcoPredecesor( arco );
    }

    /**
     * Agrega un arco al v�rtice
     * @param arco Arco a agregar al v�rtice
     * @throws Exception Excepci�n generada cuando ya hay un arco hacia el mismo v�rtice
     */
    private void agregarArcoPredecesor( Arco<K, V, A> arco ) throws Exception
    {
        predecesores.add( arco );
    }

    /**
     * Elimina todos los arcos del v�rtice
     */
    public void eliminarArcos( )
    {
        sucesores.clear( );
    }

    /**
     * Verifica si el arco especificado es sucesor de �ste
     * @param idDestino Identificador del v�rtice destino
     * @return True si es sucesor, False si no
     */
    public boolean esSucesor( K idDestino )
    {
        return darArco( idDestino ) != null;
    }

    /**
     * Devuelve el n�mero de sucesores del v�rtice
     * @return N�mero de sucesores del v�rtice
     */
    public int darNumeroSucesores( )
    {
        return sucesores.size( );
    }

    /**
     * Devuelve el n�mero de predecesores del v�rtice
     * @return N�mero de predecesores del v�rtice
     */
    public int darNumeroPredecesores( )
    {
        return predecesores.size( );
    }

    /**
     * Indica si hay un camino simple del v�rtice actual al v�rtice que se recibe como par�metro
     * @param destino V�rtice destino de la b�squeda
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
     * Devuelve el camino mas corto al v�rtice especificado
     * @param destino V�rtice destino
     * @return Camino mas corto hacia el v�rtice especificado
     * @throws Exception 
     */
    public Camino<K, V, A> darCaminoMasCorto( Vertice<K, V, A> destino ) throws Exception
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
     * Devuelve el camino mas barato hacia el v�rtice especificado
     * @param destino V�rtice destino
     * @return Camino mas barato al v�rtice especificado
     * @throws Exception 
     */
    public Camino<K, V, A> darCaminoMasBarato( Vertice<K, V, A> destino ) throws Exception
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
     * Indica si hay un ciclo que parte del v�rtice actual
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
     * Indica si hay un camino de Hamilton que pasa por el v�rtice actual, teniendo en cuenta que en dicho camino<br>
     *  ya se ha pasado por un cierto n�mero de v�rtice (longActual) y que debe pasar por todos lo v�rtices del grafo (ordenGrafo)
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
     * Devuelve el camino de Hamilton
     * @param hamilton Camino de Hamilton
     * @param ordenGrafo Orden del grafo
     * @return True si existe, False si no
     * @throws Exception 
     */
    public boolean darCaminoHamilton( Camino<K, V, A> hamilton, int ordenGrafo ) throws Exception
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
     * Indica si hay un ciclo de Hamilton que pasa por el v�rtice actual, teniendo en cuenta que en dicho camino ya se ha pasado por un<br>
     *  cierto n�mero de vertices (longActual) y que debe pasar por todos los vertices del grafo.
     * @param longitudActual Longitud actual del camino
     * @param ordenGrafo N�mero de vertices en el grafo.
     * @param K Id del v�rtice desde el que se origino el ciclo.
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
     * Retorna un ciclo hamiltoniano que pase por este v�rtice
     * @param hamilton Camino formado hasta ahora
     * @param ordenGrafo Orden del grafo
     * @return <code>true</code> Si existe un ciclo hamiltoniano o <code>false</code> en caso contrario
     * @throws Exception 
     */
    public boolean darCicloHamilton( Camino<K, V, A> hamilton, int ordenGrafo ) throws Exception
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
     * Incluye en el c�lculo de caminos m�nimos el v�rtice actual
     * @param minimos El objeto sobre el que se est� haciendo el calculo
     * @return El objeto en el que se est�n calculando los caminos m�nimos
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
     * Construye un iterador con el recorrido por profundidad que parte del v�rtice.
     * @param itera Iterador en donde se agregar�n los elementos del recorrido por profundidad que parte del v�rtice.
     */
    public void darRecorridoProfundidad( IteradorSimple<Vertice<K, V, A>> itera )
    {
        try
        {
            itera.agregar( this );
        }
        catch( Exception e )
        {
            // Nunca deber�a ocurrir esta excepci�n
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
     * Completa un arbol parcial de recubrimiento con los v�rtices no marcados del grafo que sean sucesores de este v�rtice
     * @param arbolPR Grafo que representa el �bol parcial de recubrimiento
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
                catch( Exception e )
                {
                    // Esto no deberia suceder
                }
        }
    }

    /**
     * Marca todos los v�rtices adyacentes al v�rtice.
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
    
    public String toString()
    {
    	return infoVertice.toString();
    }

}
