import Model.DataServer.UsersSaver;
import Model.MainBrain.ActionManager;
import View.Enums.Global.GlobalMassage;
import View.InputReceiver;

import java.util.Scanner;

public class Main {
    static {
        Boolean x=true;
        try{
            ActionManager.loadInformation();
        }catch (Exception e){
            System.out.println(GlobalMassage.STH_WENT_WRONG);e.printStackTrace();
            x=false;
        }
        if(x)
            System.out.println(GlobalMassage.START.giveMassage());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputReceiver inputReceiver = new InputReceiver(scanner , true);
        inputReceiver.run();boolean x=true;
        try{
        ActionManager.saveInformation();}
        catch (Exception e){
            System.out.println("sth went wrong in saving info");e.printStackTrace();x=false;
        }if(x) System.out.print("saved successfully !!!");

    }
}