import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Transcript extends Student {

    private List<CompletedCourses> completedCourses;
    private List<FailedCourses> failedCourses;
    private Double gpa;
    private int completedCredits;
    private String advisorName;
    private Student student;
    private List<String> studentSelectedCourses;
    private List<String> completedCourseStrings;
    private List<String> failedCoursesStrings;

    public Transcript() {
    }
    public Transcript(List<String> coursesTook, Double gpa, int completedCredits){
        this.studentSelectedCourses = coursesTook;
        this.gpa = gpa;
        this.completedCredits = completedCredits;
    }
    public Transcript(List<CompletedCourses> completedCourses, List<FailedCourses> failedCourses, Double gpa, int completedCredits, List<String> studentSelectedCourses, String advisorName, Student student){
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        this.gpa = gpa;
        this.completedCredits = completedCredits;
        this.advisorName = advisorName;
        this.studentSelectedCourses = studentSelectedCourses;
        this.student = student;

    }

    public List<CompletedCourses> getCompletedCourses() {
        return completedCourses;
    }
    public List<FailedCourses> getFailedCourses() {
        return failedCourses;
    }
    public void generateTranscriptForAllStudents(Student[] students){
        for (Student student : students){
            student.generateTranscript();
        }
    }
    /*public void printTranscriptAll(Student[] students2){
        for (Student s: students2) {
            System.out.println("ID: "+s.getStudentId() + "\nName: " + s.getfName() + " " +  s.getlName() + "\nCourses: " + s.getCompletedCourses() + "\nSelected Courses:" + s.getCurrentSelectedCourses() + "\nGPA: " + s.getGPA() + "\nTotalCredits: " + s.getTotalCredit() + "\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }*/
    public void seperateFailedCourses(){
        failedCoursesStrings = new ArrayList<>();
        for (CompletedCourses completedCourse : this.completedCourses){
            if (completedCourse.getCourseGrade().equals("FF")){
                failedCoursesStrings.add(completedCourse.getCourseName());
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
        for (CompletedCourses completedCourses : this.completedCourses){
            completedCourseStrings.add(completedCourses.getCourseName());
            completedCourseStrings.add(completedCourses.getCourseGrade());
        }
    }
    public void transformSpecificStudentTranscriptElementsToList(Student student){
        this.completedCourseStrings = new ArrayList<>();
        for (CompletedCourses completedCourses : student.getCompletedCourses()){
            completedCourseStrings.add(completedCourses.getCourseName() + " " + completedCourses.getCourseGrade() + " Given: " + completedCourses.getGivenSemester());
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
