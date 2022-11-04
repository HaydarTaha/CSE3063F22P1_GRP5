import java.util.ArrayList;
import java.util.List;

public class Student {
    int studentID;
    String fName;
    String lName;
    int GPA;
    String courseGrade;
    String gmailAddress;
    List<String> currentSelectedCourses;
    List<String> completedCourses;
    List<String> mandatoryCourses;
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
    public void changeStudentID(int id){
        this.studentID = id;
    }
    int getStudentID() { return this.studentID; }

    public void changefName(String firstName){
        this.fName=firstName;
    }

    String getfName() { return this.fName; }

    public void changelName(String lastName){
        this.lName = lastName;
    }
    String getlName() { return this.lName; }

    public void changeGpa(int gpa){
        this.GPA = gpa;
    }
    int getGPA() { return this.GPA; }

    public void changeCourseGrade(String courseGrade){
        this.courseGrade=courseGrade;
    }
    String getCourseGrade() { return this.courseGrade;}

    public void changeCurrentSelectedCourses(List<String>currentCourses){ this.currentSelectedCourses = currentCourses; }

    List<String> getCurrentSelectedCourses() { return this.currentSelectedCourses; }

    public void changeCompletedCourses(List<String>completedCourses){ this.completedCourses = completedCourses; }
    List<String> getCompletedCourses() { return this.completedCourses; }
    public void changeMandatoryCourses(List<String>mandatoryCourses){ this.mandatoryCourses = mandatoryCourses; }
    List<String> getMandatoryCourses() { return this.mandatoryCourses; }
    public void changeGmailAddress(String gmailAddress){
        this.gmailAddress = gmailAddress;
    }
    String getGmailAddress() { return this.gmailAddress; }

    void printStudentInfo(){
        System.out.println( "Student ID: " +this.studentID+ "\nName: " +this.fName+ " " +this.lName+ "\nGPA: " +this.GPA+ "\nEmail Address: " +this.gmailAddress+ "\nCurrent Selected Courses: " +this.currentSelectedCourses+ "\nCompleted Courses: " +this.completedCourses+ "\nCourses Needed to Finish to Graduate: " +this.mandatoryCourses);
    }
    public static void main(String args[]){
        List<String> Test = new ArrayList<>();
        Test.add("1");
        Test.add("2");

        Student newS = new Student(123, "Mert", "Özincegedik", 3, "A",Test,Test,Test,"gmail.com");

        newS.printStudentInfo();

    }
}
