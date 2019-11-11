
package main.java.org.unipisa.pr2cheli;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import main.java.org.unipisa.pr2cheli.*;

/**
 * Board ADT
 */
public class Board <E extends Data> implements DataBoard {
    private HashMap<String, ArrayList<E>> contents;
    private String owner;
    private String passw;
    private HashMap<String, ArrayList<String>> friends;

    /**
     * @param owner
     * @param passw
     * @throws InvalidDataException
     */
    public Board(String owner, String passw) throws InvalidDataException {
        DataValidator.validateAuthor(owner);
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
     * @param p the password to check
     * @throws UnauthorizedLoginException
     * @throws InvalidDataException
    */
    public void checkPasswd(String p) throws UnauthorizedLoginException, InvalidDataException {
        DataValidator.validatePassw(p);
        if(!this.passw.equals(p)) throw new UnauthorizedLoginException("Wrong Credentials");
    }

    /**
     * @param category The category name to create
     * @throws DuplicateDataException
     * @throws InvalidDataException
     */
    @Override
    public void createCategory(String category, String passw) throws DuplicateDataException, InvalidDataException {
        DataValidator.validateCategory(category);
    }

    @Override
    public void removeCategory(String category, String passw) {
        // TODO Auto-generated method stub
    }

    @Override
    public void addFriend(String category, String passw, String friend) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeFriend(String category, String passw, String friend) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean put(String passw, Data dato, String category) {
        // TODO Auto-generated method stub
        return false;
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