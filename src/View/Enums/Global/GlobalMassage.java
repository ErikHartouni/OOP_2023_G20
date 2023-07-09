package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum GlobalMassage implements EnumMassage {
    INVALID("invalid order"),
    EMPTY("this part is empty"),
    END("ended successfully"),
    START("application started successfully!"),
    STH_WENT_WRONG("something went wrong in loading database");
    final private String massage;
    GlobalMassage(String massage){
        this.massage=massage;
    }
    public String giveMassage(){
        return this.massage;
    }

}
