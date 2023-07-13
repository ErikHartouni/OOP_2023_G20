package Model.DataServer.IDHandler;

public enum TypeOfID {
    USER("#US") , RESTAURANT("#RS") , ADMIN("#AD") , FOOD("#FD") ,
    POSTMAN("#PS"), COMMENT("#CT"), ORDER("#OD");

    final private String code;
    TypeOfID(String code){
        this.code=code;
    }

    public static TypeOfID giveType(String s) {
        for (TypeOfID myEnum : TypeOfID.values()) {
            if (myEnum.giveCode().equalsIgnoreCase(s)) {
                return myEnum;
            }
        }return null;
    }

    public String giveCode(){
        return this.code;
    }
}
