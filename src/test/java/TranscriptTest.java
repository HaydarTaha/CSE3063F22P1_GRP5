import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;

class TranscriptTest {

    @Mock
    private Student mockStudent;

    private Transcript transcriptUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        transcriptUnderTest = new Transcript(List.of(new Courses()), List.of(new Courses()), 0.0, 0, List.of("value"),
                "advisorName", mockStudent);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testSeperateFailedCourses() {
        // Setup
        // Run the test
        transcriptUnderTest.seperateFailedCourses();

        // Verify the results
    }

    @Test
    void testPrintTranscriptSpecificStudent() {
        // Setup
        final Student student = new Student();
        student.setAdvisor(new Advisor());
        student.setCurrentYear(2020);
        student.setCurrentSemester(0);
        student.setStudentId(0);
        student.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        courses.setCredit(0);
        student.setCompletedCourses(List.of(courses));
        student.setAvailableCourses(List.of("value"));
        final Courses courses1 = new Courses();
        courses1.setCourseGrade("courseGrade");
        courses1.setGivenSemester(0);
        courses1.setName("name");
        courses1.setCourseCode("courseCode");
        courses1.setCredit(0);
        student.setFailedCourses(List.of(courses1));

        // Run the test
        transcriptUnderTest.printTranscriptSpecificStudent(student);

        // Verify the results
    }

    @Test
    void testTransformTranscriptElementsToList() {
        // Setup
        // Run the test
        transcriptUnderTest.transformTranscriptElementsToList();

        // Verify the results
    }

    @Test
    void testTransformSpecificStudentTranscriptElementsToList() {
        // Setup
        final Student student = new Student();
        student.setAdvisor(new Advisor());
        student.setCurrentYear(2020);
        student.setCurrentSemester(0);
        student.setStudentId(0);
        student.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        courses.setCredit(0);
        student.setCompletedCourses(List.of(courses));
        student.setAvailableCourses(List.of("value"));
        final Courses courses1 = new Courses();
        courses1.setCourseGrade("courseGrade");
        courses1.setGivenSemester(0);
        courses1.setName("name");
        courses1.setCourseCode("courseCode");
        courses1.setCredit(0);
        student.setFailedCourses(List.of(courses1));

        // Run the test
        transcriptUnderTest.transformSpecificStudentTranscriptElementsToList(student);

        // Verify the results
    }

    @Test
    void testGenerateTranscriptJson() throws Exception {
        // Setup
        final Student student = new Student();
        student.setAdvisor(new Advisor());
        student.setCurrentYear(2020);
        student.setCurrentSemester(0);
        student.setStudentId(0);
        student.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        courses.setCredit(0);
        student.setCompletedCourses(List.of(courses));
        student.setAvailableCourses(List.of("value"));
        final Courses courses1 = new Courses();
        courses1.setCourseGrade("courseGrade");
        courses1.setGivenSemester(0);
        courses1.setName("name");
        courses1.setCourseCode("courseCode");
        courses1.setCredit(0);
        student.setFailedCourses(List.of(courses1));
        final Student[] students = new Student[]{student};

        // Run the test
        transcriptUnderTest.generateTranscriptJson(students);

        // Verify the results
    }

    @Test
    void testGenerateTranscriptJson_ThrowsIllegalAccessException() {
        // Setup
        final Student student = new Student();
        student.setAdvisor(new Advisor());
        student.setCurrentYear(2020);
        student.setCurrentSemester(0);
        student.setStudentId(0);
        student.setCurrentSelectedCourses(List.of("value"));
        final Courses courses = new Courses();
        courses.setCourseGrade("courseGrade");
        courses.setGivenSemester(0);
        courses.setName("name");
        courses.setCourseCode("courseCode");
        courses.setCredit(0);
        student.setCompletedCourses(List.of(courses));
        student.setAvailableCourses(List.of("value"));
        final Courses courses1 = new Courses();
        courses1.setCourseGrade("courseGrade");
        courses1.setGivenSemester(0);
        courses1.setName("name");
        courses1.setCourseCode("courseCode");
        courses1.setCredit(0);
        student.setFailedCourses(List.of(courses1));
        final Student[] students = new Student[]{student};

        // Run the test
        assertThrows(IllegalAccessException.class, () -> transcriptUnderTest.generateTranscriptJson(students));
    }
}
