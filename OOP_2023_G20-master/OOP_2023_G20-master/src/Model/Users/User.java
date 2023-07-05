package Model.Users;

import Model.DataServer.IDHandler.ID;
import Model.RestaurantClasses.Comment;
import Model.RestaurantClasses.Food;
import Model.RestaurantClasses.Order;
import Model.RestaurantClasses.Restaurant;
import Model.RestaurantClasses.Types.FoodType;
import Model.RestaurantClasses.Types.RestaurantType;
import Model.Users.UserClasses.CreditCard;
import Others.Interfaces.RestaurantOwnerActions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person implements RestaurantOwnerActions {
    private ArrayList < Restaurant > myRestaurants;
    private HashMap<String , Integer> myRestaurantMap;// id & index
    private ID userID;
    private Boolean isInCreditCard;//true means : we are in creditcard section
    private int indexOfMyChosenRestaurant=-1 , indexOfMySelectedFood=-1;
    private Restaurant myRestaurant;
    private ArrayList < Order > history;
    private ArrayList < Order > confirmedOrder;
    private CreditCard creditCard;
    private LocalTime deliveryTime;

    public User(String username , String password , ID id){
        this.username = username ;
        this.password = password;
        this.userID=id;
        myRestaurantMap = new HashMap<>();
        myRestaurants = new ArrayList<>();
    }
    public Boolean doesHaveThisRestaurant(String name){
        for (int i=0; i < this.myRestaurants.size() ;i++)
            if (this.myRestaurants.get(i).getName().equals(name))
                return true;
        return false;
    }


    @Override
    public Boolean isOwner() {
        return this.userID.equals(this.myRestaurant.getOwner());
    }

    @Override
    public ArrayList<Restaurant> giveMyRestaurants() {
        return this.myRestaurants;
    }

    @Override
    public void createRestaurant(String name, User owner, ID id, RestaurantType restaurantType) {
        /* create then add to restaurants
        then user the createRestaurant(Restaurant) method ...*/
        Restaurant restaurant = new Restaurant(name , owner , id , restaurantType);
        this.createRestaurant(restaurant);
    }//ended

    @Override
    public void createRestaurant(Restaurant restaurant) {//add to restaurants and restaurant map ...
        this.myRestaurants.add(restaurant);
        this.myRestaurantMap.put(restaurant.getID(),this.myRestaurants.indexOf(restaurant));
    }
    @Override
    public void chooseRestaurant(String id) {
        for (int i=0; i < this.myRestaurantMap.size() ;i++)
            if (this.myRestaurantMap.containsKey(id))
                this.indexOfMyChosenRestaurant=this.myRestaurantMap.get(id);
    }


    @Override
    public RestaurantType showRestaurantType() {
        return this.myRestaurants.get(indexOfMyChosenRestaurant).showRestaurantType();
    }

    @Override
    public Boolean canChangeMyRestaurantType() {
        if (this.myRestaurant==null)
            return false;
        else if (!this.isOwner())
            return false;
        return this.myRestaurant.canChangeRestaurantType();
    }

    @Override
    public void changeMyRestaurantType(RestaurantType restaurantType) {
        if (this.canChangeMyRestaurantType())
            this.myRestaurant.EditFoodType(restaurantType);
    }

    @Override
    public ArrayList<Food> giveAllFoodsOfMyRestaurant() {
        return this.myRestaurants.get(indexOfMyChosenRestaurant).giveAllFoods();
    } // select menu

    @Override
    public Boolean doesFoodExistInMyRestaurant(String id) {
        return this.myRestaurants.get(indexOfMyChosenRestaurant).doesFoodExist(id);
    }

    @Override
    public void selectFood(String id) {
        if (this.indexOfMyChosenRestaurant!=-1 && this.indexOfMySelectedFood==-1) {
            this.myRestaurants.get(indexOfMyChosenRestaurant).selectFood(id);
            this.indexOfMySelectedFood=this.myRestaurants.get(indexOfMyChosenRestaurant).getIndexOfSelectedFood();
        }
    }

    @Override
    public void editMyRestaurantFoodName(String newName) {
        if (this.myRestaurant!=null && this.indexOfMySelectedFood==-1 && this.isOwner())
            this.myRestaurant.editFoodName(indexOfMySelectedFood, newName);
    }

    @Override
    public void editMyRestaurantFoodPrice(Integer newPrise) {
        if (this.myRestaurant!=null && this.indexOfMySelectedFood==-1 && this.isOwner())
            this.myRestaurant.editFoodPrice(indexOfMySelectedFood, newPrise);
    }

    @Override
    public Boolean doesFoodNameExistsInMyRestaurant(String name) {
        return this.myRestaurants.get(indexOfMyChosenRestaurant).doesFoodNameExists(name);
    }

    @Override
    public void addFoodToMyRestaurant(String name, FoodType foodType , LocalTime time ,
                                      Integer price , Integer discountRate , ID foodID) {
        this.myRestaurant.addFood(name,foodType,time,price,discountRate,foodID);
    }

    @Override
    public void addFoodToMyRestaurant(String name, Integer price) {
        this.myRestaurant.addFood(name,price);
    }

    @Override
    public Boolean canDeleteOrDeactivateFood(String id) {
        if (this.myRestaurant==null || this.indexOfMySelectedFood==-1 || !this.isOwner())
            return false;
        if (this.myRestaurant.canDeleteOrDeactivateFood(id)==-1)
            return false;
        return true;
    }

    @Override
    public void deleteFood(String id) {
        if (this.canDeleteOrDeactivateFood(id))
            this.myRestaurant.deleteFood(id);
    }

    @Override
    public void deactivateFood(String id) {
        if (this.canDeleteOrDeactivateFood(id))
            this.myRestaurant.deactivateFood(id);
    }

    @Override
    public void activateFood(String id) {
        if (this.canDeleteOrDeactivateFood(id))
            this.myRestaurant.activateFood(id);
    }

    @Override
    public Boolean doesFoodHaveDiscount() {
        String foodID=this.myRestaurant.getIDOfSelectedFood(this.indexOfMySelectedFood);
        int check=this.myRestaurant.doesFoodHaveDiscount(foodID);
        if (check==-1)
            return false;
        return true;
    }

    @Override
    public Boolean discountFood(Integer discount, LocalDateTime time) {
        if (this.myRestaurant==null || this.indexOfMySelectedFood==-1 || !this.isOwner())
            return false;
        else if (this.doesFoodHaveDiscount())
            return false;
        else if (discount>50)
            return false;
        String foodID=this.myRestaurant.getIDOfSelectedFood(this.indexOfMySelectedFood);
        this.myRestaurant.discountFood(foodID,discount,time);
        return true;
    }

    @Override
    public ArrayList<String> displayRating() {
        if (this.myRestaurant!=null && this.indexOfMySelectedFood!=-1) {
            return this.myRestaurant.displayRating();
        }
        return null;
    }

    @Override
    public void submitRating(int rating) {
        if (!this.isOwner() && this.indexOfMyChosenRestaurant!=-1 && this.indexOfMySelectedFood!=-1)
            this.myRestaurants.get(indexOfMyChosenRestaurant).submitRating(rating,this.userID.show());
    }

    @Override
    public void editRating(int rating) {
        if (!this.isOwner() && this.indexOfMyChosenRestaurant!=-1 && this.indexOfMySelectedFood!=-1)
            this.myRestaurants.get(indexOfMyChosenRestaurant).editRating(rating,this.userID.show());
    }


    @Override
    public ArrayList<Comment> displayCommentsForRestaurant() {
        return this.myRestaurants.get(indexOfMyChosenRestaurant).displayCommentsForRestaurant();
    }

    @Override
    public ArrayList<Comment> displayComments() {
        return this.myRestaurant.giveComments();
    }

    @Override
    public void addNewResponse(String commentID, String response) {
        if (this.isOwner() && this.myRestaurant!=null && this.indexOfMySelectedFood!=-1)
            if (this.myRestaurant.doesCommentExist(commentID))
                this.myRestaurant.addNewResponse(commentID,response);
    }

    @Override
    public void editResponse(String commentID, String response) {
        if (this.isOwner() && this.myRestaurant!=null && this.indexOfMySelectedFood!=-1)
            if (this.myRestaurant.doesCommentExist(commentID))
                this.myRestaurant.editResponse(commentID,response);
    }

    @Override
    public ArrayList<Order> displayOpenOrders() {
        if (this.myRestaurant==null || this.indexOfMySelectedFood==-1 || !this.isOwner())
            return null;
        return this.myRestaurant.displayOpenOrders();
    }

    @Override
    public void editOrder(String orderID) {
        if (this.isOwner() && this.myRestaurant!=null && this.indexOfMySelectedFood!=-1)
            this.myRestaurant.editOrder(orderID);
    }

    @Override
    public ArrayList<Order> showOrderHistory() {
        if (this.isOwner() && this.myRestaurant!=null && this.indexOfMySelectedFood!=-1)
            return this.myRestaurant.showOrderHistory();
        return null;
    }



    @Override
    public String getID() {
        return this.userID.show();
    }

    @Override
    public Boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }

    @Override
    public ArrayList<Restaurant> searchRestaurant(String restaurantName) {
        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        for (int i=0; i < this.myRestaurants.size() ;i++)
            if (this.myRestaurants.get(i).getName().equals(restaurantName))
                restaurants.add(myRestaurants.get(i));
        return restaurants;
    }

    @Override
    public Boolean canAddCommentForRestetaurant() {
        if (this.isOwner() || this.myRestaurant==null || this.indexOfMySelectedFood!=-1)
            return false;
        return true;
    }

    @Override
    public void addCommentForRestetaurant(Comment comment) {
        if (this.canAddCommentForRestetaurant())
            this.myRestaurant.addCommentForRestetaurant(comment);
    }

    @Override
    public Boolean canEditCommentForRestetaurant(String commentID) {
        if (this.isOwner() || this.myRestaurant==null || this.indexOfMySelectedFood!=-1)
            return false;
        if (!this.myRestaurant.canEditCommentForRestetaurant(this.userID.show(), commentID))
            return false;
        return true;
    }

    @Override
    public void editCommentForRestetaurant(String commentID, StringBuilder newComment) {
        if (this.canEditCommentForRestetaurant(commentID))
            this.myRestaurant.editCommentForRestetaurant(commentID, newComment);
    }

    @Override
    public Boolean canAddComment() {
        if (this.isOwner() || this.myRestaurant==null || this.indexOfMySelectedFood==-1)
            return false;
        Boolean check=false;
        for (int i=0; i < this.history.size() ;i++)
            if (this.history.get(i).getFood().equals(this.myRestaurants.get(indexOfMyChosenRestaurant).getSelectedFood()))
                check=true;
        return check;

    }

    @Override
    public void addComment(Comment comment) {
        if(this.canAddComment())
            this.myRestaurants.get(indexOfMyChosenRestaurant).addComment(comment);
    }

    @Override
    public Boolean canEditComment(String commentID) {
        if (this.isOwner() || this.myRestaurant==null || this.indexOfMySelectedFood==-1)
            return false;
        if (!this.myRestaurant.canEditComment(this.userID.show(), commentID))
            return false;
        Boolean check=false;
        for (int i=0; i < this.history.size() ;i++)
            if (this.history.get(i).getFood().equals(this.myRestaurants.get(indexOfMyChosenRestaurant).getSelectedFood()))
                check=true;
        return check;
    }

    @Override
    public void editComment(String commentID, Comment comment) {
        if(this.canEditComment(commentID))
            this.myRestaurants.get(indexOfMyChosenRestaurant).editComment(commentID,comment);
    }

    @Override
    public void addOrder() {
        if (!this.isOwner() && this.myRestaurant!=null && this.indexOfMySelectedFood!=-1) {
            Order order=new Order(this.myRestaurants.get(indexOfMyChosenRestaurant).getSelectedFood()
                    ,this.myRestaurants.get(indexOfMyChosenRestaurant));
            this.creditCard.addOrder(order);
        }
    }

    @Override
    public ArrayList<Order> accessOrderHistory() {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1)
            return this.history;
        return null;
    }

    @Override
    public Order selectOrder(String orderID) {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1)
            for (int i=0; i<history.size(); i++)
                if (this.history.get(i).getID().equals(orderID))
                    return this.history.get(i);
        return null;
    }

    @Override
    public void displayCartStatus() {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1) {
            this.creditCard.displayCartStatus();
            this.isInCreditCard=true;
        }
    }

    @Override
    public void confirmOrder() {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1 && this.isInCreditCard) {
            this.confirmedOrder = this.creditCard.getOrders();
            this.creditCard.charge((-1)*this.creditCard.getMoney());
            this.creditCard.removeAllOrders();
            //this.deliveryTime=
        }
        }

    @Override
    public LocalTime showDeliveryTime() {
        return this.deliveryTime;
    }

    @Override
    public void chargeAccount(int increaseCharge) {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1 && this.isInCreditCard)
            this.creditCard.charge(increaseCharge);
        }

    @Override
    public int displayAccountCharge() {
        if (!this.isOwner() && this.myRestaurant==null && this.indexOfMySelectedFood==-1 && this.isInCreditCard)
            return this.creditCard.getCharge();
        return 0;
    }

    @Override
    public void back() {
        if (this.indexOfMySelectedFood!=-1) {
            this.indexOfMySelectedFood=-1;
            this.giveMyRestaurants();
        }
        else if (this.indexOfMyChosenRestaurant!=-1) {
            this.indexOfMyChosenRestaurant=-1;
            this.giveAllFoodsOfMyRestaurant();
        }
        else if (this.isInCreditCard) {
            this.isInCreditCard=false;
        }
    }


}
