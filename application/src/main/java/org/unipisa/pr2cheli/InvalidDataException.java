package main.java.org.unipisa.pr2cheli;

/* Internal Exception Class to represent invalid data */
public class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}