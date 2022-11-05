import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;


public class Student {
    private int studentID;
    private String fName;
    private String lName;
    private double GPA;
    private String gmailAddress;
    private List<String> currentSelectedCourses;

    private CompletedCourses completedCourses;
    private List<String> mandatoryCourses;
    private FailedCourses failedCourses;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }


    public void setGmailAddress(String gmailAddress) {
        this.gmailAddress = gmailAddress;
    }

    public void setCurrentSelectedCourses(List<String> currentSelectedCourses) {
        this.currentSelectedCourses = currentSelectedCourses;
    }

    public void setCompletedCourses(CompletedCourses completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setMandatoryCourses(List<String> mandatoryCourses) {
        this.mandatoryCourses = mandatoryCourses;
    }

    public FailedCourses getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(FailedCourses failedCourses) {
        this.failedCourses = failedCourses;
    }

    int getStudentID() { return studentID; }

    String getfName() { return fName; }

    String getlName() { return lName; }

    double getGPA() { return GPA; }

    List<String> getCurrentSelectedCourses() { return currentSelectedCourses; }

    CompletedCourses getCompletedCourses() { return completedCourses; }

    List<String> getMandatoryCourses() { return mandatoryCourses; }

    String getGmailAddress() { return gmailAddress; }


    void printStudentInfo(){
        //System.out.println( "Student ID: " +this.studentID+ "\nName: " +this.fName+ " " +this.lName+ "\nGPA: " +this.GPA+ "\nEmail Address: " +this.gmailAddress+ "\nCurrent Selected Courses: " +this.currentSelectedCourses+ "\nCompleted Courses: " +this.completedCourses+ "\nCourses Needed to Finish to Graduate: " +this.mandatoryCourses +"\n");
    }

    public static Student createStudent(){
        Student student = new Student();
        student.setStudentID(150119643);
        student.setfName("Mert");
        student.setlName("Özincegedik");
        student.setGPA(3.22);
        student.setGmailAddress("mertozincegedik@marun.edu.tr");
        List<String>currentSelectedCourses = new ArrayList<>();
        currentSelectedCourses.add("CSE1141");
        currentSelectedCourses.add("CSE1142");
    }
    public static void main(String args[]) throws IOException {
        List<String> Test = new ArrayList<>();
        Test.add("1");
        Test.add("2");

        byte[] jsonData = Files.readAllBytes(Paths.get("C:\\Users\\mert_\\IdeaProjects\\CSE3063F22P1_GRP5\\inputs\\lectures.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(jsonData, Student.class);
        System.out.println("Stu obj\n"+student);



    }

}
