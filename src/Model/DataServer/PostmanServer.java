package Model.DataServer;

import Model.DataServer.IDHandler.ID;
import Model.Users.Person;
import Model.Users.PostMan;

import java.util.ArrayList;

public class PostmanServer {
    private ArrayList<PostMan>postMen;
    public PostmanServer(){
        postMen= new ArrayList<>();
    }

    public void load() {
    }

    public void createPostman(String s, String s1, ID id) {
        postMen.add(new PostMan(s,s1,id));
    }

    public int postmanIndex(String s) {
        for(int i=0;i<postMen.size();i++){
            if(postMen.get(i).giveID().show().equals(s))
                return i;
        }return -1;
    }

    public Person givePoster(int index) {
        return this.postMen.get(index);
    }

    public int giveNum() {
        return this.postMen.size();
    }
}
