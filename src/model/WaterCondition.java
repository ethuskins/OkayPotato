package model;

/**
 * Created by emilyhuskins on 10/12/16.
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
