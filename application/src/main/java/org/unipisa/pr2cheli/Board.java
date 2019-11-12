
package org.unipisa.pr2cheli;

import java.util.TreeSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.unipisa.pr2cheli.Exceptions.*;

//import org.unipisa.pr2cheli.*;

/**
 * Board ADT
 */
public class Board <E extends DataElement> implements DataBoard<E> {
    private HashMap<String, TreeSet<E>> contents;
    private HashMap<String, HashSet<String>> friends;
    private String owner;
    private String passw;

    /**
     * Constructor for Board
     * @param owner the owner, must be a valid username
     * @param passw the password, must be a valid password
     * @throws InvalidDataException if owner or password are invalid
     * @see org.unipisa.pr2cheli.DataValidator
     * @see org.unipisa.pr2cheli.DataValidator
     */
    public Board(String owner, String passw) throws InvalidDataException {
        DataValidator.validateUser(owner);
        DataValidator.validatePassw(passw);
        this.owner = owner;
        this.passw = passw;
        this.contents = new HashMap<String, TreeSet<E>>();
        this.friends = new HashMap<String, HashSet<String>>();
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return new String(this.owner);
    }

    /**
     * Checks if the password is correct
     * @param p the password to check, must be shorter than 128 characters
     * @throws UnauthorizedLoginException if password is wrong
     * @throws InvalidDataException if the password is invalid
    */
    public void checkPasswd(String p) throws UnauthorizedLoginException, InvalidDataException {
        DataValidator.validatePassw(p);
        if(!this.passw.equals(p)) throw new UnauthorizedLoginException("Wrong Credentials");
    }

