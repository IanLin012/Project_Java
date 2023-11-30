package fcu.oop.traffic;

public class Taxi extends Transportation {
    private int way; // private variable
    public int getWay() { // way accessor
        return way;
    }
    public Taxi(String driver, double price, int way) { // initial constructor
        super(driver, price);
        driver = getDriver();
        price = getPrice();
        this.way = way;
    }
    @Override public double calcPrice() { // override calcPrice
        return(getPrice() * getWay());
    }
}