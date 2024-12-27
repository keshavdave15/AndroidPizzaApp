package PizzaPlace.PizzaPlace;

/**
 * Defines a factory for creating different types of pizzas.
 * Implementations of this interface will specify the details of how each pizza type is created.
 *
 * The available pizza types include:
 * - Deluxe
 * - Meatzza
 * - BBQ Chicken
 * - Build Your Own
 *
 * @author Keshav Dave, Danny Watson
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza.
     *
     * @return a {@link Pizza} object representing a Deluxe pizza.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza.
     *
     * @return a {@link Pizza} object representing a Meatzza pizza.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza.
     *
     * @return a {@link Pizza} object representing a BBQ Chicken pizza.
     */
    Pizza createBBQChicken();

    /**
     * Creates a "Build Your Own" pizza.
     *
     * @return a {@link Pizza} object representing a customizable "Build Your Own" pizza.
     */
    Pizza createBuildYourOwn();
}