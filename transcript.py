import json

class Transcript:
    def __init__(self):
        self.completedCourses = []
        self.failedCourses = []
        self.gpa = 0.0
        self.completedCredits = 0
        self.student = None
        self.studentAdvisor = None
        self.studentSelectedCourses = []
        self.completedCourseStrings = []
        self.failedCoursesStrings = []

    def __init__(self, completedCourses, failedCourses, gpa, completedCredits, studentSelectedCourses, student):
        self.completedCourses = completedCourses
        self.failedCourses = failedCourses
        self.gpa = gpa
        self.completedCredits = completedCredits
        self.studentSelectedCourses = studentSelectedCourses
        self.student = student
        self.studentAdvisor = student.get_advisor()

    def get_completed_courses(self):
        return self.completedCourses

    def get_failed_courses(self):
        return self.failedCourses

    def seperate_failed_courses(self):
        self.failedCoursesStrings = []
        for completedCourse in self.completedCourses:
            if completedCourse.get_course_grade() == "FF":
                self.failedCoursesStrings.append(completedCourse.get_course_name())
                self.failedCoursesStrings.append(completedCourse.get_course_grade())

    def print_transcript_specific_student(self, student):
        student.get_transcript().transform_specific_student_transcript_elements_to_list(student)
        print("------------------------------------------------------------------------------------------------")
        print("ID: " + student.get_student_id() + "\nFullName: " + student.get_f_name() + " " + student.get_l_name() + "\nAdvisor: " + self.student.get_advisor_name() + "\nCourses Taken:" + self.completedCourseStrings + "\nGPA: " + self.gpa + "\nTotal Credits: " + self.completedCredits + "\nAvailable Courses:" + student.get_available_courses()  + "\nAdvisor Approved Courses: " + self.studentSelectedCourses + "\nFailed Courses: " + self.failedCoursesStrings)

    def transform_transcript_elements_to_list(self):
        self.completedCourseStrings = []
        for completedCourses in self.completedCourses:
            self.completedCourseStrings.append(completedCourses.get_course_name())
            self.completedCourseStrings.append(completedCourses.get_course_grade())

    def transform_specific_student_transcript_elements_to_list(self, student):
        self.completedCourseStrings = []
        for completedCourses in student.get_completed_courses():
            self.completedCourseStrings.append(completedCourses.get_course_name() + " " +
                                               completedCourses.get_course_grade() + " Given: " +
                                               completedCourses.get_given_semester())
