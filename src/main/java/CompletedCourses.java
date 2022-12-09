import java.util.ArrayList;
import java.util.List;

public class CompletedCourses extends Courses{

    private String courseName;
    private String courseGrade;
    private int givenSemester;


    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseGrade() {
        return courseGrade;
    }
    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public int getGivenSemester() {
        return givenSemester;
    }

    public void setGivenSemester(int givenSemester) {
        this.givenSemester = givenSemester;
    }


    @Override
    public String toString(){
        return getCourseName() + ", " + getCourseGrade() + ", " + getGivenSemester();
    }


}