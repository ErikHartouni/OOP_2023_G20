package Controller;

import Model.MainBrain.ActionManager;

import javax.swing.*;
import java.util.regex.Pattern;

public enum InputType {
    USER_CREATION(Pattern.compile("^ADD\\s+USER\\s+[!-z]+\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    ADMIN_CREATION(Pattern.compile("^ADD\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    USER_LOGIN(Pattern.compile("^LOGIN\\s+USER\\s+[!-z]+\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    ADMIN_LOGIN(Pattern.compile("^LOGIN\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    LOGOUT(Pattern.compile("^LOGOUT$",Pattern.CASE_INSENSITIVE)),
    SHOW_MY_RESTAURANTS(Pattern.compile("^SHOW\\s+MY\\s+RESTAURANTS$",Pattern.CASE_INSENSITIVE)),
    CREATE_RESTAURANT(Pattern.compile("^CREATE\\s+RESTAURANT\\s+[!-z]+\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    CHOSE_MY_RESTAURANT(Pattern.compile("^SHOW\\s+MY\\s+RESTAURANTS$",Pattern.CASE_INSENSITIVE)),
    SHOW_MY_FOODS(Pattern.compile("^SHOW\\s+MY\\s+FOODS$",Pattern.CASE_INSENSITIVE)),
    CHOSE_MY_FOOD(Pattern.compile("^CHOSE\\s+MY\\s+RESTAURANT\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    EDIT_RESTAURANT_TYPE(Pattern.compile("^EDIT\\s+MY\\s+RESTAURANT\\s+TYPE\\s+TO\\s+[!-z]$",Pattern.CASE_INSENSITIVE)),
    YES(Pattern.compile("^YES$",Pattern.CASE_INSENSITIVE)),
    NO(Pattern.compile("^NO$",Pattern.CASE_INSENSITIVE)),
    EDIT_FOOD_NAME(Pattern.compile("^EDIT\\s+FOOD\\s+NAME\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    EDIT_FOOD_PRICE(Pattern.compile("^EDIT\\s+FOOD\\s+PRICE\\s+[0-9]+$",Pattern.CASE_INSENSITIVE)),
    ADD_FOOD(Pattern.compile("^ADD\\s+FOOD\\s+[!-z]+\\s+[0-9]+\\s+[!-z]+\\s+[0-9]+\\s+[0-9]+$",Pattern.CASE_INSENSITIVE)),
    DELETE_FOOD(Pattern.compile("^DELETE\\s+FOOD\\s+[A-z0-9]$",Pattern.CASE_INSENSITIVE)),
    DEACTIVATE_FOOD(Pattern.compile("^DEACTIVATE\\s+FOOD\\s+[A-z0-9]+$",Pattern.CASE_INSENSITIVE)),
    ACTIVATE_FOOD(Pattern.compile("^ACTIVATE\\s+FOOD\\s+[A-z0-9]+$",Pattern.CASE_INSENSITIVE)),
    DISCOUNT_FOOD(Pattern.compile("^ADD\\s+DISCOUNT\\s+[0-9]+\\s+[0-9]+:[0-9]+:[0-9]+:[0-9]+:$",Pattern.CASE_INSENSITIVE)),
    SEARCH_RESTAURANT(Pattern.compile("^SEARCH\\s+RESTAURANT\\s+[A-z]+$",Pattern.CASE_INSENSITIVE)),
    SELECT_RESTAURANT(Pattern.compile("^SELECT\\s+RESTAURANT\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    SHOW_MENU(Pattern.compile("^SHOW\\s+MENU$",Pattern.CASE_INSENSITIVE)),
    SELECT_FOOD(Pattern.compile("^SELECT\\s+FOOD\\s+[!-z]+$",Pattern.CASE_INSENSITIVE)),
    DISPLAY_COMMENTS(Pattern.compile("^DISPLAY\\s+COMMENTS$",Pattern.CASE_INSENSITIVE)),
    ADD_COMMENT(Pattern.compile("^ADD\\s+COMMENT$",Pattern.CASE_INSENSITIVE)),

    END(Pattern.compile("^END$",Pattern.CASE_INSENSITIVE)) , INVALID_ORDER;

    InputType(){
        pattern = null;
    }
    private final Pattern pattern;
    InputType(Pattern pattern ){
        this.pattern=pattern;
    }

    public Boolean matches(String string){
        return pattern.matcher(string).matches();
    }


}