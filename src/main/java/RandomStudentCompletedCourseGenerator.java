import java.io.IOException;
import java.util.ArrayList;

public class RandomStudentCompletedCourseGenerator{

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
}
