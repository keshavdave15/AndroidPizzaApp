package PizzaPlace.PizzaPlace;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a BBQ Chicken pizza with predefined toppings and price based on size.
 * This pizza includes BBQ Chicken, Green Pepper, Provolone, and Cheddar toppings.
 * @author Keshav Dave, Danny Watson
 */
public class BBQChicken extends Pizza {

    /**
     * Constructs a BBQChicken pizza with the given size and crust.
     *
     * @param size  the size of the pizza (SMALL, MEDIUM, LARGE).
     * @param crust the crust type for the pizza.
     */
    public BBQChicken(Size size, Crust crust) {
        super(new ArrayList<>(Arrays.asList(
                Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE,
                Topping.CHEDDAR)), crust, size);
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
     *
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        if (this.getSize() == Size.SMALL) {
            return 14.99;
        }
        if (this.getSize() == Size.MEDIUM) {
            return 16.99;
        }
        if (this.getSize() == Size.LARGE) {
            return 19.99;
        }
        return 0;
    }
}