import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateAvailablesTest {

    ObjectMapper objectMapper = new ObjectMapper();
    //We used Jackson Library from Maven here to map these jsons to create corresponding lists of objects
    File advisorJsonFile = new File("inputs/Advisors.json");
    Advisor[] advisors = objectMapper.readValue(advisorJsonFile, Advisor[].class);
    File lecturesJsonFile = new File("inputs/lectures.json");
    Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);
    File studentsJsonFile = new File("inputs/studentInformation.json");
    Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);

    CalculateAvailablesTest() throws IOException {
    }

    @Test
    void testSetMaxNumberOfSelectionForCourses() {
        CalculateAvailables calculateAvailables = new CalculateAvailables();
        calculateAvailables.setMaxNumberOfSelectionForCourses(10);
        assertEquals(10,calculateAvailables.getMaxNumberOfSelectionForCourses());
    }


}