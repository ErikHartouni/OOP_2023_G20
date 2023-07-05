package View.Enums.Creation;

import Others.Interfaces.EnumMassage;

public enum UsernameCreationEnum implements EnumMassage {
    LENGTH_ERROR("username must be over 4 characters"),
    SYNTAX_ERROR("username must only contain letters and numbers and must not contain space"),
    CORRECT_USERNAME("username is in syntax"),
    USED_USERNAME("this username is not available");

    final private String massage;
    UsernameCreationEnum(String massage){
        this.massage=massage;
    }

    public String giveMassage(){
        return this.massage;
    }
}
