public class Courses {

    private String name;
    private String courseCode;
    private String prerequisite;
    private int credit;
    private int courseType;
    private int semester;

    public Courses(String name, String courseCode, String prerequisite, int credit, int courseType, int semester, int theoreticalCourseHour, int practicalLessonHour) {
        this.name = name;
        this.courseCode = courseCode;
        this.prerequisite = prerequisite;
        this.credit = credit;
        this.courseType = courseType;
        this.semester = semester;
        this.theoreticalCourseHour = theoreticalCourseHour;
        this.practicalLessonHour = practicalLessonHour;
    }

    private int theoreticalCourseHour;
    private int practicalLessonHour;

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

    public String getPrerequisite() {
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

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Courses(){







    }

}
