import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
// Student.java
public class Student {
    // 学生姓名
    private String studentName;
    // 学生id
    private String studentId;
    // 学科名
    private String courseName;
    // 分数
    private int score;
    // 构造方法
    public Student(String studentName, String studentId, String courseName, int score) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.score = score;
    }
    public String getStudentName() { return studentName; }
    public String getStudentId() { return studentId; }
    public String getCourseName() { return courseName; }
    public int getScore() { return score; }
}
