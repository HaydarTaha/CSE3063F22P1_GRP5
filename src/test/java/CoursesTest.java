import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CoursesTest  {

    Courses courseTest = new Courses();
    ObjectMapper objectMapper = new ObjectMapper();

    File courseJson = new File("inputs/lectures.json");
    Courses[] coursesArray = objectMapper.readValue(courseJson, Courses[].class);

    CoursesTest() throws IOException {
    }

    // I choose 10. element as test object because it is one of the ones whose prerequisite is filled
    // TODO: Sort test functions as main func

    @Test
    void testGetName() {
        String expect = "Computer Programming II";
        assertEquals(expect,coursesArray[10].getName());
    }

    @Test
    void testGetCourseCode() {
        String expectedCourseCode="CSE1242";
        assertEquals(expectedCourseCode,coursesArray[10].getCourseCode());
    }

    @Test
    void getSemester() {
        int expectedSemester = 2;
        assertEquals(expectedSemester,coursesArray[10].getSemester());
    }
    @Test
    void getCourseYear() {

        int expectedCourseYear = 1;
        assertEquals(expectedCourseYear,coursesArray[10].getCourseYear());
    }
    @Test
    void getTheoreticalCourseHour() {
        int expectedTheoreticalCourseHour = 3;
        assertEquals(expectedTheoreticalCourseHour,coursesArray[10].getTheoreticalCourseHour());
    }
    @Test
    void getPracticalLessonHour() {
        int expectedPracticalLessonHour = 2;
        assertEquals(expectedPracticalLessonHour,coursesArray[10].getPracticalLessonHour());
    }
    @Test
    void getCourseType() {
        int expectedCourseType = 5;
        assertEquals(expectedCourseType,coursesArray[10].getCourseType());
    }
    @Test
    void getPrerequisite() {
        // will be edited later

    }
    @Test
    void getCredit() {
        int expectedCredit = 6;
        assertEquals(expectedCredit,coursesArray[10].getCredit());
    }
    @Test
    void getPreRequisiteName() {
        // will be edited later

    }

}