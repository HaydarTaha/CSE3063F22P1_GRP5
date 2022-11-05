import netscape.javascript.JSException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

public class Output {
    public static void main(String args[]) throws Exception{
        File fName = new File("./inputs/Studentfname.txt");
        File lName = new File("./inputs/Studentlname.txt");

        creatingStudentID();

    }

    public static void creatingStudentID()throws Exception {

        int number19 = 150119000;
        int number18 = 150118000;

        int[] studentId19 = new int[250];
        int[] studentId18 = new int[250];

        for (int i = 0; i < 250; i++) {
            studentId18[i] = number18 + 4 * i;
            System.out.println(studentId18[i]);
        }

        for (int i = 0; i < 250; i++) {
            studentId19[i] = number19 + 4 * i;
            System.out.println(studentId19[i]);
        }




        FileWriter fileWriter = new FileWriter("./inputs/studentIDs.json");

        JSONArray jsonArray = new JSONArray();

        for (int i: studentId18){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("studentId", i);
            jsonArray.add(jsonObject);
        }
        for (int i: studentId19){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("studentId", i);
            jsonArray.add(jsonObject);
        }

        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.write(jsonArray.toString());


    }
}