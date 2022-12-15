import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Input {


    private String semester;
    private int courseFFRate;
    private int quotaForElectives;
    private int quotaForMandatory;
    private int maxNumberOfSelectionForCourses;



    private String coursesJsonName;
    private String electiveNTEJsonFileName;
    private String electiveFTEJsonFileName;
    private String electiveUEJsonFileName;
    private String electiveTEJsonFileName;
    private String advisorsJsonName;
    private String studentsJsonName;





    private Courses[] courses;
    private Person[] students;
    private Person[] advisors;
    private Courses[] NTE;
    private Courses[] FTE;
    private Courses[] UE;
    private Courses[] TE;



    public void setSemester(String semester) {
        this.semester = semester;
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

    public int getMaxNumberOfSelectionForCourses() {
        return maxNumberOfSelectionForCourses;
    }

    public void setMaxNumberOfSelectionForCourses(int maxNumberOfSelectonForCourses) {
        this.maxNumberOfSelectionForCourses = maxNumberOfSelectonForCourses;
    }

    public void setCoursesJsonName(String coursesJsonName) {
        this.coursesJsonName = coursesJsonName;
    }

    public void setElectiveNTEJsonFileName(String electiveNTEJsonFileName) {
        this.electiveNTEJsonFileName = electiveNTEJsonFileName;
    }

    public void setElectiveFTEJsonFileName(String electiveFTEJsonFileName) {
        this.electiveFTEJsonFileName = electiveFTEJsonFileName;
    }

    public void setElectiveUEJsonFileName(String electiveUEJsonFileName) {
        this.electiveUEJsonFileName = electiveUEJsonFileName;
    }

    public void setElectiveTEJsonFileName(String electiveTEJsonFileName) {
        this.electiveTEJsonFileName = electiveTEJsonFileName;
    }

    public void setAdvisorsJsonName(String advisorsJsonName) {
        this.advisorsJsonName = advisorsJsonName;
    }

    public void setStudentsJsonName(String studentsJsonName) {
        this.studentsJsonName = studentsJsonName;
    }

    public void createObjects() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File advisorJsonFile = new File(advisorsJsonName);
        Person[] advisors = objectMapper.readValue(advisorJsonFile, Advisor[].class);

        File lecturesJsonFile = new File(coursesJsonName);
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);

        File UEJson = new File(electiveUEJsonFileName);
        Courses[] UE = objectMapper.readValue(UEJson, Courses[].class);

        File TEJson = new File(electiveTEJsonFileName);
        Courses[] TE = objectMapper.readValue(TEJson, Courses[].class);

        File NTEJson = new File(electiveNTEJsonFileName);
        Courses[] NTE = objectMapper.readValue(NTEJson, Courses[].class);

        File FTEJson = new File(electiveFTEJsonFileName);
        Courses[] FTE = objectMapper.readValue(FTEJson, Courses[].class);


        File studentsJsonFile = new File(studentsJsonName);
        Person[] students = objectMapper.readValue(studentsJsonFile, Student[].class);

        this.advisors = advisors;
        this.courses = courses;
        this.UE = UE;
        this.TE = TE;
        this.NTE = NTE;
        this.FTE = FTE;
        this.students = students;

    }

    public void startSimulationWithInputs() throws IOException, IllegalAccessException {
        GenerateStudent generateStudent = new GenerateStudent((Student[]) students, courses, UE, TE, NTE, FTE, (Advisor[]) advisors);
        generateStudent.simulate();
    }
}