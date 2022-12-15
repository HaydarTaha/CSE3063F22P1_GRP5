import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class InputTest {

    ObjectMapper objectMapper = new ObjectMapper();
    File inputsJsonFile = new File("inputs/input.json");
    Input input = objectMapper.readValue(inputsJsonFile, Input.class);

    InputTest() throws IOException {
    }

    @Test
    void testGetCourseFFRate() {
        int expectedCourseFFRate = 20;
        assertEquals(expectedCourseFFRate,input.getCourseFFRate());
    }

    @Test
    void testGetQuotaForElectives() {
        int expecteQuotaForElectives = 20;
        assertEquals(expecteQuotaForElectives,input.getCourseFFRate());
    }

    @Test
    void testgetQuotaForMandatory() {
        int expectedQuotaForMandatory = 150;
        assertEquals(expectedQuotaForMandatory,input.getQuotaForMandatory());
    }

    @Test
    void testgetMaxNumberOfSelectionForCourses() {
        int expectedMaxNumberOfSelectionForCourses = 10;
        assertEquals(expectedMaxNumberOfSelectionForCourses,input.getMaxNumberOfSelectionForCourses());
    }


    // TODO: both will be updated later
    @Test
    void testCreateObjects() {

    }
    @Test
    void testStartSimulationWithInputs() {

    }


}
