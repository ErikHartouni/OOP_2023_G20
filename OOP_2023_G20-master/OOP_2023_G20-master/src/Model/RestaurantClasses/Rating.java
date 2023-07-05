package Model.RestaurantClasses;

public enum Rating {
    VERY_GOOD(5),
    GOOD(4),
    MEDIUM(3),
    BAD(2),
    VERY_BAD(1);

    final private int num;
    Rating(int rating){
        this.num=rating;
    }
    int giveRating(){
        return this.num;
    }
}
