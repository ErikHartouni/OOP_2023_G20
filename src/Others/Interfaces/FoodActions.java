package Others.Interfaces;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.Users.Person;

import java.util.ArrayList;
import java.util.Collection;

public interface FoodActions {
    void activate();
    void deactivate();
    Boolean doesHaveDiscount();
    String getName();
    void setDiscount(int discount);
    ArrayList<String> displayRating();//will give a string that will have number of every rating
    String show();
    ArrayList<Comment> giveComments();
   void addComment(Comment comment);
   Boolean doesCommentExist(String id);
   Boolean canEditComment(String id);//no more that one edit...
   Boolean isSender(Person person);


    void editComment(String id, StringBuilder newComment);
}
