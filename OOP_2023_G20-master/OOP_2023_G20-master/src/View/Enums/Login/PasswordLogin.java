package View.Enums.Login;

import Others.Interfaces.EnumMassage;

public enum PasswordLogin implements EnumMassage {
    LENGTH_ERROR("password must be over 6 characters"),
    SYNTAX_ERROR("password must include capital and small letters and numbers"),
    WRONG_PASSWORD("password is wrong");

    final private String massage;
    PasswordLogin(String massage){
        this.massage=massage;
    }

    public String giveMassage(){
        return this.massage;
    }
}
