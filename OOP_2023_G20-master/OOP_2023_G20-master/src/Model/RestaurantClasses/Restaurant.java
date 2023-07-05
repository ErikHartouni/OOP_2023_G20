package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;
import Model.Users.User;
import Others.Interfaces.RestaurantActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Restaurant implements RestaurantActions {
    private String name;
    private User owner;
    private ID restaurantID;
    private RestaurantType restaurantType;
    private ArrayList < Order > activeOrders;
    private ArrayList < Order > history;
    private ArrayList <Food> foods;
    private Food selectedFood;
    private ArrayList<Comment> comments;

    public Restaurant(String name , User owner , ID id , RestaurantType restaurantType){
        this.restaurantID=id;
        this.name=name ;
        this.owner=owner;
        this.restaurantType= restaurantType;
        this.activeOrders=new ArrayList<>();
    }

    @Override
    public User getOwner() {
        return this.owner;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String getID() {
        return this.restaurantID.show();
    }

    @Override
    public Food getSelectedFood() {
        return this.selectedFood;
    }

    @Override
    public String getIDOfSelectedFood(int indexOfSelectedFood) {
        return this.foods.get(indexOfSelectedFood).getID();
    }

    @Override
    public int getIndexOfSelectedFood() {
        return this.foods.indexOf(selectedFood);
    }

    @Override
    public String print() {
        return this.name +" "+this.restaurantID.show();
    }//ended

    @Override
    public RestaurantType showRestaurantType() {
        return this.restaurantType;
    }//ended

    @Override
    public Boolean canChangeRestaurantType() {
        return activeOrders.isEmpty();
    }

    @Override
    public void EditFoodType(RestaurantType restaurantType) {
        if (this.canChangeRestaurantType()) {
            this.restaurantType=restaurantType;
            for (int i=0; i < this.foods.size() ;i++)
                this.foods.remove(i);
            for (int i=0; i < this.activeOrders.size() ;i++)
                this.activeOrders.remove(i);
            this.selectedFood=null;
        }
        // owner must get new foods
    }

    @Override
    public ArrayList<Food> giveAllFoods() {
        return this.foods;
    }

    @Override
    public Boolean doesFoodExist(String id) {
        for (int i=0; i < this.foods.size() ;i++)
            if (this.foods.get(i).getID().equals(id))
                return true;
        return false;
    }

    @Override
    public void editFoodName(int indexOfMySelectedFood, String newName) {
        this.foods.get(indexOfMySelectedFood).editFoodName(newName);
    }

    @Override
    public void editFoodPrice(int indexOfMySelectedFood, Integer newPrise) {
        this.foods.get(indexOfMySelectedFood).editFoodPrice(newPrise);
    }

    @Override
    public Boolean doesFoodNameExists(String name) {
        for (int i=0; i < this.foods.size() ;i++)
            if (this.foods.get(i).getName().equals(name))
                return true;
        return false;
    }

    @Override
    public void addFood(String name, FoodType foodType, LocalTime time, Integer price, Integer discountRate, ID foodID) {
        Food food=new Food(name,foodType,time,price,discountRate,foodID);
        this.foods.add(food);
    }

    @Override
    public void addFood(String name, Integer price) {
        Food food=new Food(name,price);
        this.foods.add(food);
    }

    @Override
    public int canDeleteOrDeactivateFood(String id) {
        int foodIndex=-1;
        for (int i=0; i < this.foods.size() ;i++)
            if (this.foods.get(i).getID().equals(id)) {
                foodIndex=i;
                break;
            }
        for (int i=0; i < this.activeOrders.size() ;i++)
            if (this.activeOrders.get(i).getID().equals(id))
                return -1;
        return foodIndex;
    }

    @Override
    public void deleteFood(String id) {
        int foodIndex=this.canDeleteOrDeactivateFood(id);
        if (foodIndex!=-1)
            this.foods.remove(foodIndex);
    }

    @Override
    public void deactivateFood(String id) {
        int foodIndex=this.canDeleteOrDeactivateFood(id);
        if (foodIndex!=-1)
            this.foods.get(foodIndex).deactivate();

    }

    @Override
    public void activateFood(String id) {
        int foodIndex=this.canDeleteOrDeactivateFood(id);
        if (foodIndex!=-1)
            this.foods.get(foodIndex).activate();
    }

    @Override
    public int doesFoodHaveDiscount(String id) {//-1 = not have
        for (int i=0; i < this.foods.size() ;i++)
            if (this.foods.get(i).getID().equals(id))
                if (this.foods.get(i).doesHaveDiscount())
                    return i;
        return -1;
    }

    @Override
    public void discountFood(String id, Integer discount, LocalDateTime timeStamp) {
        if (this.doesFoodExist(id)) {
            int foodIndex=this.doesFoodHaveDiscount(id);
            this.foods.get(foodIndex).setDiscount(discount,timeStamp);
        }

    }

    @Override
    public ArrayList<String> displayRating() {
        return this.selectedFood.displayRating();
    }

    @Override
    public void submitRating(int rating, String userID) {
        this.selectedFood.submitRating(rating,userID);
    }


    @Override
    public void editRating(int rating, String userID) {
        this.selectedFood.editRating(rating,userID);
    }

    @Override
    public void selectFood(String id) {
        for (int i=0; i < this.foods.size() ;i++)
            if (this.foods.get(i).getID().equals(id))
                this.selectedFood=this.foods.get(i);
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return selectedFood.giveComments();
    }

    @Override
    public ArrayList<Comment> displayCommentsForRestaurant() {
        return this.comments;
    }

    @Override
    public void addComment(StringBuilder comment, ID id, Person sender) {
        this.selectedFood.addComment(new Comment(comment,id,sender));
    }

    @Override
    public void addComment(Comment comment) {
        this.selectedFood.addComment(comment);
    }

    @Override
    public Boolean doesCommentExist(String id){
        return this.selectedFood.doesCommentExist(id)!=-1;
    }

    @Override
    public void addNewResponse(String commentID, String response) {
        this.selectedFood.addNewResponse(commentID,response);
    }

    @Override
    public void editResponse(String commentID, String response) {
        this.selectedFood.editResponse(commentID,response);
    }

    @Override
    public void editOrder(String orderID) {
        for (int i=0; i < this.activeOrders.size() ;i++)
            if (this.activeOrders.get(i).getID().equals(orderID))
                this.activeOrders.get(i).editStatus();
    }

    @Override
    public ArrayList<Order> showOrderHistory() {
        return this.history;
    }

    @Override
    public void addCommentForRestetaurant(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public Boolean canEditCommentForRestetaurant(String personID, String commentID) {
        if (!this.isSender(personID,commentID))
            return false;
        for (int i=0; i < this.comments.size() ;i++)
            if (this.comments.get(i).getID().equals(commentID))
                return true;
        return false;
    }

    @Override
    public void editCommentForRestetaurant(String commentID, StringBuilder newComment) {
        for (int i=0; i < this.comments.size() ;i++)
            if (this.comments.get(i).getID().equals(commentID))
                this.comments.get(i).editComment(newComment);
    }

    @Override
    public Boolean canEditComment(String id) {
        return this.selectedFood.canEditComment(id);
    }

    @Override
    public Boolean canEditComment(String personID, String commentID) {
        return this.selectedFood.canEditComment(personID,commentID);
    }

    @Override
    public void editComment(String commentID, Comment comment) {
        this.selectedFood.editComment(commentID,comment);
    }

    @Override
    public Boolean isSender(String personID, String id) {
        return this.selectedFood.isSender(personID,id);
    }
    @Override
    public void editComment(String id, StringBuilder newComment) {
        if (this.canEditComment(id))
            this.selectedFood.editComment(id,newComment);
    }

    @Override
    public ArrayList<Order> displayOpenOrders() {
        return this.activeOrders;
    }
}
