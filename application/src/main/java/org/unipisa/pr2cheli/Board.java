import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;

import main.java.org.unipisa.pr2cheli.DataValidator;

/**
 * Board ADT
 */
public class Board <E extends Data> implements DataBoard {
    private HashMap<String, ArrayList<E>> contents;
    private String owner;
    private String passw;
    private HashMap<String, ArrayList<String>> friends;

    public Board(String owner, String passw) {
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
    */
    public void checkPasswd(String p) {
        DataValidator.validatePassw(p);
        if(!this.passw.equals(p)) throw new UnauthorizedLoginException("Wrong Credentials");
    }

    /**
     * @param category The category name to create
     * @throws DuplicateDataException
     */
    @Override
    public void createCategory(String category, String passw) {
        DataValidator.validateCategory(category);
    }

    /**
     * UnauthorizedLoginException
     */
    public class UnauthorizedLoginException extends Exception {
        public UnauthorizedLoginException(String msg) {
            super(msg);
        }
    }
    /**
     * DuplicateDataException
     */
    public class DuplicateDataException extends Exception {
        public DuplicateDataException(String msg) {
            super(msg);
        }
    }
}