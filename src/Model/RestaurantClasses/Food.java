package Model.RestaurantClasses;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Types.FoodType;
import java.time.LocalTime;

public class Food {
    private FoodType foodType;
    private String foodName;
    private LocalTime timeToMake;
    private Integer price , offPercent;
    private ID foodID;
    private Integer VG , G , M , B , VB , discountRate;
    private Boolean discount, activation;//true if active

}
