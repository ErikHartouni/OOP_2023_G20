package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.RestaurantClasses.Types.FoodType;
import Model.Users.Person;
import Others.Interfaces.FoodActions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Food implements FoodActions {
    private FoodType foodType;
    private String foodName;
    private LocalTime timeToMake;
    private LocalDateTime discountTime;
    private Integer price, G , M , B , VB , discountRate; //G: good , VB : very bad
    private ID foodID , restaurantID;
    private Boolean discount, activation,deleted;//true if active
    private ArrayList<Comment> comments;
    private ArrayList<Rating>ratings;
    public Food(String name, FoodType foodType , LocalTime time , Integer price , Integer discountRate , ID foodID, ID restaurantID){
        this.foodName = name ; this.foodType=foodType ; this.timeToMake = time ; this.price = price;
        this.discountRate = discountRate ; this.foodID = foodID;this.activation=true;this.restaurantID=restaurantID;
        this.deleted=false;
    }

    public Food(Food food) {
    }

    public Food(String fName, String fType, String id, int discount, int price, String ttm, String restaurantID,boolean activation,boolean deleted) {
        this.foodName=fName;
        this.foodType=FoodType.valueOf(fType);
        this.foodID= IDServer.toID(id);
        this.discountRate=discount;
        this.price=price;this.deleted=deleted;
        this.timeToMake=LocalTime.parse(ttm);
        this.restaurantID=IDServer.toID(restaurantID);
        this.activation=activation;
    }
    public void rate(int i){

    }

    public Boolean isActive(){
        return activation;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {
        this.activation=false;
    }

    @Override
    public Boolean doesHaveDiscount() {
        return discountRate!=0;
    }

    @Override
    public String getName() {
        return this.foodName;
    }

    @Override
    public void setDiscount(int discount) {

    }


    public Boolean setDiscount(int discount , LocalDateTime time) {
        if(discount==0) {
            this.discountRate = discount;
            this.discountTime = time;return true;
        }return false;
    }

    @Override
    public ArrayList<String> displayRating() {
        return null;
    }

    @Override
    public String show() {
        return this.foodName+" id:"+this.foodID.show()+" price:"+this.price.toString()+"$ off:"+
                this.discountRate.toString()+"% ";//remember to print activation...
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return comments;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public Boolean doesCommentExist(String id) {
        return null;
    }

    @Override
    public Boolean canEditComment(String id) {
        return null;
    }

    @Override
    public Boolean isSender(Person person) {
        return null;
    }
    @Override
    public void editComment(String id, StringBuilder newComment) {
    }
    public String getID(){
        return foodID.show();
    }
    public void editFoodName(String foodName){
        this.foodName=foodName;
    }

    public void editPrice(Integer newPrise) {
        this.price=newPrise;
    }
    public String getType(){
        return this.foodType.toString();
    }

    public int getDiscount() {
        return this.discountRate;
    }

    public int getPrice() {
        return this.price;
    }

    public String getTTM() {
        return this.timeToMake.toString();
    }
    public Boolean isInThisRestaurant(String id){
        return id.equals(restaurantID.show());
    }
    public String giveRestaurantID(){
        return this.restaurantID.show();
    }
    public String toString(){
        return foodName+" "+foodID+" "+price;
    }

    public boolean isDeleted() {
        return isDeleted();
    }
}
