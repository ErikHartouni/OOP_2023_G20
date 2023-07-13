package Model.DataServer;

import Model.RestaurantClasses.Order;

import java.util.ArrayList;

public class OrderServer {
    private ArrayList<Order> orders;
    public OrderServer(){
        orders=new ArrayList<>();
    }
    public void add(Order order){
        orders.add(order);
    }

    public ArrayList<Order> giveActiveOrders(){
        ArrayList<Order> ans=new ArrayList<>();
        for(Order order : orders){
            if(order.isActive())
                ans.add(order);
        }return ans;
    }
    public boolean doesExist(String id){
        for(Order order : orders){
            if(order.givID().show().equals(id))
                return true;
        }return false;
    }
    public Order giveOrder(String id){
        for(Order order : orders){
            if(order.givID().show().equals(id))
                return order;
        }return null;
    }

    public void load() {
    }

    public int giveNum() {
        return this.orders.size();
    }
}
