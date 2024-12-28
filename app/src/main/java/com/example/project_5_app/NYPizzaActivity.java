package com.example.project_5_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import PizzaPlace.PizzaPlace.BBQChicken;
import PizzaPlace.PizzaPlace.BuildYourOwn;
import PizzaPlace.PizzaPlace.NYPizza;
import PizzaPlace.PizzaPlace.Crust;
import PizzaPlace.PizzaPlace.Deluxe;
import PizzaPlace.PizzaPlace.Meatzza;
import PizzaPlace.PizzaPlace.Pizza;
import PizzaPlace.PizzaPlace.PizzaFactory;
import PizzaPlace.PizzaPlace.Size;
import PizzaPlace.PizzaPlace.Topping;

/**
 * Activity for selecting and customizing New York-style pizzas.
 * Provides options to choose pizza type, size, and toppings, and allows users
 * to add the pizza to the current order.
 * @author Keshav Dave, Danny Watson
 */
public class NYPizzaActivity extends AppCompatActivity {
    // Variables
    PizzaFactory pizzaFactory = new NYPizza();
    Pizza pizza;
    List<Topping> selectedToppings = new ArrayList<>();

    /**
     * Called when the activity is first created.
     * Initializes UI components, sets up dropdowns, listeners, and handles user actions.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nypizza_layout);

        Spinner pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        Spinner sizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        Spinner crustSpinner = findViewById(R.id.pizzaCrustSpinner);

        populateDropDowns(pizzaTypeSpinner, sizeSpinner);

        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedToppings.clear(); // Clear selected toppings
        pizza = createPizza(pizzaTypeSpinner);

        // Set the size spinner to default medium (position 1)
        sizeSpinner.setSelection(1);
        if (pizza != null) {
            pizza.setSize(Size.MEDIUM); // Explicitly set the pizza size to Medium
        }

        handlePizzaTypeSelection(pizza, crustSpinner);
        handleToppings(pizza); // Set up toppings RecyclerView
        handlePrice(pizza);
        handlePizzaImage(pizza);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
});

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (pizza != null) {
                    pizza.setSize(Size.values()[position]);
                    handlePrice(pizza);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(NYPizzaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> {
            clearSelections();
            Toast.makeText(this, "Selections cleared!", Toast.LENGTH_SHORT).show();
        });

        Button addToOrderButton = findViewById(R.id.placeOrderButton);
        addToOrderButton.setOnClickListener(v -> {
            if (pizza != null) {
                pizza.setStyle("NY Pizza");
                GlobalData.getInstance().getCurrentOrder().addPizza(pizza);
                Toast.makeText(this, "Pizza added to current order", Toast.LENGTH_SHORT).show();
                clearSelections();
            } else {
                Toast.makeText(this, "Please select a pizza to add.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Populates the dropdowns for pizza types and sizes.
     * @param pizzaTypeSpinner Spinner for selecting pizza type.
     * @param sizeSpinner      Spinner for selecting pizza size.
     */
    private void populateDropDowns(Spinner pizzaTypeSpinner, Spinner sizeSpinner) {
        List<String> pizzaTypes = Arrays.asList("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own");
        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, pizzaTypes);
        pizzaTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(pizzaTypeAdapter);

        List<Size> sizes = Arrays.asList(Size.SMALL, Size.MEDIUM, Size.LARGE);
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
    }

    /**
     * Creates a pizza based on the selected type from the spinner.
     * @param pizzaTypeSpinner Spinner for selecting pizza type.
     * @return The created pizza object.
     */
    private Pizza createPizza(Spinner pizzaTypeSpinner) {
        String selectedType = pizzaTypeSpinner.getSelectedItem().toString();
        switch (selectedType) {
            case "Deluxe":
                return pizzaFactory.createDeluxe();
            case "BBQ Chicken":
                return pizzaFactory.createBBQChicken();
            case "Meatzza":
                return pizzaFactory.createMeatzza();
            case "Build Your Own":
                return pizzaFactory.createBuildYourOwn();
            default:
                return null;
        }
    }

    /**
     * Updates the crust spinner to reflect the crust of the selected pizza.
     * @param pizza       The current pizza being customized.
     * @param crustSpinner Spinner for displaying the crust type.
     */
    private void handlePizzaTypeSelection(Pizza pizza, Spinner crustSpinner) {
        List<Crust> crusts = Collections.singletonList(pizza.getCrust());
        ArrayAdapter<Crust> crustAdapter = new ArrayAdapter<>(this, R.layout.spinner_layout, crusts);
        crustAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crustSpinner.setAdapter(crustAdapter);
    }

    /**
     * Sets up the toppings RecyclerView based on the selected pizza.
     * For "Build Your Own" pizzas, allows users to add or remove toppings.
     * @param pizza The current pizza being customized.
     */
    private void handleToppings(Pizza pizza) {
        RecyclerView toppingsRecyclerView = findViewById(R.id.toppingsRecyclerView);
        toppingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Topping> toppings;

        if (pizza instanceof BuildYourOwn) {
            toppings = Arrays.asList(Topping.values());
            selectedToppings.clear();
            selectedToppings.addAll(pizza.getToppings()); // Sync selected toppings with pizza
        } else {
            toppings = pizza.getToppings();
            selectedToppings.clear();
            selectedToppings.addAll(toppings);
        }

        ToppingsAdapter adapter = new ToppingsAdapter(
            this,
            toppings,
            selectedToppings,
            pizza instanceof BuildYourOwn,
            pizza, // Pass Pizza object
            topping -> handlePrice(pizza)
        );

        toppingsRecyclerView.setAdapter(adapter);
    }

    /**
     * Updates the displayed price of the current pizza based on its toppings and size.
     * @param pizza The current pizza being customized.
     */
    private void handlePrice(Pizza pizza) {
        EditText priceTextView = findViewById(R.id.priceTextViewDynamic);
        priceTextView.setText(String.format("%.2f", pizza.price()));
    }

    /**
     * Updates the pizza image displayed on the screen based on the selected pizza type.
     * @param pizza The current pizza being customized.
     */
    private void handlePizzaImage(Pizza pizza) {
        ImageView pizzaImageView = findViewById(R.id.pizzaImageView);
        int imageResource;

        if (pizza instanceof Deluxe) {
            imageResource = R.drawable.newyorkdeluxe;
        } else if (pizza instanceof BBQChicken) {
            imageResource = R.drawable.newyorkbbqchicken;
        } else if (pizza instanceof Meatzza) {
            imageResource = R.drawable.newyorkmeatzza;
        } else if (pizza instanceof BuildYourOwn) {
            imageResource = R.drawable.buildyourown;
        } else {
            imageResource = R.drawable.newyorkdeluxe;
        }

        pizzaImageView.setImageResource(imageResource);
    }

    /**
     * Clears all user selections and resets the dropdowns to default values.
     */
    private void clearSelections() {
        Spinner pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        Spinner sizeSpinner = findViewById(R.id.pizzaSizeSpinner);

        pizzaTypeSpinner.setSelection(0);
        sizeSpinner.setSelection(1);
    }
}