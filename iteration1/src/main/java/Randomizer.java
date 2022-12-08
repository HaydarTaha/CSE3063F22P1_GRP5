import java.io.IOException;
import java.util.*;

public class Randomizer{
    //This method basically sets AvailableCourses for every student
    //While reading CompletedCourses and checking if he failed a course he has to take it again
    //Or if it is a prerequisite for another course we delete that course so he cant take it
    public void setAvailableCoursesForEachStudent(Student[] students, Courses[] courses , Advisor[] advisors) throws IOException {
        //First we add every course to an arraylist for each semester for later use.
        addCourseNames(students, courses);
        List<String> semesterOneCoursesNames = new ArrayList<String>();
        List<String> semesterTwoCoursesNames = new ArrayList<String>();
        List<String> semesterThreeCoursesNames = new ArrayList<String>();
        List<String> semesterFourCoursesNames = new ArrayList<String>();
        List<String> semesterFiveCoursesNames = new ArrayList<String>();
        List<String> semesterSixCoursesNames = new ArrayList<String>();
        List<String> semesterSevenCoursesNames = new ArrayList<String>();
        List<String> semesterEigthCoursesNames = new ArrayList<String>();
        List<String> studentCoursesTook;
        String prerequisite;
        //We have the same arraylist but these will be changed for each student.
        ArrayList<String> calculatedSemesterTwoCourseNames;
        ArrayList<String> calculatedSemesterThreeCourseNames;
        ArrayList<String> calculatedSemesterFourCourseNames;
        ArrayList<String> calculatedSemesterFiveCourseNames;
        ArrayList<String> calculatedSemesterSixCourseNames;
        ArrayList<String> calculatedSemesterSevenCourseNames;
        ArrayList<String> calculatedSemesterEigthCourseNames;
        String courseName = "";
        String courseGrade = "";
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
        //And also here we need to reset calculatedSemesterCourses lists to fresh ones
        //After every student iteration here Because each student will have different calculated courses lists
        //They can take
        for (Student student : students){
            calculatedSemesterTwoCourseNames = new ArrayList<>(semesterTwoCoursesNames);
            calculatedSemesterThreeCourseNames = new ArrayList<>(semesterThreeCoursesNames);
            calculatedSemesterFourCourseNames = new ArrayList<>(semesterFourCoursesNames);
            calculatedSemesterFiveCourseNames = new ArrayList<>(semesterFiveCoursesNames);
            calculatedSemesterSixCourseNames = new ArrayList<>(semesterSixCoursesNames);
            calculatedSemesterSevenCourseNames = new ArrayList<>(semesterSevenCoursesNames);
            calculatedSemesterEigthCourseNames = new ArrayList<>(semesterEigthCoursesNames);
            studentCoursesTook = new ArrayList<>();
            //Look through completed courses for each student here and add it to student courses took list.
            for (CompletedCourses completedCourses : student.getCompletedCourses()){
                studentCoursesTook.add(completedCourses.getCourseName());
                studentCoursesTook.add(completedCourses.getCourseGrade());
            }
            //We check for the student's semester here
            //and use switch here to do different things for each semester
            switch (student.getCurrentSemester()){
                case 1 -> student.setAvailableCourses(semesterOneCoursesNames);
                case 2 -> {
                    //We check through our studentCoursesTook list here and add every name and grade
                    //for their completed courses and then check if he got FF
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                            if (courseGrade == "FF"){
                                boolean test = false;
                                //Here we iterate every courses available until we find the same course with the completed one of the student.
                                for (Courses courses1 : courses){
                                    //If a failed course is a prerequisite to another course
                                    //We delete that course in calculated list and add the failed course back to the calculatedlist
                                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                        prerequisite = courses1.getPreRequisiteName();
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
                        //Now after finally checking through every course he failed or not we add the calculated list
                        //to his available courses for selection.
                        student.setAvailableCourses(calculatedSemesterTwoCourseNames);

                    }
                    //Same things below except for the calculatedSemester is different list
                case 3 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterThreeCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterThreeCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterThreeCourseNames.add(courseName);

                        }

                    }
                    student.setAvailableCourses(calculatedSemesterThreeCourseNames);

                }
                case 4 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterFourCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterFourCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterThreeCourseNames.add(courseName);

                            continue;
                        }

                    }
                    student.setAvailableCourses(calculatedSemesterFourCourseNames);

                }
                case 5 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterFiveCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterFiveCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterFiveCourseNames.add(courseName);

                        }

                    }
                    student.setAvailableCourses(calculatedSemesterFiveCourseNames);

                }
                case 6 ->{
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterSixCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterSixCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterSixCourseNames.add(courseName);

                        }

                    }
                    student.setAvailableCourses(calculatedSemesterSixCourseNames);

                }
                case 7 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterSevenCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterSevenCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterSevenCourseNames.add(courseName);
                        }

                    }
                    student.setAvailableCourses(calculatedSemesterSevenCourseNames);

                }
                case 8 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                        if (courseGrade == "FF"){
                            boolean test = false;
                            for (Courses courses1 : courses){
                                if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                    prerequisite = courses1.getPreRequisiteName();
                                    calculatedSemesterEigthCourseNames.remove(courses1.getCourseCode());
                                    calculatedSemesterEigthCourseNames.add(prerequisite);
                                    test = true;
                                    break;
                                }
                            }
                            if (!test) calculatedSemesterEigthCourseNames.add(courseName);
                        }

                    }
                    student.setAvailableCourses(calculatedSemesterEigthCourseNames);

                }
            }
        }
        //After we finish iterating every students and setting their availableCourses lists
        //We then call methods inside students to make them select courses mathematically
        //and then send that to advisors then advisors either accepts/rejects courses and updates their list
        //and then we calculate gpa for everystudent
        for (Student student : students){
            student.selectFromAvailableCourses();
            student.sendToAdvisorSelectedClasses(advisors);
            student.gpaCalculator(courses);
        }
    }
        //This is for adding the course names if we decide to add more courses later
      public void addCourseNames(Student[]students, Courses[] courses) throws IOException {
        List<String> semesterOneCoursesNames = new ArrayList<String>();
        List<String> semesterTwoCoursesNames = new ArrayList<String>();
        List<String> semesterThreeCoursesNames = new ArrayList<String>();
        List<String> semesterFourCoursesNames = new ArrayList<String>();
        List<String> semesterFiveCoursesNames = new ArrayList<String>();
        List<String> semesterSixCoursesNames = new ArrayList<String>();
        List<String> semesterSevenCoursesNames = new ArrayList<String>();
        List<String> semesterEigthCoursesNames = new ArrayList<String>();
        CompletedCourses completedCoursesTest = new CompletedCourses();
        List<CompletedCourses> completedCoursesList = new ArrayList<>();

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
        //When this is not set to null we were having errors so we decided to put nulls to all completed courses.
          //We then update them in another class called GenerateStudent so it will not actually be null
        for (Student student : students){
            switch (student.getCurrentSemester()){
                case 1 -> {
                    student.setCompletedCourses(null);
                }
                case 2 -> {
                    for (int i = 0; i < semesterOneCoursesNames.size(); i++){
                        completedCoursesTest = new CompletedCourses();
                        completedCoursesTest.setCourseName(semesterOneCoursesNames.get(i));
                        completedCoursesTest.setCourseGrade(assignRandomGrades());
                        completedCoursesList.add(completedCoursesTest);


                    }
                    student.setCompletedCourses(completedCoursesList);
                    completedCoursesList = new ArrayList<>();
                }
               /* case 3 -> semesterThreeCoursesNames.add(course.getName());
                case 4 -> semesterFourCoursesNames.add(course.getName());
                case 5 -> semesterFiveCoursesNames.add(course.getName());
                case 6 -> semesterSixCoursesNames.add(course.getName());
                case 7 -> semesterSevenCoursesNames.add(course.getName());
                case 8 -> semesterEigthCoursesNames.add(course.getName());*/
            }
        }


    }
    //We decided we dont need this method anymore
    //this was calculating how many course a student can finish in integers.
    /*
    public ArrayList<Integer> generate(Student[] students) throws IOException {
        int firstSemester = 0;
        int secondSemester = 8;
        int thirdSemester = 16;
        int fourthSemester = 22;
        int fifthSemester = 27;
        int sixthSemester = 33;
        int seventhSemester = 39;
        int eigthSemester = 47;
        int secondYear = 27;
        int thirdYear = 39;
        int fourthYear = 54;
        int semester = 0;
        int randomNumber = 0;
        int min = 1;
        int max = 54;
        int range = 0;


        ArrayList<Integer> studentSemester = new ArrayList<>();
        ArrayList<Integer> randomStudentCompletedCourseNumber = new ArrayList<>();



        for(int x = 0; x < students.length; x++){
            studentSemester.add(students[x].getCurrentSemester());
        }
        for (int y = 0; y< studentSemester.size(); y++){
            semester = studentSemester.get(y);
            if (semester == 1){
                randomStudentCompletedCourseNumber.add(0);
            }
            if (semester == 2){
                min = firstSemester;
                max = secondSemester;
                range = max-min +1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 3){
                min = secondSemester;
                max = thirdSemester;
                range = max-min +1;
                randomNumber = (int) (Math.random()* range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 4){
                min = thirdSemester;
                max = fourthSemester;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 5){
                min = fourthSemester;
                max = fifthSemester;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 6){
                min = fifthSemester;
                max = sixthSemester;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 7){
                min = sixthSemester;
                max = seventhSemester;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (semester == 8){
                min = seventhSemester;
                max = eigthSemester;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
        }
        return randomStudentCompletedCourseNumber;
    }
    */
    //This method basically returns random grades whenever we call it
    public static String assignRandomGrades() throws IOException{

        String[] grades = {"AA","BA","BB","CB","CC","DC","DD","FF"};

        Random random = new Random();
        int number=0;
        int number2=0;
        String randomLetter="";

        number = random.nextInt(8);
        number2 = random.nextInt(2);
        //We have about %13 chance for every grade except for FF which has about %5 chance
        //and CC which we have like about %18 chance for that
        switch(number){
            case 0:
                randomLetter = "AA";
                break;
            case 1:
                randomLetter = "BA";
                break;
            case 2:
                randomLetter = "BB";
                break;
            case 3:
                randomLetter = "CB";
                break;
            case 4:
                randomLetter = "CC";
                break;
            case 5:
                randomLetter = "DC";
                break;
            case 6:
                randomLetter = "DD";
                break;
            case 7:
                if (number2==1) {
                    randomLetter = "FF";
                }
                else randomLetter = "CC";
                break;

        }
      /*  FileWriter fileWriter = new FileWriter("./inputs/studentGrades.json");
        JSONArray jsonArray = new JSONArray();

        for (String i: studentsArray){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseName", null);
            jsonObject.put("courseGrade", i);
            jsonArray.add(jsonObject);
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.write(jsonArray.toString());
*/
        return randomLetter;

    }
}
