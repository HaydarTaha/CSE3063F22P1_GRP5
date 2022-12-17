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

    @Test
    void testGetName() {
        String expect = "Calculus I";
        assertEquals(expect,coursesArray[0].getName());
    }

    @Test
    void testGetCourseCode() {
        String expectedCourseCode="MATH1001";
        assertEquals(expectedCourseCode,coursesArray[0].getCourseCode());
    }

    @Test
    void testGetCredit() {
    }
}