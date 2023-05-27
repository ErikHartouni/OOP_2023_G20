package View.Enums.Login;

import Others.Interfaces.EnumMassage;

public enum UsernameLogin implements EnumMassage {
    LENGTH_ERROR("username must be over 4 characters"),
    SYNTAX_ERROR("username must only contain letters and numbers and must not contain space"),
    NOT_FOUND("username not found");

    final private String massage;
    UsernameLogin(String massage){
        this.massage=massage;
    }

    public String giveMassage(){
        return this.massage;
    }
}
