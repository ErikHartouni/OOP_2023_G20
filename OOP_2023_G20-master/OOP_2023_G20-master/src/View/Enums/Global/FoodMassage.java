package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum FoodMassage implements EnumMassage {
    PRICE_CHANGED("food price changed successfully"),
    WRONG_INT("price must be posetive!"),
    FOOD_NAME_CHANGED("food name changed"),
    DELETED("food deleted from menu"),
    DEACTIVATED("food deactivated"),
    ACTIVATED("food activated"),
    CANNOT_DEACTIVATE("cannot deactivate food"),
    CANNOT_DELETE("cannot delete food"),
    HAS_DISCOUNT("food has discount"),
    SUCCESSFUL_DISCOUNT("discount created"),
    NOT_SUCCESSFUL_DISCOUNT("discount must not exceed 50%."),
    IS_OWNER("you cannot add a comment to your food!"),
    ENTER_COMMENT("please enter your comment:\n\t"),
    COMMENT_ADDED("comment added!"),
    COMMENT_NOT_FOUND("comment doesn't exist!"),
    COMMENT_EDITED("comment edited"),
    COMMENT_LENGTH("comment size must be higher than 10 characters !"),
    COMMENT_LIMIT("you can edit comment once !");

    final private String massage;
    FoodMassage(String massage){
        this.massage=massage;
    }
    @Override
    public String giveMassage() {
        return massage;
    }
}
