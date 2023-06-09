package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.Users.Person;

import java.time.LocalDateTime;

public class Comment {
    private StringBuilder comment;
    private final ID commentID;
    private final LocalDateTime localDateTime;
    private final Person sender;
    public Comment(StringBuilder comment ,ID id , Person sender){
        this.comment=comment;
        localDateTime=LocalDateTime.now();
        this.commentID=id;
        this.sender=sender;
    }
    public StringBuilder show(){
        return new StringBuilder("From "+sender.sayMyName()+"\twith id:"+commentID.show()+
                " at "+localDateTime.toString()+"\n\t"+comment);
    }

}
