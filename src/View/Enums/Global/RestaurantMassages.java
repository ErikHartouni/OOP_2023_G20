package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum RestaurantMassages implements EnumMassage {
    FOOD_NOT_FOUND("this food was not found in this restaurant"),
    SELECT_FOOD("food selected"),
    TYPE_CHANGED("food type changed"),
    RESTAURANT_SELECTED("restaurant selected");

    final private String massage;
    RestaurantMassages(String massage){
        this.massage=massage;
    }

    @Override
    public String giveMassage() {
        return this.massage;
    }
}
