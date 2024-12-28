package PizzaPlace.PizzaPlace;

import java.util.ArrayList;

/**
 * Represents a customer's order containing multiple pizzas.
 * Includes functionality to calculate totals, manage pizzas, and clone the order.
 *
 * @author Keshav Dave, Danny Watson
 */
public class Order {
    // Variables
    final double TAX_RATE = 0.06625;
    private int number;
    private ArrayList<Pizza> pizzas;

    /**
     * Constructs a new Order with an initial order number and empty pizza list.
     */
    public Order() {
        this.number = 1;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Gets the order number.
     *
     * @return the order number.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Sets the order number.
     *
     * @param num the new order number.
     */
    public void setNumber(int num) {
        number = num;
    }

    /**
     * Increments the order number by one.
     */
    public void addOneToNumber() {
        number++;
    }

    /**
     * Gets the list of pizzas in the order.
     *
     * @return the list of pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza the pizza to add.
     */
    public void addPizza(Pizza pizza) {
        if (pizza != null) {
            pizzas.add(pizza);
        }
    }

    /**
     * Removes a pizza from the order.
     *
     * @param pizza the pizza to remove.
     * @return {@code true} if the pizza was removed, {@code false} otherwise.
     */
    public boolean removePizza(Pizza pizza) {
        return pizzas.remove(pizza);
    }

    /**
     * Calculates the subtotal of the order, excluding tax.
     *
     * @return the subtotal amount.
     */
    public double getTotalAmount() {
        double total = 0.0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    /**
     * Calculates the sales tax for the order.
     *
     * @return the sales tax amount.
     */
    public double getSalesTax() {
        return getTotalAmount() * TAX_RATE;
    }

    /**
     * Calculates the total amount of the order, including tax.
     *
     * @return the total amount.
     */
    public double getOrderTotal() {
        return getTotalAmount() + getSalesTax();
    }

    /**
     * Creates a clone of the current order, including all pizzas and the order number.
     *
     * @return a new {@code Order} object that is a copy of the current order.
     */
    public Order cloner() {
        Order clone = new Order();
        clone.setNumber(this.number);
        for (Pizza pizza : this.pizzas) {
            clone.addPizza(pizza);
        }
        return clone;
    }

    /**
     * Returns a string representation of the order, including all pizzas and totals.
     *
     * @return the order details as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Order Number: " + number + "\n");
        for (Pizza pizza : pizzas) {
            orderDetails.append(pizza.toString()).append("\n");
        }
        orderDetails.append("Subtotal: $").append(String.format("%.2f", getTotalAmount())).append("\n");
        orderDetails.append("Sales Tax: $").append(String.format("%.2f", getSalesTax())).append("\n");
        orderDetails.append("Order Total: $").append(String.format("%.2f", getOrderTotal())).append("\n");
        return orderDetails.toString();
    }
}