import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            //Object obj = parser.parse(new FileReader("/Users/taha/IdeaProjects/CSE3063F22P1_GRP5/inputs/lectures.json"));
            //JSONObject jsonObject = (JSONObject)obj;
            System.out.println("Hello World!");

        } catch (Exception e){
            e.printStackTrace();

        }

    }
}