package com.example.project_5_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import PizzaPlace.PizzaPlace.Pizza;
import PizzaPlace.PizzaPlace.Topping;

/**
 * Adapter for displaying and managing toppings in a RecyclerView.
 * Handles both "Build Your Own" pizza and predefined pizza types with fixed toppings.
 * Displays an image for each topping.
 *
 * @author Keshav Dave, Danny Watson
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ViewHolder> {

    private final Context context;
    private final List<Topping> toppings;
    private final List<Topping> selectedToppings;
    private final Pizza pizza;
    private final boolean isBuildYourOwn;
    private final OnToppingSelectedListener listener;

    /**
     * Listener interface for handling topping selection events.
     */
    public interface OnToppingSelectedListener {
        void onToppingSelected(Topping topping);
    }

    /**
     * Constructor to initialize the adapter with required data.
     *
     * @param context          The activity context.
     * @param toppings         The list of all available toppings.
     * @param selectedToppings The list of currently selected toppings.
     * @param isBuildYourOwn   Flag indicating if the pizza is "Build Your Own."
     * @param pizza            The pizza object to manipulate toppings.
     * @param listener         Listener to handle topping selection events.
     */
    public ToppingsAdapter(Context context, List<Topping> toppings, List<Topping> selectedToppings,
                           boolean isBuildYourOwn, Pizza pizza, OnToppingSelectedListener listener) {
        this.context = context;
        this.toppings = toppings;
        this.selectedToppings = selectedToppings;
        this.isBuildYourOwn = isBuildYourOwn;
        this.pizza = pizza;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topping_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topping topping = toppings.get(position);

        // Set topping name
        holder.toppingName.setText(topping.toString());

        // Set topping image
        int imageResource = getImageForTopping(topping);
        holder.toppingImage.setImageResource(imageResource);

        // Check if the topping is unchangeable
        if (!isBuildYourOwn && isUnchangeableTopping(topping)) {
            holder.toppingCheckbox.setChecked(true); // Always checked
            holder.toppingCheckbox.setEnabled(false); // Disable checkbox for unchangeable toppings
        } else {
            // Enable checkbox for Build Your Own
            holder.toppingCheckbox.setChecked(selectedToppings.contains(topping));
            holder.toppingCheckbox.setEnabled(true);

            holder.toppingCheckbox.setOnClickListener(v -> {
                if (holder.toppingCheckbox.isChecked()) {
                    if (selectedToppings.size() < 7) {
                        selectedToppings.add(topping);
                        pizza.addTopping(topping);
                        if (listener != null) {
                            listener.onToppingSelected(topping);
                        }
                    } else {
                        holder.toppingCheckbox.setChecked(false);
                        Toast.makeText(context, "You can only select up to 7 toppings.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    selectedToppings.remove(topping);
                    pizza.removeTopping(topping);
                    if (listener != null) {
                        listener.onToppingSelected(topping);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    /**
     * Helper method to determine if a topping is unchangeable (fixed for predefined pizza types).
     *
     * @param topping The topping to check.
     * @return True if the topping cannot be changed, false otherwise.
     */
    private boolean isUnchangeableTopping(Topping topping) {
        List<Topping> deluxeToppings = List.of(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER,
                                               Topping.MUSHROOM, Topping.ONION);
        List<Topping> bbqToppings = List.of(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE,
                                            Topping.CHEDDAR);
        List<Topping> meatzzaToppings = List.of(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM);
        return deluxeToppings.contains(topping) || bbqToppings.contains(topping) || meatzzaToppings.contains(topping);
    }

    /**
     * Helper method to map toppings to their corresponding drawable resources.
     *
     * @param topping The topping to get the image for.
     * @return The drawabl
            e resource ID for the topping image.
     */
    private int getImageForTopping(Topping topping) {
        switch (topping) {
            case BACON:
                return R.drawable.bacon;
            case BBQ_CHICKEN:
                return R.drawable.bbqchicken;
            case BEEF:
                return R.drawable.beef;
            case BLACK_OLIVES:
                return R.drawable.blackolives;
            case CHEDDAR:
                return R.drawable.cheddar;
            case EXTRA_CHEESE:
                return R.drawable.extracheese;
            case HAM:
                return R.drawable.ham;
            case GREEN_PEPPER:
                return R.drawable.greenpepper;
            case MUSHROOM:
                return R.drawable.mushroom;
            case ONION:
                return R.drawable.onion;
            case PEPPERONI:
                return R.drawable.pepperoni;
            case PINEAPPLE:
                return R.drawable.pineapple;
            case PROVOLONE:
                return R.drawable.provolone;
            case SAUSAGE:
                return R.drawable.sausage;
            case SPINACH:
                return R.drawable.spinach;
            default:
                return R.drawable.chicagobbqchicken; // Fallback image
        }
    }

    /**
     * ViewHolder class for managing the topping item views in the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView toppingName;
        CheckBox toppingCheckbox;
        ImageView toppingImage;

        /**
         * Constructor to initialize the views in a topping item.
         *
         * @param itemView The view of the item.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            toppingName = itemView.findViewById(R.id.toppingName);
            toppingCheckbox = itemView.findViewById(R.id.toppingCheckbox);
            toppingImage = itemView.findViewById(R.id.toppingImage);
        }
    }
}