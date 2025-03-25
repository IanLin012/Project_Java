class Car {
    private Engine engine;
    private Wheel wheel;

    public Car(Engine engine, Wheel wheel) {
        this.engine = engine;
        this.wheel = wheel;
    }

    public void checkCarStatus() {
        engine.checkEngineStatus();
        wheel.checkWheelPressure();
    }
/*
    public Engine getEngine() {
        return engine;
    }

    public Wheel getWheel() {
        return wheel;
    }
*/
}

class Engine {
    public void checkEngineStatus() {
        System.out.println("引擎狀態正常。");
    }
}

class Wheel {
    public void checkWheelPressure() {
        System.out.println("輪胎胎壓正常。");
    }
}

public class Mechanic {
    public void checkCar(Car car) {
        car.checkCarStatus();
    }
}
