package Model.DataServer;

import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Model.Users.User;
import Others.Interfaces.RestaurantDataServerActions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RestaurantSaver implements RestaurantDataServerActions {
    private ArrayList<Restaurant> restaurants;
    public RestaurantSaver(){
        restaurants= new ArrayList<>();
    }
    public void load(){
        Statement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("select * from Restaurants;");
            while(resultSet.next()){
                restaurants.add(new Restaurant(resultSet.getString("rName"),
                        resultSet.getString("id"), resultSet.getString("ownerID"),
                        resultSet.getString("rType"),resultSet.getString("foods")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
    }
    @Override
    public ArrayList<Restaurant> searchRestaurant(String name){
        ArrayList<Restaurant> ans = new ArrayList<>() , restaurantsWithSimilarity = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            if(restaurant.getName().equals(name))
                ans.add(restaurant);
            else if (restaurant.getName().contains(name))
                restaurantsWithSimilarity.add(restaurant);
        }
        //sort answers
        ans.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        restaurantsWithSimilarity.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //merge answers
        ans.addAll(restaurantsWithSimilarity);
        return ans;
    }

    @Override
    public Boolean doesRestaurantExist(String id) {
        for(Restaurant restaurant :restaurants){
            if(restaurant.giveID().equals(id))
                return true;
        }return false;
    }

    @Override
    public Restaurant giveRestaurant(String id) {
        return null;
    }


    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Restaurants(rName,id,ownerID,rType,foods)values (?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Restaurants;");
            for(Restaurant restaurant : restaurants){
                statement.setString(1,restaurant.getName());
                statement.setString(2,restaurant.giveID());
                statement.setString(3,restaurant.giveOwnerID());
                statement.setString(4,restaurant.giveType());
                statement.setString(5,"");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void giveFoods(ArrayList<Food>foods){
        for(Restaurant restaurant : restaurants){
            for(Food food:foods){
                if(food.isInThisRestaurant(restaurant.giveID()))
                    restaurant.addFood(food);
            }
        }
    }

    public ArrayList<Restaurant> give() {
        return this.restaurants;
    }
}
