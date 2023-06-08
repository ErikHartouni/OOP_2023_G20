package Model.Users;

import Model.RestaurantClasses.Restaurant;
import Model.Users.UserClasses.CreditCard;
import Model.Users.UserClasses.Massage;
import Others.Interfaces.UserActions;

import java.util.ArrayList;

public abstract class Person implements UserActions {
    protected String username , password;
    protected CreditCard creditCard;
    protected ArrayList<Massage> massages;


    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }


    public Boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
