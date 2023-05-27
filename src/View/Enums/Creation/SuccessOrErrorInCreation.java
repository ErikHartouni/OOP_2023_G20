package View.Enums.Creation;

import Others.Interfaces.EnumMassage;

public enum SuccessOrErrorInCreation implements EnumMassage {
    SUCCESS("user created successfully"),
    NOT_IN_CREATION_PANEL("log out in order to create an account");


    final private String massage;
    SuccessOrErrorInCreation(String massage){
        this.massage=massage;
    }

    @Override
    public String giveMassage() {
        return this.massage;
    }
}
