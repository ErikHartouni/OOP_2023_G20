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
        controller =new Controller();
    }

    public void run(){
        while(shallContinue){
            order=scanner.nextLine();
            order = order.trim();
            if(order.equals("end"))
                shallContinue=false;
            controller.manage(order);
        }
    }

}
