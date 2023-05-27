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
        patternArrayList.add(Pattern.compile(""));
        patternArrayList.add(Pattern.compile(""));
    }
    InputType analyze(String input){
        matcherArrayList.clear();
        for(int i=0;i<patternArrayList.size();i++){
            matcherArrayList.add(patternArrayList.get(i).matcher(input));
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
        } else if (input.matches("^end$")) {
            inputType = InputType.END;
        } else {
            inputType = InputType.INVALID_ORDER;
        }return inputType;


    }
}
