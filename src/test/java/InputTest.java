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


    // TODO: both will be updated later
    @Test
    void testCreateObjects() {

    }
    @Test
    void testStartSimulationWithInputs() {

    }


}
