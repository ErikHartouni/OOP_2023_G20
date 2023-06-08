package Others.Interfaces;

import Model.DataServer.RestaurantSaver;
import Model.RestaurantClasses.Restaurant;

import java.util.ArrayList;

public interface RestaurantDataServerActions {
    ArrayList<Restaurant> searchRestaurant(String name);
    Boolean doesRestaurantExist(String id);
    Restaurant giveRestaurant(String id);
}
