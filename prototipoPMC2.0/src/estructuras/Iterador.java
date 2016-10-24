/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Iterador.java,v 1.10 2008/02/20 17:14:49 jua-gome Exp $
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

import java.io.Serializable;

/**
 * Métodos básicos que debe ofrecer todo Iterador de cupi2 Collections
 * @param <T> Tipo de datos sobre los que se iteran
 */
public interface Iterador<T> extends Serializable
{
    /**
     * Indica si aún hay elementos sobre los cuales iterar. <br>
     * <b>post: </b> Se retornó true si aún no se han recorrido todos los elementos o false en caso contrario.
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( );

    /**
     * Retorna el elemento a ser visitado. <br>
     * <b>pre: </b> Aún existe al menos un elemento sobre el cual iterar. <br>
     * <b>post: </b> Se retornó el elemento a ser visitado.
     * @return El elemento a ser visitado o null si no hay elemento para visitar
     */
    public T darSiguiente( );

    /**
     * Indica si aún hay elementos sobre los cuales retroceder. <br>
     * <b>post: </b> Se retornó true si aún hay elementos sobre los cuales retroceder o false en caso contrario.
     * @return True si aún hay elementos sobre los cuales retroceder o false en caso contrario
     */
    public boolean hayAnterior( );

    /**
     * Retorna el último elemento visitado. <br>
     * <b>pre: </b> Aún existe al menos un elemento sobre el cual retroceder. <br>
     * <b>post: </b> Se retornó el último elemento visitado.
     * @return El último elemento visitado o null si no hay elementos sobre los cuales retroceder.
     */
    public T darAnterior( );

    /**
     * Sitúa el iterador de nuevo al inicio de la colección de datos con la que se encuentra asociado. <br>
     * <b>post: </b> El iterador se encuentra al inicio de la colección de datos con la que se encuentra asociada.
     */
    public void reiniciar( );
}
