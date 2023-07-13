package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Order;
import Model.Users.UserClasses.CreditCard;
import Others.Interfaces.PostManActions;

import java.util.ArrayList;

public class PostMan  extends Person {

    public PostMan(String username , String password , ID id){
        this.username = username ;
        this.password = password;
        this.id=id;
        super.isAdmin=false;
        super.isPoster=true;
        super.cart=new ArrayList<>();
        super.creditCard=new CreditCard(0);
        super.orders = new ArrayList<>();
    }
    public void getOrder(Order order){
        order.givePoster(this.id);
        this.orders.add(order);
    }

    @Override
    public void addComment(Comment comment) {

    }
    public Order giveActiveOrder(){
        if(orders.size()!=0) return orders.get(orders.size()-1);
        return new Order(null,null,null,null);
    }

}
