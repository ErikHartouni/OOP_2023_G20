package Model.DataServer;

import Model.RestaurantClasses.Comment;
import Model.Users.Admin;

import java.sql.*;
import java.util.ArrayList;

public class CommentServer {
    private ArrayList<Comment> comments;
    public  CommentServer(){
        comments=new ArrayList<>();
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
            resultSet = statement.executeQuery("select * from Comment;");
            while(resultSet.next()){
                comments.add(new Comment(resultSet.getString("id"),resultSet.getString("com"),
                        resultSet.getString("senderID"),resultSet.getString("foodID")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Raiting (id,com,senderID,foodID)values (?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
             statement.executeUpdate("truncate table Comment;");
            for(Comment comment: comments){
                statement.setString(1,comment.giveID());
                statement.setString(2,comment.giveJustComment());
                statement.setString(3,comment.getSenderIDAsString());
                statement.setString(4,comment.giveFID());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> give() {
        return this.comments;
    }
}
