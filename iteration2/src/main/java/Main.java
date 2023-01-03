import java.io.*;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper();
        BasicConfigurator.configure();
        //We used Jackson Library from Maven here to map these jsons to create corresponding lists of objects
        File inputsJsonFile = new File("inputs/input.json");
        Input input = objectMapper.readValue(inputsJsonFile, Input.class);
        input.createObjects();
        input.startSimulationWithInputs();
    }
}