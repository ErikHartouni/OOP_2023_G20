package Model.DataServer;

import Model.RestaurantClasses.Restaurant;

import java.util.ArrayList;

public class RestaurantSaver {
    private ArrayList<Restaurant> restaurants;


    public void addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
    }
}
