import View.InputReceiver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputReceiver inputReceiver = new InputReceiver(scanner , true);
        inputReceiver.run();
    }
}