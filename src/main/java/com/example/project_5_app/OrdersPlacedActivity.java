package com.example.project_5_app;

import android.content.Intent;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import PizzaPlace.PizzaPlace.*;

/**
 * Activity class to handle displaying and managing orders placed by the user.
 * Provides functionality to view order details, cancel orders, and display total amounts.
 * @author Keshav Dave, Danny Watson
 */
public class OrdersPlacedActivity extends AppCompatActivity {
    // Variables
    private Spinner orderNumberSpinner;
    private TextView orderTotalField;
    private ListView orderItemsListView;
    private Button cancelOrderButton;
    private ArrayList<Order> listOfOrders;
    private ArrayAdapter<String> orderItemsAdapter;
    private ArrayAdapter<Integer> orderNumberAdapter;

    /**
     * Called when the activity is first created.
     * Initializes UI components and sets up data and event listeners.
     * @param savedInstanceState the saved instance state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_placed_layout);

        orderNumberSpinner = findViewById(R.id.orderNumberSpinner);
        orderTotalField = findViewById(R.id.orderTotalField);
        orderItemsListView = findViewById(R.id.orderItemsListView);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);

        listOfOrders = GlobalData.getInstance().getTotalOrders();
        if (listOfOrders == null) {
            listOfOrders = new ArrayList<>(); // Ensure the list is not null
            GlobalData.getInstance().setTotalOrders(listOfOrders);
        }

        populateOrderNumbers();

        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayOrderDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where no item is selected
            }
        });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersPlacedActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        cancelOrderButton.setOnClickListener(v -> cancelOrder());
    }

    /**
     * Populates the Spinner with the list of order numbers from the global data.
     * Sets the first order as the selected order by default if available.
     */
    private void populateOrderNumbers() {
        ArrayList<Integer> orderNumbers = new ArrayList<>();
        for (Order order : listOfOrders) {
            orderNumbers.add(order.getNumber());
        }

        orderNumberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                orderNumbers);
        orderNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(orderNumberAdapter);

        if (!listOfOrders.isEmpty()) {
            orderNumberSpinner.setSelection(0);
            displayOrderDetails();
        }
    }

    /**
     * Displays the details of the selected order, including the list of pizzas
     * and the total cost (subtotal + sales tax).
     */
    private void displayOrderDetails() {
        Integer selectedOrderNumber = (Integer) orderNumberSpinner.getSelectedItem();
        if (selectedOrderNumber != null) {
            Order selectedOrder = findOrderByNumber(selectedOrderNumber);
            if (selectedOrder != null) {
                ArrayList<String> orderItems = new ArrayList<>();
                for (Pizza pizza : selectedOrder.getPizzas()) {
                    orderItems.add(pizza.toString() + " - $" +
                            String.format("%.2f", pizza.price()));
                }

                orderItemsAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, orderItems);
                orderItemsListView.setAdapter(orderItemsAdapter);

                double total = selectedOrder.getTotalAmount() +
                        selectedOrder.getSalesTax();
                orderTotalField.setText(String.format("%.2f", total));
            }
        } else {
            orderItemsListView.setAdapter(null);
            orderTotalField.setText("");
        }
    }

    /**
     * Finds an order by its order number from the list of placed orders.
     * @param orderNumber the order number to search for.
     * @return the matching Order object, or null if no match is found.
     */
    private Order findOrderByNumber(Integer orderNumber) {
        for (Order order : listOfOrders) {
            if (order.getNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    /**
     * Cancels the currently selected order by removing it from the list of placed orders.
     * Updates the UI and displays an alert confirming the cancellation.
     */
    private void cancelOrder() {
        Integer selectedOrderNumber = (Integer) orderNumberSpinner.getSelectedItem();
        if (selectedOrderNumber != null) {
            listOfOrders.removeIf(order -> order.getNumber() == selectedOrderNumber);
            populateOrderNumbers();
            displayOrderDetails();
            showAlert("Order Canceled", "Order " + selectedOrderNumber + 
                    " has been canceled successfully.");
        } else {
            showAlert("No Order Selected", "Please select an order to cancel.");
        }
    }

    /**
     * Displays an alert dialog with a specified title and message.
     * @param title the title of the alert dialog.
     * @param message the message to display in the alert dialog.
     */
    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
