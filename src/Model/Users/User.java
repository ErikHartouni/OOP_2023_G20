package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.UserClasses.CreditCard;
import Others.Interfaces.RestaurantOwnerActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person implements RestaurantOwnerActions {
    private ArrayList < Restaurant > myRestaurants;
    private int indexOfMyChosenRestaurant , indexOfMySelectedFood;
    private Restaurant myRestaurant;
    /**
     * for sql
     **/
    public User(String userName , String passWord , String id , int credit , String message , String cart , String myRestaurant, int loc){
        this.username=userName;
        this.password=passWord;
        this.cartString=cart;
        super.id= IDServer.toID(id);
        super.creditCard=new CreditCard(credit);
        super.cart=new ArrayList<>();orders = new ArrayList<>();
        super.numberOfGraph=loc;
        isAdmin=false; isPoster=false;
        myRestaurants=new ArrayList<>();
    }

    public User(String username , String password , ID id){
        this.username = username ;
        this.password = password;
        this.id=id;
        super.isAdmin=false;
        super.isPoster=false;
        myRestaurants = new ArrayList<>();
        super.cart=new ArrayList<>();
        super.creditCard=new CreditCard(0);
        super.orders = new ArrayList<>();
    }
    public Boolean doesHaveThisRestaurant(String id){
        for(Restaurant restaurant:myRestaurants){
            if(restaurant.giveID().equals(id))
                return true;
        }return false;
    }


    @Override
    public ArrayList<Restaurant> giveMyRestaurants() {
        return this.myRestaurants;
    }

    @Override
    public void createRestaurant(String name, User owner, ID id, RestaurantType restaurantType) {
        /* create then add to restaurants
        then user the createRestaurant(Restaurant) method ...*/
        Restaurant restaurant = new Restaurant(name , owner , id , restaurantType);
        this.createRestaurant(restaurant);
    }//ended

    @Override
    public void createRestaurant(Restaurant restaurant) {//add to restaurants and restaurant map ...
        this.myRestaurants.add(restaurant);
    }
    @Override
    public void chooseRestaurant(String id) {
        for(Restaurant restaurant : myRestaurants){
            if(restaurant.giveID().equals(id)){
                this.myRestaurant=restaurant; break;}
        }
    }


    @Override
    public RestaurantType showRestaurantType() {
        return this.myRestaurant.showRestaurantType();
    }

    @Override
    public Boolean canChangeMyRestaurantType() {
        return this.myRestaurant.canChangeRestaurantType();
    }

    @Override
    public void cahngeMyRestaurantType(RestaurantType restaurantType) {

    }

    @Override
    public ArrayList<Food> giveAllFoodsOfMyRestaurant() {
        return this.myRestaurant.giveAllFoods();
    }

    @Override
    public Boolean doesFoodExistInMyRestaurant(String id) {
        return this.myRestaurant.doesFoodExist(id);
    }

    @Override
    public void selectFood(String id) {
        this.myRestaurant.selectFood(id);
    }

    @Override
    public void editMyRestaurantFoodName(String newName) {
        this.myRestaurant.editFoodName(newName);
    }

    @Override
    public void editMyRestaurantFoodPrice(Integer newPrise) {
        this.myRestaurant.editFoodPrice(newPrise);
    }

    @Override
    public Boolean doesFoodNameExistsInMyRestaurant(String name) {
        return this.myRestaurant.doesFoodNameExists(name);
    }

    @Override
    public void addFoodToMyRestaurant(String name, FoodType foodType , LocalTime time ,
                                      Integer price , Integer discountRate , ID foodID) {
        this.myRestaurant.addFood(name ,price,foodType,time,discountRate,foodID);
    }public Food addNewFoodToMyRestaurant(String name, FoodType foodType , LocalTime time ,
                                       Integer price , Integer discountRate , ID foodID) {
        return  this.myRestaurant.addFood(name ,price,foodType,time,discountRate,foodID);
    }

    @Override
    public Boolean canDeleteOrDeactivateFood(String id) {
        return this.myRestaurant.canDeleteOrDeactivateFood(id);
    }

    @Override
    public void deleteFood(String id) {
        this.myRestaurant.deleteFood(id);
    }

    @Override
    public void deactivateFood(String id) {
        this.myRestaurant.deactivateFood(id);
    }

    @Override
    public void activateFood(String id) {
        this.myRestaurant.activateFood(id);
    }

    @Override
    public Boolean doesFoodHaveDiscount() {
        return this.myRestaurant.doesFoodHaveDiscount();
    }

    @Override
    public Boolean discountFood(Integer discount, LocalDateTime time) {
        return this.myRestaurant.discountFood(discount,time);
    }



    @Override
    public ArrayList<String> displayRating() {
        return null;
    }

    @Override
    public void back() {

    }

    @Override
    public Boolean doesPasswordMatch(String password) {
        return password.equals(this.password);
    }

    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }

    public String giveMrRestaurantID() {
        return "";
    }

    public void setRestaurantLocation(int parseInt) {
        this.myRestaurant.setLocation(parseInt);
    }
}
