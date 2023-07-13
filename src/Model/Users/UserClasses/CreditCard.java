package Model.Users.UserClasses;

public class CreditCard {
    private Integer money;
    public CreditCard(int money){
        this.money=money;
    }
    public int giveMoney(){
        return this.money;
    }

    public void add(int parseInt) {
        money+=parseInt;
    }

    public boolean isEmpty() {
        return money==0;
    }
}
