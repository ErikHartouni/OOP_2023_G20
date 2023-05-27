package Model.DataServer;

import Model.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersSaver {
    private ArrayList<User> userArrayList;
    private HashMap<String , Integer> userMap;
    private int numberOfUsers;
    public UsersSaver(){
        userMap = new HashMap<>();
        userArrayList = new ArrayList<>();
        numberOfUsers=0;
    }

    public int userIndex(String name){
        if(userMap.containsKey(name))
            return userMap.get(name);
        return -1;
    }
    public User giveUser(int index){
        return userArrayList.get(index);
    }
    public void createUser(String username , String password){
        userArrayList.add(new User(username,password));
        userMap.put(username,numberOfUsers++);
    }
    public Boolean checkPassword(String username , String password){
        return userArrayList.get(userMap.get(username)).doesPasswordMatch(password);
    }

}
