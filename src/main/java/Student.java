import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentID;
    private String fName;
    private String lName;
    private double GPA;
    private String courseGrade;
    private String gmailAddress;
    private List<String> currentSelectedCourses;
    private List<String> completedCourses;
    private List<String> mandatoryCourses;
    private List<String> failedCourses;

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

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public void setGmailAddress(String gmailAddress) {
        this.gmailAddress = gmailAddress;
    }

    public void setCurrentSelectedCourses(List<String> currentSelectedCourses) {
        this.currentSelectedCourses = currentSelectedCourses;
    }

    public void setCompletedCourses(List<String> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setMandatoryCourses(List<String> mandatoryCourses) {
        this.mandatoryCourses = mandatoryCourses;
    }

    public List<String> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(List<String> failedCourses) {
        this.failedCourses = failedCourses;
    }

    int getStudentID() { return this.studentID; }

    String getfName() { return this.fName; }

    String getlName() { return this.lName; }

    double getGPA() { return this.GPA; }

    String getCourseGrade() { return this.courseGrade;}

    List<String> getCurrentSelectedCourses() { return this.currentSelectedCourses; }

    List<String> getCompletedCourses() { return this.completedCourses; }

    List<String> getMandatoryCourses() { return this.mandatoryCourses; }

    String getGmailAddress() { return this.gmailAddress; }

    public Student(int studentID, String fName, String lName, int GPA, String courseGrade, List<String> currentSelectedCourses, List<String> completedCourses, List<String> mandatoryCourses, String gmailAddress, List<String> failedCourses){
        this.studentID = studentID;
        this.fName = fName;
        this.lName = lName;
        this.GPA = GPA;
        this.courseGrade = courseGrade;
        this.currentSelectedCourses = currentSelectedCourses;
        this.completedCourses = completedCourses;
        this.mandatoryCourses = mandatoryCourses;
        this.gmailAddress = gmailAddress;

        this.failedCourses = failedCourses;
    }
    public void changeStudentID(int id){
        this.studentID = id;
    }

    public void changefName(String firstName){
        this.fName=firstName;
    }

    public void changelName(String lastName){
        this.lName = lastName;
    }

    public void changeGpa(double gpa){
        this.GPA = gpa;
    }

    public void changeCourseGrade(String courseGrade){
        this.courseGrade=courseGrade;
    }

    public void changeCurrentSelectedCourses(List<String>currentCourses){ this.currentSelectedCourses = currentCourses; }

    public void changeCompletedCourses(List<String>completedCourses){ this.completedCourses = completedCourses; }

    public void changeMandatoryCourses(List<String>mandatoryCourses){ this.mandatoryCourses = mandatoryCourses; }

    public void changeGmailAddress(String gmailAddress){
        this.gmailAddress = gmailAddress;
    }

    void printStudentInfo(){
        System.out.println( "Student ID: " +this.studentID+ "\nName: " +this.fName+ " " +this.lName+ "\nGPA: " +this.GPA+ "\nEmail Address: " +this.gmailAddress+ "\nCurrent Selected Courses: " +this.currentSelectedCourses+ "\nCompleted Courses: " +this.completedCourses+ "\nCourses Needed to Finish to Graduate: " +this.mandatoryCourses +"\n");
    }
    /*
    public static void main(String args[]){
        List<String> Test = new ArrayList<>();
        Test.add("1");
        Test.add("2");

        Student newS = new Student(123, "Mert", "Özincegedik", 3, "A",Test,Test,Test,"gmail.com", failedCourses);
        List<String> testTwo = new ArrayList<>();
        testTwo.add("3");
        testTwo.add("4");
        newS.printStudentInfo();
        newS.changeStudentID(321);
        newS.changefName("Burak");
        newS.changelName("Soyad");
        newS.changeGpa(3.12);
        newS.changeCompletedCourses(testTwo);
        newS.changeCurrentSelectedCourses(testTwo);
        newS.changeMandatoryCourses(testTwo);
        newS.changeGmailAddress("burak@gmail.com");
        newS.printStudentInfo();

    }
    */
}
