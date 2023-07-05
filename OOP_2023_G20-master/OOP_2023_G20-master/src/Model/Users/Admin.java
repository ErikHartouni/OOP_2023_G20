package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.AdminActions;

import java.util.ArrayList;

public class Admin extends Person implements AdminActions {
    private ID adminID;


    public Admin(String username , String password , ID id){
        this.adminID = id;
        this.username=username;
        this.password=password;
    }



    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }

    @Override
    public Boolean canAddCommentForRestetaurant() {
        return null;
    }

    @Override
    public void addCommentForRestetaurant(Comment comment) {

    }

    @Override
    public Boolean canEditCommentForRestetaurant(String commentID) {
        return null;
    }

    @Override
    public void editCommentForRestetaurant(String commentID, StringBuilder newComment) {

    }

    @Override
    public Boolean canAddComment() {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public Boolean canEditComment(String commentID) {
        return null;
    }

    @Override
    public void editComment(String commentID, Comment comment) {

    }
}
