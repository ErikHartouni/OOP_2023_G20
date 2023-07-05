package Others.Interfaces;

import Model.Users.Person;

public interface CommentActions {
    String getID();
    StringBuilder getComment();
    Boolean canEditComment();
    Boolean isSender(String personID);
    void editComment(StringBuilder newComment);
}
