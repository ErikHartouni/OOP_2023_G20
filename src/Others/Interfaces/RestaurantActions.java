package Others.Interfaces;

import Controller.InputAnalyzer;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.User;

import java.util.ArrayList;

public interface RestaurantActions {
    String print();//this will give a string that will have the name and the id of the restaurant
    RestaurantType showRestaurantType(); // just return the type
    Boolean canChangeRestaurantType();//true if can
    void EditFoodType(RestaurantType restaurantType);//just change it
    ArrayList<Food> giveAllFoods();
    Boolean doesFoodExist(String id);//return true if exists
    void editFoodName(String id , String newName);
    void editFoodPrice(String id , Integer newPrise);
    Boolean doesFoodNameExists(String name);
    void addFood(String name , Integer price);
    Boolean canDeleteOrDeactivateFood(String id);
    void deleteFood(String id);
    void deactivateFood(String id);
    void activateFood(String id);
    Boolean doesFoodHaveDiscount(String id);//doesn't have to check the value of discount
    void discountFood(String id , Integer discount);
    ArrayList<String>displayRating();
    void selectFood(String id);
    ArrayList<Comment> giveComments();



}
