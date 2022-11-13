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
            System.out.println(s.getStudentId() + " " + s.getfName() + " " +  s.getlName() + " " + s.getCompletedCourses());
        }


    }
}
