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
        // I wrote unit testing for getting credit.
        int creditExpect = 6;
        int creditActual = coursesArray[0].getCredit();
        int creditExpect2 = 4;
        int creditActual2 = coursesArray[6].getCredit();
        assertEquals(creditExpect2,creditActual2);
    }

    @Test
    void testGetPrerequisite() {

        String expect="[CSE1242]";
        String actual= String.valueOf(coursesArray[17].getPrerequisite());

        if(coursesArray[17].getPrerequisite().contains("")){
            System.out.println("There is no prerequisite.");

        }else{
            assertEquals(expect,actual);
        }

    }
    @Test
    void testGetTheoreticalCourseHour() {
        int actual=coursesArray[2].getTheoreticalCourseHour();
        int expect=3;
        assertEquals(expect,actual);
    }

    @Test
    void testGetPracticalLessonHour() {
        int actual=coursesArray[2].getPracticalLessonHour();
        int expect=2;
        assertEquals(expect,actual);
    }






}