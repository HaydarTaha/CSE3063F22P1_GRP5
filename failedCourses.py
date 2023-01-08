class FailedCourses:
    def __init__(self):
        self.__course_name = None
        self.__course_grade = None

    def get_course_grade(self):
        return self.__course_grade

    def set_course_grade(self, course_grade):
        self.__course_grade = course_grade

    def get_course_name(self):
        return self.__course_name

    def set_course_name(self, course_name):
        self.__course_name = course_name

    def __str__(self):
        return str("{}, {}".format(self.__course_name, self.__course_grade))