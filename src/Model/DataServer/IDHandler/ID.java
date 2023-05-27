package Model.DataServer.IDHandler;

import Controller.InputType;

public class ID {
    private String code ;
    private TypeOfID type;

    ID(int code , TypeOfID typeOfID){
        this .code = Integer.toHexString(code);
        this.type=typeOfID;
    }

    public String show(){
        return type.giveCode()+code;
    }
}
