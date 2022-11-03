import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

public class Student {
    int studentID;
    String fName;
    String lName;
    int GPA;
    String courseGrade;
    String gmailAddress;
    List<String> currentSelectedCourses = new ArrayList<>();
    List<String> completedCourses = new ArrayList<>();
    List<String> mandatoryCourses = new ArrayList<>();
    public Student(int studentID, String fName, String lName, int GPA, String courseGrade, List<String> currentSelectedCourses, List<String>completedCourses, List<String>mandatoryCourses, String gmailAddress){
        this.studentID = studentID;
        this.fName = fName;
        this.lName = lName;
        this.GPA = GPA;
        this.courseGrade = courseGrade;
        this.currentSelectedCourses = currentSelectedCourses;
        this.completedCourses = completedCourses;
        this.mandatoryCourses = mandatoryCourses;
        this.gmailAddress = gmailAddress;

    }
    void addStudentID(int id){
        this.studentID = id;
    }


    public static void main(String args[])
    {

    }
}
