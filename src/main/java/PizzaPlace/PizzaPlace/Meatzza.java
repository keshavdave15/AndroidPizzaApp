package PizzaPlace.PizzaPlace;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Meatzza pizza with predefined toppings and price based on size.
 * This pizza includes Sausage, Pepperoni, Beef, and Ham toppings.
 *
 * @author Keshav Dave, Danny Watson
 */
public class Meatzza extends Pizza {

    /**
     * Constructs a Meatzza pizza with the specified size and crust.
     *
     * @param size  the size of the pizza (SMALL, MEDIUM, LARGE).
     * @param crust the crust type for the pizza.
     */
    public Meatzza(Size size, Crust crust) {
        super(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF,
                Topping.HAM)), crust, size);
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 17.99;
        }
        if (this.getSize() == Size.MEDIUM) {
            return 19.99;
        }
        if (this.getSize() == Size.LARGE) {
            return 21.99;
        }
        return 0;
    }
}