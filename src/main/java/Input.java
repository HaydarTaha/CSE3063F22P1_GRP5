public class Input {


    private String Semester;
    private int courseFFRate;
    private int quotaForElectives;
    private int quotaForMandatory;
    private int maxNumberOfSelectonForCourses;

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public int getCourseFFRate() {
        return courseFFRate;
    }

    public void setCourseFFRate(int courseFFRate) {
        this.courseFFRate = courseFFRate;
    }

    public int getQuotaForElectives() {
        return quotaForElectives;
    }

    public void setQuotaForElectives(int quotaForElectives) {
        this.quotaForElectives = quotaForElectives;
    }

    public int getQuotaForMandatory() {
        return quotaForMandatory;
    }

    public void setQuotaForMandatory(int quotaForMandatory) {
        this.quotaForMandatory = quotaForMandatory;
    }

    public int getMaxNumberOfSelectonForCourses() {
        return maxNumberOfSelectonForCourses;
    }

    public void setMaxNumberOfSelectonForCourses(int maxNumberOfSelectonForCourses) {
        this.maxNumberOfSelectonForCourses = maxNumberOfSelectonForCourses;
    }
}
