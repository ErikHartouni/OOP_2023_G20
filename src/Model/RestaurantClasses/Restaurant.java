package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.User;
import Others.Interfaces.RestaurantActions;

import java.util.ArrayList;

public class Restaurant implements RestaurantActions {
    private String name;
    private User owner;
    private ID restaurantID;
    private RestaurantType restaurantType;
    private ArrayList < Order > activeOrders;


    public Restaurant(String name , User owner , ID id , RestaurantType restaurantType){
        this.restaurantID=id;
        this.name=name ;
        this.owner=owner;
        this.restaurantType= restaurantType;
        this.activeOrders=new ArrayList<>();
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

    }

    @Override
    public ArrayList<Food> giveAllFoods() {
        return null;
    }

    @Override
    public Boolean doesFoodExist(String id) {
        return null;
    }

    @Override
    public void editFoodName(String id, String newName) {

    }

    @Override
    public void editFoodPrice(String id, Integer newPrise) {

    }

    @Override
    public Boolean doesFoodNameExists(String name) {
        return null;
    }

    @Override
    public void addFood(String name, Integer price) {

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


}
