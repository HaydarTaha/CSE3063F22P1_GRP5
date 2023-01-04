from courses import Courses

class CompletedCourses(Courses):
    def __init__(self, course_name, course_grade, given_semester):
        self.course_name = course_name
        self.course_grade = course_grade
        self.given_semester = given_semester

    def get_course_name(self):
        return self.course_name

    def set_course_name(self, course_name):
        self.course_name = course_name

    def get_course_grade(self):
        return self.course_grade

    def set_course_grade(self, course_grade):
        self.course_grade = course_grade

    def get_given_semester(self):
        return self.given_semester

    def set_given_semester(self, given_semester):
        self.given_semester = given_semester

    def __str__(self):
        return f"{self.course_name}, {self.course_grade}, {self.given_semester}"
