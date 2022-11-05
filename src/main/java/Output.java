import netscape.javascript.JSException;
import org.json.simple.JSONObject;

import java.io.*;

public class Output {
    public static void main(String args[]) throws Exception{
        File fName = new File("./inputs/Studentfname.txt");
        File lName = new File("./inputs/Studentlname.txt");
        BufferedReader fNameBr = new BufferedReader(new FileReader(fName));
        BufferedReader lNameBr = new BufferedReader(new FileReader(lName));

        String fNameStr, lNameStr;

        JSONObject json = new JSONObject();
        try {
            while ((fNameStr =fNameBr.readLine()) != null && (lNameStr = lNameBr.readLine()) != null){
                json.put("name", fNameStr + " " + lNameStr);
            }
        } catch (JSException e){
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("./inputs/studentNames.json"))){
            out.write(json.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}