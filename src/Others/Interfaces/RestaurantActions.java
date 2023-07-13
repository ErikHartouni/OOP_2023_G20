package Others.Interfaces;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Rating;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;

import java.time.LocalTime;
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

    Food addFood(String name, Integer price, FoodType foodType,
                 LocalTime time, Integer discountRate, ID foodID);

    Boolean canDeleteOrDeactivateFood(String id);
    void deleteFood(String id);
    void deactivateFood(String id);
    void activateFood(String id);
    Boolean doesFoodHaveDiscount(String id);//doesn't have to check the value of discount
    void discountFood(String id , Integer discount);
    ArrayList<String>displayRating();
    void selectFood(String id);
    ArrayList<Comment> giveComments();
    void addComment(StringBuilder comment, ID id, Person sender);

    void addComment(Comment comment);

    Boolean doesCommentExist(String id);

    Boolean canEditComment(String s);
    Boolean isSender(Person person);
    void appendRating(Rating rating);

    void editComment(String id, StringBuilder newComment);

    Food giveSelectedFood();
}
