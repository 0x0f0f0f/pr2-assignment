package org.unipisa.pr2cheli;

import java.util.Iterator;
import java.util.List;

import org.unipisa.pr2cheli.Exceptions.*;

/**
 * DataBoard
 * I metodi che contengono il campo passw
 * vengono eseguiti se si rispettano i controlli di identità
 */
public interface DataBoard<E extends DataElement> {
    /* Crea una categoria di dati */
    public void createCategory(String category, String passw)
    throws DuplicateDataException, InvalidDataException, UnauthorizedLoginException;

    /* Rimuove una categoria di dati */
    public void removeCategory(String category, String passw)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException;

    /* Aggiunge un amico ad una categoria di dati */
    public void addFriend(String category, String passw, String friend)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException;

    /* Rimuove un amico da una categoria di dati */
    public void removeFriend(String category, String passw, String friend)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException;

    /* Inserisce un dato in bacheca*/
    public boolean put(String passw, E dato, String category)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException;

    /* Restituisce una copia del dato in bacheca */
    public E get(String passw, E dato)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException;

    /* Rimuove e restituisce un dato dalla bacheca */
    public E remove(String passw, E dato)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException;

    /* Crea la lista dei dati in bacheca di una determinata categoria */
    public List<E> getDataCategory(String passw, String category)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException;

    /* Aggiunge un like a un dato */
    public void insertLike(String friend, E dato)
    throws DuplicateDataException, InvalidDataException, DataNotFoundException ;

    /* Restituisce un iteratore (senza remove) che genera tutti
    i dati in bacheca ordinati rispetto al numero di like */
    public Iterator<E> getIterator(String passw);

    /* Restituisce un iteratore (senza remove) che genera tutti i dati
    in bacheca condivisi */
    public Iterator<E> getFriendIterator(String friend);
}