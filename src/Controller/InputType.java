package Controller;

import Model.MainBrain.ActionManager;

import javax.swing.*;
import java.util.regex.Pattern;

public enum InputType {
    USER_CREATION("^CREATE\\s+USER\\s+[!-z]+\\s+[!-z]+$" ),
    ADMIN_CREATION("^CREATE\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$" ),
    USER_LOGIN("^LOGIN\\s+USER\\s+[!-z]+\\s+[!-z]+$" ),
    ADMIN_LOGIN("^LOGIN\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$" ),
    LOGOUT("^LOGOUT$" ),
    SHOW_MY_RESTAURANTS("^SHOW\\s+MY\\s+RESTAURANTS$" ),
    CREATE_RESTAURANT("^CREATE\\s+RESTAURANT\\s+[!-z]+\\s+[!-z]+$" ),
    CHOSE_MY_RESTAURANT("^CHOSE\\s+MY\\s+RESTAURANT\\s+[!-z]+$" ),
    SHOW_MY_FOODS("^SHOW\\s+MY\\s+FOODS$" ),
    CHOSE_MY_FOOD("^CHOSE\\s+MY\\s+FOOD\\s+[!-z]+$" ),
    EDIT_RESTAURANT_TYPE("^EDIT\\s+MY\\s+RESTAURANT\\s+TYPE\\s+TO\\s+[!-z]+$" ),
    YES("^YES$" ),
    NO("^NO$" ),
    EDIT_FOOD_NAME("^EDIT\\s+FOOD\\s+NAME\\s+[!-z]+$" ),
    EDIT_FOOD_PRICE("^EDIT\\s+FOOD\\s+PRICE\\s+[0-9]+$" ),
    ADD_FOOD("^ADD\\s+FOOD\\s+[!-z]+\\s+[0-9]+\\s+[!-z]+\\s+[0-9]+\\s+[0-9]+$" ),
    DELETE_FOOD("^DELETE\\s+FOOD\\s+[!-z]+$" ),
    DEACTIVATE_FOOD(  "^DEACTIVATE\\s+FOOD\\s+[!-z]+$" ),
    ACTIVATE_FOOD(  "^ACTIVATE\\s+FOOD\\s+[!-z]+$" ),
    DISCOUNT_FOOD(  "^ADD\\s+DISCOUNT\\s+[0-9]+\\s+[0-9]+:[0-9]+:[0-9]+:[0-9]+:$" ),
    SEARCH_RESTAURANT(  "^SEARCH\\s+RESTAURANT\\s+[A-z]+$" ),
    SELECT_RESTAURANT(  "^SELECT\\s+RESTAURANT\\s+[!-z]+$" ),
    SHOW_MENU(  "^SHOW\\s+MENU$" ),
    SELECT_FOOD(  "^SELECT\\s+FOOD\\s+[!-z]+$" ),
    DISPLAY_COMMENTS(  "^DISPLAY\\s+COMMENTS$" ),
    ADD_COMMENT(  "^ADD\\s+COMMENT$" ),
    EDIT_COMMENT(  "^EDIT\\s+COMMENT\\s+[!-z]+$" ),
    DISPLAY_RATING(  "^DISPLAY\\s+RATING$" ),
    SUBMIT_RATING("^SUBMIT\\s+RATING\\s+[A-z]+$" ),
    ADD_TO_CART("^ADD\\s+THIS\\s+FOOD\\s+TO\\s+CHART$"),
    SHOW_CART("^SHOW\\s+CART"),
    BACK("^BACK$"),
    END("^END$") , INVALID_ORDER;


    InputType(){
        this.pattern = Pattern.compile("end");
    }
    private final Pattern pattern;
    InputType(String regex){
        this.pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE) ;
    }

    public Boolean matches(String string){
        return pattern.matcher(string).matches();
    }


}