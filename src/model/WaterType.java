package model;


public enum WaterType {
    BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER;

    public String toString() {
        switch(this) {
            case BOTTLED: return "Bottled Water";
            case WELL: return "Well";
            case STREAM: return "Stream";
            case LAKE: return "Lake";
            case SPRING: return "Spring";
            case OTHER: return "Other";
            default: throw new IllegalArgumentException();
        }
    }
}
