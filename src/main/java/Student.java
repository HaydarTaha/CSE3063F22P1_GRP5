import java.io.File;
import java.io.FileWriter;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;


public class Student {
    private int studentId;
    private String fName;
    private String lName;
    private int totalCredit;

    private int advisorId;
    private double gpa;
    private int currentYear;
    private int currentSemester;
    private List<String> currentSelectedCourses;
    private List<CompletedCourses> completedCourses;

    private List<String> availableCourses;
    private List<FailedCourses> failedCourses;
    private int counter = 0;



    public int getCurrentYear() {
        return currentYear;
    }
    public int getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(int advisorId) {
        this.advisorId = advisorId;
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

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }
    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
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

    String getfName() { return fName; }

    String getlName() { return lName; }

    double getGPA() { return gpa; }

    List<String> getCurrentSelectedCourses() { return currentSelectedCourses; }

    List<CompletedCourses> getCompletedCourses() { return completedCourses; }

    public void selectFromAvailableCourses(){
        ArrayList<String> coursesAdd = new ArrayList<>();
        if (availableCourses.size() > 10){
            for (String s : availableCourses){
                if (checkIfCourseFailed(s)) {
                    coursesAdd.add(s);
                }
            }
            if (coursesAdd.size() < 10){
                for (String s : availableCourses) {
                    while (coursesAdd.size() != 10){
                        coursesAdd.add(s);
                    }
                }
                this.currentSelectedCourses.addAll(coursesAdd);
            }
            else if (coursesAdd.size() > 10){
                while (coursesAdd.size() != 10){
                    coursesAdd.remove(10);
                }
                this.currentSelectedCourses.addAll(coursesAdd);
            }
        }
        else {
            this.currentSelectedCourses.addAll(availableCourses);
        }


    }


    List<String> getAvailableCourses() { return availableCourses; }

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
    public void sendToAdvisorSelectedClasses(Advisor[]advisors){
        for (Advisor advisor : advisors){
            if (this.advisorId == advisor.getAdvisorId()){
                advisor.advisorControl(currentSelectedCourses, this);
            }
        }
    }

    public void changeSelectedCourses(ArrayList<String> advisorApprovedCourses, ArrayList<String> advisorRejectedCoursesAndReasons){
        currentSelectedCourses.clear();
        currentSelectedCourses.addAll(advisorApprovedCourses);
        System.out.println(currentSelectedCourses);
        //TODO: decide what to do with rejected courses and their reasons
    }
    public int getCompletedCourseNumber(){
        return completedCourses.size();
    }

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
            if (completedCourses1.getCourseGrade().equals("AA")) {
                sum = sum + (AA * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("BA")) {
                sum = sum + (BA * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("BB")) {
                sum = sum + (BB * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("CB")) {
                sum = sum + (CB * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("CC")) {
                sum = sum + (CC * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("DC")) {
                sum = sum + (DC * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("DD")) {
                sum = sum + (DD * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;
                transcriptCreditSum = transcriptCreditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("FD")) {
                sum = sum + (FD * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;

            } else if (completedCourses1.getCourseGrade().equals("FF")) {
                sum = sum + (FF * credit);
                System.out.println("Sum = " + sum);
                creditSum = creditSum + credit;

            } else {
                System.out.println("Hatali giris yaptiniz.");
            }
        }

        //  System.out.println("Total Credit : " + creditSum);
        double GPA = (int)((sum / creditSum) * 100.0) / 100.0 ;
        this.gpa = GPA;
       // System.out.println("gpa : " + gpa);
        // System.out.println(transcriptCreditSum);
        creditSum = 0;
        totalCredit = transcriptCreditSum;

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

