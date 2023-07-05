package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.Users.Person;
import Others.Interfaces.CommentActions;

import java.time.LocalDateTime;

public class Comment implements CommentActions {
    private StringBuilder comment;
    private final ID commentID;
    private final LocalDateTime localDateTime;
    private final Person sender;
    private Boolean edited;
    public Comment(StringBuilder comment ,ID id , Person sender){
        this.comment=comment;
        localDateTime=LocalDateTime.now();
        this.commentID=id;
        this.sender=sender;
        this.edited=false;
    }
    public StringBuilder show(){
        StringBuilder ans= new StringBuilder("From "+sender.sayMyName()+"\twith id:"+commentID.show());
        ans.append(" at ").append(localDateTime.toString()).append("\n\t");
        if(edited)
            ans.append(" -edited- ");
        ans.append(comment);
        return ans;
    }

    @Override
    public String getID() {
        return this.commentID.show();
    }

    public StringBuilder getComment() {
        return this.comment;
    }

    @Override
    public Boolean canEditComment() {
        return !edited;
    }

    @Override
    public Boolean isSender(String personID) {
        return personID.equals(this.sender.getID());
    }

    @Override
    public void editComment(StringBuilder newComment) {
        if (this.canEditComment()) {
            this.edited=true;
            this.comment=newComment;
        }
    }
}
