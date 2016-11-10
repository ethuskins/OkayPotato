package model;



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
