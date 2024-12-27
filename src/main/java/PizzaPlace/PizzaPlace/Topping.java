package PizzaPlace.PizzaPlace;

/**
 * Represents the available toppings for a pizza.
 * Each topping has a user-friendly string representation provided by the overridden {@code toString()} method.
 * Toppings include various meats, vegetables, cheeses, and other ingredients.
 * @author Keshav Dave, Danny Watson
 */
public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    BBQ_CHICKEN,
    PROVOLONE,
    CHEDDAR,
    BEEF,
    HAM,
    PINEAPPLE,
    SPINACH,
    BLACK_OLIVES,
    EXTRA_CHEESE,
    BACON;

    /**
     * Provides a user-friendly string representation of the topping.
     * @return the topping name in a readable format (e.g., "Sausage", "Green pepper").
     */
    @Override
    public String toString() {
        if (this == SAUSAGE) {
            return "Sausage";
        }
        if (this == PEPPERONI) {
            return "Pepperoni";
        }
        if (this == GREEN_PEPPER) {
            return "Green pepper";
        }
        if (this == ONION) {
            return "Onion";
        }
        if (this == MUSHROOM) {
            return "Mushroom";
        }
        if (this == BBQ_CHICKEN) {
            return "BBQ chicken";
        }
        if (this == PROVOLONE) {
            return "Provolone";
        }
        if (this == CHEDDAR) {
            return "Cheddar";
        }
        if (this == BEEF) {
            return "Beef";
        }
        if (this == HAM) {
            return "Ham";
        }
        if (this == PINEAPPLE) {
            return "Pineapple";
        }
        if (this == SPINACH) {
            return "Spinach";
        }
        if (this == BLACK_OLIVES) {
            return "Black olives";
        }
        if (this == EXTRA_CHEESE) {
            return "Extra cheese";
        }
        if (this == BACON) {
            return "Bacon";
        }
        return "";
    }
}