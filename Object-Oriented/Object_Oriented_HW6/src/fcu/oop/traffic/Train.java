package fcu.oop.traffic;

public class Train extends Transportation {
    private double road; // private variable
    public double getRoad() { // road accessor
        return road;
    }
    public Train(String driver, double price, double road) { // initial constructor
        super(driver, price);
        driver = getDriver();
        price = getPrice();
        this.road = road;
    }
    @Override public double calcPrice() { // override calcPrice
        return(getPrice() * getRoad());
    }
}