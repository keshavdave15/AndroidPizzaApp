Android Pizza App
  A dynamic Android application that simulates an ordering system for RU Pizzeria, allowing users to customize, order, and manage pizzas. 

Features
  Pizza Styles: Supports Chicago and New York-style pizzas.
  Specialty Pizzas: Deluxe, BBQ Chicken, Meatzza with predefined toppings.
  Build Your Own Pizza: Customize pizzas with up to 7 toppings from a selection of 13 available options.
  Dynamic Pricing:
  	•	Base prices vary by pizza size (Small, Medium, Large).
  	•	Additional cost per topping for custom pizzas.
  Order Management:
  	•	Add multiple pizzas to a single order.
  	•	View, modify, or delete pizzas in an order.
  	•	Real-time calculation of subtotals, sales tax (NJ rate of 6.625%), and order totals.
  Export Orders: Save order details to a text file for record-keeping.

Key Technical Highlights
  Android Components:
  	•	Utilizes RecyclerView to display pizza options and toppings with images.
  	•	Implements Spinner, AlertDialog, ListView, and Toast for interactive UI elements.
  Multi-Screen Navigation:
  	•	Two Android Activities with associated XML layouts for smooth user experience.
  Singleton Design Pattern:
  	•	A globally shared data model for managing orders and pizza data.
  Custom Icons and Resources:
  	•	Custom launcher icon and pizza images for a visually appealing app.
  String Resources:
  	•	Eliminated hardcoded text warnings by using strings.xml.

Technology Stack
  Java: Core language for business logic and app functionality.
  XML: Layout design for Android screens.
  Gradle: Build and dependency management.
  Android Studio: IDE for development and testing.

Code Structure
  app/src/main/java/: Contains Java source files, including:
  	•	Pizza class hierarchy (Deluxe, BBQChicken, Meatzza, BuildYourOwn).
  	•	Controllers for managing orders and pizza creation.
  app/src/main/res/: Contains resources, such as:
  	•	layout/: XML files for UI screens.
  	•	mipmap/: Launcher icon resources.
  	•	values/: Strings and style definitions.
