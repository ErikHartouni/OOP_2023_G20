package Controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputAnalyzer {
    private InputType inputType;
    private ArrayList<Pattern> patternArrayList;
    private ArrayList<Matcher> matcherArrayList;
    InputAnalyzer(){
        matcherArrayList=new ArrayList<>();
        patternArrayList = new ArrayList<>();
        patternArrayList.add(Pattern.compile("^ADD\\s+USER\\s+[!-z]+\\s+[!-z]+$"));//0
        patternArrayList.add(Pattern.compile("^ADD\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^LOGIN\\s+USER\\s+[!-z]+\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^LOGIN\\s+ADMIN\\s+[!-z]+\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^LOGOUT$"));
        patternArrayList.add(Pattern.compile("^SHOW\\s+MY\\s+RESTAURANTS$"));//5
        patternArrayList.add(Pattern.compile("^CREATE\\s+RESTAURANT\\s+[!-z]+\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^SHOW\\s+MY\\s+RESTAURANTS$"));
        patternArrayList.add(Pattern.compile("^CHOSE\\s+MY\\s+RESTAURANT\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^SHOW\\s+MY\\s+FOODS$"));
        patternArrayList.add(Pattern.compile("^CHOSE\\s+MY\\s+FOOD\\s+[!-z]+$"));//10
        patternArrayList.add(Pattern.compile("^EDIT\\s+MY\\s+RESTAURANT\\s+TYPE\\s+TO\\s+[!-z]$"));
        patternArrayList.add(Pattern.compile("^YES$"));
        patternArrayList.add(Pattern.compile("^NO$"));
        patternArrayList.add(Pattern.compile("^EDIT\\s+FOOD\\s+NAME\\s+[!-z]+$"));
        patternArrayList.add(Pattern.compile("^ADD\\s+FOOD\\s+[!-z]+\\s+[0-9]+\\s+[!-z]+\\s+[0-9]+\\s+[0-9]+$"));//15
        patternArrayList.add(Pattern.compile("^DELETE\\s+FOOD\\s+[A-z0-9]$"));
        patternArrayList.add(Pattern.compile("^DEACTIVATE\\s+FOOD\\s+[A-z0-9]+$"));
        patternArrayList.add(Pattern.compile("^ACTIVATE\\s+FOOD\\s+[A-z0-9]+$"));
        patternArrayList.add(Pattern.compile("^ADD\\s+DISCOUNT\\s+[0-9]+\\s+[0-9]+:[0-9]+:[0-9]+:[0-9]+:$"));
        patternArrayList.add(Pattern.compile("^SEARCH\\s+RESTAURANT\\s+[A-z]+$"));//20
        patternArrayList.add(Pattern.compile("^SELECT\\s+RESTAURANT\\s+[!-Z]+$"));

    }
    InputType analyze(String input){
        matcherArrayList.clear();
        for (Pattern pattern : patternArrayList) {
            matcherArrayList.add(pattern.matcher(input));
        }
        if(matcherArrayList.get(1).matches()){
            inputType = InputType.ADMIN_CREATION;
        }else if (matcherArrayList.get(0).matches()){
            inputType = InputType.USER_CREATION;
        } else if (matcherArrayList.get(3).matches()) {
            inputType = InputType.ADMIN_LOGIN;
        } else if (matcherArrayList.get(2).matches()) {
            inputType = InputType.USER_LOGIN;
        } else if (matcherArrayList.get(4).matches()){
            inputType = InputType.LOGOUT;
        } else if (matcherArrayList.get(5).matches()){
            inputType = InputType.SHOW_MY_RESTAURANTS;
        } else if (matcherArrayList.get(6).matches()) {
            inputType = InputType.CREATE_RESTAURANT;
        } else if(matcherArrayList.get(7).matches()){
            inputType = InputType.SHOW_MY_FOODS;
        }else if(matcherArrayList.get(8).matches()){
            inputType = InputType.CHOSE_MY_RESTAURANT;
        } else if (matcherArrayList.get(9).matches()) {
            inputType = InputType.SHOW_MY_FOODS;
        } else if (matcherArrayList.get(10).matches()) {
            inputType = InputType.CHOSE_MY_FOOD;
        } else if (matcherArrayList.get(11).matches()) {
            inputType = InputType.EDIT_RESTAURANT_TYPE;
        } else if (matcherArrayList.get(12).matches()) {
            inputType = InputType.YES;
        } else if (matcherArrayList.get(13).matches()) {
            inputType = InputType.NO;
        } else if (matcherArrayList.get(14).matches()) {
            inputType = InputType.EDIT_FOOD_NAME;
        } else if (matcherArrayList.get(15).matches()) {
            inputType = InputType.ADD_FOOD;
        } else if (matcherArrayList.get(16).matches()) {
            inputType = InputType.DELETE_FOOD;
        } else if (matcherArrayList.get(17).matches()) {
            inputType = InputType.DEACTIVATE_FOOD;
        } else if (matcherArrayList.get(18).matches()) {
            inputType = InputType.ACTIVATE_FOOD;
        } else if (matcherArrayList.get(19).matches()) {
            inputType = InputType.DISCOUNT_FOOD;
        } else if (matcherArrayList.get(20).matches()) {
            inputType = InputType.SEARCH_RESTAURANT;
        } else if (matcherArrayList.get(21).matches()) {
            inputType = InputType.SELECT_RESTAURANT;
        } else if (input.matches("^end$")) {
            inputType = InputType.END;
        } else {
            inputType = InputType.INVALID_ORDER;
        }return inputType;


    }
}
