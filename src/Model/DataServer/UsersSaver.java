package Model.DataServer;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.TypeOfID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Restaurant;
import Model.Users.User;
import Model.Users.UserClasses.Massage;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersSaver {
    private   ArrayList<User> userArrayList;
    private   HashMap<String , Integer> userMap;
    private   int numberOfUsers;
    public  UsersSaver(){
        userMap = new HashMap<>();
        userArrayList = new ArrayList<>();
        numberOfUsers=0;
    }

    public   int userIndex(String name){
        if(userMap.containsKey(name))
            return userMap.get(name);
        return -1;
    }
    public   User giveUser(int index){
        return userArrayList.get(index);
    }
    public   void createUser(String username , String password , ID id){
        userArrayList.add(new User(username,password,id));
        userMap.put(username,numberOfUsers++);
    }
    public   Boolean checkPassword(String username , String password){
        return userArrayList.get(userMap.get(username)).doesPasswordMatch(password);
    }

    public   void getItems(){
        Statement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("select * from Users;");
            while(resultSet.next()){
                userArrayList.add(new User(resultSet.getString("userName"),resultSet.getString("thePassWord"),
                        resultSet.getString("id"),resultSet.getInt("creditCard"),resultSet.getString("massages"),
                        resultSet.getString("cart"),resultSet.getString("myRestaurants")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Users(userName,thePassWord,id,creditCard,massages,cart,myRestaurants)values (?,?,?,?,?,?,?);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Users;");
            for(User user:userArrayList){
                statement.setString(1,user.giveName());
                statement.setString(2,user.givePass());
                statement.setString(3,user.giveID().show());
                statement.setInt(4,user.giveCart());
                statement.setString(5,"");
                statement.setString(6,user.giveByuCart());
                statement.setString(7,"");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void giveMessages(ArrayList<Massage>massages){
        for(User user :userArrayList){
            for(Massage massage:massages){
                if(massage.getRID().equals(user.giveID().show())||massage.getSenderID().equals(user.giveID().show()))
                    user.getMessageFromServer(massage);
            }
        }
    }
    public void giveMyRestaurants(ArrayList<Restaurant> restaurants){
        for(User user:userArrayList){
            for(Restaurant restaurant :restaurants){
                if(restaurant.isOwner(user.giveID()))
                    user.createRestaurant(restaurant);
            }
        }
    }
    public void giveComments(ArrayList<Comment>comments){
        for(User user : userArrayList){
            for(Comment comment : comments){
                if(comment.isSender(user.giveID())){
                    user.addComment(comment);
                }
            }
        }
    }

}
