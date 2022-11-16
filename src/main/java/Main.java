import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File advisorJsonFile = new File("inputs/Advisors.json");
        Advisor[] advisors = objectMapper.readValue(advisorJsonFile, Advisor[].class);
        File lecturesJsonFile = new File("inputs/lectures.json");
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);
        File studentsJsonFile = new File("inputs/studentInformation.json");
        Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);
        GenerateStudent generateStudent = new GenerateStudent(students, courses);
        generateStudent.simulate();
        Randomizer randomizer = new Randomizer();
        randomizer.setAvailableCoursesForEachStudent(students, courses, advisors);
        Transcript transcript = new Transcript();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        transcript.printTranscript(students);
    }
}