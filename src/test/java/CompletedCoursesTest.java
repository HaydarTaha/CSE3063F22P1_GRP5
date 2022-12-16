import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CompletedCoursesTest {

    CompletedCoursesTest() throws IOException {
    }

    // Datas might be from lectures.json
    @Test
    void testGetCourseName() {
        CompletedCourses completedCoursesTest = new CompletedCourses();
        completedCoursesTest.setCourseName("ATA121");
        assertEquals("ATA121", completedCoursesTest.getCourseName());
    }
    @Test
    void testGetCourseGrade() {
        CompletedCourses completedCoursesTest = new CompletedCourses();
        completedCoursesTest.setCourseGrade("BA");
        assertEquals("BA", completedCoursesTest.getCourseGrade());
    }
    @Test
    void testGetGivenSemester() {
        CompletedCourses completedCoursesTest = new CompletedCourses();
        completedCoursesTest.setGivenSemester(1);
        assertEquals(1, completedCoursesTest.getGivenSemester());
    }
    @Test
    void testToString() {
        CompletedCourses completedCoursesTest = new CompletedCourses();
        completedCoursesTest.setCourseName("ATA122");
        completedCoursesTest.setCourseGrade("AA");
        completedCoursesTest.setGivenSemester(2);

        assertEquals("ATA122, AA, 2", completedCoursesTest.toString());
    }


}
