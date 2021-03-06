package model;

/**
 * Enums designating the possible water conditions.
 */
public enum WaterCondition {
    WASTE, TREATABLE_CLEAR, TREATABLE_MUDDY, POTABLE;

    public String toString() {
        switch(this) {
            case WASTE: return "Waste";
            case TREATABLE_CLEAR: return "Treatable Clear";
            case TREATABLE_MUDDY: return "Treatable Muddy";
            case POTABLE: return "Potable";
            default: throw new IllegalArgumentException();
        }
    }
}
