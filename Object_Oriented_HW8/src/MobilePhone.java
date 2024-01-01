public class MobilePhone extends ThreeCProduct {
    private String brand; // private variable
    public String getBrand() { // brand accessor
        return brand;
    }
    MobilePhone(String brand) { // initial constructor
        this.brand = brand;
    }
    @Override
    public void turnOn() {
        System.out.println("Mobile phone power on!");
    }
    @Override
    public void turnOff() {
        System.out.println("Mobile phone shut down.\n");
    }
}
