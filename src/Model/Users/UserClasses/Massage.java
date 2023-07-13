package Model.Users.UserClasses;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.RestaurantClasses.Restaurant;
import Model.Users.Person;

import java.time.LocalTime;

public class Massage {
    private ID id;
    private StringBuilder massage;
    private LocalTime time;
    private ID sender, receiver;
    public Massage(String massage , String sender , String receiver , String id , String time){
        this.massage= new StringBuilder(massage);
        this.sender=IDServer.toID(sender);
        this.receiver=IDServer.toID(receiver);
        this.id=IDServer.toID(id);
        this.time=LocalTime.parse(time);
    }
    public Massage(StringBuilder massage,ID sender , ID receiver , ID id){
        this.id=id;
        this.massage=massage;
        this.time=LocalTime.now();
        this.sender=sender;
        this.receiver=receiver;
    }

    public StringBuilder getMassage(){
        return new StringBuilder("\tfrom "+sender+" "+time.toString()+":\n\t"+massage);
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
    public Massage (Restaurant restaurant,ID admin){
        this .massage = new StringBuilder ("Hi,\n Iwant to create new Restaurant.");
        this.time=LocalTime.now();
        this.receiver=admin;
        this.sender=IDServer.toID(restaurant.giveOwnerID());
    }

}
