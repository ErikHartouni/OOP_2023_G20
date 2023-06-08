package Model.Users;

import Model.DataServer.IDHandler.ID;
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
    private HashMap<String , Integer> myRestaurantMap;
    private ID userID;
    private int indexOfMyChosenRestaurant , indexOfMySelectedFood;
    private Restaurant myRestaurant;

    public User(String username , String password , ID id){
        this.username = username ;
        this.password = password;
        this.userID=id;
        myRestaurantMap = new HashMap<>();
        myRestaurants = new ArrayList<>();
    }
    public Boolean doesHaveThisRestaurant(String name){
        return myRestaurantMap.containsKey(name);
    }


    @Override
    public ArrayList<Restaurant> giveMyRestaurants() {
        return null;
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

    }
    @Override
    public void chooseRestaurant(String id) {

    }


    @Override
    public RestaurantType showRestaurantType() {
        return null;
    }

    @Override
    public Boolean canChangeMyRestaurantType() {
        return null;
    }

    @Override
    public void editMyRestaurantFoodType(RestaurantType restaurantType) {

    }

    @Override
    public ArrayList<Food> giveAllFoodsOfMyRestaurant() {
        return null;
    }

    @Override
    public Boolean doesFoodExistInMyRestaurant(String id) {
        return null;
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
        return null;
    }

    @Override
    public void addFoodToMyRestaurant(String name, FoodType foodType , LocalTime time ,
                                      Integer price , Integer discountRate , ID foodID) {

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
        return null;
    }

    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }
}
