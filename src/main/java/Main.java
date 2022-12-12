import java.io.*;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper();
        BasicConfigurator.configure();
        //We used Jackson Library from Maven here to map these jsons to create corresponding lists of objects
        File advisorJsonFile = new File("inputs/Advisors.json");
        Advisor[] advisors = objectMapper.readValue(advisorJsonFile, Advisor[].class);
        File lecturesJsonFile = new File("inputs/lectures.json");
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);
        File studentsJsonFile = new File("inputs/studentInformation.json");
        Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);
        //We go to generateStudent here to simulate their CompletedCourses
        GenerateStudent generateStudent = new GenerateStudent(students, courses);
        generateStudent.simulate();
        //Then we print the transcript
        Transcript transcript = new Transcript();
        transcript.generateAvailableCourses(students, advisors, courses);
        transcript.generateTranscriptForAllStudents(students);
        transcript.generateTranscriptJson(students);
        for (Courses courses1 : courses){
            System.out.println(courses1.getCourseCode() + " " + courses1.getQuota());
        }

    }
}