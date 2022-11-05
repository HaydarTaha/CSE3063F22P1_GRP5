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

    public void randomID(){
        int number19 = 150119000;
        int number18 = 150118000;
        int number17 = 150117000;
        int number16 = 150116000;

        int studentId;

        for (int i = number18; i<number19; i=i+4){
            studentId=i;
        }





    }
}