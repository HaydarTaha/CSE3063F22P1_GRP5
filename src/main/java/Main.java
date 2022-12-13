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
        Person[] advisors = objectMapper.readValue(advisorJsonFile, Advisor[].class);
        File lecturesJsonFile = new File("inputs/lectures.json");
        Courses[] courses = objectMapper.readValue(lecturesJsonFile, Courses[].class);

        File UEJson = new File("inputs/UE.json");
        Courses[] UE = objectMapper.readValue(UEJson, Courses[].class);

        File TEJson = new File("inputs/TE.json");
        Courses[] TE = objectMapper.readValue(TEJson, Courses[].class);

        File NTEJson = new File("inputs/NTE.json");
        Courses[] NTE = objectMapper.readValue(NTEJson, Courses[].class);

        File FTEJson = new File("inputs/FTE.json");
        Courses[] FTE = objectMapper.readValue(FTEJson, Courses[].class);


        File studentsJsonFile = new File("inputs/studentInformation.json");
        Person[] students = objectMapper.readValue(studentsJsonFile, Student[].class);
        //We go to generateStudent here to simulate their CompletedCourses



        GenerateStudent generateStudent = new GenerateStudent((Student[]) students, courses,UE,TE,NTE,FTE);
        generateStudent.simulate();
        //Then we print the transcript


        Transcript transcript = new Transcript();
        transcript.generateAvailableCourses((Student[]) students, (Advisor[]) advisors, courses);
        transcript.generateTranscriptForAllStudents((Student[]) students);
        transcript.generateTranscriptJson((Student[]) students);
        for (Courses courses1 : courses){
            System.out.println(courses1.getCourseCode() + " " + courses1.getQuota());
        }

    }
}