import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Advisor extends Student {
    //Basic attributes of advisors
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
    //This function has a mathematical operation
    //it either accepts a course or rejects it and each has a chance
    //explained below
    public void advisorControl(List<String> chosenClasses, Student student){

        Random random = new Random();
        String[] stringArray = new String[3];
        //We have 3 reasons for rejection so we put them here.
        stringArray[0]="Rejected because quota is full";
        stringArray[1]="Rejected because the timing is not appropriate";
        stringArray[2]="Rejected because student cannot take many lessons";

        ArrayList<String> rejectedList = new ArrayList<>();
        ArrayList<String> acceptedList = new ArrayList<>();

        int randomNumber;
        int randomNumber2;
        int randomNumber3;
        //this gets size of parameter chosenClasses
        int courseSize = chosenClasses.size();
        //we check its size and for each element of chosenClasses we either put it to acceptedList
        //or rejectedList with reason.
        //We decided we accept/reject randomly from the list, rejection has about %5 chance
        //We will decide what to do with them on next iteration.
        for (int i=0; i<courseSize; i++){
            randomNumber = random.nextInt(3);
            randomNumber2 = random.nextInt(3);
            randomNumber3 = random.nextInt(3);
            switch (randomNumber){
                case 0:
                    //For this we have 1/3 chance of rejection with the reason
                    if (randomNumber2==0 && randomNumber3==0){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
                case 1:
                    // another 1/3 chance of rejection but with another reason
                    if (randomNumber2==1 && randomNumber3==1){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
                case 2:
                    //same as above with another reason
                    if (randomNumber2==2 && randomNumber3==2){
                        rejectedList.add(student.getCurrentSelectedCourses().get(i));
                    }else{
                        acceptedList.add(student.getCurrentSelectedCourses().get(i));
                    }
                    break;
            }
        }
        //Then we call changeSelectedCourses method with courses which we accepted and rejected
        //to update their selection
        student.changeSelectedCourses(acceptedList,rejectedList, this.getfName() + " " + this.getlName());
    }
}