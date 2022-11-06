import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File studentsJsonFile = new File("deneme.json");
        Student[] students = objectMapper.readValue(studentsJsonFile, Student[].class);
        for(int i = 0; i < 2; i++){
            System.out.println(students[i].getfName());
            for(int j = 0; j < 2; j++){
                System.out.println(students[i].getCompletedCourses().get(j).getCourseName());
            }
        }
    }
}