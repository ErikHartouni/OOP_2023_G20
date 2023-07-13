package View.Enums.Global;

import Others.Interfaces.EnumMassage;

public enum PostmanMessages implements EnumMassage {
    NOT_FOUND("no such order found");

    private String mess;
    PostmanMessages(String mess){
        this.mess=mess;
    }
    @Override
    public String giveMassage() {
        return mess;
    }
}
