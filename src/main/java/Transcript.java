import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Transcript extends Student {

    private List<Courses> completedCourses;
    private List<Courses> failedCourses;
    private Double gpa;
    private int completedCredits;
    private String advisorName;
    private List<String> studentSelectedCourses;
    private List<String> completedCourseStrings;
    private List<String> failedCoursesStrings;

    public Transcript() {
    }
    public Transcript(List<Courses> completedCourses, List<Courses> failedCourses, Double gpa, int completedCredits, List<String> studentSelectedCourses, String advisorName, Student student){
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        this.gpa = gpa;
        this.completedCredits = completedCredits;
        this.advisorName = advisorName;
        this.studentSelectedCourses = studentSelectedCourses;

    }

    public List<Courses> getCompletedCourses() {
        return completedCourses;
    }
    public List<Courses> getFailedCourses() {
        return failedCourses;
    }
    /*public void printTranscriptAll(Student[] students2){
        for (Student s: students2) {
            System.out.println("ID: "+s.getStudentId() + "\nName: " + s.getfName() + " " +  s.getlName() + "\nCourses: " + s.getCompletedCourses() + "\nSelected Courses:" + s.getCurrentSelectedCourses() + "\nGPA: " + s.getGPA() + "\nTotalCredits: " + s.getTotalCredit() + "\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }*/
    public void seperateFailedCourses(){
        failedCoursesStrings = new ArrayList<>();
        for (Courses completedCourse : this.completedCourses){
            if (completedCourse.getCourseGrade().equals("FF")){
                failedCoursesStrings.add(completedCourse.getName());
                failedCoursesStrings.add(completedCourse.getCourseGrade());
            }

        }
    }
    public void printTranscriptSpecificStudent(Student student){
        student.getTranscript().transformSpecificStudentTranscriptElementsToList(student);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("ID: " + student.getStudentId() + "\nFullName: " + student.getfName() + " " + student.getlName() + "\nAdvisor: " + this.advisorName + "\nCourses Taken:" + this.completedCourseStrings + "\nGPA: " + this.gpa + "\nTotal Credits: " + this.completedCredits + "\nAvailable Courses:" + student.getAvailableCourses()  + "\nAdvisor Approved Courses: " + this.studentSelectedCourses + "\nFailed Courses: " + this.failedCoursesStrings );
    }
    public void transformTranscriptElementsToList(){
        this.completedCourseStrings = new ArrayList<>();
        for (Courses completedCourses : this.completedCourses){
            completedCourseStrings.add(completedCourses.getName());
            completedCourseStrings.add(completedCourses.getCourseGrade());
        }
    }
    public void transformSpecificStudentTranscriptElementsToList(Student student){
        this.completedCourseStrings = new ArrayList<>();
        for (Courses completedCourses : student.getCompletedCourses()){
            completedCourseStrings.add(completedCourses.getName() + " " + completedCourses.getCourseGrade() + " Given: " + completedCourses.getGivenSemester());
        }
        seperateFailedCourses();
    }

    public void generateTranscriptJson(Student[] students) throws IllegalAccessException {
        JSONArray arr=new JSONArray();
        for (Student std:students){
            JSONObject jsonObj=new JSONObject();
            for (Field f:std.getClass().getDeclaredFields()){
                f.setAccessible(true);
                if(!f.getName().equals("failedCourses") && !f.getName().equals("availableCourses"))
                    jsonObj.put(f.getName(),f.get(std));
            }
            arr.add(jsonObj);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("./StudentsOutput.json"))){
            out.write(arr.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
