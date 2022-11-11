import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.File;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;


public class Init {


    public void StudentGenerator(int numberofStudents) throws IOException, ParseException {
        String[] studentIDs = {};
        for (int i = 0; i <numberofStudents; i++){
            String studentID = generateStudentId(studentIDs);
            studentIDs = new String[]{studentID};
        }
    }

    public String generateStudentId(String[] arrayList) {
        int min = 0;
        int max = 9;
        int range = 0;
        range = max - min + 1;
        StringBuilder studentID = new StringBuilder();
        int randomNumber;
        for (int i = 0; i < 9; i++) {
            randomNumber = (int) (Math.random() * range) + min;
            studentID.append(randomNumber);
        }
        System.out.println();
        String newStudentId;
        newStudentId = studentID.toString();

        return studentID.toString();
    }

}



