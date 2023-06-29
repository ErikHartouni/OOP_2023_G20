package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Others.Interfaces.RestaurantOwnerActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person implements RestaurantOwnerActions {
    private ArrayList < Restaurant > myRestaurants;
    private ID userID;
    private int indexOfMyChosenRestaurant , indexOfMySelectedFood;
    private Restaurant myRestaurant;

    public User(String username , String password , ID id){
        this.username = username ;
        this.password = password;
        this.userID=id;
        super.isAdmin=false;
        super.isPoster=false;
        myRestaurants = new ArrayList<>();
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
        return null;
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

    }

    @Override
    public void editMyRestaurantFoodName(String newName) {

    }

    @Override
    public void editMyRestaurantFoodPrice(Integer newPrise) {

    }

    @Override
    public Boolean doesFoodNameExistsInMyRestaurant(String name) {
        return this.myRestaurant.doesFoodNameExists(name);
    }

    @Override
    public void addFoodToMyRestaurant(String name, FoodType foodType , LocalTime time ,
                                      Integer price , Integer discountRate , ID foodID) {
        this.myRestaurant.addFood(name ,price,foodType,time,discountRate,foodID);
    }

    @Override
    public Boolean canDeleteOrDeactivateFood(String id) {
        return null;
    }

    @Override
    public void deleteFood(String id) {

    }

    @Override
    public void deactivateFood(String id) {

    }

    @Override
    public void activateFood(String id) {

    }

    @Override
    public Boolean doesFoodHaveDiscount() {
        return null;
    }

    @Override
    public Boolean discountFood(Integer discount, LocalDateTime time) {
        return null;
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
}
