package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum OtherErrors implements EnumMassage {
    NOT_USER("you are not a user to have your restaurants"),
    NOT_IN_MAIN_MENU("you must be in main menu for this action");

    final private String massage;
    OtherErrors(String massage){
        this.massage=massage;
    }
    public String giveMassage(){
        return this.massage;
    }
}
