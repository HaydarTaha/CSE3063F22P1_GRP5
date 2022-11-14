import java.util.List;

public class Transcript {

    private List<CompletedCourses> completedCourses;
    private List<FailedCourses> failedCourses;
    private Double gpa;
    private int completedCredits;

    public List<CompletedCourses> getCompletedCourses() {
        return completedCourses;
    }
    public List<FailedCourses> getFailedCourses() {
        return failedCourses;
    }
    public Double getGpa() {
        return gpa;
    }
    public int getCompletedCredits() {
        return completedCredits;
    }
    public void printTranscript(Student[] students2){
        for (Student s: students2) {
            System.out.println("ID: "+s.getStudentId() + "\nName: " + s.getfName() + " " +  s.getlName() + "\nCourses: " + s.getCompletedCourses() + "\nGPA: " + s.getGPA() + "\nTotalCredits: " + s.getTotalCredit() + "\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        }


    }
}
