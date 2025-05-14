import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student(); // construct student object
        student.setName("Andy"); // set name
        student.setId(1001); // set id
        while(true) {
            System.out.print("Input subject: ");
            String subject = scanner.next(); // input subject
            if(subject.equals("exit")) { // end when input "exit"
                break;
            }
            if(Student.subjectIsContained(subject)) {
                System.out.print("Input grade: ");
                int grade = scanner.nextInt(); // input grade
                student.setGrades(subject, grade);
            }
            else {
                System.out.println("this subject:" + subject + " not found"); // the input subject not in allSubject
            }
        }
        System.out.println("\nStudent name: " + student.getName() + ", id: " + student.getId()); // print name & id
        System.out.println("This student has " + student.getGrades().size() + " subjects"); // print number of subjects
        for(Map.Entry<String, Integer> entry : student.getGrades().entrySet()) { // print all subjects & grades
            System.out.println("Subject: " + entry.getKey() + ", grade:" + entry.getValue());
        }
    }
}