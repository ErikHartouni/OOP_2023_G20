package Model.DataServer;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Restaurant;
import Model.Users.Admin;
import Model.Users.User;
import Model.Users.UserClasses.Massage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminSaver {
    private ArrayList<Admin> admins;
    private HashMap<String , Integer> adminMap;
    private int numberOfAdmins;
    private User user;
    public AdminSaver(){
        admins=new ArrayList<>();
        adminMap= new HashMap<>();
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
            resultSet = statement.executeQuery("select * from Admin;");
            while(resultSet.next()){
                admins.add(new Admin(resultSet.getString("userName"),resultSet.getString("thePassWord"),
                        resultSet.getString("id"),resultSet.getInt("creditCard"),resultSet.getString("massages"),
                        resultSet.getString("cart")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int adminIndex(String name){
        if(adminMap.containsKey(name))
            return adminMap.get(name);
        return -1;
    }
    public void createAdmin(String name , String password , ID id ){
        admins.add(new Admin(name , password , id));
        adminMap.put(name , numberOfAdmins++);
    }
    public Boolean checkPassword(String username , String password){
        return admins.get(adminMap.get(username)).doesPasswordMatch(password);
    }
    public Admin giveAdmin(int index){
        return admins.get(index);
    }

    public void save() {
        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/oop_snap_food","root","erik7567") ;
            statement = connection.prepareStatement("insert into Admin(userName , thePassWord,id,creditCard,massages,cart)values (?,?,?,?,?,?);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            statement.executeUpdate("truncate table Admin;");
            for(Admin admin:admins){
                statement.setString(1,admin.giveName());
                statement.setString(2,admin.givePass());
                statement.setString(3,admin.giveID().show());
                statement.setInt(4,admin.giveCart());
                statement.setString(5,admin.giveShoppingCart());
                statement.setString(6,admin.giveByuCart());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void giveMessages(ArrayList<Massage>massages){
        for(Admin admin : admins){
            for(Massage m :massages){
                if(admin.giveID().show().equals(m.getSenderID())||
                admin.giveID().show().equals(m.getRID()))
                    admin.getMessageFromServer(m);
            }
        }
    }
    public void sendToAll(Restaurant restaurant){
        for(Admin admin:admins){

        }
    }

    public int giveNum() {
        return this.admins.size();
    }
}
