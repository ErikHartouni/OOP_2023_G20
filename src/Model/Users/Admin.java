package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.AdminActions;

import java.util.ArrayList;

public class Admin extends Person{//implements AdminActions {
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
}
