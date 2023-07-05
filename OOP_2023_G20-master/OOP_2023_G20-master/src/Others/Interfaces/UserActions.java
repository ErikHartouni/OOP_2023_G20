package Others.Interfaces;

import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;

public interface UserActions {
    String sayMyName();
    String getID();
    Boolean doesPasswordMatch(String password);
    ArrayList<Restaurant> searchRestaurant(String restaurantName);
    Boolean canAddCommentForRestetaurant();
    void addCommentForRestetaurant(Comment comment);
    Boolean canEditCommentForRestetaurant(String commentID);
    void editCommentForRestetaurant(String commentID, StringBuilder newComment);
    Boolean canAddComment();
    void addComment(Comment comment);
    Boolean canEditComment(String commentID);
    void editComment(String commentID, Comment comment);
    void addOrder();
    ArrayList<Order> accessOrderHistory();
    Order selectOrder(String orderID);
    void displayCartStatus();
    void confirmOrder();
    LocalTime showDeliveryTime();
    void chargeAccount(int increaseCharge);
    int displayAccountCharge();
}
