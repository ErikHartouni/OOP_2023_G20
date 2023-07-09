package Model.DataServer;

import Model.RestaurantClasses.Restaurant;
import Model.Users.UserClasses.Massage;

import java.sql.*;
import java.util.ArrayList;

public class MassageServer {
    private ArrayList < Massage > massages;
    public MassageServer(){
        massages=new ArrayList<>();
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
            resultSet = statement.executeQuery("select * from Messages;");
            while(resultSet.next()){
                massages.add(new Massage(resultSet.getString("mess"),
                        resultSet.getString("sender"), resultSet.getString("receiver"),
                        resultSet.getString("id"),resultSet.getString("theTime")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(){
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Messages (mess,sender , receiver , id,theTime) values (?,?,?,?,?);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Messages;");
            for(Massage massage : massages){
                statement.setString(1,massage.getJustMess());
                statement.setString(2,massage.getSenderID());
                statement.setString(3,massage.getRID());
                statement.setString(4,massage.getID());
                statement.setString(5,massage.getTime());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Massage> give() {
        return this.massages;
    }
}
