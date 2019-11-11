/* Internal Exception Class to represent invalid data */
public static class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}