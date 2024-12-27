package PizzaPlace.PizzaPlace;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Deluxe pizza with predefined toppings and price based on size.
 * This pizza includes Sausage, Pepperoni, Green Pepper, Onion, and Mushroom toppings.
 *
 * @author Keshav Dave, Danny Watson
 */
public class Deluxe extends Pizza {

    /**
     * Constructs a Deluxe pizza with the specified size and crust.
     *
     * @param size  the size of the pizza (SMALL, MEDIUM, LARGE).
     * @param crust the crust type for the pizza.
     */
    public Deluxe(Size size, Crust crust) {
        super(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER,
                Topping.ONION, Topping.MUSHROOM)), crust, size);
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     *
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 16.99;
        }
        if (this.getSize() == Size.MEDIUM) {
            return 18.99;
        }
        if (this.getSize() == Size.LARGE) {
            return 20.99;
        }
        return 0;
    }
}