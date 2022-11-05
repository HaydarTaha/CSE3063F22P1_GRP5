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
        System.out.println("************************");
        creatingStudentID();
        System.out.println("************************");

        try (PrintWriter out = new PrintWriter(new FileWriter("./inputs/studentNames.json"))){
            out.write(json.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    public static void creatingStudentID() {
            int number19 = 150119000;
            int number18 = 150118000;
            int number17 = 150117000;
            int number16 = 150116000;

            int[] studentId19 = new int[250];
            int[] studentId18 = new int[250];
            int[] studentId17 = new int[250];
            int[] studentId16 = new int[250];

            for (int i = 0; i < 250; i++) {
                studentId19[i] = number19 + 4 * i;
                System.out.println(studentId19[i]);
            }

            for (int i = 0; i < 250; i++) {
                studentId18[i] = number18 + 4 * i;
                System.out.println(studentId18[i]);
            }


            for (int i = 0; i < 250; i++) {
                studentId17[i] = number17 + 4 * i;
                System.out.println(studentId17[i]);
            }

            for (int i = 0; i < 250; i++) {
                studentId16[i] = number16 + 4 * i;
                System.out.println(studentId16[i]);
            }

        }
    }
}