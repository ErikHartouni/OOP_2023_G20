package Model.DataServer;

import Model.RestaurantClasses.Restaurant;
import Others.Interfaces.RestaurantDataServerActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RestaurantSaver implements RestaurantDataServerActions {
    private ArrayList<Restaurant> restaurants;
    public RestaurantSaver(){
        restaurants= new ArrayList<>();
    }

    public void addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
    }
    @Override
    public ArrayList<Restaurant> searchRestaurant(String name){
        ArrayList<Restaurant> ans = new ArrayList<>() , restaurantsWithSimilarity = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            if(restaurant.getName().equals(name))
                ans.add(restaurant);
            else if (restaurant.getName().contains(name))
                restaurantsWithSimilarity.add(restaurant);
        }
        //sort answers
        ans.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        restaurantsWithSimilarity.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //merge answers
        ans.addAll(restaurantsWithSimilarity);
        return ans;
    }

    @Override
    public Boolean doesRestaurantExist(String id) {
        return null;
    }

    @Override
    public Restaurant giveRestaurant(String id) {
        return null;
    }


}
