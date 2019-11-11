package main.java.org.unipisa.pr2cheli;

/**
 * DataValidator
 */
public class DataValidator {
    /**
     * Validator method to check if the author is an alphanumeric value no longer than 50 characters
     * And beginning with a word or digit
     * @param author A string containing the author
     * @throws InvalidDataException
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
     * Validator method to check if text length is less or equal than 128 characters and not empty
     * @param text A string containing text
     * @throws InvalidDataException
     */
    public static void validateText(String text) throws InvalidDataException {
        if(text == null) throw new NullPointerException();
        if(text.length() == 0)  throw new InvalidDataException("Text field is empty");
        if(text.length() > 128) throw new InvalidDataException("Text field is greater than 128 chars");
    }

    /**
     * @param p A string indicating the password
     * Validator method to check if a password's length is less or equal than 128 characters and not empty
     * @throws InvalidDataException
     */
    public static void validatePassw(String p) throws InvalidDataException {
        if(p == null) throw new NullPointerException();
        if(p.length() == 0)  throw new InvalidDataException("Password field is empty");
        if(p.length() > 128) throw new InvalidDataException("Password field is greater than 128 chars");
    }

    /**
     * Validator method to check if category is an alphanumeric value no longer than 50 characters
     * And beginning with a word or digit
     * @param cat A string indicating the category
     * @throws InvalidDataException
     */
    public static void validateCategory(String cat) throws InvalidDataException {
        if(cat == null) throw new NullPointerException();
        if(cat.length() == 0) throw new InvalidDataException("Category field is empty");
        if(cat.length() > 50) throw new InvalidDataException("Category field is greater than 128 chars");
        /* Check alphanumeric regex */
        if(! Pattern.compile("[\\w\\d]+[\\w\\d-_]*").matcher(cat).matches())
            throw new InvalidDataException("Invalid Category field");
    }

}