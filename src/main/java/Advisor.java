import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Advisor extends Student {

    private int advisorId;

    private String fName;

    private String lName;

    public int getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(int advisorId) {
        this.advisorId = advisorId;
    }

    public void setfName(String fName){ this.fName = fName;}

    public void setlName(String lName){ this.lName = lName;}

    String getfName() { return fName; }

    String getlName() { return lName; }

    public void advisorControl(List<String> chosenClasses, Student student){

        Random random = new Random();
        String[] stringArray = new String[3];
        stringArray[0]="Rejected because quota is full";
        stringArray[1]="Rejected because the timing is not appropriate";
        stringArray[2]="Rejected because student cannot take many lessons";

        ArrayList<String> rejectedList = new ArrayList<>();
        ArrayList<String> acceptedList = new ArrayList<>();

        int randomNumber;
        int randomNumber2;
        int randomNumber3;

        int courseSize = chosenClasses.size();
        for (int i=0; i<courseSize; i++){
            randomNumber = random.nextInt(3);
            randomNumber2 = random.nextInt(3);
            randomNumber3 = random.nextInt(3);
            switch (randomNumber){
                case 0:
                    if (randomNumber2==0 && randomNumber3==0){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
                case 1:
                    if (randomNumber2==1 && randomNumber3==1){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
                case 2:
                    if (randomNumber2==2 && randomNumber3==2){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
            }
        }
        student.changeSelectedCourses(acceptedList,rejectedList);
    }
}