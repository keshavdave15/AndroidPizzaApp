package com.example.project_5_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.View;
import PizzaPlace.PizzaPlace.*;

/**
 * Activity to display and manage the current order.
 * Allows users to view, remove pizzas, confirm the order, or clear the order.
 * @author eshav Dave, Danny Watson
 */
public class CurrentOrderActivity extends AppCompatActivity {
    // Variables
    private EditText orderNumberField, subtotalField, salesTaxField, totalField;
    private ListView orderListView;
    private Button removePizzaButton, confirmOrderButton, clearOrderButton;
    private Order order;
    private ArrayAdapter<String> orderItemsAdapter;
    private ArrayList<Order> ordersPlaced;
    private int selectedPizzaIndex = -1;

    /**
     * Called when the activity is first created.
     * Initializes UI components, sets up event listeners, and loads order details
     * from GlobalData.
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most
     *                           recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order_layout);

        orderNumberField = findViewById(R.id.orderNumberField);
        subtotalField = findViewById(R.id.subtotalField);
        salesTaxField = findViewById(R.id.salesTaxField);
        totalField = findViewById(R.id.totalField);
        orderListView = findViewById(R.id.orderListView);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);
        clearOrderButton = findViewById(R.id.clearOrderButton);

        removePizzaButton.setEnabled(false);
        confirmOrderButton.setEnabled(false);
        clearOrderButton.setEnabled(false);

        orderItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, new ArrayList<>());
        orderListView.setAdapter(orderItemsAdapter);
        orderListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        order = GlobalData.getInstance().getCurrentOrder();
        if (order == null) {
            order = new Order(); // Ensure `order` is not null
            GlobalData.getInstance().setCurrentOrder(order);
        }

        ordersPlaced = GlobalData.getInstance().getTotalOrders();
        if (ordersPlaced == null) {
            ordersPlaced = new ArrayList<>();
            GlobalData.getInstance().setTotalOrders(ordersPlaced);
        }
        displayOrderDetails();

        orderListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPizzaIndex = position;
            removePizzaButton.setEnabled(true);

            for (int i = 0; i < orderListView.getChildCount(); i++) {
                View item = orderListView.getChildAt(i);
                if (item != null) {
                    item.setBackgroundColor(getResources().getColor(android.R.color.white)); // Reset to white
                }
            }

            // Highlight the selected item
            view.setBackgroundColor(getResources().getColor(R.color.selected_item_color));
        });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        removePizzaButton.setOnClickListener(v -> removeSelectedPizza());
        confirmOrderButton.setOnClickListener(v -> confirmOrder());
        clearOrderButton.setOnClickListener(v -> clearOrder());
    }

    /**
     * Displays the details of the current order, including pizzas and order summary.
     * Updates the UI components based on the order's state.
     */
    private void displayOrderDetails() {
        if (order != null && !order.getPizzas().isEmpty()) {
            orderNumberField.setText(String.valueOf(order.getNumber()));
            ArrayList<String> orderDetails = new ArrayList<>();

            for (Pizza pizza : order.getPizzas()) {
                orderDetails.add(pizza.toString() + " - $" + String.format("%.2f", pizza.price()));
            }

            ArrayAdapter<String> orderItemsAdapter = new ArrayAdapter<String>(this,
                    R.layout.order_item_layout, R.id.pizzaDetailsTextView, orderDetails);
            orderListView.setAdapter(orderItemsAdapter);

            updateOrderSummary();
            removePizzaButton.setEnabled(true);
            confirmOrderButton.setEnabled(true);
            clearOrderButton.setEnabled(true);
        } else {
            orderNumberField.setText("");
            orderListView.setAdapter(null);

            subtotalField.setText("");
            salesTaxField.setText("");
            totalField.setText("");
            removePizzaButton.setEnabled(false);
            confirmOrderButton.setEnabled(false);
            clearOrderButton.setEnabled(false);
        }
    }

    /**
     * Updates the subtotal, sales tax, and total fields based on the current order.
     */
    private void updateOrderSummary() {
        double subtotal = order.getTotalAmount();
        double tax = order.getSalesTax();
        double total = order.getOrderTotal();

        subtotalField.setText(String.format("%.2f", subtotal));
        salesTaxField.setText(String.format("%.2f", tax));
        totalField.setText(String.format("%.2f", total));
    }

    /**
     * Removes the selected pizza from the current order.
     * Updates the UI and order details accordingly.
     */
    private void removeSelectedPizza() {
        if (selectedPizzaIndex >= 0 && order != null) {
            Pizza selectedPizza = order.getPizzas().get(selectedPizzaIndex);
            order.removePizza(selectedPizza);
            selectedPizzaIndex = -1;
            removePizzaButton.setEnabled(false);
            displayOrderDetails();
        }
    }

    /**
     * Confirms the current order by adding it to the placed orders list.
     * Displays a confirmation dialog and resets the current order.
     */
    private void confirmOrder() {
        if (order != null && !order.getPizzas().isEmpty()) {
            Order orderCopy = order.cloner();
            ordersPlaced.add(orderCopy);

            new AlertDialog.Builder(this)
                    .setTitle("Order Confirmation")
                    .setMessage("Order Placed Successfully!\n\nOrder Number: " + order.getNumber() +
                            "\nTotal: $" + String.format("%.2f", order.getOrderTotal()))
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();

            order.addOneToNumber();
            order.getPizzas().clear();
            displayOrderDetails();
        }
    }

    /**
     * Clears all pizzas from the current order.
     * Updates the UI to reflect the cleared state.
     */
    private void clearOrder() {
        if (order != null) {
            order.getPizzas().clear();
            displayOrderDetails();
        }
    }
}