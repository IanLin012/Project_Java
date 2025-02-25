/*
// 1.1 練習1
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("大家好，我叫 " + name + "，今年 " + age + " 歲。");
    }

    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 18);
        p1.introduce();
        p2.introduce();
    }
}
*/

// 1.5 練習1
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
