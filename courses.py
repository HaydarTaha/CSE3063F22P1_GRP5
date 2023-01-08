class Courses:
    def __init__(self, data):
        self.__list_of_students = None
        self.__quota = None
        self.__name = data['name']
        self.__course_code = data['courseCode']
        self.__prerequisite = data['prerequisite']
        self.__credit = data['credit']
        self.__course_type = data['courseType']
        self.__semester = data['semester']
        self.__course_year = data['courseYear']
        self.__theoretical_course_hour = data['theoreticalCourseHour']
        self.__practical_lesson_hour = data['practicalLessonHour']

    def set_quota(self, quota):
        self.__quota = quota

    def set_course_type(self, courseType):
        self.__course_type = courseType

    def set_semester(self, semester):
        self.__semester = semester

    def set_theoretical_course_hour(self, theoretical_course_hour):
        self.__theoretical_course_hour = theoretical_course_hour

    def set_practical_lesson_hour(self, practical_lesson_hour):
        self.__practical_lesson_hour = practical_lesson_hour

    def get_course_type(self):
        return self.__course_type

    def get_name(self):
        return self.__name

    def get_course_code(self):
        return self.__course_code

    def get_prerequisite(self):
        return self.__prerequisite

    def get_credit(self):
        return self.__credit

    def set_name(self, name):
        self.__name = name

    def set_course_code(self, course_code):
        self.__course_code = course_code

    def set_course_year(self, course_year):
        self.__course_year = course_year

    def set_prerequisite(self, prerequisite):
        self.__prerequisite = prerequisite

    def set_credit(self, credit):
        self.__credit = credit

    def get_semester(self):
        return self.__semester

    def get_course_year(self):
        return self.__course_year

    def get_theoretical_course_hour(self):
        return self.__theoretical_course_hour

    def get_practical_lesson_hour(self):
        return self.__practical_lesson_hour

    def check_if_prerequisite(self, courses):
        return courses.get_prerequisite[0] != ""

    def check_if_two_prerequisite(self, courses):
        if courses.get_prerequisite()[0] != "" and courses.get_prerequisite()[1] != "":
            return True
        else:
            return False

    def get_prerequisite_name(self):
        return self.__prerequisite[0]

    def get_two_prerequisite_name(self, courses):
        preRequisiteNames = [courses.get_prerequisite()[0], courses.get_prerequisite()[1]]
        return preRequisiteNames

    def add_to_list_of_students(self, student):
        self.__list_of_students.append(student)
