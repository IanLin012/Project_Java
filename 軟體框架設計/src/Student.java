//1.2 練習2
public class Student {
    private String name;
    private int score;
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    static int totalStudents = 0;

    public void displayInfo() {
        System.out.println("Name: " + name + ", Score: " + score);
        totalStudents++;
    }

    static int getTotalStudents() {
        return totalStudents;
    }

    public static void main(String[] args) {
        Student std1 = new Student("A", 99);
        Student std2 = new Student("B", 88);
        Student std3 = new Student("C", 77);
        std1.displayInfo();
        std2.displayInfo();
        std3.displayInfo();

        System.out.println(getTotalStudents());
    }
}

