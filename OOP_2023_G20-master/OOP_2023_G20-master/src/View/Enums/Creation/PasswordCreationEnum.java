package View.Enums.Creation;

import Others.Interfaces.EnumMassage;

public enum PasswordCreationEnum implements EnumMassage {
    LENGTH_ERROR("password must be over 6 characters"),
    SYNTAX_ERROR("password must include capital and small letters and numbers and at least one of # or $ or & or @"),
    PASSWORD_CORRECT("password is in syntax");

    final private String massage;

    PasswordCreationEnum( String massage) {
        this.massage=massage;
    }

    @Override
    public String giveMassage(){
        return this.massage;
    }
}
