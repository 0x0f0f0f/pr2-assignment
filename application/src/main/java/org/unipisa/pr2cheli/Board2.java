
package org.unipisa.pr2cheli;

import java.util.TreeSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.unipisa.pr2cheli.Exceptions.*;

//import org.unipisa.pr2cheli.*;

/**
 * Board2 ADT.
 * Abstract invariant: Owner and passwd exist and are valid usernames and passwords,
 * categories is not null and contains valid categories which in turn contain valid posts.
 */
public class Board2 <E extends DataElement> implements DataBoard<E> {
    private ArrayList<Category<E>> categories;
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
    public Board2(String owner, String passw) throws InvalidDataException {
        DataValidator.validateUser(owner);
        DataValidator.validatePassw(passw);
        this.owner = owner;
        this.passw = passw;
        this.categories = new ArrayList<Category<E>>();
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
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) throw new DuplicateDataException(category);
        }
        Category<E> newCat = new Category<E>(category);
        this.categories.add(newCat);
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
        Category<E> toRemove = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toRemove = c;
            }
        }
        if(toRemove == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toRemove);
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
        Category<E> toAdd = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toAdd = c;
            }
        }
        if(toAdd == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toAdd);
        HashSet<String> frs = toAdd.getFriends();
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(frs.contains(friend)) {
            this.categories.add(toAdd);
            throw new DuplicateDataException("friend: " + friend);
        }
        frs.add(friend);
        toAdd.setFriends(frs);
        this.categories.add(toAdd);
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
        Category<E> toRemove = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toRemove = c;
            }
        }
        if(toRemove == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toRemove);
        HashSet<String> frs = toRemove.getFriends();
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(!frs.contains(friend)) {
            this.categories.add(toRemove);
            throw new DataNotFoundException("friend: " + friend);
        }
        frs.remove(friend);
        toRemove.setFriends(frs);
        this.categories.add(toRemove);
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
        if(!dato.getAuthor().equals(this.getOwner())) throw new InvalidDataException("Author-Owner mismatch");
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        Category<E> toAdd = null;
        for(Category<E> c : this.categories) {
            System.out.println("I'm visiting category" + c.getCategory());
            if(c.getCategory().equals(category)) {
                toAdd = c;
            }
        }
        if(toAdd == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toAdd);
        TreeSet<E> cts = toAdd.getContents();
        // this should not happen
        if(cts == null) throw new NullPointerException();
        E cloned = (E)dato.clone();
        cloned.setCategory(category);
        if(cts.contains(cloned)) {
            this.categories.add(toAdd);
            throw new DuplicateDataException(cloned.display() + " in category " + category);
        }
        cts.add(cloned);
        toAdd.setContents(cts);
        this.categories.add(toAdd);
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
        for(Category<E> c : this.categories) {
            if(c.getContents().contains(dato)) x = dato;
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
        Category<E> toRemove = null;
        E x = null;
        for(Category<E> c : this.categories) {
            if(c.getContents().remove(dato)) {
                x = dato;
            }
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
        Category<E> toReturn = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toReturn = c;
            }
        }
        if(toReturn == null) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = toReturn.getContents();
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
        Category<E> updated = null;
        for(Category<E> c : this.categories) {
            TreeSet<E> t = c.getContents();
            if(c.getFriends().contains(friend) && t.contains(dato)) {
                updated = c;
                t.remove(dato);
                x = (E) dato.clone();
                x.addLike(friend);
                System.out.println("Just added like by " + friend + " to " + x.display());
                t.add(x);
                c.setContents(t);
            }
        }
        if(x == null || updated == null) throw new DataNotFoundException("post not found or @" + friend + " is not authorized to view it");
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
    public Iterator<E> getIterator(String passw)
    throws UnauthorizedLoginException, InvalidDataException {
        this.checkPasswd(passw);
        TreeSet <E> all = new TreeSet<E>();
        for(Category<E> c : this.categories) {
            for(E e : c.getContents()) {
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
    public Iterator<E> getFriendIterator(String friend)
    throws InvalidDataException {
        DataValidator.validateUser(friend);
        ArrayList <E> all = new ArrayList<E>();
        for(Category<E> c : this.categories) {
            if(c.getFriends().contains(friend)) {
                for(E e : c.getContents()) {
                    all.add(e);
                }
            }
        }
        List<E> unmodifiableAll = Collections.unmodifiableList(all);
        return unmodifiableAll.iterator();
    }
}