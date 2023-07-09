package Model.Users.UserClasses;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.Users.Person;

import java.time.LocalTime;

public class Massage {
    private ID id;
    private StringBuilder massage;
    private LocalTime time;
    private ID sender, receiver;
    public Massage(String massage , String sender , String receiver , String id , String time){

    }
    public Massage(StringBuilder massage,ID sender , ID receiver , ID id){
        this.id=id;
        this.massage=massage;
        this.time=LocalTime.now();
        this.sender=sender;
        this.receiver=receiver;
    }

    public StringBuilder getMassage(){
        return new StringBuilder("\tfrom "+sender+":\n\t"+massage);
    }
    public String getJustMess(){
        return new String(this.massage);
    }
    public String getSenderID(){
        return this.sender.show();
    }public String getRID(){
        return this.receiver.show();
    }public String getID(){
        return this.id.show();
    }
    public String getTime(){
        return time.toString();
    }

}
