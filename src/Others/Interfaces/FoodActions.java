package Others.Interfaces;

import java.util.ArrayList;

public interface FoodActions {
    void activate();
    void deactivate();
    Boolean doesHaveDiscount();
    String getName();
    void setDiscount(int discount);
    ArrayList<String> displayRating();//will give a string that will have number of every rating
    String show();


}
