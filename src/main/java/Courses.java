import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Courses  {
    //Attributes of Courses object
    private String name;
    private String courseCode;
    private List<String> prerequisite;
    private int credit;
    private int courseType;
    private int semester;

    private String courseGrade;
    private int givenSemester;

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public void setGivenSemester(int givenSemester) {
        this.givenSemester = givenSemester;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public int getGivenSemester() {
        return givenSemester;
    }




    public void setQuota(int quota) {
    }

    private int courseYear;
    private int theoreticalCourseHour;
    private int practicalLessonHour;
    private final List<Student> listOfStudents = new ArrayList<>();
    //Basic Setters and Getters
    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setTheoreticalCourseHour(int theoreticalCourseHour) {
        this.theoreticalCourseHour = theoreticalCourseHour;
    }

    public void setPracticalLessonHour(int practicalLessonHour) {
        this.practicalLessonHour = practicalLessonHour;
    }

    public int getSemester() {
        return semester;
    }
    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public int getTheoreticalCourseHour() {
        return theoreticalCourseHour;
    }

    public int getPracticalLessonHour() {
        return practicalLessonHour;
    }

    public int getCourseType() {
        return courseType;
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<String> getPrerequisite() {
        return prerequisite;
    }

    public int getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    //This is used to check if the given course has a prerequisite
    public Boolean checkIfPrerequisite(Courses courses){
        return !Objects.equals(courses.getPrerequisite().get(0), "");
    }
    //This checks if it has two pre requisites
    public Boolean checkIfTwoPreRequisite(Courses courses){
        return !Objects.equals(courses.getPrerequisite().get(0), "") && !Objects.equals(courses.getPrerequisite().get(1), "");
    }
    //Gets first pre requisite name of this course
    public String getPreRequisiteName() {
        return prerequisite.get(0);
    }
    //returns both pre requisite names
    public ArrayList<String> getTwoPreRequisiteName(Courses courses){
        ArrayList<String>preRequisiteNames = new ArrayList<>();
        preRequisiteNames.add(courses.getPrerequisite().get(0));
        preRequisiteNames.add(courses.getPrerequisite().get(1));
        return preRequisiteNames;
    }

    public void addToListOfStudents(Student student){
        this.listOfStudents.add(student);
    }


}
