import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

public class Output {
    public static void main(String args[]) throws Exception{
        File fName = new File("./inputs/Studentfname.txt");
        File lName = new File("./inputs/Studentlname.txt");
        BufferedReader fNameBr = new BufferedReader(new FileReader(fName));
        BufferedReader lNameBr = new BufferedReader(new FileReader(lName));

        String fNameStr, lNameStr;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        FileWriter fileWriter = new FileWriter("./inputs/studentNames.json");

        while ((fNameStr =fNameBr.readLine()) != null && (lNameStr = lNameBr.readLine()) != null){
            JSONObject json = new JSONObject();
            json.put("fName", fNameStr);
            json.put("lName", lNameStr);
            jsonArray.add(json);
        }
        PrintWriter out = new PrintWriter(fileWriter);
        out.write(jsonArray.toString());

    }
}