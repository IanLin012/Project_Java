public class Computer extends ThreeCProduct {
    private String brand, userName; // two private variable
    public String getBrand() { // brand accessor
        return brand;
    }
    public String getUserName() { // userName accessor
        return userName;
    }
    Computer(String brand, String userName) { // initial constructor
        this.brand = brand;
        this.userName = userName;
    }
    @Override
    public void turnOn() {
        System.out.println("Computer power on!");
    }
    @Override
    public void turnOff() {
        System.out.println("Computer shut down.\n");
    }
}
