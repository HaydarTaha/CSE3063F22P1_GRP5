import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GenerateStudent {
    private Student[] student;
    private Courses[] courses;
    private List<String> firstSemesterCourses;
    private HashMap<String, List<String>> secondSemesterCoursesHash;
    private HashMap<String, List<String>> thirdSemesterCoursesHash;
    private HashMap<String, List<String>> fourthSemesterCoursesHash;
    private HashMap<String, List<String>> fifthSemesterCourses;
    private HashMap<String, List<String>> sixthSemesterCoursesHash;
    private HashMap<String, List<String>> seventhSemesterCoursesHash;
    private HashMap<String, List<String>> eighthSemesterCoursesHash;
    private HashMap<String, List<String>> prerequisiteList;
    private Courses[] UE;
    private Courses[] TE;
    private Courses[] NTE;
    private Courses[] FTE;

    //This constructor called in Main class and send student and courses arrays
    public GenerateStudent(Student[] student, Courses[] courses,Courses[] UE,Courses[] TE, Courses[] NTE,Courses[] FTE){
        this.student = student;
        this.courses = courses;
        this.UE=UE;
        this.TE=TE;
        this.NTE=NTE;
        this.FTE=FTE;
    }

    //This method get courseCodesFrom Courses array and check their semester and add to named (CourseSemester)SemesterCourses
    //For the first semester courses, type is List for the other semester type is HashMap. In the HashMap we are holding courseCode
    //and their prerequisite courses
    public void addCourseNames(){
        this.firstSemesterCourses = new ArrayList<String>();
        this.secondSemesterCoursesHash = new HashMap<>();
        this.thirdSemesterCoursesHash = new HashMap<>();
        this.fourthSemesterCoursesHash = new HashMap<>();
        this.fifthSemesterCourses = new HashMap<>();
        this.sixthSemesterCoursesHash = new HashMap<>();
        this.seventhSemesterCoursesHash = new HashMap<>();
        this.eighthSemesterCoursesHash = new HashMap<>();
        this.prerequisiteList = new HashMap<>();
        for (Courses course : this.courses) {
            switch (course.getSemester()){
                case 1:
                    firstSemesterCourses.add(course.getCourseCode());
                    break;
                case 2:
                    secondSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 3:
                    thirdSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 4:
                    fourthSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 5:
                    fifthSemesterCourses.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 6:
                    sixthSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 7:
                    seventhSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
                case 8:
                    eighthSemesterCoursesHash.put(course.getCourseCode(), course.getPrerequisite());
                    break;
            }
            //In the prerequisiteList we are storing all courses who have prerequisite
            if (course.getPrerequisite() != null){
                for (String prerequisite : course.getPrerequisite()){
                    if (!prerequisite.equals("")){
                        prerequisiteList.put(course.getCourseCode(), course.getPrerequisite());
                    }
                }
            }
        }
    }

    //In this method we are setting year to student
    //We are calculating year from their studentNumbers
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

    //In this method we are setting semester to student
    //We are giving semester from their currentYear and which semester user want to simulate
    public void semesterSetter(Student s, String semester){
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
    }

    //In this method we are setting completedCourses, failedCourses and availableCourses in the student we sent to this method as a parameter
    public void setCoursesList(Student s){
        List<CompletedCourses> completedCourses = new ArrayList<>();
        List<FailedCourses> failedCourses = new ArrayList<>();
        List<String> availableCourses = new ArrayList<>();
        s.setCompletedCourses(completedCourses);
        s.setFailedCourses(failedCourses);
        s.setAvailableCourses(availableCourses);
    }

    //In this method we are assigning FF grade and CourseCode to currentSemesterFailed courses list
    public void assignFailedCourses(List<FailedCourses> currentSemesterFailed, String courseCode){
        FailedCourses failedCourses = new FailedCourses();
        failedCourses.setCourseGrade("FF");
        failedCourses.setCourseName(courseCode);
        currentSemesterFailed.add(failedCourses);
    }

    //In this method we are locking course if the course prerequisite course is failed
    public void prerequisiteControlAndLock(String courseCode, HashMap<String, List<String>> lockedCourses){
        prerequisiteList.forEach((courseName, prerequisite) -> {
            if (prerequisite.contains(courseCode)){
                List <String> failedPrerequisite = new ArrayList<>();
                failedPrerequisite.add(courseCode);
                lockedCourses.put(courseName, failedPrerequisite);
            }
        });
    }

    //In this method we are adding courseCode, courseGrade and given semester to currentSemesterCompleted List
    public void addCompletedCourses(List<CompletedCourses> currentSemesterCompleted, String courseCode, String grade, int finishedSemester){
        CompletedCourses completedCourses = new CompletedCourses();
        completedCourses.setCourseName(courseCode);
        completedCourses.setCourseGrade(grade);
        completedCourses.setGivenSemester(finishedSemester);
        currentSemesterCompleted.add(completedCourses);
    }

    //In this method we are simulating failed courses if passed add to currentSemesterCompleted else keep it in failedCoursesList
    public void simulateFailedCourses(Student s ,List<CompletedCourses> currentSemesterCompleted, int currentSemester){
        int failedCoursesSize = s.getFailedCourses().size();
        if (failedCoursesSize > 0){
            for (int i = 0; i < failedCoursesSize; i++) {
                String courseCode = s.getFailedCourses().get(i).getCourseName();
                if (!currentSemesterCompleted.contains(courseCode)){
                    if (courseIsGivenAlready(s, courseCode)){
                        continue;
                    } else {
                        String grade = null;
                        try {
                            grade = assignRandomGrades();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!grade.equals("FF")){
                            if (!courseIsGivenAlready(s, courseCode)){
                                addCompletedCourses(currentSemesterCompleted, courseCode, grade, currentSemester);
                                s.getFailedCourses().remove(courseCode);
                                failedCoursesSize--;
                            }
                        }
                    }
                }

            }
        }
    }

    //In this method we are checking If the course is locked and prerequisite is given set to student AvailableCoursesList
    public void unlockLockedCoursesAndSetAvailable(Student s, List<CompletedCourses> completedCourses, HashMap<String, List<String>> lockedCourses){
        if (completedCourses.size() > 0){
            if (lockedCourses.size() > 0){
                for (int i = 0; i < completedCourses.size(); i++) {
                    int finalI = i;
                    AtomicBoolean lockedCheck = new AtomicBoolean(false);
                    lockedCourses.forEach((courseName, prerequisite) -> {
                        for (String prerequisiteCode : prerequisite){
                            if (prerequisiteCode == completedCourses.get(finalI).getCourseName()){
                                s.getAvailableCourses().add(courseName);
                                lockedCheck.set(true);
                            }
                        }
                    });
                    if (lockedCheck.get()){
                        lockedCourses.remove(completedCourses.get(i).getCourseName());
                    }
                }
            }
        }
    }

    //In this method we are checking AvailableCoursesList if there is a course we are simulating these courses
    public void checkAvailableCourses(Student s, int semester, List<CompletedCourses> currentSemesterCompleted, List<FailedCourses> currentSemesterFailed, HashMap<String, List<String>> lockedCourses, HashMap<String, List<String>> currentSemesterCourses){
        for (Courses courseList : courses){
            if (s.getAvailableCourses().size() > 0){
                if (s.getAvailableCourses().size() != 0){
                    int size = s.getAvailableCourses().size();
                    for (int i = 0; i < size; i++) {
                        String courseCode = s.getAvailableCourses().get(i);
                        if (courseList.getCourseCode().equals(courseCode) && courseList.getSemester() == semester - 1){
                            if (courseIsGivenAlready(s, courseCode)){
                                continue;
                            } else {
                                if (checkCourseHasPrerequisite(courseCode)){
                                    if (checkPrerequisiteCourseIsGiven(s, courseCode, i)){
                                        String grade = null;
                                        try {
                                            grade = assignRandomGrades();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        if (grade == "FF"){
                                            assignFailedCourses(currentSemesterFailed, courseCode);
                                            prerequisiteControlAndLock(courseCode, lockedCourses);
                                        } else {
                                            addCompletedCourses(currentSemesterCompleted, courseCode, grade, semester);
                                            s.getAvailableCourses().remove(courseCode);
                                            size--;
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
                                        assignFailedCourses(currentSemesterFailed, courseCode);
                                        prerequisiteControlAndLock(courseCode, lockedCourses);
                                    } else {
                                        addCompletedCourses(currentSemesterCompleted, courseCode, grade, semester);
                                        s.getAvailableCourses().remove(courseCode);
                                        size--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //In this method we are checking the course is given already from failed courses list
    public void checkCourseGiven(Student s){
        for (CompletedCourses completed : s.getCompletedCourses()){
            String courseCode = completed.getCourseName();
            int size = s.getFailedCourses().size();
            for (int i = 0; i < size; i++) {
                if (s.getFailedCourses().get(i).getCourseName().equals(courseCode)){
                    s.getFailedCourses().remove(i);
                    size--;
                }
            }
        }
    }

    //In this method we are removing duplicate values from Student completedCourses List
    public void removeUnnamedCourses(Student s){
        List<CompletedCourses> completedCourses = new ArrayList<>(s.getCompletedCourses());
        List<String> duplicateCourses = new ArrayList<>();
        List<Integer> duplicateTime = new ArrayList<>();
        for (int i = 0; i < s.getCompletedCourses().size(); i++) {
            int count = 0;
            String courseCode = s.getCompletedCourses().get(i).getCourseName();

            for (int j = i; j < s.getCompletedCourses().size(); j++) {
                String checkCode = s.getCompletedCourses().get(j).getCourseName();
                if (courseCode.equals(checkCode)){
                    count++;
                }
            }
            if (count >= 2){
                duplicateCourses.add(courseCode);
                duplicateTime.add(count);
            }
        }
        if (duplicateCourses.size() > 0){
            int dupSize = duplicateCourses.size();
            int courseSize = s.getCompletedCourses().size();
            for (int i = 0; i < dupSize; i++) {
                List<Integer> dupPosition = new ArrayList<>();
                for (int j = 0; j < courseSize; j++) {
                    String code = s.getCompletedCourses().get(j).getCourseName();
                    if (code.equals(duplicateCourses.get(i))){
                        dupPosition.add(j);
                    }
                }
                int dupPosSize = dupPosition.size() - 1;
                for (int j = 0; j < dupPosSize; j++) {
                    int position = dupPosition.get(j);
                    s.getCompletedCourses().remove(j);
                    courseSize--;
                }
            }
        }


        for (CompletedCourses completedCourses1 : s.getCompletedCourses()){
            Random random = new Random();
            int value = random.nextInt(5);
            if (completedCourses1.getCourseName().contains("UE")){
                completedCourses1.setCourseName(UE[value].getCourseCode());

            } else if (completedCourses1.getCourseName().contains("FTE")){
                completedCourses1.setCourseName(FTE[value].getCourseCode());

            } else if (completedCourses1.getCourseName().contains("NTE")){
                completedCourses1.setCourseName(NTE[value].getCourseCode());

            } else if (completedCourses1.getCourseName().contains("TE")){
                completedCourses1.setCourseName(TE[value].getCourseCode());

            }

        }
    }

    //In this method we are checking if the course has Prerequisite course
    public boolean checkCourseHasPrerequisite(String courseCode){
        AtomicBoolean IsCoursePrerequisite = new AtomicBoolean(false);
        prerequisiteList.forEach((courseName, prerequisite) -> {
            if (courseName.equals(courseCode) && prerequisite.size() != 0){
                IsCoursePrerequisite.set(true);
            }
        });
        return IsCoursePrerequisite.get();
    }

    //In this method we are checking if the prerequisite course is given
    public boolean checkPrerequisiteCourseIsGiven(Student s, String courseCode, int semester){
        List<String> prerequisiteCourses = new ArrayList<>();
        prerequisiteList.forEach((courseName, prerequisite) -> {
            if (courseCode.equals(courseName)){
                for (String prerequisiteCode : prerequisite){
                    prerequisiteCourses.add(prerequisiteCode);
                }
            }
        });
        if (prerequisiteCourses.size() == 0){
            return true;
        }
        if (s.getCompletedCourses().size() == 0){
            return false;
        }
        for (CompletedCourses completedCourses : s.getCompletedCourses()){
            for (String prerequisite : prerequisiteCourses){
                if (completedCourses.getCourseName().equals(prerequisite)){
                    return true;
                }
            }
        }
        return false;
    }

    //In this method we are checking the course is given already
    public boolean courseIsGivenAlready(Student s, String courseCode){
        for (CompletedCourses completedCourses : s.getCompletedCourses()){
            if (completedCourses.getCourseName() == courseCode){
                return true;
            }
        }
        return false;
    }

    //In this method we are assigning Advisor to student
    public void setStudentAdvisor(Student s){
        Random random = new Random();
        int number = 0;
        number = random.nextInt(5);
        s.setAdvisorId(number);
    }

    public void caseTwo(List<CompletedCourses> currentSemesterCompleted, Student s, int i, List<FailedCourses> currentSemesterFailed, HashMap<String, List<String>> lockedCourses) throws IOException {
        for (String courseCode : firstSemesterCourses){
            if (!currentSemesterCompleted.contains(courseCode)){
                String grade = assignRandomGrades();
                if (!courseIsGivenAlready(s, courseCode)){
                    if (grade == "FF"){
                        assignFailedCourses(currentSemesterFailed, courseCode);
                        prerequisiteControlAndLock(courseCode, lockedCourses);
                    } else {
                        addCompletedCourses(currentSemesterCompleted, courseCode, grade, i);
                    }
                }
            }
        }
        int currentSemesterCompletedSize = currentSemesterCompleted.size();
        int currentSemesterFailedSize = currentSemesterFailed.size();
        for (int j = 0; j < currentSemesterCompletedSize; j++) {
            s.getCompletedCourses().add(currentSemesterCompleted.get(j));
        }
        for (int j = 0; j < currentSemesterFailedSize; j++) {
            s.getFailedCourses().add(currentSemesterFailed.get(j));
        }
        currentSemesterCompleted.clear();
        currentSemesterFailed.clear();
    }
    public void caseThree(List<CompletedCourses> currentSemesterCompleted, Student s, int i, List<FailedCourses> currentSemesterFailed, HashMap<String, List<String>> lockedCourses){
        int finalI = i;
        secondSemesterCoursesHash.forEach((courseCode, prerequisite) -> {
            if (!currentSemesterCompleted.contains(courseCode)){
                if (!lockedCourses.containsKey(courseCode)){
                    if (!checkCourseHasPrerequisite(courseCode)){
                        String grade = null;
                        try {
                            grade = assignRandomGrades();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!courseIsGivenAlready(s, courseCode)){
                            if (grade == "FF"){
                                assignFailedCourses(currentSemesterFailed, courseCode);
                                prerequisiteControlAndLock(courseCode, lockedCourses);
                            } else {
                                addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                            }
                        }
                    } else {
                        if (checkPrerequisiteCourseIsGiven(s, courseCode, finalI)){
                            String grade = null;
                            try {
                                grade = assignRandomGrades();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (!courseIsGivenAlready(s, courseCode)){
                                if (grade == "FF"){
                                    assignFailedCourses(currentSemesterFailed, courseCode);
                                    prerequisiteControlAndLock(courseCode, lockedCourses);
                                } else {
                                    addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                                }
                            }
                        }
                    }
                }
            }
        });
        simulateFailedCourses(s, currentSemesterCompleted, finalI);
        int currentSemesterCompletedSize = currentSemesterCompleted.size();
        int currentSemesterFailedSize = currentSemesterFailed.size();
        for (int j = 0; j < currentSemesterCompletedSize; j++) {
            s.getCompletedCourses().add(currentSemesterCompleted.get(j));
        }
        for (int j = 0; j < currentSemesterFailedSize; j++) {
            s.getFailedCourses().add(currentSemesterFailed.get(j));
        }
        currentSemesterCompleted.clear();
        currentSemesterFailed.clear();
        unlockLockedCoursesAndSetAvailable(s, s.getCompletedCourses(), lockedCourses);
    }
    public void caseFour(List<CompletedCourses> currentSemesterCompleted, Student s, int i, List<FailedCourses> currentSemesterFailed, HashMap<String, List<String>> lockedCourses){
        int finalI = i;
        checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, thirdSemesterCoursesHash);
        thirdSemesterCoursesHash.forEach((courseCode, prerequisite) -> {
            if (!currentSemesterCompleted.contains(courseCode)){
                if (!lockedCourses.containsKey(courseCode)){
                    if (!checkCourseHasPrerequisite(courseCode)){
                        String grade = null;
                        try {
                            grade = assignRandomGrades();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!courseIsGivenAlready(s, courseCode)){
                            if (grade == "FF"){
                                assignFailedCourses(currentSemesterFailed, courseCode);
                                prerequisiteControlAndLock(courseCode, lockedCourses);
                            } else {
                                addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                            }
                        }
                    } else {
                        if (checkPrerequisiteCourseIsGiven(s, courseCode, finalI)){
                            String grade = null;
                            try {
                                grade = assignRandomGrades();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (!courseIsGivenAlready(s, courseCode)){
                                if (grade == "FF"){
                                    assignFailedCourses(currentSemesterFailed, courseCode);
                                    prerequisiteControlAndLock(courseCode, lockedCourses);
                                } else {
                                    addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                                }
                            }
                        }
                    }
                }
            }
        });
        simulateFailedCourses(s, currentSemesterCompleted, finalI);
        int currentSemesterCompletedSize = currentSemesterCompleted.size();
        int currentSemesterFailedSize = currentSemesterFailed.size();
        for (int j = 0; j < currentSemesterCompletedSize; j++) {
            s.getCompletedCourses().add(currentSemesterCompleted.get(j));
        }
        for (int j = 0; j < currentSemesterFailedSize; j++) {
            s.getFailedCourses().add(currentSemesterFailed.get(j));
        }
        currentSemesterCompleted.clear();
        currentSemesterFailed.clear();
        unlockLockedCoursesAndSetAvailable(s, s.getCompletedCourses(), lockedCourses);
    }
    public void otherCases(List<CompletedCourses> currentSemesterCompleted, Student s, int i, List<FailedCourses> currentSemesterFailed, HashMap<String, List<String>> lockedCourses, HashMap<String, List<String>> semesterCourses){
        int finalI = i;
        checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, fourthSemesterCoursesHash);
        semesterCourses.forEach((courseCode, prerequisite) -> {
            if (!currentSemesterCompleted.contains(courseCode)){
                if (!lockedCourses.containsKey(courseCode)){
                    if (!checkCourseHasPrerequisite(courseCode)){
                        String grade = null;
                        try {
                            grade = assignRandomGrades();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (!courseIsGivenAlready(s, courseCode)){
                            if (grade == "FF"){
                                assignFailedCourses(currentSemesterFailed, courseCode);
                                prerequisiteControlAndLock(courseCode, lockedCourses);
                            } else {
                                addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                            }
                        }
                    } else {
                        if (checkPrerequisiteCourseIsGiven(s, courseCode, finalI)){
                            String grade = null;
                            try {
                                grade = assignRandomGrades();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (!courseIsGivenAlready(s, courseCode)){
                                if (grade == "FF"){
                                    assignFailedCourses(currentSemesterFailed, courseCode);
                                    prerequisiteControlAndLock(courseCode, lockedCourses);
                                } else {
                                    addCompletedCourses(currentSemesterCompleted, courseCode, grade, finalI);
                                }
                            }
                        }
                    }
                }
            }

        });
        simulateFailedCourses(s, currentSemesterCompleted, finalI);
        int currentSemesterCompletedSize = currentSemesterCompleted.size();
        int currentSemesterFailedSize = currentSemesterFailed.size();
        for (int j = 0; j < currentSemesterCompletedSize; j++) {
            s.getCompletedCourses().add(currentSemesterCompleted.get(j));
        }
        for (int j = 0; j < currentSemesterFailedSize; j++) {
            s.getFailedCourses().add(currentSemesterFailed.get(j));
        }
        currentSemesterCompleted.clear();
        currentSemesterFailed.clear();
        unlockLockedCoursesAndSetAvailable(s, s.getCompletedCourses(), lockedCourses);
    }

    //In this method we simulate the semester up to the student's semester
    public void simulateSemester(Student s, String semester) throws IOException {
        semesterSetter(s, semester);
        List<CompletedCourses> currentSemesterCompleted = new ArrayList<>();
        List<FailedCourses> currentSemesterFailed = new ArrayList<>();
        HashMap<String, List<String>> lockedCourses = new HashMap<>();
        for (int i = 1; i <= s.getCurrentSemester(); i++) {
            if (i == 1){
                setCoursesList(s);
            } else if (i == 2){
                caseTwo(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses);
            } else if(i == 3){
                caseThree(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses);
            } else if (i == 4) {
                caseFour(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses);
            } else if (i == 5){
                otherCases(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses, fourthSemesterCoursesHash);
            } else if (i == 6) {
                otherCases(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses, fifthSemesterCourses);
            } else if (i == 7) {
                otherCases(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses, sixthSemesterCoursesHash);
            } else if (i == 8) {
                otherCases(currentSemesterCompleted, s, i, currentSemesterFailed, lockedCourses, seventhSemesterCoursesHash);
            }
            checkCourseGiven(s);
        }
    }

    //In this method we are calling generateYear, simulateSemester, removeUnnamedCourses and setStudentAdvisor methods
    void simulate() throws IOException {
        addCourseNames();
        for (Student s : student){
            generateYear(s);
            simulateSemester(s, "Spring");
            removeUnnamedCourses(s);
            setStudentAdvisor(s);
            int availableCoursesSize = s.getAvailableCourses().size();
            for (int i = 0; i < availableCoursesSize; i++) {
                s.getAvailableCourses().remove(i);
                availableCoursesSize--;
            }
            List<String> currentSelected = new ArrayList<>();
            s.setCurrentSelectedCourses(currentSelected);
            if (s.getFailedCourses().size() > 0){
                for (FailedCourses failedCourses : s.getFailedCourses()){
                    CompletedCourses completedCourses = new CompletedCourses();
                    completedCourses.setCourseName(failedCourses.getCourseName());
                    completedCourses.setCourseGrade(failedCourses.getCourseGrade());
                    s.getCompletedCourses().add(completedCourses);
                }
            }
        }
    }

    //In this method we are returning random grades for the simulate method
    public String assignRandomGrades() throws IOException {

        Random random = new Random();
        String randomLetter = null;

        int number = random.nextInt(7);
        int number2 = random.nextInt(2);

        switch (number){
            case 0:
                switch (number2) {
                    case 0 -> randomLetter = "AA";
                    case 1 -> randomLetter = "BA";
                }
                break;
            case 1:
                randomLetter = switch (number2) {
                    case 0 -> "BA";
                    case 1 -> "BB";
                    default -> randomLetter;
                };
                break;
            case 2:
                randomLetter = switch (number2) {
                    case 0 -> "BB";
                    case 1 -> "CB";
                    default -> randomLetter;
                };
                break;
            case 3:
                randomLetter = switch (number2) {
                    case 0 -> "CB";
                    case 1 -> "CC";
                    default -> randomLetter;
                };
                break;
            case 4:
                randomLetter = switch (number2) {
                    case 0 -> "CC";
                    case 1 -> "DC";
                    default -> randomLetter;
                };
                break;
            case 5:
                randomLetter = switch (number2) {
                    case 0 -> "DC";
                    case 1 -> "DD";
                    default -> randomLetter;
                };
                break;
            case 6:
                randomLetter = switch (number2) {
                    case 0 -> "DD";
                    case 1 -> "FF";
                    default -> randomLetter;
                };
                break;
        }
        return randomLetter;
    }
}
