package View.Enums.Login;

import Others.Interfaces.EnumMassage;

public enum LogeIn implements EnumMassage {
    ALREADY_LOGGED_IN("first log out in order to login as another account"),
    SUCCESS("logged in successfully"),
    NOT_NULL_USER("logout in order to log in");
    final private String massage;
    LogeIn(String massage){
        this.massage=massage;
    }
    public String giveMassage(){
        return this.massage;
    }
}
