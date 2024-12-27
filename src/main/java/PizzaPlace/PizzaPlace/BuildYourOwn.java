package PizzaPlace.PizzaPlace;

import java.util.ArrayList;

/**
 * Represents a customizable "Build Your Own" pizza.
 * Allows users to select their own toppings, and the price is calculated based on the size
 * and number of toppings added.
 *
 * @author Keshav Dave, Danny Watson
 */
public class BuildYourOwn extends Pizza {

    /**
     * Constructs a "Build Your Own" pizza with no initial toppings.
     *
     * @param size  the size of the pizza (SMALL, MEDIUM, LARGE).
     * @param crust the crust type for the pizza.
     */
    public BuildYourOwn(Size size, Crust crust) {
        super(new ArrayList<>(), crust, size);
    }

    /**
     * Calculates the price of the "Build Your Own" pizza.
     * The price is determined by the base price for the size and an additional cost
     * for each topping added.
     *
     * @return the total price of the pizza.
     */
    @Override
    public double price() {
        double basePrice = 0;
        if (this.getSize() == Size.SMALL) {
            basePrice = 8.99;
        }
        if (this.getSize() == Size.MEDIUM) {
            basePrice = 10.99;
        }
        if (this.getSize() == Size.LARGE) {
            basePrice = 12.99;
        }
        return basePrice + (super.getToppings().size() * 1.69);
    }
}