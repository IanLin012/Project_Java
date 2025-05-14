// 1.5 練習1：設計Person類別
public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        person1.displayInfo();

        Person person2 = new Person("Alice", 25);
        person2.displayInfo();
    }
}
