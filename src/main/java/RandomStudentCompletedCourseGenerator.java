import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomStudentCompletedCourseGenerator{

    public void addCourseNames(Student[]students, Courses[] courses) throws IOException {
        CompletedCourses completedCoursesTest = new CompletedCourses();
        List<CompletedCourses> completedCoursesList = new ArrayList<>();
        int y = 0;
        Random randomCourseAdder = new Random();
        int randomNumber = 0;
        List<String> testArrayList = new ArrayList<>();
        List<String> semesterOneCoursesNames = new ArrayList<String>();
        List<String> semesterTwoCoursesNames = new ArrayList<String>();
        List<String> semesterThreeCoursesNames = new ArrayList<String>();
        List<String> semesterFourCoursesNames = new ArrayList<String>();
        List<String> semesterFiveCoursesNames = new ArrayList<String>();
        List<String> semesterSixCoursesNames = new ArrayList<String>();
        List<String> semesterSevenCoursesNames = new ArrayList<String>();
        List<String> semesterEigthCoursesNames = new ArrayList<String>();
        for (Courses course : courses) {
            switch (course.getSemester()) {
                case 1 -> semesterOneCoursesNames.add(course.getCourseCode());
                case 2 -> semesterTwoCoursesNames.add(course.getCourseCode());
                case 3 -> semesterThreeCoursesNames.add(course.getCourseCode());
                case 4 -> semesterFourCoursesNames.add(course.getName());
                case 5 -> semesterFiveCoursesNames.add(course.getName());
                case 6 -> semesterSixCoursesNames.add(course.getName());
                case 7 -> semesterSevenCoursesNames.add(course.getName());
                case 8 -> semesterEigthCoursesNames.add(course.getName());
            }


        }
        for (Student student : students){
            switch (student.getCurrentSemester()){
                case 1 -> student.setCompletedCourses(null);
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
