
package main.java.org.unipisa.pr2cheli;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;

import main.java.org.unipisa.pr2cheli.Data;
import main.java.org.unipisa.pr2cheli.DataBoard;
import main.java.org.unipisa.pr2cheli.DataValidator;

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
     */
    @Override
    public void createCategory(String category, String passw) throws DuplicateDataException, InvalidDataException {
        DataValidator.validateCategory(category);
    }

    /**
     * UnauthorizedLoginException
     */
    public static class UnauthorizedLoginException extends Exception {
        public UnauthorizedLoginException(String msg) {
            super(msg);
        }
    }
    /**
     * DuplicateDataException
     */
    public static class DuplicateDataException extends Exception {
        public DuplicateDataException(String msg) {
            super(msg);
        }
    }
}