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
    private double gpa;
    private String gmailAddress;
    private List<String> currentSelectedCourses;

    private List<CompletedCourses> completedCourses;
    private List<String> mandatoryCourses;
    private List<FailedCourses> failedCourses;



    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }


    public void setGmailAddress(String gmailAddress) {
        this.gmailAddress = gmailAddress;
    }

    public void setCurrentSelectedCourses(List<String> currentSelectedCourses) {
        this.currentSelectedCourses = currentSelectedCourses;
    }

    public void setCompletedCourses(List<CompletedCourses> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setMandatoryCourses(List<String> mandatoryCourses) {
        this.mandatoryCourses = mandatoryCourses;
    }

    public List<FailedCourses> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(List<FailedCourses> failedCourses) {
        this.failedCourses = failedCourses;
    }

    int getStudentID() { return studentID; }

    String getfName() { return fName; }

    String getlName() { return lName; }

    double getGPA() { return gpa; }

    List<String> getCurrentSelectedCourses() { return currentSelectedCourses; }

    List<CompletedCourses> getCompletedCourses() { return completedCourses; }

    List<String> getMandatoryCourses() { return mandatoryCourses; }

    String getGmailAddress() { return gmailAddress; }

}