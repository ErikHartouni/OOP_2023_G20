package Others.Interfaces;

import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.RestaurantType;

import java.util.ArrayList;

public interface RestaurantOwnerActions extends UserActions{
    ArrayList < Restaurant > giveMyRestaurants();
    void createRestaurant(String name);//will need to change this to implement location
    RestaurantType showRestaurantType(); // use the same method in restaurant
    Boolean canChangeMyRestaurantType();//use the same method in restaurant
    void editMyRestaurantFoodType(RestaurantType restaurantType);//use the same method in restaurant
    ArrayList<Food> giveAllFoodsOfMyRestaurant();
    Boolean doesFoodExistInMyRestaurant(String id);//return true if exists
    void selectFood(String id);
    void editMyRestaurantFoodName(String newName);
    void editMyRestaurantFoodPrice(String id , Integer newPrise);
    Boolean doesFoodNameExistsInMyRestaurant(String name);
    void addFoodToMyRestaurant(String name , Integer price);
    Boolean canDeleteOrDeactivateFood(String id);
    void deleteFood(String id);
    void deactivateFood(String id);
    void activateFood(String id);
    Boolean doesFoodHaveDiscount(String id);//doesn't have to check the value of discount
    void discountFood(String id , Integer discount);
    ArrayList<String> displayRating();
    void back();
    


}
