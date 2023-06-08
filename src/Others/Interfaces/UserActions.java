package Others.Interfaces;

import Model.RestaurantClasses.Restaurant;

import java.util.ArrayList;

public interface UserActions {
    Boolean doesPasswordMatch(String password);
    ArrayList<Restaurant> searchRestaurant(String restaurantName);
}
