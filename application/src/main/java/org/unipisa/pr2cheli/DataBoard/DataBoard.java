package main.java.org.unipisa.pr2cheli;

import java.util.Iterator;
import java.util.List;

import main.java.org.unipisa.pr2cheli.Data;

/**
 * DataBoard
 * I metodi che contengono il campo passw
 * vengono eseguiti se si rispettano i controlli di identità
 */
public interface DataBoard<E extends Data> {
    /* Crea una categoria di dati */
    public void createCategory(String category, String passw);

    /* Rimuove una categoria di dati */
    public void removeCategory(String category, String passw);

    /* Aggiunge un amico ad una categoria di dati */
    public void addFriend(String category, String passw, String friend);

    /* Rimuove un amico da una categoria di dati */
    public void removeFriend(String category, String passw, String friend);

    /* Inserisce un dato in bacheca*/
    public boolean put(String passw, E dato, String category);

    /* Restituisce una copia del dato in bacheca */
    public E get(String passw, E dato);

    /* Rimuove e restituisce un dato dalla bacheca */
    public E remove(String passw, E dato);

    /* Crea la lista dei dati in bacheca di una determinata categoria */
    public List<E> getDataCategory(String passw, String category);

    /* Aggiunge un like a un dato */
    void insertLike(String friend, E dato);

    /* Restituisce un iteratore (senza remove) che genera tutti
    i dati in bacheca ordinati rispetto al numero di like */
    public Iterator<E> getIterator(String passw);

    /* Restituisce un iteratore (senza remove) che genera tutti i dati
    in bacheca condivisi */
    public Iterator<E> getFriendIterator(String friend);
}