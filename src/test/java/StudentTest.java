import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
}