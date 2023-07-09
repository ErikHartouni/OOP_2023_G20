package Model.RestaurantClasses;

import Model.Users.User;

import java.util.ArrayList;

public class Order {
    private ArrayList<Food> foods;
    private User buyer;
    public Boolean contains(Food food){
        return foods.contains(food);
    }

    //location missing
}
