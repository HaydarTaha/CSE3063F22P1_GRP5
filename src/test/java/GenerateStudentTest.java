import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GenerateStudentTest {

    ObjectMapper objectMapper = new ObjectMapper();
    File courseJson = new File("inputs/lectures.json");
    Courses[] coursesArray = objectMapper.readValue(courseJson, Courses[].class);

    GenerateStudentTest() throws IOException {
    }

    @Test
    void checkCourseHasPrerequisite() {

        boolean control=true;
        boolean actual=false;

        if(coursesArray[10].getPrerequisite().contains("")){
            actual=false;
        }else{
            actual=true;
        }
        assertEquals(control,actual);
    }

    @Test
    void checkPrerequisiteCourseIsGiven() {

    }

    @Test
    void checkAssignedGrade() throws IOException {
        GenerateStudent s1 = new GenerateStudent();
        s1.setCourseFFRate(100);
        String result1 = s1.assignRandomGrades();
        assertNotEquals("", result1);
    }

}