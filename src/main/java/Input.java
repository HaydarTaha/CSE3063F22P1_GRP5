import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Input {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(".\\inputs\\lectures.json"));
            JSONObject jsonObject = (JSONObject) obj;
            //String studentid =  (String) jsonObject.get("studentId");
            //String fname = (String) jsonObject.get("fName");
            //String lname = (String) jsonObject.get("lName");
            JSONArray lectures = (JSONArray)jsonObject.get("lectures");
            //JSONArray fname = (JSONArray)jsonObject.get("fName");
            //JSONArray lname = (JSONArray)jsonObject.get("lName");
            System.out.println("Lectures" + lectures);

            //System.out.println("First Name:" + fname);
            //System.out.println("Last Name:" + lname);

            Iterator<String> iterator = lectures.iterator();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }



        } catch (Exception e){
            e.printStackTrace();

        }

    }
}