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
    void testGetPrerequisite() {
        String expectedPrerequisite = "CSE1241";
        assertEquals(expectedPrerequisite,coursesArray[10].getPrerequisite().get(0));
    }

    @Test
    void testGetPreRequisiteName() {
        String expectedPrerequisite = "CSE1241";
        assertEquals(expectedPrerequisite,coursesArray[10].getPreRequisiteName());
    }

    @Test
    void testGetCredit() {
        int expectedCredit = 6;
        assertEquals(expectedCredit,coursesArray[10].getCredit());
    }

    @Test
    void testGetCourseType() {
        int expectedCourseType = 5;
        assertEquals(expectedCourseType,coursesArray[10].getCourseType());
    }

    @Test
    void testGetSemester() {
        int expectedSemester = 2;
        assertEquals(expectedSemester,coursesArray[10].getSemester());
    }

    @Test
    void testGetCourseYear() {
        int expectedCourseYear = 1;
        assertEquals(expectedCourseYear,coursesArray[10].getCourseYear());
    }

    @Test
    void testGetTheoreticalCourseHour() {
        int expectedTheoreticalCourseHour = 3;
        assertEquals(expectedTheoreticalCourseHour,coursesArray[10].getTheoreticalCourseHour());
    }

    @Test
    void testGetPracticalLessonHour() {
        int expectedPracticalLessonHour = 2;
        assertEquals(expectedPracticalLessonHour,coursesArray[10].getPracticalLessonHour());
    }

    @Test
    void testCheckIfPrerequisite() {
        // For who has prereq
        boolean expectedCheckIfPrerequisite1 = true;
        assertEquals(expectedCheckIfPrerequisite1,coursesArray[10].checkIfPrerequisite(coursesArray[10]));
        // For who has not prereq
        boolean expectedCheckIfPrerequisite0 = false;
        assertEquals(expectedCheckIfPrerequisite0,coursesArray[9].checkIfPrerequisite(coursesArray[9]));
    }

    @Test
    void testCheckIfTwoPreRequisite() {
        boolean expectedCheckIfTwoPreRequisite =  false;
        assertEquals(expectedCheckIfTwoPreRequisite,coursesArray[0].checkIfTwoPreRequisite(coursesArray[0]));
    }


}