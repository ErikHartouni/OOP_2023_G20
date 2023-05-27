package Controller;

import Model.MainBrain.ActionManager;

public class Controller {
    private InputAnalyzer inputAnalyzer;
    private InputType inputType;
    static ActionManager actionManager;

    public Controller(){
        inputAnalyzer=new InputAnalyzer();
        actionManager = new ActionManager();
    }

    private void analyzeInput(String input){
        this.inputType = inputAnalyzer.analyze(input);
    }
    public void manage(String string){
        analyzeInput(string);
        switch (inputType){
            case USER_CREATION -> actionManager.createUser(string);
            case ADMIN_CREATION -> actionManager.createAdmin(string);
            case USER_LOGIN -> actionManager.loginUser(string);
            case ADMIN_LOGIN -> actionManager.loginAdmin(string);
            case LOGOUT -> actionManager.logout();
            case SHOW_MY_RESTAURANTS -> actionManager.showMyRestaurants();

            case END -> actionManager.end();
            case INVALID_ORDER -> actionManager.invalid();
        }
    }

}
