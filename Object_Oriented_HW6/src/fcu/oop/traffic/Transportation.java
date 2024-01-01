package fcu.oop.traffic;

public class Transportation {
    private String driver; // 2 private variable
    private double price;
    public String getDriver() { // driver accessor
        return driver;
    }
    public double getPrice() { // price accessor
        return price;
    }
    Transportation(String driver, double price) { // initial constructor
        this.driver = driver;
        this.price = price;
    }
    public double calcPrice() { // public function
        return 0.5;
    }
}