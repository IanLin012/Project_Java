import  java.util.HashMap;

public class Student {
    private String name; // 3 private variable
    private int id;
    private HashMap<String, Integer> grades = new HashMap<>();
    public String getName() { // name accessor
        return name;
    }
    public int getId() { // id accessor
        return id;
    }
    public HashMap<String, Integer> getGrades() { // grades accessor
        return grades;
    }
    public void setName(String name) { // name mutator
        this.name = name;
    }
    public void setId(int id) { // id mutator
        this.id = id;
    }
    public void setGrades(String subject, int grade) { // grades mutator
        this.grades.put(subject.toUpperCase(), grade);
    }
    static String[] allSubject = {"Calculus", "Statistics", "Algorithm", "Math", "English"}; // static array
    public static boolean subjectIsContained(String subject) { // public static function
        boolean b = false;
        for (String s : allSubject) {
            if (subject.equalsIgnoreCase(s)) { // subject in allSubject
                b = true;
                break;
            }
        }
        return(b);
    }
}