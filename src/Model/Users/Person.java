package Model.Users;

import Model.Users.UserClasses.CreditCard;
import Model.Users.UserClasses.Massage;

import java.util.ArrayList;

public abstract class Person {//implements UserActions
    protected String username , password;
    protected CreditCard creditCard;
    protected ArrayList<Massage> massages;


    public abstract void editMyRestaurantFoodPrice(Integer newPrise);

    public Boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}