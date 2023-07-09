package Model.DataServer;

import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.Users.Admin;

import java.sql.*;
import java.util.ArrayList;

public class FoodServer {
    private ArrayList<Food> foods;
    public FoodServer(){
        foods=new ArrayList<>();
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
            resultSet = statement.executeQuery("select * from Food;");
            while(resultSet.next()){
                foods.add(new Food(resultSet.getString("fName"),resultSet.getString("fType"),
                        resultSet.getString("id"),resultSet.getInt("discount"),resultSet.getInt("price"),
                        resultSet.getString("TTM")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCommentsAndRatings(ArrayList<String> ratings , ArrayList<Comment> comments){
        for(Food food:foods){
            for(String str : ratings){
                if(str.split("\\s+")[0].equals(food.getID()))
                    food.rate(Integer.parseInt(str.split("\\s+")[2]));
            }
            for(Comment c :comments){
                if(food.getID().equals(c.getSenderIDAsString()))
                    food.addComment(c);
            }
        }
    }
    public void addFood(){

    }
    public void save(){
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Raiting (fName,fType,id,discount,price,TTM)values (?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Food;");
            for(Food food:foods){
                statement.setString(1,food.getName());
                statement.setString(2,food.getType());
                statement.setString(3,food.getID());
                statement.setInt(4,food.getDiscount());
                statement.setInt(5,food.getPrice());
                statement.setString(6,food.getTTM());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Food>give(){
        return this.foods;
    }
}
