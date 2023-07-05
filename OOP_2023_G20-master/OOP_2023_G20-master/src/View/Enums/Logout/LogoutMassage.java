package View.Enums.Logout;

import Others.Interfaces.EnumMassage;

public enum LogoutMassage implements EnumMassage {
    SUCCESS("logged out successfully"),
    NULL_USER("you must login in order to logout");

    final private String massage;
    LogoutMassage(String massage){
        this.massage=massage;
    }
    public String giveMassage(){
        return this.massage;
    }
}
