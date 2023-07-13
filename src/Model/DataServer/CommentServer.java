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
                        resultSet.getString("senderID"),resultSet.getString("foodID"),
                        resultSet.getString("theTime"),resultSet.getBoolean("edited")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into comment (id,com,senderID,foodID,theTime,edited)values (?,?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
             statement.executeUpdate("truncate table comment;");
            for(Comment comment: comments){
                statement.setString(1,comment.giveID());
                statement.setString(2,comment.giveJustComment());
                statement.setString(3,comment.getSenderIDAsString());
                statement.setString(4,comment.giveFID());
                statement.setString(5,comment.giveTime());
                statement.setBoolean(6,comment.giveEdited());
                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comment> give() {
        return this.comments;
    }

    public void add(Comment comment1) {
        this.comments.add(comment1);
    }
}
