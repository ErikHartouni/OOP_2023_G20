package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.UserClasses.CreditCard;
import Model.Users.UserClasses.Massage;
import Others.Interfaces.RestaurantOwnerActions;

import java.util.ArrayList;

public class User extends Person implements RestaurantOwnerActions {
    private ArrayList < Restaurant > myRestaurants;
    private ID serID;
    private int indexOfMyChosenRestaurant , indexOfMySelectedFood;

    public User(String username , String password){
        this.username = username ;
        this.password = password;
    }


    @Override
    public ArrayList<Restaurant> giveMyRestaurants() {
        return null;
    }

    @Override
    public void createRestaurant(String name) {

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
    public void editMyRestaurantFoodPrice(String id, Integer newPrise) {

    }

    @Override
    public Boolean doesFoodNameExistsInMyRestaurant(String name) {
        return null;
    }

    @Override
    public void addFoodToMyRestaurant(String name, Integer price) {

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
    public Boolean doesFoodHaveDiscount(String id) {
        return null;
    }

    @Override
    public void discountFood(String id, Integer discount) {

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
}
