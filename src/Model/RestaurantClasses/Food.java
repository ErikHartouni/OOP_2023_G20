package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.FoodType;
import Others.Interfaces.FoodActions;

import java.time.LocalTime;
import java.util.ArrayList;

public class Food implements FoodActions {
    private FoodType foodType;
    private String foodName;
    private LocalTime timeToMake;
    private Integer price, G , M , B , VB , discountRate; //G: good , VB : very bad
    private ID foodID;
    private Boolean discount, activation;//true if active
    private ArrayList<Comment> comments;

    public Food(String name, FoodType foodType , LocalTime time , Integer price , Integer discountRate , ID foodID){
        this.foodName = name ; this.foodType=foodType ; this.timeToMake = time ; this.price = price;
        this.discountRate = discountRate ; this.foodID = foodID;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public Boolean doesHaveDiscount() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setDiscount(int discount) {

    }

    @Override
    public ArrayList<String> displayRating() {
        return null;
    }

    @Override
    public String show() {
        return this.foodName+" id:"+this.foodID+" price:"+this.price.toString()+"$ off:"+this.discountRate.toString()+"%";
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return comments;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
