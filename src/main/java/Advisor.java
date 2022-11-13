import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Advisor extends Student {
    private int advisorId;
    private String fName;

    private String lName;

    private List<String> studentsLooking;

    public void setfName(String fName){ this.fName = fName;}

    public void setlName(String lName){ this.lName = lName;}

    public void setStudentsLooking(List<String> studentsLooking){
        this.studentsLooking = studentsLooking;
    }

    String getfName() { return fName; }

    String getlName() { return lName; }

    List<String> getStudentsLooking() { return studentsLooking; }

    public void advisorControl(ArrayList<String> chosenClasses, Student student){
        Scanner scanner = new Scanner(System.in);
        String userPromt;
        String reason;
        //TODO
        // student.setRejectedList();
        // Buraya rejectedList yaparken ilk eleman rejected course ismi diğeri sebep şeklinde yapın rejectedListWithReasons = [CSE1142, zaman uyuşmuyor, CSE 3033, kota dolu, CSE3063, kota çok az] gibi
        // ondan sonra student.changeCurrentCourses(approvedList, rejectedListWithReasons) en sonunda yapın
        // En son olarak hoca burada manuel olarak değilde dersleri otomatik approve/reject yapsın demiş. O yüzden mantığınıza göre uydurun.

        //
        Random random = new Random();
        String[] stringArray = new String[3];
        stringArray[0]="Rejected because quota is full";
        stringArray[1]="Rejected because the timing is not appropriate";
        stringArray[2]="Rejected because student cannot take many lessons";

        int randomNumber = random.nextInt(3);
        int randomNumber2 = random.nextInt(3);
        int randomNumber3 = random.nextInt(3);

        switch (randomNumber){
            case 1:
                if (randomNumber2==0 && randomNumber3==0){
                    System.out.println(stringArray[0]);
                }
                break;
            case 2:
                if (randomNumber2==1 && randomNumber3==1){
                    System.out.println(stringArray[1]);
                }
                break;
            case 3:
                if (randomNumber2==2 && randomNumber3==2){
                    System.out.println(stringArray[1]);
                }
                break;
        }

        /*for (int i = 0; i< studentCoursesTook.size(); i +=2) {
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i + 1);
        }
        for (int i = 0; i< studentCoursesTook.size(); i +=2) {
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i + 1);
        }*/


        for (int i=0; i<chosenClasses.size(); i++){
            System.out.println("Do you accept course: " + chosenClasses.get(i) + "from student: " + student.getfName() + " " + student.getlName());
            userPromt= scanner.nextLine();
            if (userPromt.equals("Yes")){
                ;
            }
            else if(userPromt.equals("No")){
                System.out.println("Why do you reject?");
                reason=scanner.nextLine();
                System.out.println(chosenClasses.get(i) + " Course is rejected. The reason is " + reason);
                chosenClasses.remove(i);
                i--;
            }
        }
    }
}