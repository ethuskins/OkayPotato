package model;


class FileFormatException extends Exception {
    private final String line;

    public FileFormatException(String str) {
        line = str;
    }

    public String getOriginalLine() { return line; }
}
