package PizzaPlace.PizzaPlace;

/**
 * Factory class for creating New York-style pizzas.
 * Implements the {@link PizzaFactory} interface to provide specific types of pizzas
 * with predefined sizes and crusts characteristic of New York-style pizza.
 *
 * @author Keshav Dave, Danny Watson
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with medium size and Brooklyn crust.
     *
     * @return a {@link Deluxe} pizza.
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Size.MEDIUM, Crust.BROOKLYN);
    }

    /**
     * Creates a Meatzza pizza with medium size and hand-tossed crust.
     *
     * @return a {@link Meatzza} pizza.
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Size.MEDIUM, Crust.HAND_TOSSED);
    }

    /**
     * Creates a BBQ Chicken pizza with medium size and thin crust.
     *
     * @return a {@link BBQChicken} pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Size.MEDIUM, Crust.THIN);
    }

    /**
     * Creates a "Build Your Own" pizza with medium size and hand-tossed crust.
     *
     * @return a {@link BuildYourOwn} pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Size.MEDIUM, Crust.HAND_TOSSED);
    }
}