import java.io.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
        //We then go to randomizer to get their availableCourses calculated from CompletedCourses
        Randomizer randomizer = new Randomizer();
        randomizer.setAvailableCoursesForEachStudent(students, courses, advisors);
        //Then we print the transcript
        Transcript transcript = new Transcript();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        transcript.printTranscript(students);
    }
}