package model;

/**
 * Controlls the file format exceptions.
 */
class FileFormatException extends Exception {
    private final String line;

    /**
     * constructor for the file formal exception
     * @param str the message
     */
    public FileFormatException(String str) {
        line = str;
    }

    /**
     * getter method for line
     * @return the line
     */
    public String getOriginalLine() { return line; }
}
