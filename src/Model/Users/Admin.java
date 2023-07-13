package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Restaurant;
import Model.Users.UserClasses.CreditCard;
import Others.Interfaces.AdminActions;

import java.util.ArrayList;

public class Admin extends Person implements AdminActions {


    public Admin(String username , String password , ID id){
        this.id = id;
        this.username=username;
        this.password=password;
        super.isAdmin=true;
        super.isPoster=false;
        super.cart=new ArrayList<>();
        super.creditCard=new CreditCard(0);
        orders=new ArrayList<>();
        super.isAdmin=true;
        super.isPoster=false;
    }

    public Admin(String userName, String thePassWord, String id, int creditCard, String massages, String cart) {
        this.username=userName;
        this.password=thePassWord;
        this.id= IDServer.toID(id);
        this.creditCard=new CreditCard(creditCard);
        super.cart=new ArrayList<>();orders=new ArrayList<>();
    }


    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }
}
