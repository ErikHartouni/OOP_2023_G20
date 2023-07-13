package Model.DataServer;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Rating;
import Model.Users.Admin;

import java.sql.*;
import java.util.ArrayList;

public class RatingServer {
    private ArrayList<String > ratings;
    public RatingServer(){
        ratings=new ArrayList<>();
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
            resultSet = statement.executeQuery("select * from Raiting;");
            while(resultSet.next()){
                ratings.add(resultSet.getString("fID")+" "+resultSet.getString("uID")+" "+resultSet.getInt("num"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
             statement = connection.prepareStatement("insert into Raiting (fID,uID,num)values (?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Raiting;");
            String [] s ;
            for(String str : ratings){
                s=str.split("\\s+");
//                resultSet = statement.executeQuery("insert into Raiting (fID,uID,num)values ("
//                        +s[0]+" ," +s[1]+", "+Integer.parseInt(s[2])+");");
                statement.setString(1, s[0]);
                statement.setString(2,s[1]);
                statement.setInt(3,1);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> give() {
        return this.ratings;
    }
    public void addRating(String restaurantID , ID personID , int rating){
        this.ratings.add(restaurantID+" "+personID.show()+" "+rating);
    }
}
