import java.util.ArrayList;
import java.util.List;

public class CompletedCourses{

    private String courseName;
    private String courseGrade;

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

    @Override
    public String toString(){
        return getCourseName() + ", "+getCourseGrade();
    }


}