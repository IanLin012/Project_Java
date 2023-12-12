public class ThreeCProduct {
    public void turnOn() {
        System.out.println("3C power on!");
    }
    public void turnOff() {
        System.out.println("3C shut down");
    }
    public void displayInfo(String brand) {
        System.out.println("This product's brand is:" + brand);
    }
    public void displayInfo(String brand, String userName) {
        System.out.println("This product's brand is:" + brand + ", user's name is:" + userName);
    }
}
