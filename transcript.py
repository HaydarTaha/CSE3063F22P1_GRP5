import json


class Transcript:
    def __init__(self, completedCourses, failedCourses, gpa, completedCredits, studentSelectedCourses, student):
        self.completedCourses = completedCourses
        self.failedCourses = failedCourses
        self.gpa = gpa
        self.completedCredits = completedCredits
        self.studentSelectedCourses = studentSelectedCourses
        self.student = student
        self.failedCoursesStrings = []
        self.completedCourseStrings = []

    def separate_failed_courses(self):
        for completedCourse in self.completedCourses:
            if completedCourse.get_course_grade == "FF":
                self.failedCoursesStrings.append(completedCourse.get_course_name())
                self.failedCoursesStrings.append(completedCourse.get_course_grade())

    def print_transcript_specific_student(self, student):
        student.transcript.transform_specific_student_transcript_elements_to_list(student)
        print("------------------------------------------------------------------------------------------------")
        print(str("ID: {}\nFullName: {}\nCourses Taken: {}\nGPA: {}\nTotal Credits: {}\nAvailable Courses: {}\nAdvisor Approved Courses: {}\nFailed Courses: {}".format(student.get_student_id(), student.get_full_name(), self.completedCourseStrings, self.gpa, self.completedCredits, student.get_available_courses(), self.studentSelectedCourses, self.failedCoursesStrings)))

    def transform_transcript_elements_to_list(self):
        self.completedCourseStrings = []
        for completedCourses in self.completedCourses:
            self.completedCourseStrings.append(completedCourses.get_course_name())
            self.completedCourseStrings.append(completedCourses.get_course_grade())

    def transform_specific_student_transcript_elements_to_list(self, student):
        for completedCourses in student.get_completed_courses():
            self.completedCourseStrings.append(str("{} {} Given: {}".format(completedCourses.get_course_name(), completedCourses.get_course_grade(), completedCourses.get_given_semester())))

    def save_transcript_to_json(self):
        data = {}
        data['completedCourses'] = []
        for completedCourse in self.completedCourses:
            data['completedCourses'].append({
                'courseName': completedCourse.get_course_name(),
                'courseGrade': completedCourse.get_course_grade(),
                'givenSemester': completedCourse.get_given_semester()
            })
        data['failedCourses'] = []
        for failedCourse in self.failedCourses:
            data['failedCourses'].append({
                'courseName': failedCourse.get_course_name(),
                'courseGrade': failedCourse.get_course_grade(),
                'givenSemester': failedCourse.get_given_semester()
            })
        data['gpa'] = self.gpa
        data['completedCredits'] = self.completedCredits
        data['studentSelectedCourses'] = self.studentSelectedCourses
        data['student'] = {
            'studentId': self.student.student_id,
            'fName': self.student.f_name,
            'lName': self.student.l_name,
            'advisor': self.student.get_advisor_name()
        }

        with open('transcript.json', 'w') as outfile:
            json.dump(data, outfile)
