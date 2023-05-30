package Controller;

import Model.MainBrain.ActionManager;

public class Controller {
    private final InputAnalyzer inputAnalyzer;
    private InputType inputType;
    static ActionManager actionManager;
    private Boolean isInCheckPoint;

    public Controller(){
        inputAnalyzer=new InputAnalyzer();
        actionManager = new ActionManager();
        isInCheckPoint = false;
    }

    private void analyzeInput(String input){
        this.inputType = inputAnalyzer.analyze(input);
    }
    public void manage(String string){
        analyzeInput(string);
        if(!isInCheckPoint){
            switch (inputType) {
                case USER_CREATION -> actionManager.createUser(string);
                case ADMIN_CREATION -> actionManager.createAdmin(string);
                case USER_LOGIN -> actionManager.loginUser(string);
                case ADMIN_LOGIN -> actionManager.loginAdmin(string);
                case LOGOUT -> actionManager.logout();
                case SHOW_MY_RESTAURANTS -> actionManager.showMyRestaurants();
                case CREATE_RESTAURANT -> actionManager.createRestaurant(string);
                case CHOSE_MY_RESTAURANT -> actionManager.choseMyRestaurant(string);
                case SHOW_MY_FOODS -> actionManager.showMyFoods();
                case CHOSE_MY_FOOD -> actionManager.choseMyFood(string);
                case EDIT_RESTAURANT_TYPE -> isInCheckPoint = actionManager.editRestaurantType(string);
                case EDIT_FOOD_NAME -> actionManager.editFoodName(string);
                case EDIT_FOOD_PRICE -> actionManager.editFoodPrice(string);
                case ADD_FOOD -> actionManager.addFood(string);
                case END -> actionManager.end();
                case INVALID_ORDER -> actionManager.invalid();
            }
        }else{
            switch (inputType){
                case YES -> actionManager.booleanManager(true);
                case NO -> isInCheckPoint = false;
                default -> actionManager.invalid();
            }
        }
    }

}
