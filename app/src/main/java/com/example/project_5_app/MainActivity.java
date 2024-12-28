package com.example.project_5_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main menu activity for the Android app.
 * Provides navigation to Chicago Style Pizza, NY Style Pizza, Current Order, and Orders Placed.
 *
 * @author Keshav Dave, Danny Watson
 */
public class MainActivity extends AppCompatActivity {
    private ImageView chicagoPizzaImage;
    private ImageView nyPizzaImage;
    private ImageView ordersplacedimage;
    private ImageView currentOrderImage;

    /**
     * Called when the activity is first created.
     * Initializes the UI components and sets click listeners for each
     * ImageView to navigate to the respective activities.
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down,
     *                           this Bundle contains the data it most
     *                           recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenulayout);

        chicagoPizzaImage = findViewById(R.id.chicagoPizzaImage);
        nyPizzaImage = findViewById(R.id.nyPizzaImage);
        ordersplacedimage = findViewById(R.id.ordersPlacedImage);
        currentOrderImage = findViewById(R.id.currentOrderImage);

        chicagoPizzaImage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChicagoPizzaActivity.class);
            startActivity(intent);
        });

        nyPizzaImage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NYPizzaActivity.class);
            startActivity(intent);
        });

        ordersplacedimage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrdersPlacedActivity.class);
            startActivity(intent);
        });

        currentOrderImage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        });
    }
}