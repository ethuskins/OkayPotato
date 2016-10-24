package model;

/**
 * Created by Jack Winski on 10/24/2016.
 */

    public enum QuaCondition {
        SAFE, TREATABLE, UNSAFE;

    public String toString() {
        switch(this) {
            case SAFE: return "Safe";
            case TREATABLE: return "Treatable";
            case UNSAFE: return "Unsafe";
            default: throw new IllegalArgumentException();
        }
    }
}
