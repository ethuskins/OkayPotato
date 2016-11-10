package model;

/**
 * Created by robertwaters on 10/11/16.
 */
class FileFormatException extends Exception {
    private final String line;

    public FileFormatException(String str) {
        line = str;
    }

    public String getOriginalLine() { return line; }
}
