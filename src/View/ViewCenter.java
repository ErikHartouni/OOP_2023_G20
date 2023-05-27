package View;

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
            }
        }else cout(GlobalMassage.EMPTY);
    }
}
