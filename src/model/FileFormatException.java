package model;

/**
 * Created by robertwaters on 10/11/16.
 */
public class FileFormatException extends Exception {
    private String line;

    public FileFormatException(String str) {
        line = str;
    }

    public String getOriginalLine() { return line; }
}
