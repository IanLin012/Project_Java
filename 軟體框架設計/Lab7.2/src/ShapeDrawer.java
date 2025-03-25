public class ShapeDrawer {

    public void drawShape(Shape s) {
        s.draw();
    }

    interface Shape {
        public void draw();
    }

    class Rectangle implements Shape {
        public void draw() {
            System.out.println("繪製矩形");
        }
    }

    class Circle implements Shape {
        public void draw() {
            System.out.println("繪製圓形");
        }
    }

    class Triangle implements Shape {
        public void draw() {
            System.out.println("繪製三角形");
        }
    }

    public static void main(String[] args) {
        ShapeDrawer drawer = new ShapeDrawer();
        drawer.drawShape(drawer.new Rectangle());
        drawer.drawShape(drawer.new Circle());
        drawer.drawShape(drawer.new Triangle());
    }
}
