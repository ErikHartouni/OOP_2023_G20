package Model.Users.UserClasses;

import Model.RestaurantClasses.Order;

import java.time.LocalTime;
import java.util.ArrayList;

public class CreditCard {
    private Integer charge;
    private ArrayList<Order> orders;
    private LocalTime time;
    public CreditCard(int money){
        this.charge =money;
    }
    public int getMoney() {
        return this.totalMoney();
    }
    public void charge(int increaseCharge){
        this.charge+=increaseCharge;
    }

    public Integer getCharge() {
        return this.charge;
    }
    public void removeAllOrders(){
        for (int i=0; i<orders.size(); i++)
            this.orders.remove(i);
    }
    public ArrayList<Order> getOrders() {
        return this.orders;
    }
    public void addOrder(Order newOrder) {
        this.orders.add(newOrder);
    }
    public int totalMoney(){
        int totalMoney=0;
        for (int i=0; i<orders.size(); i++)
            totalMoney+=this.orders.get(i).getFood().getPrice();
        return totalMoney;
    }
    public void displayCartStatus() {
        this.getOrders();
        this.getMoney();
    }
}
