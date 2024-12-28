package PizzaPlace.PizzaPlace;

import java.util.ArrayList;

/**
 * Represents an abstract base class for different types of pizzas.
 * Provides common properties and methods such as size, crust, toppings, and style.
 * Concrete subclasses must implement the {@link #price()} method to calculate the pizza price.
 *
 * @author Keshav Dave, Danny Watson
 */
public abstract class Pizza {
    // Variables
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Constructs a new Pizza object with the specified toppings, crust, and size.
     *
     * @param toppings the list of toppings for the pizza.
     * @param crust    the crust type of the pizza.
     * @param size     the size of the pizza.
     */
    protected Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Gets the size of the pizza.
     *
     * @return the pizza size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the crust type of the pizza.
     *
     * @return the pizza crust.
     */
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * Gets a copy of the list of toppings for the pizza.
     *
     * @return a list of toppings.
     */
    public ArrayList<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size the new size of the pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Sets the style of the pizza.
     * Includes an extra instance variable under the method.
     *
     * @param style the style of the pizza (e.g., "New York", "Chicago").
     */
    public void setStyle(String style) {
        this.style = style;
    }
    private String style;

    /**
     * Adds a topping to the pizza. A maximum of 7 toppings is allowed.
     *
     * @param topping the topping to add.
     * @return {@code true} if the topping was added, {@code false} otherwise.
     */
    public boolean addTopping(Topping topping) {
        if (toppings.size() < 7) {
            return toppings.add(topping);
        }
        return false;
    }

    /**
     * Removes a topping from the pizza.
     *
     * @param topping the topping to remove.
     * @return {@code true} if the topping was removed, {@code false} otherwise.
     */
    public boolean removeTopping(Topping topping) {
        return toppings.remove(topping);
    }

    /**
     * Converts the list of toppings to a comma-separated string.
     *
     * @return a string representation of the toppings.
     */
    private String toppingsToString() {
        StringBuilder toppingsBuilder = new StringBuilder();
        for (Topping topping : getToppings()) {
            if (toppingsBuilder.length() > 0) {
                toppingsBuilder.append(", ");
            }
            toppingsBuilder.append(topping.toString());
        }
        return toppingsBuilder.toString();
    }

    /**
     * Helper method to find out what type of pizza this is for printing.
     *
     * @return a string representation of the pizza.
     */
    private String type() {
        if (this instanceof Deluxe) {
            return "Deluxe";
        }
        if (this instanceof BBQChicken) {
            return "BBQ Chicken";
        }
        if (this instanceof Meatzza) {
            return "Meatzza";
        }
        if (this instanceof BuildYourOwn) {
            return "Build Your Own";
        }
        return "";
    }

    /**
     * Provides a string representation of the pizza, including its type, style, crust, size, and toppings.
     *
     * @return a string representation of the pizza.
     */
    @Override
    public String toString() {
        return String.format("%s, %s style, %s crust, %s, %s",
                type(),
                style,
                crust,
                toppingsToString(),
                size
        );
    }

    /**
     * Abstract method to calculate the price of the pizza.
     * Must be implemented by subclasses to provide specific pricing logic.
     *
     * @return the price of the pizza.
     */
    public abstract double price();
}