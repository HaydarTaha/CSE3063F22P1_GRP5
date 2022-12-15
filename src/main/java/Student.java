
import java.util.*;
import org.apache.log4j.Logger;

public class Student extends Person{
    static Logger logger = Logger.getLogger(Student.class.getName());
    //Attributes for students are here
    private int studentId;

    private int totalCredit;

    private Advisor advisor;
    private double gpa;
    private int currentYear;
    private int currentSemester;
    private List<String> currentSelectedCourses;
    private List<CompletedCourses> completedCourses;
    private List<String> availableCourses;
    private List<FailedCourses> failedCourses;
    private Transcript transcript;


    //private int counter = 0;

    //Setters and Getters
    public void setfName(String fName) {
        super.setfName(fName);
    }

    public void setlName(String lName) {
        super.setlName(lName);
    }
    public Transcript getTranscript() { return transcript; }
    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
    public Advisor getAdvisor() {
        return advisor;
    }
    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }



    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }




    public void setCurrentSelectedCourses(List<String> currentSelectedCourses) {
        this.currentSelectedCourses = currentSelectedCourses;
    }

    public void setCompletedCourses(List<CompletedCourses> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setAvailableCourses(List<String> mandatoryCourses) {
        this.availableCourses = mandatoryCourses;
    }

    public List<FailedCourses> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(List<FailedCourses> failedCourses) {
        this.failedCourses = failedCourses;
    }

    int getStudentId() { return studentId; }

    double getGPA() { return gpa; }

    List<String> getCurrentSelectedCourses() { return currentSelectedCourses; }

    List<CompletedCourses> getCompletedCourses() { return completedCourses; }
    //This method selects courses which are in availableCourses
    public void selectFromAvailableCourses(int maxNumberOfSelectionForCourses){
        ArrayList<String> coursesAdd = new ArrayList<>();
        //Here if we have more than 10 available courses we first check failed courses
        //and add them first, after that we randomly select other classes until it's size is 10
        if (availableCourses.size() > maxNumberOfSelectionForCourses){
            for (String s : availableCourses){
                if (checkIfCourseFailed(s)) {
                    coursesAdd.add(s);
                    logger.info(getfName() + " " +  getlName()+" Prioritized choosing: " + s + " Because of failing before because he had more than 10 courses available for choosing");
                }
            }
            //Here we check the list again and if it has less than 10 we add them until it becomes size 10
            //we add those to the list. until it's size is 10
            if (coursesAdd.size() < maxNumberOfSelectionForCourses){
                for (String s : availableCourses) {
                    if (!coursesAdd.contains(s))
                        coursesAdd.add(s);
                    if (coursesAdd.size() == maxNumberOfSelectionForCourses){
                        break;
                    }
                }
                //then we update selectedCourses with this method
                this.currentSelectedCourses.addAll(coursesAdd);
            }
            //If however the size becomes more than 10
            //we remove some until it goes back to size 10 again.
            else if (coursesAdd.size() > maxNumberOfSelectionForCourses){
                while (coursesAdd.size() != maxNumberOfSelectionForCourses){
                    coursesAdd.remove(maxNumberOfSelectionForCourses);
                }
                this.currentSelectedCourses.addAll(coursesAdd);
            }
        }
        //If the size is not more than 10 we add every available course to selectedCourses attr.
        else {
            this.currentSelectedCourses.addAll(availableCourses);
        }



    }


    public void chooseFromElectiveCourses(Courses[] UE, Courses[] FTE, Courses[] NTE, Courses[] TE){
        for (int i = 0; i < currentSelectedCourses.size(); i++) {
            Random random = new Random();
            int value = random.nextInt(5);
            if (currentSelectedCourses.get(i).contains("UE")){
                currentSelectedCourses.set(i, UE[value].getCourseCode());
            } else if (currentSelectedCourses.get(i).contains("FTE")){
                currentSelectedCourses.set(i, FTE[value].getCourseCode());
            } else if (currentSelectedCourses.get(i).contains("NTE")){
                currentSelectedCourses.set(i, NTE[value].getCourseCode());
            } else if (currentSelectedCourses.get(i).contains("TE")){
                currentSelectedCourses.set(i, TE[value].getCourseCode());
            }
        }
    }



    List<String> getAvailableCourses() { return availableCourses; }
    //This checks if the course is failed for this student
    //and returns true if failed or false if passed
    boolean checkIfCourseFailed(String courseCode){
        boolean check = false;
        for (CompletedCourses completedCourses1 : this.completedCourses){
            if (completedCourses1.getCourseName().equals(courseCode) && completedCourses1.getCourseGrade().equals("FF")) {
                check = true;
                break;
            }
        }
        return check;
    }
    //This method sends currentSelectedCourses to the corresponding advisor for this student
    public void sendToAdvisorSelectedClasses(){
        advisor.advisorControl(currentSelectedCourses, this);
    }
    //This is a method the advisor calls
    //it changes selected courses depending on if each course is accepted or rejected and then updates it
    public void changeSelectedCourses(ArrayList<String> advisorApprovedCourses, ArrayList<String> advisorRejectedCoursesAndReasons, String advisorName){
        currentSelectedCourses.clear();
        currentSelectedCourses.addAll(advisorApprovedCourses);
        logger.info("For student: " + getfName() + " " + getlName() + "\n" +advisorName + " approved: " + advisorApprovedCourses + "\nrejected: " + advisorRejectedCoursesAndReasons);
    }
    //this returns how many courses this student finished.
    public int getCompletedCourseNumber(){
        return completedCourses.size();
    }
    //We call this to calculate GPA of this student
    public void gpaCalculator(Courses[] courses){
        String letterAA = "4.00";
        double AA = Double.parseDouble(letterAA);
        String letterBA = "3.50";
        double BA = Double.parseDouble(letterBA);
        String letterBB = "3.00";
        double BB = Double.parseDouble(letterBB);
        String letterCB = "2.50";
        double CB = Double.parseDouble(letterCB);
        String letterCC = "2.00";
        double CC = Double.parseDouble(letterCC);
        String letterDC = "1.50";
        double DC = Double.parseDouble(letterDC);
        String letterDD = "1.00";
        double DD = Double.parseDouble(letterDD);
        String letterFD = "0.50";
        double FD = Double.parseDouble(letterFD);
        String letterFF = "0.00";
        double FF = Double.parseDouble(letterFF);

        int a = getCompletedCourseNumber();

        int credit;
        double sum = 0;
        int creditSum = 0;
        int transcriptCreditSum = 0;
        credit = 0;
        sum = 0;
        for (CompletedCourses completedCourses1 : completedCourses) {
            for (Courses courses1 : courses){
                if (completedCourses1.getCourseName().equals(courses1.getCourseCode())) {
                    credit = courses1.getCredit();
                    break;
                }
            }
            switch (completedCourses1.getCourseGrade()) {
                case "AA" -> {
                    sum = sum + (AA * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "BA" -> {
                    sum = sum + (BA * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "BB" -> {
                    sum = sum + (BB * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "CB" -> {
                    sum = sum + (CB * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "CC" -> {
                    sum = sum + (CC * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "DC" -> {
                    sum = sum + (DC * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "DD" -> {
                    sum = sum + (DD * credit);
                    creditSum = creditSum + credit;
                    transcriptCreditSum = transcriptCreditSum + credit;
                }
                case "FD" -> {
                    sum = sum + (FD * credit);
                    creditSum = creditSum + credit;
                }
                case "FF" -> {
                    sum = sum + (FF * credit);
                    creditSum = creditSum + credit;
                }
                default -> System.out.println("Hatali giris yaptiniz.");
            }
        }
        double GPA = (int)((sum / creditSum) * 100.0) / 100.0 ;
        this.gpa = GPA;
        this.totalCredit = transcriptCreditSum;

    }
    public String getAdvisorName(){
        return advisor.getfName() + " " + advisor.getlName();
    }
    public void generateTranscript(){
        Transcript transcript = new Transcript(this.completedCourses, this.failedCourses, this.getGPA(), this.totalCredit, this.getCurrentSelectedCourses(), getAdvisorName(), this);
        this.transcript = transcript;
        transcript.printTranscriptSpecificStudent(this);
    }


}

    /*public void setCompletedCoursesFromGivenArray(String courseName, String courseGrade, ArrayList<String> arrayOfCourses){
        CompletedCourses completedcoursesTest = new CompletedCourses();
        completedcoursesTest.setCourseName(courseName);
        completedcoursesTest.setCourseGrade(courseGrade);
        for (int i = 0 ; i < arrayOfCourses.size(); i ++){
            this.completedCourses.set(i, completedcoursesTest);
        }

        for (int i = 0; i < courseName.size(); i++){
            completedCourses.add(courseName.get(i))
        }
    }*/

