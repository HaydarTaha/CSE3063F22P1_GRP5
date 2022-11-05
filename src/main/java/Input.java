
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Input {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(".\\\\inputs\\\\students.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String studentId =  (String) jsonObject.get("studentId");
            String fname = (String) jsonObject.get("fName");
            String lname = (String) jsonObject.get("lName");

            System.out.println("Student Id:" + studentId);
            System.out.println("First Name:" + fname);
            System.out.println("Last Name:" + lname);



        } catch (Exception e){
            e.printStackTrace();

        }

    }
}
