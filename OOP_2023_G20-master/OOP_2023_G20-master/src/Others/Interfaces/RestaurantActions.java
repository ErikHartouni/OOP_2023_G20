package Others.Interfaces;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;
import Model.Users.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RestaurantActions {
    User getOwner();
    String getName();
    String getID();
    Food getSelectedFood();
    String  getIDOfSelectedFood(int indexOfSelectedFood);
    int getIndexOfSelectedFood();
    String print();//this will give a string that will have the name and the id of the restaurant
    RestaurantType showRestaurantType(); // just return the type
    Boolean canChangeRestaurantType();//true if can
    void EditFoodType(RestaurantType restaurantType);//just change it
    ArrayList<Food> giveAllFoods();
    Boolean doesFoodExist(String id);//return true if exists
    void editFoodName(int indexOfMySelectedFood , String newName);
    void editFoodPrice(int indexOfMySelectedFood, Integer newPrise);
    Boolean doesFoodNameExists(String name);
    void addFood(String name, FoodType foodType , LocalTime time , Integer price , Integer discountRate , ID foodID);
    void addFood(String name , Integer price);
    int canDeleteOrDeactivateFood(String id);
    void deleteFood(String id);
    void deactivateFood(String id);
    void activateFood(String id);
    int doesFoodHaveDiscount(String id);//doesn't have to check the value of discount
    void discountFood(String id , Integer discount, LocalDateTime timeStamp);
    ArrayList<String>displayRating();
    void submitRating(int rating, String userID);
    void editRating(int rating, String userID);
    void selectFood(String id);
    ArrayList<Comment> giveComments();
    ArrayList<Comment> displayCommentsForRestaurant();
    void addComment(StringBuilder comment, ID id, Person sender);
    void addComment(Comment comment);
    Boolean doesCommentExist(String id);
    void addNewResponse(String commentID, String response);
    void editResponse(String commentID, String response);
    void editOrder(String orderID);
    ArrayList<Order> showOrderHistory();
    void addCommentForRestetaurant(Comment comment);
    Boolean canEditCommentForRestetaurant(String personID, String commentID);
    void editCommentForRestetaurant(String commentID, StringBuilder newComment);
    Boolean canEditComment(String id);
    Boolean canEditComment(String personID, String commentID);
    void editComment(String commentID, Comment comment);
    Boolean isSender(String personID,String id);
    void editComment(String id, StringBuilder newComment);
    ArrayList<Order> displayOpenOrders();
}
