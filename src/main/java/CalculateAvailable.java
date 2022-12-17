import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class CalculateAvailable {
    Logger logger = Logger.getLogger(CalculateAvailable.class.getName());
    List<String> semesterOneCoursesNames = new ArrayList<String>();
    List<String> semesterTwoCoursesNames = new ArrayList<String>();
    List<String> semesterThreeCoursesNames = new ArrayList<String>();
    List<String> semesterFourCoursesNames = new ArrayList<String>();
    List<String> semesterFiveCoursesNames = new ArrayList<String>();
    List<String> semesterSixCoursesNames = new ArrayList<String>();
    List<String> semesterSevenCoursesNames = new ArrayList<String>();
    List<String> semesterEigthCoursesNames = new ArrayList<String>();
    ArrayList<String> calculatedSemesterTwoCourseNames;
    ArrayList<String> calculatedSemesterThreeCourseNames;
    ArrayList<String> calculatedSemesterFourCourseNames;
    ArrayList<String> calculatedSemesterFiveCourseNames;
    ArrayList<String> calculatedSemesterSixCourseNames;
    ArrayList<String> calculatedSemesterSevenCourseNames;
    ArrayList<String> calculatedSemesterEightCourseNames;
    List<String> studentCoursesTook;
    String courseName;
    String courseGrade;
    String prerequisite;

    public void setMaxNumberOfSelectionForCourses(int maxNumberOfSelectionForCourses) {
        this.maxNumberOfSelectionForCourses = maxNumberOfSelectionForCourses;
    }

    public int getMaxNumberOfSelectionForCourses() {
        return maxNumberOfSelectionForCourses;
    }

    int maxNumberOfSelectionForCourses;

    public void setAttributes(Courses[] courses){
        //Everytime i call this method i need to update these lists again
        for (Courses course : courses) {
            switch (course.getSemester()) {
                case 1 -> semesterOneCoursesNames.add(course.getCourseCode());
                case 2 -> semesterTwoCoursesNames.add(course.getCourseCode());
                case 3 -> semesterThreeCoursesNames.add(course.getCourseCode());
                case 4 -> semesterFourCoursesNames.add(course.getCourseCode());
                case 5 -> semesterFiveCoursesNames.add(course.getCourseCode());
                case 6 -> semesterSixCoursesNames.add(course.getCourseCode());
                case 7 -> semesterSevenCoursesNames.add(course.getCourseCode());
                case 8 -> semesterEigthCoursesNames.add(course.getCourseCode());
            }
        }
    }
    public ArrayList<String> putAvailableCoursesCaseTwo(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                //Here we iterate every courses available until we find the same course with the completed one of the student.
                for (Courses courses1 : courses){
                    //If a failed course is a prerequisite to another course
                    //We delete that course in calculated list and add the failed course back to the calculatedlist
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterTwoCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterTwoCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                //If he failed but there is not any prerequisite needed for above courses
                // we add the same course again but we do not remove anything.
                if (!test){
                    calculatedSemesterTwoCourseNames.add(courseName);
                }
            }
        }
        return calculatedSemesterTwoCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseThree(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterThreeCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterThreeCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterThreeCourseNames.add(courseName);
            }
        }
        return calculatedSemesterThreeCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseFour(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterFourCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterFourCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterThreeCourseNames.add(courseName);
            }
        }
        return calculatedSemesterFourCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseFive(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterFiveCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterFiveCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterFiveCourseNames.add(courseName);
            }
        }
        return calculatedSemesterFiveCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseSix(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.warning(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterSixCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterSixCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterSixCourseNames.add(courseName);
            }
        }
        return calculatedSemesterSixCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseSeven(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterSevenCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterSevenCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterSevenCourseNames.add(courseName);
            }
        }
        return calculatedSemesterSevenCourseNames;
    }
    public ArrayList<String> putAvailableCoursesCaseEigth(Courses[] courses, List<String> studentCoursesTook,String stdName){
        for (int i = 0; i< studentCoursesTook.size(); i +=2){
            courseName = studentCoursesTook.get(i);
            courseGrade = studentCoursesTook.get(i+1);
            if (Objects.equals(courseGrade, "FF")){
                boolean test = false;
                for (Courses courses1 : courses){
                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                        prerequisite = courses1.getPreRequisiteName();
                        logger.info(stdName + " Failed: " + prerequisite + " He cannot choose " + courses1.getCourseCode());
                        calculatedSemesterEightCourseNames.remove(courses1.getCourseCode());
                        calculatedSemesterEightCourseNames.add(prerequisite);
                        test = true;
                        break;
                    }
                }
                if (!test) calculatedSemesterEightCourseNames.add(courseName);
            }
        }
        return calculatedSemesterEightCourseNames;
    }
    public void calculatedCoursesSetter(){
        calculatedSemesterTwoCourseNames = new ArrayList<>(semesterTwoCoursesNames);
        calculatedSemesterThreeCourseNames = new ArrayList<>(semesterThreeCoursesNames);
        calculatedSemesterFourCourseNames = new ArrayList<>(semesterFourCoursesNames);
        calculatedSemesterFiveCourseNames = new ArrayList<>(semesterFiveCoursesNames);
        calculatedSemesterSixCourseNames = new ArrayList<>(semesterSixCoursesNames);
        calculatedSemesterSevenCourseNames = new ArrayList<>(semesterSevenCoursesNames);
        calculatedSemesterEightCourseNames = new ArrayList<>(semesterEigthCoursesNames);
        studentCoursesTook = new ArrayList<>();
    }
    //This method basically sets AvailableCourses for every student
    //While reading CompletedCourses and checking if he failed a course he has to take it again
    //Or if it is a prerequisite for another course we delete that course so he cant take it
    public void setAvailableCoursesForEachStudent(Student[] students, Courses[] courses , Advisor[] advisors, Courses[] UE, Courses[] TE, Courses[] FTE, Courses[] NTE, int maxNumberOfSelectionForCourses) throws IOException {
        //First we add every course to an arraylist for each semester for later use.
        setAttributes(courses);
        for (Student student : students){
            //And also here we need to reset calculatedSemesterCourses lists to fresh ones
            calculatedCoursesSetter();
            //Look through completed courses for each student here and add it to student courses took list.
            for (Courses completedCourses : student.getCompletedCourses()){
                studentCoursesTook.add(completedCourses.getName());
                studentCoursesTook.add(completedCourses.getCourseGrade());
            }
            //We check for the student's semester here
            switch (student.getCurrentSemester()){
                //We check through our studentCoursesTook list here and add every name and grade
                //Now after finally checking through every course he failed or not we add the calculated list
                //to his available courses for selection.
                case 1 -> student.setAvailableCourses(semesterOneCoursesNames);
                case 2 -> student.setAvailableCourses(putAvailableCoursesCaseTwo(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 3 -> student.setAvailableCourses(putAvailableCoursesCaseThree(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 4 -> student.setAvailableCourses(putAvailableCoursesCaseFour(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 5 -> student.setAvailableCourses(putAvailableCoursesCaseFive(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 6 -> student.setAvailableCourses(putAvailableCoursesCaseSix(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 7 -> student.setAvailableCourses(putAvailableCoursesCaseSeven(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
                case 8 -> student.setAvailableCourses(putAvailableCoursesCaseEigth(courses, studentCoursesTook, student.getfName() + " " + student.getlName()));
            }
        }
        //After we finish iterating every students and setting their availableCourses lists
        //We then call methods inside students to make them select courses mathematically
        //and then send that to advisors then advisors either accepts/rejects courses and updates their list
        //and then we calculate gpa for everystudent
        setStudentsForEachAdvisor(students, advisors);
        for (Student student : students){
            student.selectFromAvailableCourses(maxNumberOfSelectionForCourses);
            student.chooseFromElectiveCourses(UE, FTE, NTE, TE);
            student.sendToAdvisorSelectedClasses();
            student.gpaCalculator(courses);
        }
        setStudentsForEachCourses(students, courses);

    }

    public void setStudentsForEachAdvisor(Student[] students, Advisor[] advisors){
        for (Advisor advisor : advisors){
            for (Student student : students){
                if (student.getAdvisor() == advisor){
                    advisor.addAdvisorsLookingList(student);
                }
            }
        }
    }
    public void setStudentsForEachCourses(Student[] students, Courses[] courses){
        for (Student std : students){
            for (String s : std.getCurrentSelectedCourses()){
                for (Courses crs : courses){
                    if (Objects.equals(crs.getCourseCode(), s)){
                        crs.addToListOfStudents(std);
                    }
                }
            }
        }
    }
}
