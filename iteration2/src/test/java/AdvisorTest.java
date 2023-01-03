import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AdvisorTest {

    private Advisor advisorUnderTest;

    @BeforeEach
    void setUp() {
        advisorUnderTest = new Advisor();
    }

    @Test
    void testSetfName() {
        // Setup
        // Run the test
        advisorUnderTest.setfName("fName");

        // Verify the results
    }

    @Test
    void testSetlName() {
        // Setup
        // Run the test
        advisorUnderTest.setlName("lName");

        // Verify the results
    }

    @Test
    void testSetAdvisorId() {
        // Setup
        // Run the test
        advisorUnderTest.setAdvisorId(0);

        // Verify the results
    }

    @Test
    void testAdvisorControl() {
        // Setup
        final Student student = new Student();
        student.setAdvisor(new Advisor());
        student.setCurrentYear(2020);
        student.setCurrentSemester(0);
        student.setStudentId(0);
        student.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setCourseType(0);
        courses.setSemester(0);
        courses.setTheoreticalCourseHour(0);
        courses.setPracticalLessonHour(0);
        courses.setCourseYear(2020);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        student.setCompletedCourses(List.of(courses));

        // Run the test
        advisorUnderTest.advisorControl(List.of("value"), student);

        // Verify the results
    }

    @Test
    void testAddAdvisorsLookingList() {
        // Setup
        final Student std = new Student();
        std.setAdvisor(new Advisor());
        std.setCurrentYear(2020);
        std.setCurrentSemester(0);
        std.setStudentId(0);
        std.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setCourseType(0);
        courses.setSemester(0);
        courses.setTheoreticalCourseHour(0);
        courses.setPracticalLessonHour(0);
        courses.setCourseYear(2020);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        std.setCompletedCourses(List.of(courses));

        // Run the test
        advisorUnderTest.addAdvisorsLookingList(std);

        // Verify the results
    }

    @Test
    void testCheckCourseQuota() {
        // Setup
        // Run the test
        advisorUnderTest.checkCourseQuota();

        // Verify the results
    }
}
