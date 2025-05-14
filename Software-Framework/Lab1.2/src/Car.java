// 1.2 練習1：定義類別與使用建構子
public class Car {
    private String brand;
    private int speed = 0;
    public Car(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void accelerate(int increase) {
        setSpeed(speed += increase);
    }

    public static void main(String[] args) {
        Car car = new Car("Toyota", 0);

        car.accelerate(10);
        System.out.println("目前速度: " + car.getSpeed() + " km/h");

        car.accelerate(10);
        System.out.println("目前速度: " + car.getSpeed() + " km/h");

        car.accelerate(10);
        System.out.println("目前速度: " + car.getSpeed() + " km/h");
    }
}
