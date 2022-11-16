import java.io.IOException;
import java.text.ParseException;
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

    public GenerateStudent(Student[] student, Courses[] courses){
        this.student = student;
        this.courses = courses;
    }

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
            if (course.getPrerequisite() != null){
                for (String prerequisite : course.getPrerequisite()){
                    if (!prerequisite.equals("")){
                        prerequisiteList.put(course.getCourseCode(), course.getPrerequisite());
                    }
                }
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
    public void setCoursesList(Student s){
        List<CompletedCourses> completedCourses = new ArrayList<>();
        List<FailedCourses> failedCourses = new ArrayList<>();
        List<String> availableCourses = new ArrayList<>();
        s.setCompletedCourses(completedCourses);
        s.setFailedCourses(failedCourses);
        s.setAvailableCourses(availableCourses);
    }
    public void assignFailedCourses(List<FailedCourses> currentSemesterFailed, String courseCode){
        FailedCourses failedCourses = new FailedCourses();
        failedCourses.setCourseGrade("FF");
        failedCourses.setCourseName(courseCode);
        currentSemesterFailed.add(failedCourses);
    }
    public void prerequisiteControlAndLock(String courseCode, HashMap<String, List<String>> lockedCourses){
        prerequisiteList.forEach((courseName, prerequisite) -> {
            if (prerequisite.contains(courseCode)){
                List <String> failedPrerequisite = new ArrayList<>();
                failedPrerequisite.add(courseCode);
                lockedCourses.put(courseName, failedPrerequisite);
            }
        });
    }

    public void addCompletedCourses(List<CompletedCourses> currentSemesterCompleted, String courseCode, String grade, int finishedSemester){
        CompletedCourses completedCourses = new CompletedCourses();
        completedCourses.setCourseName(courseCode);
        completedCourses.setCourseGrade(grade);
        completedCourses.setGivenSemester(finishedSemester);
        currentSemesterCompleted.add(completedCourses);
    }

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
    
    public void removeDuplicates(Student s){
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
    }
    public boolean checkCourseHasPrerequisite(String courseCode){
        AtomicBoolean IsCoursePrerequisite = new AtomicBoolean(false);
        prerequisiteList.forEach((courseName, prerequisite) -> {
            if (courseName.equals(courseCode) && prerequisite.size() != 0){
                IsCoursePrerequisite.set(true);
            }
        });
        return IsCoursePrerequisite.get();
    }

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
    public boolean courseIsGivenAlready(Student s, String courseCode){
        for (CompletedCourses completedCourses : s.getCompletedCourses()){
            if (completedCourses.getCourseName() == courseCode){
                return true;
            }
        }
        return false;
    }

    public void setStudentAdvisor(Student s){
        Random random = new Random();
        int number = 0;
        number = random.nextInt(5);
        s.setAdvisorId(number);
    }

    public void simulateSemester(Student s, String semester) throws IOException {
        semesterSetter(s, semester);
        List<CompletedCourses> currentSemesterCompleted = new ArrayList<>();
        List<FailedCourses> currentSemesterFailed = new ArrayList<>();
        HashMap<String, List<String>> lockedCourses= new HashMap<>();
        for (int i = 1; i <= s.getCurrentSemester(); i++) {
            if (i == 1){
                setCoursesList(s);
            } else if (i == 2){
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
            } else if(i == 3){
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
            } else if (i == 4) {
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
            } else if (i == 5){
                int finalI = i;
                checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, fourthSemesterCoursesHash);
                fourthSemesterCoursesHash.forEach((courseCode, prerequisite) -> {
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
            } else if (i == 6) {
                int finalI = i;
                checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, fifthSemesterCourses);
                fifthSemesterCourses.forEach((courseCode, prerequisite) -> {
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
            } else if (i == 7) {
                int finalI = i;
                checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, sixthSemesterCoursesHash);
                sixthSemesterCoursesHash.forEach((courseCode, prerequisite) -> {
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
            } else if (i == 8) {
                int finalI = i;
                checkAvailableCourses(s, finalI, currentSemesterCompleted, currentSemesterFailed, lockedCourses, seventhSemesterCoursesHash);
                seventhSemesterCoursesHash.forEach((courseCode, prerequisite) -> {
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
            checkCourseGiven(s);
        }
    }

    void simulate() throws IOException {
        addCourseNames();
        for (Student s : student){
            generateYear(s);
            simulateSemester(s, "Spring");
            removeDuplicates(s);
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
