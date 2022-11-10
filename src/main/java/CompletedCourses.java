public class CompletedCourses extends Courses {

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
    public void setCCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    @Override
    public String toString(){
        return getCourseName() + ", "+getCourseGrade();
    }
}