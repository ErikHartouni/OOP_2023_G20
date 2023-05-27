package Model.DataServer;

import Model.Users.Admin;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminSaver {
    private ArrayList<Admin> admins;
    private HashMap<String , Integer> adminMap;
    private int numberOfAdmins;
    public AdminSaver(){
        admins=new ArrayList<>();
        adminMap= new HashMap<>();
    }

    public int adminIndex(String name){
        if(adminMap.containsKey(name))
            return adminMap.get(name);
        return -1;
    }
    public void createAdmin(String name , String password){
        admins.add(new Admin(name , password));
        adminMap.put(name , numberOfAdmins++);
    }
    public Boolean checkPassword(String username , String password){
        return admins.get(adminMap.get(username)).doesPasswordMatch(password);
    }
    public Admin giveAdmin(int index){
        return admins.get(index);
    }
}
