package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum FoodMassage implements EnumMassage {
    PRICE_CHANGED("food price changed successfully"),
    WRONG_INT("price must be posetive!"),
    FOOD_NAME_CHANGED("food name changed");

    final private String massage;
    FoodMassage(String massage){
        this.massage=massage;
    }
    @Override
    public String giveMassage() {
        return massage;
    }
}
