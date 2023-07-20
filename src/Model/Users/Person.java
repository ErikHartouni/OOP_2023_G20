package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Restaurant;
import Model.Users.UserClasses.CreditCard;
import Model.Users.UserClasses.Massage;
import Others.Interfaces.UserActions;

import java.util.ArrayList;

public abstract class Person implements UserActions {
    protected int numberOfGraph=-1;
    protected String username , password;
    protected CreditCard creditCard;
    protected ArrayList<Massage> massages;
    protected ArrayList<Food> cart;
    protected Boolean isAdmin , isPoster;
    protected ID id;
    protected String cartString,shoppingCartSaver;
    protected ArrayList<Order> orders;


    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        return null;
    }
    @Override
    public String sayMyName(){
        return username;
    }


    public Boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
    public Boolean isUser(){
        return (!isAdmin && !isPoster);
    }
    public Boolean isAdmin(){
        return isAdmin;
    }
    public void addToCart(Food food){
        this.cart.add(food);
    }
    public ArrayList<Food> getFoodOfCart(){
        return cart;
    }
    public ID giveID(){
        return this.id;
    }
    public String giveName(){
        return username;
    }
    public String givePass(){
        return password;
    }
    public int giveCart(){
        return this.creditCard.giveMoney();
    }
    public String giveMessages(){
        return null;
    }
    public String giveByuCart(){
        return null;
    }
    public void getMessageFromServer(Massage massage){
        massages.add(massage);
    }


    public boolean doesFoodExistInCart(String s) {
        for(Food food : cart){
            if(food.getID().equals(s))
                return true;
        }return false;
    }

    public void deleteFromCart(String s) {
        for(int i=0;i<cart.size();i++){
            if(cart.get(i).getID().equals(s)){
                cart.remove(i);
                for(int j=i;j<cart.size()-1;j++){
                    cart.add(j,cart.get(i));
                }
                break;
            }

        }
    }

    public void setLocation(int parseInt) {
        numberOfGraph=parseInt;
    }

    public void addToCreditCard(int parseInt) {
        this.creditCard.add(parseInt);
    }

    public boolean isShoppingCartEmpty() {
        return creditCard.isEmpty();
    }
    public int getCredit(){
        return creditCard.giveMoney();
    }
    public boolean doesHveEnoughMoney(){
        int num =0;
        for(Food food:this.cart){
            num+=food.getPrice();
        }return num<=creditCard.giveMoney();
    }

    public boolean doesHaveLoc() {
        return numberOfGraph!=-1;
    }

    public ArrayList<String> puchase() {
        int num =0;
        for(Food food:this.cart){
            num+=food.getPrice();
        }creditCard.add(-num);
        Order order= new Order (cart,id,null,null);
        orders.add(order);
        return order.giveFormation();
    }

    public Integer getLoc() {
        return this.numberOfGraph;
    }

    public ArrayList<ArrayList<String>> showHistory() {
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for(Order order : orders){
            ans.add(order.giveString());
        }
        return ans;
    }

    public void bought(ArrayList<ArrayList<Integer>> root,ID orderID) {
        orders.add(new Order(cart,this.id,orderID,root));
        cart=new ArrayList<>();
    }
    public String giveShoppingCart(){
        String str="" ;
        for(Food food : cart)
            str+=food.getID();
        return str;
    }
    public void reloadShoppingCart(ArrayList<Food>foods){
        String[] ids = shoppingCartSaver.split("#");
        for(String str : ids){
            for(Food food : foods){
                if(food.getID().equals(str))
                    this.addToCart(food);
            }
        }
    }

}
