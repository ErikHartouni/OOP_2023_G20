package Others.Interfaces;

import Model.Users.Person;

public interface CommentActions {
    Boolean canEditComment();
    Boolean isSender(Person person);
    void editComment(StringBuilder newComment);
}
