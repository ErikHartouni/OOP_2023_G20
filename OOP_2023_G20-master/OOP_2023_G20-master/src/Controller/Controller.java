package Controller;

import Model.MainBrain.ActionManager;
import View.InputReceiver;

public class Controller {
    static ActionManager actionManager;

    public Controller(InputReceiver inputReceiver){
        actionManager = new ActionManager(inputReceiver);
    }
    private InputType analyzeInput(String string){
        for(InputType input: InputType.values()){
            if(input.matches(string))
                return input;
        }
        return InputType.INVALID_ORDER;
    }
    public void manage(String string){

        switch (analyzeInput(string)) {
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
            case EDIT_RESTAURANT_TYPE -> actionManager.editRestaurantType(string);
            case EDIT_FOOD_NAME -> actionManager.editFoodName(string);
            case EDIT_FOOD_PRICE -> actionManager.editFoodPrice(string);
            case ADD_FOOD -> actionManager.addFood(string);
            case DELETE_FOOD -> actionManager.deleteFood(string);
            case DEACTIVATE_FOOD -> actionManager.deactivateFood(string);
            case ACTIVATE_FOOD -> actionManager.activateFood(string);
            case DISCOUNT_FOOD -> actionManager.discountFood(string);
            case SEARCH_RESTAURANT -> actionManager.searchRestaurant(string);
            case SELECT_RESTAURANT -> actionManager.selectRestaurant(string);
            case SHOW_MENU -> actionManager.showMenu();
            case SELECT_FOOD -> actionManager.selectFood(string);
            case DISPLAY_COMMENTS -> actionManager.displayComments();
            case ADD_COMMENT -> actionManager.addComment();
            case EDIT_COMMENT -> actionManager.editComment(string);
            case END -> actionManager.end();
            case INVALID_ORDER -> actionManager.invalid();
        }

    }

}
