package Model.MainBrain;

import Controller.InputType;
import Model.DataServer.*;
import Model.DataServer.IDHandler.IDServer;
import Model.DataServer.IDHandler.TypeOfID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Rating;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.Person;
import Model.Users.PostMan;
import Model.Users.User;
import Others.ErrorsAndExceptions.EnumValueMakingException;
import View.Enums.Creation.PasswordCreationEnum;
import View.Enums.Creation.SuccessOrErrorInCreation;
import View.Enums.Creation.UsernameCreationEnum;
import View.Enums.Global.*;
import View.Enums.Login.LogeIn;
import View.Enums.Login.PasswordLogin;
import View.Enums.Login.UsernameLogin;
import View.Enums.Logout.LogoutMassage;
import View.InputReceiver;
import View.ViewCenter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ActionManager {
    private final ViewCenter viewCenter;
    private String myRestaurantId;
    private OnlinePlace onlinePlace;
    private String[] orderPiece;
    private Person person;
    private Restaurant outerRestaurant;
    private static IDServer idServer;
    private static PostmanServer postmanData;
    private static UsersSaver usersData;
    private static AdminSaver adminsData;
    private static RestaurantSaver restaurantsData;
    private static MassageServer massageServer;
    private static RatingServer ratingServer;
    public static LocationServer locationServer;
    private static FoodServer foodServer;
    private static CommentServer commentServer;
    private static OrderServer orderServer;
    private final InputReceiver inputReceiver;

    public static void loadInformation(){
        orderServer=new OrderServer();
        postmanData=new PostmanServer();
        ratingServer=new RatingServer();//
        locationServer=new LocationServer();//
        foodServer=new FoodServer();//
        commentServer=new CommentServer();//
        massageServer=new MassageServer();//
        usersData = new UsersSaver();//
        adminsData = new AdminSaver();//
        restaurantsData = new RestaurantSaver();//
        //now load the information
        ratingServer.load();
        commentServer.load();
        foodServer.load();
        usersData.getItems();
        restaurantsData.load();
        orderServer.load();
        massageServer.load();
        locationServer.getFile();
        adminsData.load();
        postmanData.load();
        //now put the information in it's right place
        usersData.giveMessages(massageServer.give());
        adminsData.giveMessages(massageServer.give());
        restaurantsData.giveComments(commentServer.give());
        restaurantsData.giveFoods(foodServer.give());
        restaurantsData.geiveRatings(ratingServer.give());
        usersData.giveMyRestaurants(restaurantsData.give());
        idServer=new IDServer(foodServer.giveNum(),usersData.giveNum(),restaurantsData.giveNum(),
                adminsData.giveNum(),postmanData.giveNum(),orderServer.giveNum());
    }

    //constructor
    public ActionManager(InputReceiver inputReceiver){
        viewCenter=new ViewCenter();
        onlinePlace = OnlinePlace.LOGOUT_LOGIN_CREATION_MENU;
        this.inputReceiver=inputReceiver;
    }

    public static void saveInformation() {
        ratingServer.save();
        commentServer.save();
        foodServer.save();
        usersData.save();
        restaurantsData.save();
        massageServer.save();
        adminsData.save();
    }

    //methods
    public void logout(){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            person=null;
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
                                    person=usersData.giveUser(index);
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
                                    person=adminsData.giveAdmin(index);
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
                if (person.isUser()) {
                    RestaurantType restaurantType;
                    try {
                        restaurantType = RestaurantType.valueOf(orderPiece[3]);
                        if (!((User)person).doesHaveThisRestaurant(orderPiece[2])) {
                            Restaurant newRestaurant = new Restaurant(orderPiece[2], ((User)person), idServer.createID(TypeOfID.RESTAURANT), restaurantType);
                            ((User)person).createRestaurant(newRestaurant);
                            restaurantsData.addRestaurant(newRestaurant);
                            viewCenter.cout(RestaurantMassages.RESTAURANT_CREATED);
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
                if (person.isUser()) {
                    viewCenter.showArraylist(((User)person).giveMyRestaurants());
                } else viewCenter.cout(UserMassages.NOT_USER);
            } else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }//complete
    public void choseMyRestaurant(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece = string.split("\\s+");
            if (person.isUser()) {
                if (onlinePlace == OnlinePlace.MAIN_MENU) {
                    if (((User)person).doesHaveThisRestaurant(orderPiece[3])) {
                        ((User)person).chooseRestaurant(orderPiece[3]);
                        onlinePlace = OnlinePlace.MY_RESTAURANT_MENU;
                        viewCenter.cout(UserMassages.ENTERED_MY_RESTAURANT);
                    } else viewCenter.cout(UserMassages.NO_RESTAURANT_FOUND);
                } else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void showMyFoods(){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (person.isUser()) {
                if (onlinePlace == OnlinePlace.MY_RESTAURANT_MENU) {
                    viewCenter.showArraylist(((User)person).giveAllFoodsOfMyRestaurant());
                } else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void choseMyFood(String string){
        orderPiece = string.split("\\s+");
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if (person.isUser()) {
                if (onlinePlace == OnlinePlace.MY_RESTAURANT_MENU) {
                    if (((User)person).doesFoodExistInMyRestaurant(orderPiece[3])) {
                        ((User)person).selectFood(orderPiece[3]);
                        onlinePlace = OnlinePlace.MY_FOOD_MENU;
                        viewCenter.cout(RestaurantMassages.SELECT_FOOD);
                    } else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
                } else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            } else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void editRestaurantType(String string){
        orderPiece= string.split("\\s+");
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;}
        if(!person.isUser()){
            viewCenter.cout(UserMassages.NOT_USER);return;}
        if(onlinePlace!=OnlinePlace.MY_RESTAURANT_MENU){
            viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);return;}
        if (!((User)person).canChangeMyRestaurantType()){
            viewCenter.cout(UserMassages.CANNOT_CHANGE_RESTAURANT_TYPE);return;}
        try{
            RestaurantType restaurantType = RestaurantType.valueOf(orderPiece[5]);
            viewCenter.cout(RestaurantMassages.ASK_TO_CHANGE_TYPE);
            try{
                InputType inputType = InputType.valueOf(inputReceiver.getYesOrNo().toUpperCase());
                if(inputType==InputType.YES){
                    ((User)person).cahngeMyRestaurantType(restaurantType);
                    viewCenter.cout(RestaurantMassages.TYPE_CHANGED);
                }
            }catch (Exception e){
                invalid();
                editRestaurantType(string);
            }
        }catch (Exception e ){
            viewCenter.cerr(e);
        }

    }

    public void editFoodName(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(person.isUser()){
                if(onlinePlace == OnlinePlace.MY_FOOD_MENU){
                    orderPiece = string.split("\\s+");
                    if(!((User)person).doesFoodNameExistsInMyRestaurant(orderPiece[3])){
                        ((User)person).editMyRestaurantFoodName(orderPiece[3]);
                        viewCenter.cout(FoodMassage.FOOD_NAME_CHANGED);
                    }else viewCenter.cout(UserMassages.FOOD_NAME_REPETITIOUS);
                }else viewCenter.cout(UserMassages.NOT_IN_FOOD_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void editFoodPrice(String string){
        if(onlinePlace != OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(person.isUser()){
                if(onlinePlace == OnlinePlace.MY_FOOD_MENU){
                    orderPiece = string.split("\\s+");
                    try{
                        int newPrice = Integer.parseInt(orderPiece[3]);
                        if(newPrice>0){
                            ((User)person).editMyRestaurantFoodPrice(newPrice);
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
            if(person.isUser()){
                if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
                    orderPiece=string.split("\\s+");
                    try{
                        int price = Integer.parseInt(orderPiece[3]);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime localTime = LocalTime.parse("00:"+orderPiece[5]+":"+orderPiece[6]);
                        FoodType foodType = FoodType.valueOf(orderPiece[4]);
                        foodServer.addFood(((User)person).addNewFoodToMyRestaurant(orderPiece[2],foodType,
                                localTime,price,0,this.idServer.createID(TypeOfID.FOOD)));
                        viewCenter.cout(RestaurantMassages.FOOD_ADDED);
                    }catch (NumberFormatException | DateTimeParseException | EnumValueMakingException e){viewCenter.cerr(e);}
                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }//still not codded...

    private String cutName(String string , int n){
        int i=0;
        for(int k = 0; k< n; k++){
            i = string.indexOf(" ",i+1);
        }return string.substring(i);
    }

    public void deleteFood(String string) {
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(  person.isUser()){
                if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
                    orderPiece = string.split("\\s+");
                    if(((User)person).doesFoodExistInMyRestaurant(orderPiece[2])){
                        if(((User)person).canDeleteOrDeactivateFood(orderPiece[2])){
                            ((User)person).deleteFood(orderPiece[2]);
                            viewCenter.cout(FoodMassage.DELETED);
                        }else viewCenter.cout(FoodMassage.CANNOT_DELETE);
                    }else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }

    public void deactivateFood(String string) {
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(  person.isUser()){
                if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
                    orderPiece = string.split("\\s+");
                    if(((User)person).doesFoodExistInMyRestaurant(orderPiece[2])){
                        if(((User)person).canDeleteOrDeactivateFood(orderPiece[2])){
                            ((User)person).deactivateFood(orderPiece[2]);
                            viewCenter.cout(FoodMassage.DEACTIVATED);
                        }else viewCenter.cout(FoodMassage.CANNOT_DEACTIVATE);
                    }else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }

    public void activateFood(String string) {
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(  person.isUser()){
                if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
                    if(((User)person).doesFoodExistInMyRestaurant(orderPiece[2])){
                        orderPiece = string.split("\\s+");
                        ((User)person).activateFood(orderPiece[2]);
                        viewCenter.cout(FoodMassage.ACTIVATED);
                    }else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
                }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void discountFood(String string){
        orderPiece = string.split("\\s+");
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(  person.isUser()){
                if(onlinePlace==OnlinePlace.MY_FOOD_MENU){
                    if(!((User)person).doesFoodHaveDiscount()){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:HH:mm:ss");
                        LocalDateTime localDateTime = LocalDateTime.parse(orderPiece[3],formatter);
                        if(((User)person).discountFood(Integer.parseInt(orderPiece[2]),localDateTime)){
                            viewCenter.cout(FoodMassage.SUCCESSFUL_DISCOUNT);
                        }else viewCenter.cout(FoodMassage.NOT_SUCCESSFUL_DISCOUNT);
                    }else viewCenter.cout(FoodMassage.HAS_DISCOUNT);
                }else viewCenter.cout(UserMassages.NOT_IN_FOOD_MENU);
            }else viewCenter.cout(UserMassages.NOT_USER);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }

    public void searchRestaurant(String string){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(onlinePlace==OnlinePlace.MAIN_MENU){
                orderPiece=string.split("\\s+");
                viewCenter.showArraylist(restaurantsData.searchRestaurant(orderPiece[2]));
            }else viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }

    public void selectRestaurant(String string) {
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=string.split("\\s+");
            if(restaurantsData.doesRestaurantExist(orderPiece[2])){
                outerRestaurant=restaurantsData.giveRestaurant(orderPiece[2]);
                onlinePlace=OnlinePlace.OUT_RESTAURANT;
                viewCenter.cout(RestaurantMassages.RESTAURANT_SELECTED);
            }else viewCenter.cout(UserMassages.NO_RESTAURANT_FOUND);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void showMenu(){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(onlinePlace==OnlinePlace.OUT_RESTAURANT){
                viewCenter.showArraylist(outerRestaurant.giveFoodsForUser());
            }viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void selectFood(String string){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(onlinePlace==OnlinePlace.OUT_RESTAURANT){
                orderPiece=string.split("\\s+");
                if(outerRestaurant.doesFoodExist(orderPiece[2])){
                    outerRestaurant.selectFood(orderPiece[2]);
                    onlinePlace=OnlinePlace.OUT_FOOD_MENU;
                    viewCenter.cout(RestaurantMassages.SELECT_FOOD);
                }else viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);
            }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void displayComments(){
        if(onlinePlace!=OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            if(onlinePlace==OnlinePlace.OUT_RESTAURANT){
                viewCenter.showArraylist(outerRestaurant.giveComments());
            }else viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);
        }else viewCenter.cout(UserMassages.NOT_LOGGED_IN);
    }
    public void addComment(){
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace==OnlinePlace.MY_FOOD_MENU){//check if user is not owner
            viewCenter.cout(FoodMassage.IS_OWNER);return;
        }if(onlinePlace!=OnlinePlace.OUT_RESTAURANT){
            viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);return;
        }
        viewCenter.cout(FoodMassage.ENTER_COMMENT);
        do{
            StringBuilder comment = inputReceiver.getComment();
            if(comment.length()>10){
                Comment comment1 = new Comment(comment,idServer.createID(TypeOfID.COMMENT),person.giveID(),this.outerRestaurant.giveIDAsID());
                this.outerRestaurant.addComment(comment1);
                commentServer.add(comment1);
                viewCenter.cout(FoodMassage.COMMENT_ADDED);
                break;
            }else viewCenter.cout(FoodMassage.COMMENT_LENGTH);
        }while(true);
    }
    public void editComment(String string) {
        orderPiece=string.split("\\s+");
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if (onlinePlace!=OnlinePlace.OUT_FOOD_MENU){
            viewCenter.cout(UserMassages.NOT_IN_FOOD_MENU);return;
        }if(!this.outerRestaurant.doesCommentExist(orderPiece[2])){
            viewCenter.cout(FoodMassage.COMMENT_NOT_FOUND);return;
        }if(!this.outerRestaurant.canEditComment(orderPiece[2])){
            viewCenter.cout(FoodMassage.COMMENT_LIMIT);return;
        }viewCenter.cout(FoodMassage.ENTER_COMMENT);
        do{
            StringBuilder newComment = inputReceiver.getComment();
            if(newComment.length()>10){
                if(person!=null){
                    this.outerRestaurant.editComment(orderPiece[2],newComment);break;
                }
            }else viewCenter.cout(FoodMassage.COMMENT_LENGTH);
        }while (true);
        viewCenter.cout(FoodMassage.COMMENT_EDITED);
    }
    public void displayRating(){
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;}
        if(onlinePlace!=OnlinePlace.OUT_RESTAURANT){
            viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);return;}
        viewCenter.showArraylist(this.outerRestaurant.displayRating());
    }
    public void submitRating(String string){
        orderPiece=string.split("\\s+");
        if (onlinePlace== OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;}
        if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
            viewCenter.cout(RestaurantMassages.RATING_YOURSELF);return;}
        if(onlinePlace!=OnlinePlace.OUT_RESTAURANT){
            viewCenter.cout(UserMassages.NOT_IN_RESTAURANT_MENU);return;}
        this.outerRestaurant.appendRating(Rating.valueOf(orderPiece[2].toUpperCase()));
        ratingServer.addRating(this.outerRestaurant.giveID(),this.person.giveID(),Rating.valueOf(orderPiece[2].toUpperCase()).giveRating());
        viewCenter.cout(RestaurantMassages.RATED);
    }
    public void addThisFoodToCart() {
        if (onlinePlace== OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;}
        if(onlinePlace==OnlinePlace.MY_FOOD_MENU){
            viewCenter.cout(RestaurantMassages.BUIING_YOURSELF);return;}
        if(onlinePlace!=OnlinePlace.OUT_FOOD_MENU){
            viewCenter.cout(FoodMassage.BE_INSIDE);}
        person.addToCart(this.outerRestaurant.giveSelectedFood());
        viewCenter.cout(FoodMassage.FOOD_ADDD_TO_CART);
    }

    public void showMyCart() {
        if (onlinePlace== OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;}
        viewCenter.showArraylist(person.getFoodOfCart());
    }
    public void back(){
        if(onlinePlace==OnlinePlace.OUT_FOOD_MENU){
            onlinePlace=OnlinePlace.OUT_RESTAURANT;
        }else if(onlinePlace==OnlinePlace.MY_FOOD_MENU){
            onlinePlace=OnlinePlace.MY_RESTAURANT_MENU;
        }else if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
            onlinePlace=OnlinePlace.MAIN_MENU;
        }else if(onlinePlace==OnlinePlace.OUT_RESTAURANT){
            onlinePlace=OnlinePlace.MAIN_MENU;
        }
    }

    public void deleteFromCart(String string) {
        orderPiece=string.split("\\s+");
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }if(!person.doesFoodExistInCart(orderPiece[1])){
            viewCenter.cout(RestaurantMassages.FOOD_NOT_FOUND);return;
        }person.deleteFromCart(orderPiece[1]);
    }

    public void setLocation(int parseInt) {
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace==OnlinePlace.MAIN_MENU){
            person.setLocation(parseInt);viewCenter.cout(UserMassages.LOC_SET);return;
        }if(onlinePlace==OnlinePlace.MY_RESTAURANT_MENU){
            ((User)person).setRestaurantLocation(parseInt);viewCenter.cout(RestaurantMassages.LOC_SET);return;
        }viewCenter.cout(GlobalMassage.WRONG_PLACE);
    }

    public void showMyCredit() {
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }viewCenter.cout(String.valueOf(person.giveCart()));
    }

    public void addToCard(String string) {
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }try{
            person.addToCreditCard(Integer.parseInt(string.split("\\s+")[1]));
            viewCenter.cout(UserMassages.CARD_ADDED);
        }catch (Exception e){
            viewCenter.cout(GlobalMassage.INVALID);
        }
    }

    public void purchase() {
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;}
        if(!person.doesHveEnoughMoney()){
            viewCenter.cout(UserMassages.EMPTY_CART);return;
        }if(!person.doesHaveLoc()){
            viewCenter.cout(UserMassages.NO_LOC);return;
        }person.bought(getRoot(person.puchase(),person.getLoc()),idServer.createID(TypeOfID.ORDER));
        viewCenter.cArray(getRoot(person.puchase(),person.getLoc()));
    }
    private ArrayList<ArrayList<Integer>> getRoot(ArrayList<String> ids , Integer destination){
        ArrayList<Integer> integers = restaurantsData.giveRestaurants(ids);
        ArrayList<ArrayList<Integer>> root = new ArrayList<>();
        for(int i= 0; i<integers.size()-1;i++){
            root.add(locationServer.showTheShortestWay(integers.get(i),integers.get(i+1)));
        }root.add(locationServer.showTheShortestWay(integers.get(integers.size()-1),destination));
        return root;
    }

    public void showHistory() {
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }viewCenter.showArraylist(person.showHistory());
    }
    public void createPostman(String string){
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=string.split("\\s+");
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z]+[0-9]*[A-z]+")){
                    if(usersData.userIndex(orderPiece[2])==-1){
                        if (orderPiece[3].length() > 6) {
                            if (orderPiece[3].matches("[A-z0-9@#$&]+")) {
                                postmanData.createPostman(orderPiece[2], orderPiece[3],idServer.createID(TypeOfID.POSTMAN));
                                viewCenter.cout(UsernameCreationEnum.CORRECT_USERNAME);
                                viewCenter.cout(PasswordCreationEnum.PASSWORD_CORRECT);
                                viewCenter.cout(SuccessOrErrorInCreation.SUCCESS);
                            } else viewCenter.cout(PasswordCreationEnum.SYNTAX_ERROR);
                        } else viewCenter.cout(PasswordCreationEnum.LENGTH_ERROR);
                    }else viewCenter.cout(UsernameCreationEnum.USED_USERNAME);
                }else viewCenter.cout(UsernameCreationEnum.SYNTAX_ERROR);
            }else viewCenter.cout(UsernameCreationEnum.LENGTH_ERROR);
        }else viewCenter.cout(SuccessOrErrorInCreation.NOT_IN_CREATION_PANEL);
    }
    public void loginPostman(String order){
        if(onlinePlace == OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            orderPiece=order.split("\\s+");
            int index=postmanData.postmanIndex(orderPiece[2]);
            if(orderPiece[2].length()>=5){
                if(orderPiece[2].matches("[A-z0-9]+")){
                    if(index!=-1){
                        if(orderPiece[3].length()>6){
                            if(orderPiece[3].matches("[A-z0-9@#$&]+")){
                                if(adminsData.checkPassword(orderPiece[2],orderPiece[3])){
                                    person=postmanData.givePoster(index);
                                    onlinePlace=OnlinePlace.MAIN_MENU;
                                    viewCenter.cout(LogeIn.SUCCESS);
                                }else viewCenter.cout(PasswordLogin.WRONG_PASSWORD);
                            }else viewCenter.cout(PasswordLogin.SYNTAX_ERROR);
                        }else viewCenter.cout(PasswordLogin.LENGTH_ERROR);
                    }else viewCenter.cout(UsernameLogin.NOT_FOUND);
                }else viewCenter.cout(UsernameLogin.SYNTAX_ERROR);
            }else viewCenter.cout(UsernameLogin.LENGTH_ERROR);
        }else viewCenter.cout(LogeIn.NOT_NULL_USER);
    }
    public void showActiveOrders(){
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }if(person.isAdmin()|| person.isUser()){
            viewCenter.cout(UserMassages.NOT_POSTMAN);return;
        }viewCenter.showArraylist(orderServer.giveActiveOrders());
    }
    public void selectOrder(String order){
        orderPiece=order.split("\\s+");
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }if(person.isAdmin()|| person.isUser()){
            viewCenter.cout(UserMassages.NOT_POSTMAN);return;
        }if(!orderServer.doesExist(orderPiece[2])){
            viewCenter.cout(PostmanMessages.NOT_FOUND);return;
        }((PostMan)person).getOrder(orderServer.giveOrder(orderPiece[2]));
        viewCenter.cArray(((PostMan) person).giveActiveOrder().giveRoot());
    }
    public void showMyPostOrder(){
        if(onlinePlace==OnlinePlace.LOGOUT_LOGIN_CREATION_MENU){
            viewCenter.cout(UserMassages.NOT_LOGGED_IN);return;
        }if(onlinePlace!=OnlinePlace.MAIN_MENU){
            viewCenter.cout(UserMassages.NOT_IN_MAIN_MENU);return;
        }if(person.isAdmin()|| person.isUser()){
            viewCenter.cout(UserMassages.NOT_POSTMAN);return;
        }viewCenter.cArray(((PostMan) person).giveActiveOrder().giveRoot());
    }
}