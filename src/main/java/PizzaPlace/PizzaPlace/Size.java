package PizzaPlace.PizzaPlace;

/**
 * Represents the sizes available for a pizza.
 * Each size has a user-friendly string representation provided by the overridden {@code toString()} method.
 *
 * @author Keshav Dave, Danny Watson
 */
public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * Provides a user-friendly string representation of the pizza size.
     *
     * @return the size in a readable format (e.g., "Small", "Medium", "Large").
     */
    @Override
    public String toString() {
        if (this == Size.SMALL) {
            return "Small";
        }
        if (this == Size.MEDIUM) {
            return "Medium";
        }
        if (this == Size.LARGE) {
            return "Large";
        }
        return "";
    }
}