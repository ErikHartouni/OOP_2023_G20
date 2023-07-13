package View;

import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.EnumMassage;
import View.Enums.Global.GlobalMassage;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewCenter {

    public <T extends Enum<T> & EnumMassage> void cout(T t){
        System.out.println(t.giveMassage());
    }
    public void cout(String string){//credit card
        System.out.println(string);
    }
    public <T extends ArrayList<E>,E> void showArraylist(T list){
        if(!list.isEmpty()){
            if(list.get(0) instanceof Restaurant){
                for(E restaurant : list)
                    System.out.println(((Restaurant)restaurant).print());
            }else if (list.get(0) instanceof Food){
                for(E food : list)
                    System.out.println(((Food)food).show());
            }else if (list.get(0) instanceof Comment){
                for (E comment : list)
                    System.out.println(((Comment)comment).show());
            }else if (list.get(0)instanceof Order){
                for(E order : list)
                    System.out.println(((Order)order).giveString());
            } else if (list.get(0) instanceof String) {
                for(E str : list)
                    System.out.println(str);
            }
        }else cout(GlobalMassage.EMPTY);
    }

    public <T extends Exception> void cerr(T t){
        System.out.println(t.toString());
    }

    public void cArray(ArrayList<ArrayList<Integer>> root) {
        if(root==null){
            System.out.println("empty");return;
        }
        for(ArrayList<Integer> arrayList : root){
            System.out.println(arrayList);
        }
    }
}
