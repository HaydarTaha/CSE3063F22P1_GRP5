import com.beust.ah.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student studentTest = new Student();
    ObjectMapper objectMapper = new ObjectMapper();

    File studentJson = new File("inputs/studentNames.json");
    Student[] studentArray = objectMapper.readValue(studentJson, Student[].class);

    StudentTest() throws IOException {
    }

    @Test
    void getfName() {
        String expect = "NİLAY";
        assertEquals(expect,studentArray[0].getfName());
    }

    @Test
    void getlName() {
        String expect = "AKSOY";
        assertEquals(expect,studentArray[0].getlName());
    }
    @Test
    void testSetfName(){
        Student studentTest = new Student();
        studentTest.setfName("MAHMUT");
        assertEquals("MAHMUT",studentTest.getfName());
    }

    @Test
    void testSetlName(){
        Student studentTest = new Student();
        studentTest.setlName("TUNCER");
        assertEquals("TUNCER",studentTest.getlName());
    }

    @Test
    void testSetCurrentYear() {
        Student studentTest = new Student();
        studentTest.setCurrentYear(0);
        assertEquals(0,studentTest.getCurrentYear());
    }

    @Test
    void testSetCurrentSemester() {
        Student studentTest = new Student();
        studentTest.setCurrentSemester(3);
        assertEquals(3,studentTest.getCurrentSemester());
    }

    //will be written later
    @Test
    void testGetTranscript() {
        Student studentTest = new Student();
        String expectedTranscript = "2.75";
        //assertEquals(expectedTranscript,studentTest.getCurrentSemester());
    }

    @Test
    void testStudentId() {
        Student studentTest = new Student();
        studentTest.setStudentId(150116000);
        assertEquals(150116000,studentTest.getStudentId());
    }

    @Test
    void testSetAdvisorName() {
        Student studentTest = new Student();
        Advisor advisor = new Advisor();
        advisor.setAdvisorId(4);
        advisor.setfName("Müjdat");
        advisor.setlName("SOYTÜRK");
        studentTest.setAdvisor(advisor);
        assertEquals("Müjdat SOYTÜRK",studentTest.getAdvisorName());

    }

    @Test
    void testCompletedCourses() {
        String courseName = "MATH1001";
        String grade = "CC";
        int credit = 6;
        int semester = 1;
        int year = 1;

        CompletedCourses testCompletedCourses = new CompletedCourses();
        testCompletedCourses.setCourseName("MATH1001");
        testCompletedCourses.setCourseGrade("CC");
        testCompletedCourses.setCredit(6);
        testCompletedCourses.setSemester(1);
        testCompletedCourses.setCourseYear(1);

        assertEquals(testCompletedCourses.getCourseName(), courseName);
        assertEquals(testCompletedCourses.getCourseGrade(), grade);
        assertEquals(testCompletedCourses.getCredit(), credit);
        assertEquals(testCompletedCourses.getSemester(), semester);
        assertEquals(testCompletedCourses.getCourseYear(), year);
    }

    @Test
    void testFailedCourses() {
        String expectedCourseName = "ECON2004";
        String expectedGrade = "DC";
        int expectedCredit = 4;
        int expectedSemester = 3;
        int expectedYear = 2;

        FailedCourses testFailedCourses = new FailedCourses();
        testFailedCourses.setCourseName("ECON2004");
        testFailedCourses.setCourseGrade("DC");
        testFailedCourses.setCredit(4);
        testFailedCourses.setSemester(3);
        testFailedCourses.setCourseYear(2);

        assertEquals(testFailedCourses.getCourseName(), expectedCourseName);
        assertEquals(testFailedCourses.getCourseGrade(), expectedGrade);
        assertEquals(testFailedCourses.getCredit(), expectedCredit);
        assertEquals(testFailedCourses.getSemester(), expectedSemester);
        assertEquals(testFailedCourses.getCourseYear(), expectedYear);

    }

}