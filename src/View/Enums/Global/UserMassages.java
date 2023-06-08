package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum UserMassages implements EnumMassage {
    NOT_USER("you are not a user to have your restaurants"),
    NOT_LOGGED_IN("you must log in first"),
    NOT_IN_MAIN_MENU("you must be in main menu for this action"),
    NOT_IN_FOOD_MENU("you haven't chosen a food yet"),
    USER_HAS_THIS_RESTAURANT("you have a restaurant with this name"),
    INVALID_TYPE_OF_RESTAURANT("invalid type of restaurant"),
    NO_RESTAURANT_FOUND("no restaurant was found with this name"),
    NOT_IN_RESTAURANT_MENU("you are not in restaurant menu"),
    ACCEPT_RESTAURANT_TYPE_CHANGE("do you want to change your restaurant type? YES or NO"),
    CANNOT_CHANGE_RESTAURANT_TYPE("you cannot change your restaurant type"),
    ENTERED_MY_RESTAURANT("entered restaurant menu"),
    FOOD_NAME_REPETITIOUS("food name already exists in this restaurant"),
    NO_SELECTED_RESTAURANT("you have not selected a restaurant yet");

    final private String massage;
    UserMassages(String massage){
        this.massage=massage;
    }
    public String giveMassage(){
        return this.massage;
    }
}
