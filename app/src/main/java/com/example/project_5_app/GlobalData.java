package com.example.project_5_app;

import java.util.ArrayList;

import PizzaPlace.PizzaPlace.*;

/**
 * Singleton class for sharing global data between different Activities.
 * Manages the current order and a list of all placed orders.
 * @author Keshav Dave, Danny Watson
 */
public class GlobalData {
    // Variables
    private static GlobalData instance;
    private Order currentOrder;
    private ArrayList<Order> totalOrders;

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private GlobalData() {
        totalOrders = new ArrayList<>();
        currentOrder = new Order();
    }

    /**
     * Provides a synchronized method to get the singleton instance of
     * GlobalData.
     * If the instance is null, a new one is created.
     * @return The singleton instance of GlobalData.
     */
    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    /**
     * Retrieves the current order.
     * @return The current order.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Updates the current order.
     * @param currentOrder The new current order to be set.
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Retrieves the list of all placed orders.
     * @return A list of all placed orders.
     */
    public ArrayList<Order> getTotalOrders() {
        return totalOrders;
    }

    /**
     * Updates the list of all placed orders.
     * @param totalOrders The new list of orders to be set.
     */
    public void setTotalOrders(ArrayList<Order> totalOrders) {
        this.totalOrders = totalOrders;
    }
}