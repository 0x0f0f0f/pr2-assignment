package org.unipisa.pr2cheli;
import java.util.ArrayList;

/**
 * Overview for the Data ADT
 * Represents a tweet-like text post made by somebody in a board
 *
 * Abstract invariant:
 * Author must be an alphanumeric name less than 50 characters and not empty,
 * containing only words, digits, dash or underscore. Must begin with a letter or digit
 * Text must be less or equal than 128 characters and not empty
 * The number of likes must be equal to the number of elements in the like array
 */

/* Abstraction Function(author, text) = <author, text> if author and text are valid, undefined otherwise
   Representation Invariant = author != null && text != null
    && 0 < author.length() <= 50 && 0 < text.length() <= 128
    && author contains only alphanumeric and dashes and begins with alphanum
       This data type is immutable therefore the representation invariant is always valid
    && numlikes = likes.length()
   if it is valid after allocation, where validation is made.
 */
public class Data {
    private String author;
    private String text;
    private int numlikes;
    private ArrayList<String> likes;

    /**
     * Allocate a data element
     * @param author a valid username
     * @see org.unipisa.pr2cheli.DataValidator
     * @throws InvalidDataException if username or text are invalid
     */
    public Data(String author, String text) throws InvalidDataException {
        DataValidator.validateUser(author);
        DataValidator.validateText(text);
        this.author = author;
        this.text = text;
        this.numlikes = 0;
        this.likes = new ArrayList<String> likes;
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

    /**
     * Add a like to the post
     * @param who who liked the post, must be a valid username
     * @throws InvalidDataException if who is not a valid username
     * @throws DuplicateDataException if who already exists in likes
     */
    public void addLike(String who)
    throws InvalidDataException, DataNotFoundException {
        DataValidator.validateUser(who);
        if(this.likes.contains(who)) throw new DuplicateDataException(who);
        this.numlikes++;
        this.likes.add(who);
    }

    /**
     * Remove a like from a post
     * @param who who liked the post, must be a valid username and present in likes
     * @throws InvalidDataException if who is not a valid username
     * @throws DataNotFoundException if who is not present in the likes list
     */
    public void removeLike(String who)
    throws InvalidDataException, DataNotFoundException {
        DataValidator.validateUser(who);
        if(!this.likes.contains(who)) throw new DataNotFoundException(who);
        this.numlikes--;
        this.likes.remove(who);
    }
}