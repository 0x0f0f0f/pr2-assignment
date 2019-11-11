package org.unipisa.pr2cheli;

/**
 * DataNotFoundException
 * You've tried to access a value that does not exist
 */
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}