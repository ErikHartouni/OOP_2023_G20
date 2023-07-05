package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.FoodType;
import Model.Users.UserClasses.Massage;
import Others.Interfaces.FoodActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Food implements FoodActions {
    private FoodType foodType;
    private String foodName;
    private LocalTime timeToMake;
    private LocalDateTime timeStamp;
    private Integer price, G , M , B , VB , discountRate; //G: good , VB : very bad
    private ID foodID;
    private Boolean discount, activation;//true if active
    private ArrayList<Comment> comments;
    private HashMap<Integer, String> ratings;//Rating & userID
    private HashMap<String , Massage> responseMap;// id & response


    public Food(String name, FoodType foodType , LocalTime time , Integer price , Integer discountRate , ID foodID){
        this.foodName = name ; this.foodType=foodType ; this.timeToMake = time ; this.price = price;
        this.discountRate = discountRate ; this.foodID = foodID;
    }
    public Food(String name, Integer price){
        this.foodName = name ; this.price = price;
        this.discountRate =0;
    }

    @Override
    public void activate() {
        this.activation=true;
    }

    @Override
    public void deactivate() {
        this.activation=false;
    }

    @Override
    public Boolean doesHaveDiscount() {
        return this.discount;
    }

    @Override
    public LocalTime getTime() {
        return this.timeToMake;
    }

    @Override
    public String getName() {
        return this.foodName;
    }

    @Override
    public String getID() {
        return this.foodID.show();
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void editFoodName(String newName) {
        this.foodName=newName;
    }

    @Override
    public void editFoodPrice(Integer newPrise) {
        this.price=newPrise;
    }

    @Override
    public void setDiscount(int discount, LocalDateTime timeStamp) {
        this.discountRate=discount;
        this.timeStamp=timeStamp;
    }

    @Override
    public ArrayList<String> changeRatingsToString() {
        ArrayList<String> rate =new ArrayList<String>();
        for (int i=0; i < this.ratings.size() ;i++)
                rate.add(this.ratings.get(i).toString());
        return rate;
    }

    @Override
    public ArrayList<String> displayRating() {
        return this.changeRatingsToString();
    }

    @Override
    public void submitRating(int rating, String userID) {
        this.ratings.put(rating,userID);
    }

    @Override
    public void editRating(int rating, String userID) {
        if (this.ratings.containsValue(userID))
            this.ratings.put(rating,userID);
    }

    @Override
    public String show() {
        return this.foodName+" id:"+this.foodID+" price:"+this.price.toString()+"$ off:"+this.discountRate.toString()+"%";
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return this.comments;
    }

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public int doesCommentExist(String id) {
        for (int i=0; i < this.comments.size() ;i++)
            if (this.comments.get(i).getID().equals(id))
                return i;
        return -1;
    }

    @Override
    public void addNewResponse(String commentID, String response) {
        Massage massage=new Massage(response);
        this.responseMap.put(commentID,massage);
    }

    @Override
    public void editResponse(String commentID, String response) {
        Massage massage=new Massage(response);
        this.responseMap.put(commentID,massage);

    }

    @Override
    public Boolean canEditComment(String id) {
        int commentIndex=this.doesCommentExist(id);
        if (commentIndex!=-1 )
            if (this.comments.get(commentIndex).canEditComment())
                return true;
        return false;
    }

    @Override
    public Boolean canEditComment(String personID, String commentID) {
        int commentIndex=this.doesCommentExist(commentID);
        if (commentIndex!=-1)
            if (this.comments.get(commentIndex).canEditComment())
                return this.comments.get(commentIndex).isSender(personID);
        return false;
    }

    @Override
    public void editComment(String commentID, Comment comment) {
        for (int i=0; i < this.comments.size() ;i++)
            if (this.comments.get(i).getID().equals(commentID))
                this.comments.get(i).editComment(comment.getComment());
    }

    @Override
    public Boolean isSender(String personID, String id) {
        int commentIndex=this.doesCommentExist(id);
        if (commentIndex!=-1)
            return this.comments.get(commentIndex).isSender(personID);
        return false;
    }
    @Override
    public void editComment(String id, StringBuilder newComment) {
        if (this.canEditComment(id))
            for (int i=0; i < this.comments.size() ;i++)
                if (this.comments.get(i).getID().equals(id))
                    this.comments.get(i).editComment(newComment);
    }
}
