import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class RandomStudentCompletedCourseGenerator{
    public void setAvailableCoursesForEachStudent(Student[] students, Courses[] courses){
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
        ArrayList<String> calculatedSemesterTwoCourseNames;
        ArrayList<String> calculatedSemesterThreeCourseNames;
        ArrayList<String> calculatedSemesterFourCourseNames;
        ArrayList<String> calculatedSemesterFiveCourseNames;
        ArrayList<String> calculatedSemesterSixCourseNames;
        ArrayList<String> calculatedSemesterSevenCourseNames;
        ArrayList<String> calculatedSemesterEigthCourseNames;
        String courseName = "";
        String courseGrade = "";
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

        for (Student student : students){
            calculatedSemesterTwoCourseNames = new ArrayList<>(semesterTwoCoursesNames);
            calculatedSemesterThreeCourseNames = new ArrayList<>(semesterThreeCoursesNames);
            calculatedSemesterFourCourseNames = new ArrayList<>(semesterFourCoursesNames);
            calculatedSemesterFiveCourseNames = new ArrayList<>(semesterFiveCoursesNames);
            calculatedSemesterSixCourseNames = new ArrayList<>(semesterSixCoursesNames);
            calculatedSemesterSevenCourseNames = new ArrayList<>(semesterSevenCoursesNames);
            calculatedSemesterEigthCourseNames = new ArrayList<>(semesterEigthCoursesNames);
            studentCoursesTook = new ArrayList<>();
            for (CompletedCourses completedCourses : student.getCompletedCourses()){
                studentCoursesTook.add(completedCourses.getCourseName());
                studentCoursesTook.add(completedCourses.getCourseGrade());
            }
            switch (student.getCurrentSemester()){
                case 1 -> student.setAvailableCourses(semesterOneCoursesNames);
                case 2 -> {
                    for (int i = 0; i< studentCoursesTook.size(); i +=2){
                        courseName = studentCoursesTook.get(i);
                        courseGrade = studentCoursesTook.get(i+1);
                            if (courseGrade == "FF"){
                                boolean test = false;
                                for (Courses courses1 : courses){
                                    if (Objects.equals(courseName, courses1.getPreRequisiteName())){
                                        prerequisite = courses1.getPreRequisiteName();
                                        calculatedSemesterTwoCourseNames.remove(courses1.getCourseCode());
                                        calculatedSemesterTwoCourseNames.add(prerequisite);
                                        test = true;
                                        break;
                                    }
                                }
                                if (!test){
                                    calculatedSemesterTwoCourseNames.add(courseName);
                                }

                            }

                        }
                        student.setAvailableCourses(calculatedSemesterTwoCourseNames);

                    }
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

    }

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
        int y = 0;
        Random randomCourseAdder = new Random();
        int randomNumber = 0;
        List<String> testArrayList = new ArrayList<>();

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

    public static String assignRandomGrades() throws IOException{

        String[] grades = {"AA","BA","BB","CB","CC","DC","DD","FF"};

        Random random = new Random();
        int number=0;
        int number2=0;
        String randomLetter="";

        number = random.nextInt(8);
        number2 = random.nextInt(2);

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
