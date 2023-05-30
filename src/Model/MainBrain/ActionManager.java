package Model.MainBrain;

import Model.DataServer.AdminSaver;
import Model.DataServer.IDHandler.IDServer;
import Model.DataServer.IDHandler.TypeOfID;
import Model.DataServer.RestaurantSaver;
import Model.DataServer.UsersSaver;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Admin;
import Model.Users.User;
import Others.ErrorsAndExceptions.EnumValueMakingException;
import View.Enums.Creation.PasswordCreationEnum;
import View.Enums.Creation.SuccessOrErrorInCreation;
import View.Enums.Creation.UsernameCreationEnum;
import View.Enums.Global.FoodMassage;
import View.Enums.Global.GlobalMassage;
import View.Enums.Global.RestaurantMassages;
import View.Enums.Global.UserMassages;
import View.Enums.Login.LogeIn;
import View.Enums.Login.PasswordLogin;
import View.Enums.Login.UsernameLogin;
import View.Enums.Logout.LogoutMassage;
import View.ViewCenter;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ActionManager {
    private ViewCenter viewCenter;
    private OnlinePlace onlinePlace;
    private String[] orderPiece;
    private  User user;
    private Admin admin;
    private IDServer idServer;
    private BooleanManagingType booleanManagingType;
    private static UsersSaver usersData;
    private static AdminSaver adminsData;
    private static RestaurantSaver restaurantsData;
    private RestaurantType restaurantType;

    //constructor
    public ActionManager(){
        viewCenter=new ViewCenter();
        usersData = new UsersSaver();
        adminsData = new AdminSaver();
        onlinePlace = OnlinePlace.LOGOUT_LOGIN_CREATION_MENU;
        idServer=new IDServer();
    }
    //methods
    public void logout(){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            user=null;
            admin=null;
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
                                adminsData.createAdmin(orderPiece[2], orderPiece[3],idServer.createID(TypeOfID.ADMIN));
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
                                usersData.createUser(orderPiece[2], orderPiece[3],idServer.createID(TypeOfID.USER));
                                viewCenter.cout(UsernameCreationEnum.CORRECT_USERNAME);
                                viewCenter.cout(PasswordCreationEnum.PASSWORD_CORRECT);
                                viewCenter.cout(SuccessOrErrorInCreation.SUCCESS);
                            } else viewCenter.cout(PasswordCreationEnum.SYNTAX_ERROR);
                        } else viewCenter.cout(PasswordCreationEnum.LENGTH_ERROR);
                    }else viewCenter.cout(UsernameCreationEnum.USED_USERNAME);
                }else viewCenter.cout(UsernameCreationEnum.SYNTAX_ERROR);
            }else viewCenter.cout(UsernameCreationEnum.LENGTH_ERROR);
        }else viewCenter.cout(SuccessOrErrorInCreation.NOT_IN_CREATION_PANEL);
    }//ended
    public void createRestaurant(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece = string.split("\\s+");
            if (onlinePlace == OnlinePlace.MAIN_MENU) {
                if (user != null) {
                    RestaurantType restaurantType;
                    try {
                        restaurantType = RestaurantType.valueOf(orderPiece[3].toUpperCase());
                        if (!user.doesHaveThisRestaurant(orderPiece[2])) {
                            Restaurant newRestaurant = new Restaurant(orderPiece[2], user, idServer.createID(TypeOfID.RESTAURANT), restaurantType);
                            user.createRestaurant(newRestaurant);
                            restaurantsData.addRestaurant(newRestaurant);
                        } else viewCenter.cout(UserMassages.USER_HAS_THIS_RESTAURANT);
                    } catch (RuntimeException exception) {
                        viewCenter.cout(UserMassages.INVALID_TYPE_OF_RESTAURANT);
                    }
                } else viewCenter.cout(UserMassages.NOT_USER);
            } else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void showMyRestaurants(){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (onlinePlace == OnlinePlace.MAIN_MENU) {
                if (user != null) {
                    viewCenter.showArraylist(this.user.giveMyRestaurants());
                } else viewCenter.cout(UserMassages.NOT_USER);
            } else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }//complete
    public void choseMyRestaurant(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece = string.split("\\s+");
            if (user != null) {
                if (onlinePlace == OnlinePlace.MAIN_MENU) {
                    if (this.user.doesHaveThisRestaurant(orderPiece[3])) {
                        this.user.chooseRestaurant(orderPiece[3]);
                        onlinePlace = OnlinePlace.MY_RESTAURANT_MENU;
                        viewCenter.cout(UserMassages.ENTERED_MY_RESTAURANT);
                    } else viewCenter.cout(UserMassages.NO_RESTAURANT_FOUND);
                } else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void showMyFoods(){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (user != null) {
                if (onlinePlace == OnlinePlace.MY_RESTAURANT_MENU) {
                    viewCenter.showArraylist(this.user.giveAllFoodsOfMyRestaurant());
                } else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void choseMyFood(String string){
        orderPiece = string.split("\\s+");
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (user != null) {
                if (onlinePlace == OnlinePlace.MY_RESTAURANT_MENU) {
                    if (user.doesFoodExistInMyRestaurant(orderPiece[3])) {
                        this.user.selectFood(orderPiece[3]);
                        onlinePlace = OnlinePlace.MY_FOOD_MENU;
                        viewCenter.cout(RestaurantMassages.SELECT_FOOD);
                    } else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
                } else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public Boolean editRestaurantType(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (user != null) {
                if(onlinePlace == OnlinePlace.MY_RESTAURANT_MENU){
                    if(this.user.canChangeMyRestaurantType()){
                        try {
                             restaurantType = RestaurantType.valueOf(orderPiece[3].toUpperCase());
                             viewCenter.cout(UserMassages.ACCEPT_RESTAURANT_TYPE_CHANGE);
                             booleanManagingType = BooleanManagingType.RESTAURANT_TYPE;
                             return true;
                        } catch (RuntimeException exception) {
                            viewCenter.cout(UserMassages.INVALID_TYPE_OF_RESTAURANT);
                        }
                    }else viewCenter.cout(UserMassages.CANNOT_CHANGE_RESTAURANT_TYPE);
                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
        return false;
    }
    public void booleanManager(Boolean toDO){
        switch (booleanManagingType){
            case RESTAURANT_TYPE :
                this.user.editMyRestaurantFoodType(this.restaurantType);
                restaurantType = null;
                viewCenter.cout(RestaurantMassages.TYPE_CHANGED);
                break;

        }
    }
    public void editFoodName(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(user != null){
                if(onlinePlace == OnlinePlace.MY_FOOD_MENU){
                    orderPiece = string.split("\\s+");
                    if(!user.doesFoodNameExistsInMyRestaurant(cutName(string,4))){
                        user.editMyRestaurantFoodName(cutName(string , 4));
                        viewCenter.cout(FoodMassage.FOOD_NAME_CHANGED);
                    }else viewCenter.cout(UserMassages.FOOD_NAME_REPETITIOUS);
                }else viewCenter.cout(UserMassages.NOT_IN_FOOD_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void editFoodPrice(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(user != null){
                if(onlinePlace == OnlinePlace.MY_FOOD_MENU){
                    orderPiece = string.split("\\s+");
                    try{
                        int newPrice = Integer.parseInt(orderPiece[4]);
                        if(newPrice>0){
                            user.editMyRestaurantFoodPrice(newPrice);
                            viewCenter.cout(FoodMassage.PRICE_CHANGED);
                        }else viewCenter.cout(FoodMassage.WRONG_INT);
                    }catch (NumberFormatException e){
                        viewCenter.cerr(e);
                    }
                }else viewCenter.cout(UserMassages.NOT_IN_FOOD_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void addFood(String string)throws EnumValueMakingException {
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(user != null){
                if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
                    orderPiece=string.split("\\s+");
                    try{
                        int price = Integer.parseInt(orderPiece[3]);
                        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
                        LocalTime localTime = LocalTime.parse("0:"+orderPiece[5]+":"+orderPiece[6]);
                        FoodType foodType = FoodType.valueOf(orderPiece[4].toUpperCase());
                    }catch (NumberFormatException | DateTimeParseException | EnumValueMakingException e){viewCenter.cerr(e);}


                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }



    //private methods
    private String cutName(String string , int n){
        int i=0;
        for(int k=0;k<n;k++){
            i = string.indexOf(" ",i+1);
        }return string.substring(i);
    }

}

/*
if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(user != null){
                if(onlinePlace==OnlinePlace){

                }else viewCenter.cout();
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
 */
