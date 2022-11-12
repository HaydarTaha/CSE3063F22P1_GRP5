import java.util.List;

public class TranscriptCreator{

    private Student[] student;

    public TranscriptCreator(Student[] student) {
        this.student = student;
    }

    public void createTranscript(){

        for (int i=0; i<1; i++)
        System.out.println("Transcript of Student(" + student[i].getStudentId() + ")\nCompleted Courses: " + takeCompletedCourses(student[i]) +
                "\nFailed Courses: " + takeFailedCourses(student[i]) + "\nGpa : " + calculateGPA(student[i])
                +"\nTotal Credit: " + calculateTotalCredits(student[i]));

    }
    public List<CompletedCourses> takeCompletedCourses(Student student){

        return student.getCompletedCourses();
    }
    public List<FailedCourses> takeFailedCourses(Student student){
        return student.getFailedCourses();
    }
    public double calculateGPA(Student student){
        gpaCalculator gpaCalculator1 = new gpaCalculator();
        double gpa = student.getGPA();
        return gpa;
    }
    public int calculateTotalCredits(Student student){
        int totalCredit=0;
        return totalCredit;
    }
}
