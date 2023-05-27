package Model.DataServer.IDHandler;

public enum TypeOfID {
    USER("#US") , RESTAURANT("#RS") , ADMIN("#AD") , FOOD("#FD") ,
    POSTMAN("PS");

    final private String code;
    TypeOfID(String code){
        this.code=code;
    }
    public String giveCode(){
        return this.code;
    }
}
