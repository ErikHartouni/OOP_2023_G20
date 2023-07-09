package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.AdminActions;

import java.util.ArrayList;

public class Admin extends Person implements AdminActions {


    public Admin(String username , String password , ID id){
        this.id = id;
        this.username=username;
        this.password=password;
        super.isAdmin=true;
        super.isPoster=false;
    }

    public Admin(String userName, String thePassWord, String id, int creditCard, String massages, String cart) {

    }


    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }
}
