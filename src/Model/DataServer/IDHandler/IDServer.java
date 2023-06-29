package Model.DataServer.IDHandler;

public class IDServer {
    private Integer foodNumber , userNumber , adminNumber ,
            restaurantNumber , postmanNumber;
    public IDServer(){
        foodNumber=0;userNumber=0;restaurantNumber=0;adminNumber=0;postmanNumber=0;
    }
    public ID createID(TypeOfID type ){
        int code = 0;
        switch(type){
            case USER -> code = ++userNumber;
            case ADMIN -> code = ++adminNumber;
            case RESTAURANT -> code = ++restaurantNumber;
            case FOOD -> code = ++foodNumber;
        }
        return new ID(code,type);
    }

}
