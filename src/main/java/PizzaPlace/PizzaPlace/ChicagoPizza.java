package PizzaPlace.PizzaPlace;

/**
 * Factory class for creating Chicago-style pizzas.
 * Implements the {@link PizzaFactory} interface to provide specific types of pizzas
 * with predefined sizes and crusts characteristic of Chicago-style pizza.
 *
 * @author Keshav Dave, Danny Watson
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with medium size and deep-dish crust.
     *
     * @return a Deluxe pizza.
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Size.MEDIUM, Crust.DEEP_DISH);
    }

    /**
     * Creates a Meatzza pizza with medium size and stuffed crust.
     *
     * @return a {@link Meatzza} pizza.
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Size.MEDIUM, Crust.STUFFED);
    }

    /**
     * Creates a BBQ Chicken pizza with medium size and pan crust.
     *
     * @return a {@link BBQChicken} pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Size.MEDIUM, Crust.PAN);
    }

    /**
     * Creates a "Build Your Own" pizza with medium size and pan crust.
     *
     * @return a {@link BuildYourOwn} pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Size.MEDIUM, Crust.PAN);
    }
}