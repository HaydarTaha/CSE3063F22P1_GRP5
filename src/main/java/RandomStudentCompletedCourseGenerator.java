import java.io.IOException;
import java.util.ArrayList;

public class RandomStudentCompletedCourseGenerator{

    public ArrayList<Integer> generate(Student[] students) throws IOException {
        int firstYear = 16;
        int secondYear = 27;
        int thirdYear = 39;
        int fourthYear = 54;
        int year = 0;
        int randomNumber = 0;
        int min = 1;
        int max = 54;
        int range = 0;

        ArrayList<Integer> studentYear = new ArrayList<>();
        ArrayList<Integer> randomStudentCompletedCourseNumber = new ArrayList<>();
        for(int x = 0; x < students.length; x++){
            studentYear.add(students[x].getCurrentYear());
        }
        for (int y = 0; y< studentYear.size(); y++){
            year = studentYear.get(y);
            if (year == 1){
                max = firstYear;
                min = 1;
                range = max-min +1;
                randomNumber = (int) (Math.random()* range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (year == 2){
                min = 16;
                max = secondYear;
                range = max-min +1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (year == 3){
                min = 27;
                max = thirdYear;
                range = max-min +1;
                randomNumber = (int) (Math.random()* range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }
            if (year == 4){
                min = 39;
                max = fourthYear;
                range = max - min + 1;
                randomNumber = (int) (Math.random() * range) + min;
                randomStudentCompletedCourseNumber.add(randomNumber);
            }

        }
        return randomStudentCompletedCourseNumber;
    }
}
