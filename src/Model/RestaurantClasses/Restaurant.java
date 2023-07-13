package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;
import Model.Users.User;
import Others.Interfaces.RestaurantActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Restaurant implements RestaurantActions {
    private int numberOfGraph;
    private String name;
    private ID restaurantID , ownerID;
    private RestaurantType restaurantType;
    private ArrayList < Order > activeOrders;
    private ArrayList <Food> foods;
    private Food selectedFood;
    private ArrayList<Integer> ratings;
    private ArrayList<Comment>commentArraylist;

    public Restaurant(String name , String id , String ownerID , String type , String foods,ArrayList<Integer>integers, int number){
        this.name = name;
        this.ownerID= IDServer.toID(ownerID);
        this.restaurantID=IDServer.toID(id);
        this.restaurantType = RestaurantType.valueOf(type);
        this.foods=new ArrayList<>();
        this.ratings=integers;this.commentArraylist=new ArrayList<>();
        this.numberOfGraph=number;
    }
    public Restaurant(String name , User owner , ID id , RestaurantType restaurantType){
        this.restaurantID=id;
        this.name=name ;this.commentArraylist=new ArrayList<>();
        this.ownerID=owner.giveID();
        this.restaurantType= restaurantType;
        this.activeOrders=new ArrayList<>();
        this.foods=new ArrayList<>();ratings=new ArrayList<>(5);
        for(int i=0;i<4;i++)ratings.add(i,0);this.numberOfGraph=-1;
    }
    public String getName(){
        return name;
    }
    public Boolean isOwner(ID id){
        return id.equals(ownerID);
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
        this.restaurantType=restaurantType;
        for (int i=0; i < this.foods.size() ;i++)
            this.foods.remove(i);
        for (int i=0; i < this.activeOrders.size() ;i++)
            this.activeOrders.remove(i);
        this.selectedFood=null;
    }

    @Override
    public ArrayList<Food> giveAllFoods() {
        return foods;
    }
    public ArrayList<Food> giveFoodsForUser(){
        ArrayList<Food> ans = new ArrayList<>();
        for(Food food:foods){
            if(food.isActive())
                ans.add(food);
        }return ans;
    }

    @Override
    public Boolean doesFoodExist(String id) {
        for (Food food : foods)
            if (food.getID().equals(id))
                return true;
        return false;
    }

    @Override
    public void editFoodName(String id, String newName) {
        for(Food food : foods){
            if(food.getID().equals(id)){
                food.editFoodName(newName);break;}
        }
    }
    public void editFoodName(String name){
        this.selectedFood.editFoodName(name);
    }
    public String giveID(){
        return this.restaurantID.show();
    }

    @Override
    public void editFoodPrice(String id, Integer newPrise) {
        for(Food food : foods){
            if(food.getID().equals(id)){
                food.editPrice(newPrise);break;}
        }
    }public void editFoodPrice( Integer newPrise) {
        this.selectedFood.editPrice(newPrise);
    }


    @Override
    public Boolean doesFoodNameExists(String name) {
        for(Food food : foods){
            if(food.getName().equals(name))
                return true;
        }return false;
    }

    @Override
    public void addFood(String name, Integer price) {

    }

    @Override
    public Food addFood(String name, Integer price, FoodType foodType,
                        LocalTime time, Integer discountRate, ID foodID) {
        Food food = new Food(name,foodType,time,price,discountRate,foodID,this.restaurantID);
        this.foods.add(food);
        return this.foods.get(this.foods.indexOf(food));
    }

    @Override
    public Boolean canDeleteOrDeactivateFood(String id) {
        for(Food food : foods){
            if(food.getID().equals(id)){
                for(Order  order : activeOrders){
                    if(order.contains(food))
                        return false;
                }
            }
        }return true;
    }

    @Override
    public void deleteFood(String id) {
        int index = -1;
        for (int i=0; i < this.foods.size() ;i++){
            if (this.foods.get(i).getID().equals(id)) {
                index=i;break;}
        }foods.remove(index);
        for(int i=index;i<foods.size()-1;i++){
            foods.add(i,new Food(foods.get(i+1)));
        }

    }

    @Override
    public void deactivateFood(String id) {
        for (Food food : this.foods) {
            if (food.getID().equals(id)) {
                food.deactivate();
            }
        }
    }

    @Override
    public void activateFood(String id) {
        for (Food food : this.foods) {
            if (food.getID().equals(id)) {
                food.activate();
            }
        }
    }

    @Override
    public Boolean doesFoodHaveDiscount(String id) {
        for (Food food : this.foods)
            if (food.getID().equals(id))
                return food.doesHaveDiscount();
        return false;//this never happens
    }

    public Boolean doesFoodHaveDiscount(){
        return this.selectedFood.doesHaveDiscount();
    }
    @Override
    public void discountFood(String id, Integer discount) {

    }


    public Boolean discountFood(Integer discount , LocalDateTime time) {
        return this.selectedFood.setDiscount(discount,time);
    }

    @Override
    public ArrayList<String> displayRating() {
        ArrayList<String>ans = new ArrayList<>();
        for(int i=0;i<5;i++){
            ans.add((i+1)+" :  "+ratings.get(1+i));
        }return ans;
    }

    @Override
    public void selectFood(String id) {
        for (Food food : this.foods)
            if (food.getID().equals(id))
                this.selectedFood = food;
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return this.commentArraylist;
    }

    @Override
    public void addComment(StringBuilder comment, ID id, Person sender) {

    }

    @Override
    public void addComment(Comment comment) {
        this.commentArraylist.add(comment);
    }
    @Override
    public Boolean doesCommentExist(String id){
        return this.selectedFood.doesCommentExist(id);
    }

    @Override
    public Boolean canEditComment(String id) {
        return this.selectedFood.canEditComment(id);
    }

    @Override
    public Boolean isSender(Person person) {
        return this.selectedFood.isSender(person);
    }

    @Override
    public void appendRating(Rating rating) {
        this.ratings.add(rating.giveRating()-1,ratings.get(rating.giveRating()-1)+1);
    }

    @Override
    public void editComment(String id, StringBuilder newComment) {
    }
    @Override
    public Food giveSelectedFood(){
        return this.selectedFood;
    }
    public String giveOwnerID(){
        return this.ownerID.show();
    }public String giveType(){
        return this.restaurantType.name();
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }
    public ID giveIDAsID(){
        return this.restaurantID;
    }

    public void setLocation(int parseInt) {
        this.numberOfGraph=parseInt;
    }

    public Integer givePose() {
        return this.numberOfGraph;
    }

    public ArrayList<Integer> giveRating() {
        return this.ratings;
    }

    public void appendRating(int parseInt) {
        ratings.add(parseInt-1,ratings.get(parseInt-1)+1);
    }
}
