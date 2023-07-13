package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.DataServer.IDHandler.IDServer;
import Model.Users.Person;
import Others.Interfaces.CommentActions;

import java.time.LocalDateTime;

public class Comment implements CommentActions {
    private StringBuilder comment;
    private  ID commentID;
    private  LocalDateTime localDateTime;
    private  ID sender ,restaurantID;
    private Boolean edited;
    public Comment(StringBuilder comment ,ID id , ID senderID , ID restaurantID){
        this.comment=comment;
        localDateTime=LocalDateTime.now();
        this.commentID=id;
        this.sender=senderID;
        this.restaurantID=restaurantID;
        this.edited=false;
    }

    public Comment(String id, String com, String senderID, String foodID,String time,Boolean edited) {
        this.comment= new StringBuilder(com);
        this.restaurantID= IDServer.toID(foodID);
        this.commentID=IDServer.toID(id);
        this.sender=IDServer.toID(senderID);
        this.localDateTime=LocalDateTime.parse(time);
        this.edited=edited;
    }
    public String getSenderIDAsString(){
        return sender.show();
    }

    public StringBuilder show(){
        StringBuilder ans= new StringBuilder("From "+sender.show()+"\twith id:"+commentID.show());
        ans.append(" at ").append(localDateTime.toString()).append(":\n\t");
        if(edited)
            ans.append(" -edited- ");
        ans.append(comment);
        return ans;
    }

    @Override
    public Boolean canEditComment() {
        return !edited;
    }

    @Override
    public Boolean isSender(Person person) {
        return person.giveID().equals(sender);
    }
    public Boolean isSender(ID id){
        return id.equals(sender);
    }


    @Override
    public void editComment(StringBuilder newComment) {
        edited=true;
        this.comment=newComment;
    }
    public String giveJustComment(){
        return String.valueOf(this.comment);
    }
    public String giveID(){
        return this.commentID.show();
    }
    public String giveSenderID(){
        return this.sender.show();
    }
    public String giveFID(){
        return this.restaurantID.show();
    }

    public String giveTime() {
        return this.localDateTime.toString();
    }

    public boolean giveEdited() {
        return this.edited;
    }
}
