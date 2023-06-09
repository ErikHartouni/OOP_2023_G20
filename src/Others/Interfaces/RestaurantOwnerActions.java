package Others.Interfaces;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RestaurantOwnerActions extends UserActions{
    ArrayList < Restaurant > giveMyRestaurants();
    void createRestaurant(String name , User owner , ID id , RestaurantType restaurantType);//will need to change this to implement location
    void createRestaurant(Restaurant restaurant);
    void chooseRestaurant(String id);//find and set restaurant . Do not check if there is no restaurant with this id
    RestaurantType showRestaurantType(); // use the same method in restaurant
    Boolean canChangeMyRestaurantType();//use the same method in restaurant
    void cahngeMyRestaurantType(RestaurantType restaurantType);//use the same method in restaurant
    ArrayList<Food> giveAllFoodsOfMyRestaurant();
    Boolean doesFoodExistInMyRestaurant(String id);//return true if exists
    void selectFood(String id);
    void editMyRestaurantFoodName(String newName);
    void editMyRestaurantFoodPrice(Integer newPrise);
    Boolean doesFoodNameExistsInMyRestaurant(String name);

    void addFoodToMyRestaurant(String name, FoodType foodType, LocalTime time,
                               Integer price, Integer discountRate, ID foodID);

    Boolean canDeleteOrDeactivateFood(String id);
    void deleteFood(String id);
    void deactivateFood(String id);
    void activateFood(String id);
    Boolean doesFoodHaveDiscount();//doesn't have to check the value of discount
    Boolean discountFood( Integer discount , LocalDateTime time);//return true if it was done (check value of discount)


    ArrayList<String> displayRating();
    void back();
    


}
