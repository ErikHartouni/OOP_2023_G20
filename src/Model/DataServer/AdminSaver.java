package Model.DataServer;

import Model.DataServer.IDHandler.ID;
import Model.Users.Admin;
import Model.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminSaver {
    private final ArrayList<Admin> admins;
    private final HashMap<String , Integer> adminMap;
    private int numberOfAdmins;
    private User user;
    public AdminSaver(){
        admins=new ArrayList<>();
        adminMap= new HashMap<>();
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
}
