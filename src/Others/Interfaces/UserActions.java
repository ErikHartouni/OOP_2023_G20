package Others.Interfaces;

import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Restaurant;

import java.util.ArrayList;

public interface UserActions {
    String sayMyName();

    Boolean doesPasswordMatch(String password);
    ArrayList<Restaurant> searchRestaurant(String restaurantName);
    void addComment(Comment comment);
}
