package Model.DataServer.IDHandler;


public class ID {
    private final String code ;
    private final TypeOfID type;

    ID(int code , TypeOfID typeOfID){
        this .code = Integer.toHexString(code);
        this.type=typeOfID;
    }

    public String show(){
        return type.giveCode()+code;
    }
}
