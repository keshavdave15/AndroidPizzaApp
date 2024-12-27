package PizzaPlace.PizzaPlace;

/**
 * Represents the types of pizza crusts available.
 * Each crust type has a user-friendly string representation provided by the overridden {@code toString()} method.
 *
 * @author Keshav Dave, Danny Watson
 */
public enum Crust {
    DEEP_DISH,
    BROOKLYN,
    PAN,
    THIN,
    STUFFED,
    HAND_TOSSED;

    /**
     * Provides a user-friendly string representation of the crust type.
     *
     * @return the name of the crust in a readable format.
     */
    @Override
    public String toString() {
        if (this == DEEP_DISH) {
            return "Deep dish";
        }
        if (this == BROOKLYN) {
            return "Brooklyn";
        }
        if (this == PAN) {
            return "Pan";
        }
        if (this == THIN) {
            return "Thin";
        }
        if (this == STUFFED) {
            return "Stuffed";
        }
        if (this == HAND_TOSSED) {
            return "Hand tossed";
        }
        return "";
    }
}