import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File lecturesJsonFile = new File("inputs\\lectures.json");
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);


        File studentsJsonFile = new File("inputs\\students.json");
        Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);
        RandomStudentCompletedCourseGenerator randomStudentCompletedCourseGenerator = new RandomStudentCompletedCourseGenerator();
        ArrayList<Integer> randomCompletedCourseNumbers = randomStudentCompletedCourseGenerator.generate(students);
        System.out.println(randomCompletedCourseNumbers);




    }
}