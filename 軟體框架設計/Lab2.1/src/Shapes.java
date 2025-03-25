// lab2.1.01 方法覆蓋
abstract class Shape {
    abstract double area();
}

class Rectangle extends Shape {
    private int w=0, h=0;
    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }
    double area() {
        return w * h;
    }
}

public class Shapes {
    public static void main(String[] args) {
        Shape s = new Rectangle(5, 10);
        System.out.println(s.area());
    }
}