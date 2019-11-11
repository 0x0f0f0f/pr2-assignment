import java.util.regex.*;

/**
 * Overview for the Data ADT
 * Represents a text post made by somebody in a board
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
 * This data type is unmutable therefore the representation invariant is always valid
 * if it is valid after allocation, where validation is made.
 */
class Data {
    private String author;
    private String text;

    /**
     * Validator method to check if the author is an alphanumeric value no longer than 50 characters
     */
    public static void validateAuthor(String author) throws InvalidDataException {
        if(author == null) throw new NullPointerException();
        if(author.length() == 0) throw new InvalidDataException("Author field is empty");
        if(author.length() > 50) throw new InvalidDataException("Author field is greater than 128 chars");
        /* Check alphanumeric regex */
        if(! Pattern.compile("[\\w\\d]+[\\w\\d-_]*").matcher(author).matches())
            throw new InvalidDataException("Invalid Author field");
    }

    /**
     * Validator method to check if text is less or equal than 128 characters and not empty
     */
    public static void validateText(String text) throws InvalidDataException {
        if(text == null) throw new NullPointerException();
        if(text.length() == 0)  throw new InvalidDataException("Text field is empty");
        if(text.length() > 128) throw new InvalidDataException("Text field is greater than 128 chars");
    }

    /**
     * @requires: author != null && text != null
     *  && 0 < author.length() <= 50 && 0 < text.length() <= 128
     *  && author contains only alphanumeric and dashes and begins with alphanum
     * @effects: allocates a data element
     * @modifies: this
     * @throws: InvalidDataException
     */
    public Data(String author, String text) throws InvalidDataException {
        validateAuthor(author);
        validateText(text);
        this.author = author;
        this.text = text;
    }

    /**
     * @requires: representation invariant conditions
     * @effects: returns a copy of the author
    */
    public String getAuthor() {
        return new String(this.author);
    }

    /**
     * @requires: representation invariant conditions
     * @effects: returns a copy of the text
    */
    public String getText() {
        return new String(this.text);
    }

    /** @requires: representation invariant conditions
     *  @effects: prints out this.toString()
    */
    public void display() {
        System.out.println(this.toString());
    }

    /**
     * @requires: representation invariant conditions
     * @effects: return a string representation of this
    */
    public String toString() {
        return "@" + this.getAuthor() + " says: " + this.getText();
    }

    /* Internal Exception Class to represent invalid data */
    public static class InvalidDataException extends Exception {
        public InvalidDataException(String msg) {
            super(msg);
        }
    }
}