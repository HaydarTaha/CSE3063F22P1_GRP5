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
    public static void main(String[] args) throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        File lecturesJsonFile = new File("inputs\\lectures.json");
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);
        RandomStudentCompletedCourseGenerator test = new RandomStudentCompletedCourseGenerator();


        File studentsJsonFile = new File("inputs\\students.json");
        Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);



        RandomStudentCompletedCourseGenerator randomStudentCompletedCourseGenerator = new RandomStudentCompletedCourseGenerator();
        randomStudentCompletedCourseGenerator.addCourseNames(students, courses);
        randomStudentCompletedCourseGenerator.setAvailableCoursesForEachStudent(students, courses);
        System.out.println(students[0].getCompletedCourses());
        System.out.println(students[1].getCompletedCourses());
        System.out.println(students[0].getAvailableCourses());
        System.out.println(students[1].getAvailableCourses());


        File inputJsonFile = new File("inputs\\input.json");
        Input[] inputs = objectMapper.readValue(inputJsonFile, Input[].class);
        for (int i = 0; i < 1; i++){
            System.out.println(inputs[i].getNumberOfStudents());
        }


      /*  RandomStudentCompletedCourseGenerator randomStudentCompletedCourseGenerator = new RandomStudentCompletedCourseGenerator();
        ArrayList<Integer> randomCompletedCourseNumbers = randomStudentCompletedCourseGenerator.generate(students);
        System.out.println(randomCompletedCourseNumbers);*/




    }
}