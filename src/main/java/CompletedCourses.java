public class CompletedCourses {

    private String courseName;
    private String courseGrade;

    public String getcourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getcourseGrade() {
        return courseGrade;
    }
    public void setCCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    @Override
    public String toString(){
        return getcourseName() + ", "+getcourseGrade();
    }
}