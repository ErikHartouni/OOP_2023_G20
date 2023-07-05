import View.Enums.Global.GlobalMassage;
import View.InputReceiver;

import java.util.Scanner;

public class Main {
    static {
        System.out.println(GlobalMassage.START);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputReceiver inputReceiver = new InputReceiver(scanner , true);
        inputReceiver.run();
    }
}