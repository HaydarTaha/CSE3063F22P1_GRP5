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


}