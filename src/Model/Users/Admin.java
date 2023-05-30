package Model.Users;

import Model.DataServer.IDHandler.ID;
import Others.Interfaces.AdminActions;

public class Admin extends Person{//implements AdminActions {
    private ID adminID;


    public Admin(String username , String password , ID id){
        this.adminID = id;
        this.username=username;
        this.password=password;
    }

    @Override
    public void editMyRestaurantFoodPrice(Integer newPrise) {
        
    }
}
