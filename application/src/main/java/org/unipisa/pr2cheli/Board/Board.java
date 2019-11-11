
package org.unipisa.pr2cheli;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import org.unipisa.pr2cheli.*;

/**
 * Board ADT
 */
public class Board <E extends Data> implements DataBoard {
    private HashMap<String, ArrayList<E>> contents;
    private String owner;
    private String passw;
    private HashMap<String, ArrayList<String>> friends;

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
        this.friends = new HashMap<String, ArrayList<String>>();
        this.contents = new HashMap<String, ArrayList<E>>();
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
        this.contents.put(category, new ArrayList<E>());
        this.friends.put(category, new ArrayList<String>());
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
        if(this.contents.remove(category) == null) throw new DataNotFoundException(category);
        if(this.friends.remove(category) == null) throw new DataNotFoundException(category);
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
        if(!this.friends.containsKey(category)) throw new DataNotFoundException(category);
        ArrayList<String> frs = this.friends.get(category);
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(frs.contains(friend)) throw new DuplicateDataException(friend);
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
        if(!this.friends.containsKey(category)) throw new DataNotFoundException(category);
        ArrayList<String> frs = this.friends.get(category);
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(!frs.contains(friend)) throw new DataNotFoundException(friend);
        frs.remove(friend);
    }

    /**
     * Add a post to a category.
     * modifies this.friends
     * @param category The category name to create, must be a valid category name
     * @param dato The element to add to the category posts
     * @param passw The password, must be a matching and valid password
     * @throws DuplicateDataException if dato is already associated to category
     * @throws DataNotFoundException if category does not exists
     * @throws InvalidDataException if category or passwd are invalid
     * @throws UnauthorizedLoginException if there is a password mismatch
     * @see org.unipisa.pr2cheli.DataValidator
     */
    @Override
    public boolean put(String passw, E dato, String category) {
        DataValidator.validateCategory(category);
        this.checkPasswd(passw);
        if(!this.contents.containsKey(category)) throw new DataNotFoundException(category);
        ArrayList<String> cts = this.friends.get(category);
        // this should not happen
        if(cts == null) throw new NullPointerException();
        if(cts.contains(friend)) throw new DuplicateDataException(friend);
        frs.add(friend);
    }

    @Override
    public Data get(String passw, Data dato) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Data remove(String passw, Data dato) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getDataCategory(String passw, String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertLike(String friend, Data dato) {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterator getIterator(String passw) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator getFriendIterator(String friend) {
        // TODO Auto-generated method stub
        return null;
    }
}