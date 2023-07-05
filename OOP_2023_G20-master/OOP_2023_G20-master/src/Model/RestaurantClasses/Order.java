package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.Users.User;

public class Order {
    private ID orderID;
    private Food food;
    private Restaurant restaurant;
    private User buyer;
    private String status=new String("making");
    public Order(Food food,Restaurant restaurant){
        this.restaurant=restaurant;
        this.food=food;
    }

    //location missing
    public String getID() {
        return this.food.getID();
    }
    public Food getFood() {
        return this.food;
    }
    public void editStatus(){
        this.status=new String("sent");
    }
}
