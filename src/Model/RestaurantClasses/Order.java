package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;

import java.util.ArrayList;

public class Order {
    private ArrayList<Food> foods;
    private ID buyer,poster , orderID;
    private boolean activation;
    private ArrayList<ArrayList<Integer>>postRoot;
    public Order (ArrayList<Food> foods, ID buyer,ID orderID,ArrayList<ArrayList<Integer>>postRoot){
        this.orderID=orderID;
        activation=true;
        this.foods=foods;
        this.buyer=buyer;
        this.postRoot=postRoot;
    }

    public Boolean contains(Food food){
        return foods.contains(food);
    }

    public ArrayList<String> giveFormation() {
        ArrayList<String> id = new ArrayList<>();id.add(foods.get(0).giveRestaurantID()); Boolean bool;
        for(Food food : foods){
            bool=false;
            for(String str : id){
                if(food.giveRestaurantID().equals(str))
                    bool=true;
            }
            if(!bool)
                id.add(food.giveRestaurantID());
        }
        return id;
    }
    public ArrayList<String> giveString(){
        ArrayList<String>ans = new ArrayList<>();
        for(Food food : foods){
            ans.add(food.toString());
        }
        if(poster!=null)ans.add("poster : "+poster);else ans.add("no poster yet");
        return ans;
    }

    public void givePoster(ID id) {
        this.poster=id;
    }

    public boolean isActive() {
        return activation;
    }

    public ID givID() {
        return this.orderID;
    }

    public ArrayList<ArrayList<Integer>> giveRoot() {
        return this.postRoot;
    }


}
