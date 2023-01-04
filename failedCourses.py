from courses import Courses

class FailedCourses(Courses):
    def __init__(self, course_name, course_grade):
        self.course_name = course_name
        self.course_grade = course_grade

    def get_course_grade(self):
        return self.course_grade

    def set_course_grade(self, course_grade):
        self.course_grade = course_grade

    def get_course_name(self):
        return self.course_name

    def set_course_name(self, course_name):
        self.course_name = course_name

    def __str__(self):
        return f"{self.course_name}, {self.course_grade}"