    /**
     * Create a new category.
     * modifies this.contents and this.friends
     * @param category The category name to create, must be a valid category
     * @param passw The password, must be a valid password
     * @throws DuplicateDataException if category already exists
     * @throws InvalidDataException if category or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public void createCategory(String category, String passw)
    throws DuplicateDataException, InvalidDataException, UnauthorizedLoginException {
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        if(this.contents.containsKey(category)) throw new DuplicateDataException(category);
        if(this.friends.containsKey(category)) throw new DuplicateDataException(category);
        this.contents.put(category, new TreeSet<E>());
        this.friends.put(category, new HashSet<String>());
    }

    /**
     * Remove an already existing category.
     * modifies this.contents this.friends
     * @param category The category name to create, must be a valid category
     * @param passw The password, must be a matching and valid password
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public void removeCategory(String category, String passw)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        if(this.contents.remove(category) == null) throw new DataNotFoundException("category: " + category);
        if(this.friends.remove(category) == null) throw new DataNotFoundException("category: " + category);
    }

    /**
     * Add a friend to a category.
     * modifies this.friends
     * @param category The category name to create, must be a valid category name
     * @param friend The friend name to add to the category, must be a valid username
     * @param passw The password, must be a matching and valid password
     * @throws DuplicateDataException if friend already associated to category
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category, friend or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public void addFriend(String category, String passw, String friend)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        DataValidator.validateCategory(category);
        DataValidator.validateUser(friend);
        this.checkPasswd(passw);
        if(!this.friends.containsKey(category)) throw new DataNotFoundException("category: " + category);
        HashSet<String> frs = this.friends.get(category);
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(frs.contains(friend)) throw new DuplicateDataException("friend: " + friend);
        frs.add(friend);
    }

    /**
     * Remove a friend from a category.
     * modifies this.friends
     * @param category The category name to create, must be a valid category name
     * @param friend The friend name to add to the category, must be a valid username
     * @param passw The password, must be a matching and valid password
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category, friend or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public void removeFriend(String category, String passw, String friend)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataValidator.validateCategory(category);
        DataValidator.validateUser(friend);
        this.checkPasswd(passw);
        if(!this.friends.containsKey(category)) throw new DataNotFoundException("category: " + category);
        HashSet<String> frs = this.friends.get(category);
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(!frs.contains(friend)) throw new DataNotFoundException("friend: " + friend);
        frs.remove(friend);
    }

    /**
     * Add a post to a category.
     * modifies this.contents
     * @param category The category name to create, must be a valid category name
     * @param dato The element to add to the category posts
     * @param passw The password, must be a matching and valid password
     * @throws DuplicateDataException if dato is already associated to category
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category, post author or contents, or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @throws NullPointerException if dato is null
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public boolean put(String passw, E dato, String category)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        if(dato == null) throw new NullPointerException();
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        if(!this.contents.containsKey(category)) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = this.contents.get(category);
        // this should not happen
        if(cts == null) throw new NullPointerException();
        if(!dato.getAuthor().equals(this.getOwner())) throw new InvalidDataException("Author-Owner mismatch");
        E cloned = (E)dato.clone();
        cloned.setCategory(category);
        if(cts.contains(cloned)) throw new DuplicateDataException(cloned.display() + " in category " + category);
        cts.add(dato);
        return true;
    }

    /**
     * Get a post. (???)
     * @param dato The element to get from the category posts
     * @param passw The password, must be a matching and valid password
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if passwd is invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @throws NullPointerException if dato is null
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public E get(String passw, E dato)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        if(dato == null) throw new NullPointerException();
        this.checkPasswd(passw);
        E x = null;
        for(TreeSet<E> t : this.contents.values()) {
            if(t.contains(dato)) x = dato;
        }
        if(x == null) throw new DataNotFoundException(dato.display());
        return x;
    }

    /**
     * Remove a post.
     * modifies this.contents
     * @param dato The post to remove
     * @param passw The password, must be a matching and valid password
     * @throws DataNotFoundException if the post does not exists
     * @throws InvalidDataException if passwd is invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @throws NullPointerException if dato is null
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public E remove(String passw, E dato)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        if(dato == null) throw new NullPointerException();
        this.checkPasswd(passw);
        E x = null;
        for(TreeSet<E> t : this.contents.values()) {
            if(t.remove(dato)) x = dato;
        }
        if(x == null) throw new DataNotFoundException(dato.display());
        return x;
    }

    /**
     * Get a List of posts in a category.
     * @param passw The password, must be a matching and valid password
     * @param category The category name to create, must be a valid category name
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category, or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public List<E> getDataCategory(String passw, String category)
    throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        if(!this.contents.containsKey(category)) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = this.contents.get(category);
        // this should not happen
        if(cts == null) throw new NullPointerException();
        return new ArrayList<E>(cts);
    }

    /**
     * Add a like to a post.
     * @param dato The element to add to the category posts
     * @param friend The friend that liked the post
     * @throws DuplicateDataException if post was already liked by friend
     * @throws DataNotFoundException if category or post do not exists
     * @throws InvalidDataException if dato's content is invalid
     * @throws NullPointerException if dato is null
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public void insertLike(String friend, E dato)
    throws DuplicateDataException, InvalidDataException, DataNotFoundException {
        if(dato == null) throw new NullPointerException();
        E x = null;
        for(String category : this.contents.keySet()) {
            TreeSet<E> t = this.contents.get(category);
            if(this.friends.get(category) == null) throw new NullPointerException("friends list in " + category);
            if(this.friends.get(category).contains(friend)) {
                if(t.contains(dato)) {
                    t.remove(dato);
                    x = dato;
                    dato.addLike(friend);
                    t.add(dato);
                }
            }
        }
        if(x == null) throw new DataNotFoundException("post not found or @" + friend + " is not authorized to view it");
    }

    /**
     * Get an iterator for all posts sorted by like number.
     * @param passw The board password, must be a valid and matching password
     * @throws NullPointerException if data is corrupted
     * @throws InvalidDataException if password is invalid or data contents are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public Iterator getIterator(String passw)
    throws UnauthorizedLoginException, InvalidDataException {
        this.checkPasswd(passw);
        TreeSet <E> all = new TreeSet<E>();
        for(TreeSet<E> t : this.contents.values()) {
            for(E e : t) {
                all.add(e);
            }
        }

        SortedSet<E> unmodifiableAll = Collections.unmodifiableSortedSet(all);
        return unmodifiableAll.iterator();
    }

    /**
     * Get an iterator for all posts accessible by a friend.
     * @param friend The friend, must be a  valid and matching username
     * @throws NullPointerException if data is corrupted
     * @throws InvalidDataException if password is invalid or data contents are invalid
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public Iterator getFriendIterator(String friend)
    throws InvalidDataException {
        DataValidator.validateUser(friend);
        ArrayList <E> all = new ArrayList<E>();
        for(String cat : this.contents.keySet()) {
            if(this.friends.get(cat).contains(friend)) {
                for(E e : this.contents.get(cat)) {
                    all.add(e);
                }
            }
        }

        List<E> unmodifiableAll = Collections.unmodifiableList(all);
        return unmodifiableAll.iterator();
    }
}