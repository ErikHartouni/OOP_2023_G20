package Others.Interfaces;

import Model.RestaurantClasses.Comment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface FoodActions {
    void activate();
    void deactivate();
    Boolean doesHaveDiscount();
    LocalTime getTime();
    String getName();
    String getID();
    int getPrice();
    void editFoodName(String newName);
    void editFoodPrice(Integer newPrise);
    void setDiscount(int discount, LocalDateTime timeStamp);
    ArrayList<String> changeRatingsToString();
    ArrayList<String> displayRating();//will give a string that will have number of every rating
    void submitRating(int rating, String userID);
    void editRating(int rating, String userID);
    String show();
    ArrayList<Comment> giveComments();
   void addComment(Comment comment);
   int doesCommentExist(String id);
    void addNewResponse(String commentID, String response);
    void editResponse(String commentID, String response);
   Boolean canEditComment(String id);//no more that one edit...
    Boolean canEditComment(String personID, String commentID);
    void editComment(String commentID, Comment comment);
   Boolean isSender(String personID, String id);
    void editComment(String id, StringBuilder newComment);
}
