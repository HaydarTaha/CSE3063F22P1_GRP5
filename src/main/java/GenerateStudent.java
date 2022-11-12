import java.io.IOException;
import java.util.*;

public class GenerateStudent {
    private Student[] student;
    private Courses[] courses;
    private Courses[] nteCourses;
    private Courses[] ueCourses;
    private Courses[] teCourses;
    private Courses[] fteCourses;

    private List<String> firstSemesterCourses;
    private HashMap<String, List<String>> secondSemesterCourses;
    private HashMap<String, List<String>> thirdSemesterCourses;
    private HashMap<String, List<String>> fourthSemesterCourses;
    private HashMap<String, List<String>> fifthSemesterCourses;
    private HashMap<String, List<String>> sixthSemesterCourses;
    private HashMap<String, List<String>> seventhSemesterCourses;
    private HashMap<String, List<String>> eighthSemesterCourses;

    public GenerateStudent(Student[] student, Courses[] courses, Courses[] nteCourses, Courses[] ueCourses, Courses[] teCourses, Courses[] fteCourses){
        this.student = student;
        this.courses = courses;
        this.nteCourses = nteCourses;
        this.ueCourses = ueCourses;
        this.teCourses = teCourses;
        this.fteCourses = fteCourses;
    }

    public void addCourseNames(){
        this.firstSemesterCourses = new ArrayList<String>();
        this.secondSemesterCourses = new HashMap<>();
        this.thirdSemesterCourses = new HashMap<>();
        this.fourthSemesterCourses = new HashMap<>();
        this.fifthSemesterCourses = new HashMap<>();
        this.sixthSemesterCourses = new HashMap<>();
        this.seventhSemesterCourses = new HashMap<>();
        this.eighthSemesterCourses = new HashMap<>();

        for (Courses course : this.courses) {
            switch (course.getSemester()){
                case 1:
                    firstSemesterCourses.add(course.getCourseCode());
                    break;
                case 2:
                    secondSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 3:
                    thirdSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 4:
                    fourthSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 5:
                    fifthSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 6:
                    sixthSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 7:
                    seventhSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 8:
                    eighthSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
            }
        }
    }

    public void generateYear(Student student){
        int number = student.getStudentId();
        int yearNumber = ((number/1000) - 150000);

        if (yearNumber == 116){
            student.setCurrentYear(4);
        } else if (yearNumber == 117){
            student.setCurrentYear(3);
        } else if (yearNumber == 118){
            student.setCurrentYear(2);
        } else {
            student.setCurrentYear(1);
        }
    }

