package Model.DataServer.IDHandler;

import java.util.Arrays;

public class IDServer {
    private Integer foodNumber , userNumber , adminNumber ,
            restaurantNumber , postmanNumber,orderNumber;
    public IDServer(int foodNumber , int userNumber , int restaurantNumber , int adminNumber , int postmanNumber , int orderNumber){
        this.foodNumber=foodNumber;this.userNumber=userNumber;this.restaurantNumber=restaurantNumber;
        this.adminNumber=adminNumber;this.postmanNumber=postmanNumber;this.orderNumber=orderNumber;
    }
    public static ID toID(String string){
        String[]check=string.split("_");
        TypeOfID type = TypeOfID.giveType(check[0]);
        return new ID(Integer.parseInt(check[1]),type);
    }
    public ID createID(TypeOfID type ){
        int code = 0;
        switch(type){
            case USER -> code = ++userNumber;
            case ADMIN -> code = ++adminNumber;
            case RESTAURANT -> code = ++restaurantNumber;
            case FOOD -> code = ++foodNumber;
            case POSTMAN -> code = ++postmanNumber;
            case ORDER -> code = ++orderNumber;
        }
        return new ID(code,type);
    }


}
