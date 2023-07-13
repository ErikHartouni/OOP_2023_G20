package Model.DataServer;

import Model.RestaurantClasses.Comment;
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
                ArrayList <Integer> integers = new ArrayList<>();
                integers.add(resultSet.getInt("veryGood"));
                integers.add(resultSet.getInt("good"));
                integers.add(resultSet.getInt("medum"));
                integers.add(resultSet.getInt("bad"));
                integers.add(resultSet.getInt("veryBad"));
                restaurants.add(new Restaurant(resultSet.getString("rName"),
                        resultSet.getString("id"), resultSet.getString("ownerID"),
                        resultSet.getString("rType"),resultSet.getString("foods"),integers,
                        resultSet.getInt("loc")));
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
        for(Restaurant restaurant : restaurants){
            if(restaurant.giveID().equals(id))
                return this.restaurants.get(restaurants.indexOf(restaurant));
        }return null;
    }


    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Restaurants(rName,id,ownerID,rType,foods," +
                    "veryGood,good,medum,bad,veryBad,loc)values (?,?,?,?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Restaurants;");
            for(Restaurant restaurant : restaurants){
                ArrayList <Integer> ints = restaurant.giveRating();
                statement.setString(1,restaurant.getName());
                statement.setString(2,restaurant.giveID());
                statement.setString(3,restaurant.giveOwnerID());
                statement.setString(4,restaurant.giveType());
                statement.setString(5,"");
                statement.setInt(6,ints.get(0));
                statement.setInt(7,ints.get(1));
                statement.setInt(8,ints.get(2));
                statement.setInt(9,ints.get(3));
                statement.setInt(10,ints.get(4));
                statement.setInt(11,restaurant.givePose());
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

    public ArrayList<Integer> giveRestaurants(ArrayList<String> ids) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(String str : ids){
            for(Restaurant restaurant :restaurants){
                if(restaurant.giveID().equals(str))
                    ans.add(restaurant.givePose());
            }
        }return ans;
    }

    public int giveNum() {
        return this.restaurants.size();
    }

    public void geiveRatings(ArrayList<String> give) {
        for(String str : give){
            String [] strings = str.split("\\s+");
            for(Restaurant restaurant : restaurants){
                if(strings[0].equals(restaurant.giveID()))
                    restaurant.appendRating(Integer.parseInt(strings[2]));
            }
        }
    }

    public void giveComments(ArrayList<Comment> give) {
        for(Comment comment : give){
            for(Restaurant restaurant : restaurants){
                if(comment.giveFID().equals(restaurant.giveID()))
                    restaurant.addComment(comment);
            }
        }
    }
}
