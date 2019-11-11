package main.java.org.unipisa.pr2cheli;
import java.util.ArrayList;

/**
 * Overview for the Data ADT
 * Represents a tweet-like text post made by somebody in a board
 *
 * Abstract invariant:
 * Author must be an alphanumeric name less than 50 characters and not empty,
 * containing only words, digits, dash or underscore. Must begin with a letter or digit
 * Text must be less or equal than 128 characters and not empty
 *
 * Abstraction Function(author, text) = <author, text> if author and text are valid, undefined otherwise
 *
 * Representation Invariant = author != null && text != null
 *  && 0 < author.length() <= 50 && 0 < text.length() <= 128
 *  && author contains only alphanumeric and dashes and begins with alphanum
 *
 * This data type is immutable therefore the representation invariant is always valid
 * if it is valid after allocation, where validation is made.
 */
public class Data {
    private String author;
    private String text;
    private int likes;
    private ArrayList<String> friendsliked;

    /**
     * @param author
     * @requires: author != null && text != null
     *  && 0 < author.length() <= 50 && 0 < text.length() <= 128
     *  && author contains only alphanumeric and dashes and begins with alphanum
     * @effects: allocates a data element
     * @modifies: this
     * @throws: InvalidDataException
     */
    public Data(String author, String text) throws InvalidDataException {
        DataValidator.validateAuthor(author);
        DataValidator.validateText(text);
        this.author = author;
        this.text = text;
        this.likes = 0;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the textual representation of this object
     */
    public String display() {
        return this.toString();
    }

    /**
     * @return the textual representation of this object
     */
    public String toString() {
        return "@" + this.getAuthor() + " says: " + this.getText();
    }
}