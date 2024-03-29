package View;

import Controller.Controller;

import java.util.Scanner;

public class InputReceiver {
    private Scanner scanner;
    private Boolean shallContinue;
    private String order;
    private Controller controller;

    public InputReceiver(Scanner scanner , Boolean shallContinue){
        this.scanner=scanner;
        this.shallContinue=shallContinue;
        controller =new Controller(this);
    }

    public void run(){
        while(shallContinue){
            try{
            order=scanner.nextLine();
            order = order.trim();
            if(order.equals("end"))
                shallContinue=false;
            controller.manage(order);}catch (Exception e){
                System.out.println("sth went wrong");e.printStackTrace();
            }
        }
    }
    public String getYesOrNo(){
        return scanner.nextLine();
    }
    public StringBuilder getComment(){
        return new StringBuilder(scanner.nextLine());
    }

}
