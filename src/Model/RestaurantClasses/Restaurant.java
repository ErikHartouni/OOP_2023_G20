package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;
import Model.Users.User;
import Others.Interfaces.RestaurantActions;

import java.time.LocalTime;
import java.util.ArrayList;

public class Restaurant implements RestaurantActions {
    private String name;
    private User owner;
    private ID restaurantID;
    private RestaurantType restaurantType;
    private ArrayList < Order > activeOrders;
    private ArrayList <Food> foods;
    private Food selectedFood;


    public Restaurant(String name , User owner , ID id , RestaurantType restaurantType){
        this.restaurantID=id;
        this.name=name ;
        this.owner=owner;
        this.restaurantType= restaurantType;
        this.activeOrders=new ArrayList<>();
        this.foods=new ArrayList<>();
    }
    public String getName(){
        return name;
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
    public String giveID(){
        return this.restaurantID.show();
    }

    @Override
    public void editFoodPrice(String id, Integer newPrise) {
        for(Food food : foods){
            if(food.getID().equals(id)){
                food.editPrice(newPrise);break;}
        }
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
    public void addFood(String name, Integer price, FoodType foodType,
                        LocalTime time, Integer discountRate, ID foodID) {
        this.foods.add(new Food(name,foodType,time,price,discountRate,foodID));
    }

    @Override
    public Boolean canDeleteOrDeactivateFood(String id) {
        for(Food food : foods){
            if(food.getID().equals(id))
                return food.canBeDeletedOrDeactivated();
        }return false;//never happens
    }

    @Override
    public void deleteFood(String id) {
        int index = -1;
        for (int i=0; i < this.foods.size() ;i++){
            if (this.foods.get(i).getID().equals(id)) {
                index=i;break;}
        }for(int i=index;i<foods.size()-1;i++){
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

    @Override
    public void discountFood(String id, Integer discount) {
        for(Food food : foods){
            if(food.getID().equals(id))
                food.setDiscount(discount);
        }
    }

    @Override
    public ArrayList<String> displayRating() {
        return this.selectedFood.displayRating();
    }

    @Override
    public void selectFood(String id) {
        for (Food food : this.foods)
            if (food.getID().equals(id))
                this.selectedFood = food;
    }

    @Override
    public ArrayList<Comment> giveComments() {
        return selectedFood.giveComments();
    }

    @Override
    public void addComment(StringBuilder comment, ID id, Person sender) {
        this.selectedFood.addComment(new Comment(comment,id,sender));
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

    }

    @Override
    public void editComment(String id, StringBuilder newComment) {
    }
    @Override
    public Food giveSelectedFood(){
        return this.selectedFood;
    }

}
