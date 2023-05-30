package View;

import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.EnumMassage;
import View.Enums.Global.GlobalMassage;

import java.util.ArrayList;

public class ViewCenter {

    public <T extends Enum<T> & EnumMassage> void cout(T t){
        System.out.println(t.giveMassage());
    }
    public <T extends ArrayList<E>,E> void showArraylist(T list){
        if(!list.isEmpty()){
            if(list.get(0) instanceof Restaurant){
                for(E restaurant : list){
                    System.out.println(((Restaurant)restaurant).print());
                }
            }else if (list.get(0) instanceof Food){
                for(E food : list){
                    System.out.println(((Food)food).show());
                }
            }
        }else cout(GlobalMassage.EMPTY);
    }

    public <T extends Exception> void cerr(T t){
        System.out.println(t.toString());
    }
}
