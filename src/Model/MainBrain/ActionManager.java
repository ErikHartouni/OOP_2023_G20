package Model.MainBrain;

import Model.DataServer.AdminSaver;
import Model.DataServer.UsersSaver;
import Model.Users.Admin;
import Model.Users.User;
import View.Enums.Creation.PasswordCreationEnum;
import View.Enums.Creation.SuccessOrErrorInCreation;
import View.Enums.Creation.UsernameCreationEnum;
import View.Enums.Global.GlobalMassage;
import View.Enums.Global.OtherErrors;
import View.Enums.Login.LogeIn;
import View.Enums.Login.PasswordLogin;
import View.Enums.Login.UsernameLogin;
import View.Enums.Logout.LogoutMassage;
import View.ViewCenter;

public class ActionManager {
    private ViewCenter viewCenter;
    private OnlinePlace onlinePlace;
    private String[] orderPiece;
    private  User user;
    private Admin admin;

    private static UsersSaver usersData;
    private static AdminSaver adminsData;

    //constructor
    public ActionManager(){
        viewCenter=new ViewCenter();
        usersData = new UsersSaver();
        adminsData = new AdminSaver();
        onlinePlace = OnlinePlace.LOGOUT_LOGIN_CREATION_MENU;
    }
    //methods
    public void logout(){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            user=null;
            viewCenter.cout(LogoutMassage.SUCCESS);
            onlinePlace = OnlinePlace.LOGOUT_LOGIN_CREATION_MENU;
        }else {
            viewCenter.cout(LogoutMassage.NULL_USER);
        }
    }
    public void invalid(){
        viewCenter.cout(GlobalMassage.INVALID);
    }
    public void loginUser(String order){
        orderPiece=order.split("\\s+");
        int index =usersData.userIndex(orderPiece[2]);
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z0-9]+")){
                    if(index!=-1){
                        if(orderPiece[3].length()>6){
                            if(orderPiece[3].matches("[A-z0-9@#$&]+")){
                                if(usersData.checkPassword(orderPiece[2],orderPiece[3])){
                                    user=usersData.giveUser(index);
                                    viewCenter.cout(LogeIn.SUCCESS);
                                    onlinePlace=OnlinePlace.MAIN_MENU;
                                }else viewCenter.cout(PasswordLogin.WRONG_PASSWORD);
                            }else viewCenter.cout(PasswordLogin.SYNTAX_ERROR);
                        }else viewCenter.cout(PasswordLogin.LENGTH_ERROR);
                    }else viewCenter.cout(UsernameLogin.NOT_FOUND);
                }else viewCenter.cout(UsernameLogin.SYNTAX_ERROR);
            }else viewCenter.cout(UsernameLogin.LENGTH_ERROR);
        }else viewCenter.cout(LogeIn.NOT_NULL_USER);
    }//ended
    public void loginAdmin(String order){
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=order.split("\\s+");
            int index=adminsData.adminIndex(orderPiece[2]);
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z0-9]+")){
                    if(index!=-1){
                        if(orderPiece[3].length()>6){
                            if(orderPiece[3].matches("[A-z0-9@#$&]+")){
                                if(adminsData.checkPassword(orderPiece[2],orderPiece[3])){
                                    admin=adminsData.giveAdmin(index);
                                    onlinePlace=OnlinePlace.MAIN_MENU;
                                    viewCenter.cout(LogeIn.SUCCESS);
                                }else viewCenter.cout(PasswordLogin.WRONG_PASSWORD);
                            }else viewCenter.cout(PasswordLogin.SYNTAX_ERROR);
                        }else viewCenter.cout(PasswordLogin.LENGTH_ERROR);
                    }else viewCenter.cout(UsernameLogin.NOT_FOUND);
                }else viewCenter.cout(UsernameLogin.SYNTAX_ERROR);
            }else viewCenter.cout(UsernameLogin.LENGTH_ERROR);
        }else viewCenter.cout(LogeIn.NOT_NULL_USER);
    }//ended
    public void end(){
        viewCenter.cout(GlobalMassage.END);
    }//neads data saving
    public void createAdmin(String string){
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=string.split("\\s+");
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z]+[0-9]*[A-z]+")){
                    if(adminsData.adminIndex(orderPiece[2])==-1){
                        if (orderPiece[3].length() > 6) {
                            if (orderPiece[3].matches("[A-z0-9@#$&]+")) {
                                adminsData.createAdmin(orderPiece[2], orderPiece[3]);
                                viewCenter.cout(UsernameCreationEnum.CORRECT_USERNAME);
                                viewCenter.cout(PasswordCreationEnum.PASSWORD_CORRECT);
                                viewCenter.cout(SuccessOrErrorInCreation.SUCCESS);
                            } else {
                                viewCenter.cout(PasswordCreationEnum.SYNTAX_ERROR);
                            }
                        } else {
                            viewCenter.cout(PasswordCreationEnum.LENGTH_ERROR);
                        }
                    }else {
                        viewCenter.cout(UsernameCreationEnum.USED_USERNAME);
                    }
                }else{
                    viewCenter.cout(UsernameCreationEnum.SYNTAX_ERROR);
                }
            }else {
                viewCenter.cout(UsernameCreationEnum.LENGTH_ERROR);
            }
        }else{
            viewCenter.cout(SuccessOrErrorInCreation.NOT_IN_CREATION_PANEL);
        }
    }//ended
    public void createUser(String string){
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=string.split("\\s+");
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z]+[0-9]*[A-z]+")){
                    if(usersData.userIndex(orderPiece[2])==-1){
                        if (orderPiece[3].length() > 6) {
                            if (orderPiece[3].matches("[A-z0-9@#$&]+")) {
                                usersData.createUser(orderPiece[2], orderPiece[3]);
                                viewCenter.cout(UsernameCreationEnum.CORRECT_USERNAME);
                                viewCenter.cout(PasswordCreationEnum.PASSWORD_CORRECT);
                                viewCenter.cout(SuccessOrErrorInCreation.SUCCESS);
                            } else {
                                viewCenter.cout(PasswordCreationEnum.SYNTAX_ERROR);
                            }
                        } else {
                            viewCenter.cout(PasswordCreationEnum.LENGTH_ERROR);
                        }
                    }else {
                        viewCenter.cout(UsernameCreationEnum.USED_USERNAME);
                    }
                }else{
                    viewCenter.cout(UsernameCreationEnum.SYNTAX_ERROR);
                }
            }else {
                viewCenter.cout(UsernameCreationEnum.LENGTH_ERROR);
            }
        }else{
            viewCenter.cout(SuccessOrErrorInCreation.NOT_IN_CREATION_PANEL);
        }
    }//ended
    public void showMyRestaurants(){
        if(onlinePlace == OnlinePlace.MAIN_MENU){
            if(user!=null){
                viewCenter.showArraylist(this.user.giveMyRestaurants());
            }else viewCenter.cout(OtherErrors.NOT_USER);
        }else viewCenter.cout(OtherErrors.NOT_IN_MAIN_MENU);
    }//complete
    public void choseMyRestaurant(String string){

    }

}
