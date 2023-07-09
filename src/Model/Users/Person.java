package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.Users.UserClasses.CreditCard;
import Model.Users.UserClasses.Massage;
import Others.Interfaces.UserActions;

import java.util.ArrayList;

public abstract class Person implements UserActions {
    private int numberOfGraph;
    protected String username , password;
    protected CreditCard creditCard;
    protected ArrayList<Massage> massages;
    protected ArrayList<Food> cart;
    protected Boolean isAdmin , isPoster;
    protected ID id;


    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }
    @Override
    public String sayMyName(){
        return username;
    }


    public Boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
    public Boolean isUser(){
        return (!isAdmin && !isPoster);
    }
    public Boolean isAdmin(){
        return isAdmin;
    }
    public void addToCart(Food food){

    }
    public ArrayList<Food> getFoodOfCart(){
        return null;
    }
    public ID giveID(){
        return this.id;
    }
    public String giveName(){
        return username;
    }
    public String givePass(){
        return password;
    }
    public int giveCart(){
        return this.creditCard.giveMoney();
    }
    public String giveMessages(){
        return null;
    }
    public String giveByuCart(){
        return null;
    }
    public void getMessageFromServer(Massage massage){
        massages.add(massage);
    }


}