    public void simulateSemester(Student s, String semester) throws IOException {
        addCourseNames();
        if (semester == "Fall"){
            switch (s.getCurrentYear()){
                case 1:
                    s.setCurrentSemester(1);
                    break;
                case 2:
                    s.setCurrentSemester(3);
                    break;
                case 3:
                    s.setCurrentSemester(5);
                    break;
                case 4:
                    s.setCurrentSemester(7);
                    break;
            }
        } else if (semester == "Spring") {
            switch (s.getCurrentYear()){
                case 1:
                    s.setCurrentSemester(2);
                    break;
                case 2:
                    s.setCurrentSemester(4);
                    break;
                case 3:
                    s.setCurrentSemester(6);
                    break;
                case 4:
                    s.setCurrentSemester(8);
                    break;
            }
        }
        List<CompletedCourses> completedCoursesList = new ArrayList<>();
        List<FailedCourses> failedCoursesList = new ArrayList<>();
        for (int i = 1; i <= s.getCurrentSemester(); i++) {
            if (i == 1){
                s.setCompletedCourses(completedCoursesList);
                s.setFailedCourses(failedCoursesList);
            } else if (i == 2){
                for (String courseCode : firstSemesterCourses){
                    String grade = assignRandomGrades();
                    if (grade == "FF"){
                        FailedCourses failedCourses = new FailedCourses();
                        failedCourses.setCourseGrade(grade);
                        failedCourses.setCourseName(courseCode);
                        s.getFailedCourses().add(failedCourses);
                    } else {
                        CompletedCourses completedCourses = new CompletedCourses();
                        completedCourses.setCourseName(courseCode);
                        completedCourses.setCourseGrade(grade);
                        s.getCompletedCourses().add(completedCourses);
                    }
                }
            } else if(i == 3){
                secondSemesterCourses.forEach((courseCode, prerequisite) -> {
                    if (prerequisite.size() == 0){
                        try {
                            String grade = assignRandomGrades();
                            if (grade == "FF"){
                                FailedCourses failedCourses = new FailedCourses();
                                failedCourses.setCourseGrade(grade);
                                failedCourses.setCourseName(courseCode);
                                s.getFailedCourses().add(failedCourses);
                            } else {
                                CompletedCourses completedCourses = new CompletedCourses();
                                completedCourses.setCourseName(courseCode);
                                completedCourses.setCourseGrade(grade);
                                s.getCompletedCourses().add(completedCourses);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        if (s.getFailedCourses().size() > 0){
                            boolean failed = false;
                            for (FailedCourses failedCourses : s.getFailedCourses()){
                                if (prerequisite.contains(failedCourses.getCourseName())){
                                    failed = true;
                                    break;
                                } else {
                                    failed = false;
                                }
                            }
                            if (failed == false){
                                String grade = null;
                                try {
                                    grade = assignRandomGrades();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                if (grade == "FF"){
                                    FailedCourses failedCourses = new FailedCourses();
                                    failedCourses.setCourseGrade(grade);
                                    failedCourses.setCourseName(courseCode);
                                    s.getFailedCourses().add(failedCourses);
                                } else {
                                    CompletedCourses completedCourses = new CompletedCourses();
                                    completedCourses.setCourseName(courseCode);
                                    completedCourses.setCourseGrade(grade);
                                    s.getCompletedCourses().add(completedCourses);
                                }
                            }
                        } else {
                            String grade = null;
                            try {
                                grade = assignRandomGrades();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (grade == "FF"){
                                FailedCourses failedCourses = new FailedCourses();
                                failedCourses.setCourseGrade(grade);
                                failedCourses.setCourseName(courseCode);
                                s.getFailedCourses().add(failedCourses);
                            } else {
                                CompletedCourses completedCourses = new CompletedCourses();
                                completedCourses.setCourseName(courseCode);
                                completedCourses.setCourseGrade(grade);
                                s.getCompletedCourses().add(completedCourses);
                            }
                        }
                    }
                });
            }
        }
    }


    void printStudents() throws IOException {
        for (Student s : student){
            generateYear(s);
            simulateSemester(s, "Fall");
            if (s.getCurrentSemester() == 3){
                int size = firstSemesterCourses.size() + secondSemesterCourses.size();
                System.out.println(size);
                System.out.println(s.getCurrentYear() + " " + s.getfName() + " " + s.getlName() + " " + s.getCompletedCourses() + "\n" + s.getFailedCourses());
                System.out.println(s.getCompletedCourses().size() + s.getFailedCourses().size());
            }

        }
    }

    public String assignRandomGrades() throws IOException {

        Random random = new Random();
        String randomLetter = null;
        int number = 0;
        int number2 = 0;

        number = random.nextInt(7);
        number2 = random.nextInt(2);

        switch (number){
            case 0:
                switch (number2){
                    case 0:
                        randomLetter = "AA";
                        break;
                    case 1:
                        randomLetter = "BA";
                        break;
                }
                break;
            case 1:
                switch (number2){
                    case 0:
                        randomLetter = "BA";
                        break;
                    case 1:
                        randomLetter = "BB";
                        break;
                }
                break;
            case 2:
                switch (number2){
                    case 0:
                        randomLetter = "BB";
                        break;
                    case 1:
                        randomLetter = "CB";
                        break;
                }
                break;
            case 3:
                switch (number2){
                    case 0:
                        randomLetter = "CB";
                        break;
                    case 1:
                        randomLetter = "CC";
                        break;
                }
                break;
            case 4:
                switch (number2){
                    case 0:
                        randomLetter = "CC";
                        break;
                    case 1:
                        randomLetter = "DC";
                        break;
                }
                break;
            case 5:
                switch (number2){
                    case 0:
                        randomLetter = "DC";
                        break;
                    case 1:
                        randomLetter = "DD";
                        break;
                }
                break;
            case 6:
                switch (number2){
                    case 0:
                        randomLetter = "DD";
                        break;
                    case 1:
                        randomLetter = "FF";
                        break;
                }
                break;
        }
        return randomLetter;
    }
}
