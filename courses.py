class Courses:
    def __init__(self):
        self.name = ""
        self.courseCode = ""
        self.prerequisite = []
        self.credit = 0
        self.courseType = 0
        self.semester = 0
        self.quota = 0
        self.courseYear = 0
        self.theoreticalCourseHour = 0
        self.practicalLessonHour = 0
        self.listOfStudents = []

    def set_quota(self, quota):
        self.quota = quota

    def set_course_type(self, courseType):
        self.courseType = courseType

    def set_semester(self, semester):
        self.semester = semester

    def set_theoretical_course_hour(self, theoreticalCourseHour):
        self.theoreticalCourseHour = theoreticalCourseHour

    def set_practical_lesson_hour(self, practicalLessonHour):
        self.practicalLessonHour = practicalLessonHour

    def get_semester(self):
        return self.semester

    def get_course_year(self):
        return self.courseYear

    def set_course_year(self, courseYear):
        self.courseYear = courseYear

    def get_theoretical_course_hour(self):
        return self.theoreticalCourseHour

    def get_practical_lesson_hour(self):
        return self.practicalLessonHour

    def get_course_type(self):
        return self.courseType

    def get_name(self):
        return self.name

    def get_course_code(self):
        return self.courseCode

    def get_prerequisite(self):
        return self.prerequisite

    def get_credit(self):
        return self.credit

    def set_name(self, name):
        self.name = name

    def set_course_code(self, courseCode):
        self.courseCode = courseCode

    def set_prerequisite(self, prerequisite):
        self.prerequisite = prerequisite

    def set_credit(self, credit):
        self.credit = credit

    def check_if_prerequisite(self, courses):
        return courses.get_prerequisite()[0] != ""

    def check_if_two_prerequisite(self, courses):
        if courses.get_prerequisite()[0] != "" and courses.get_prerequisite()[1] != "":
            return True
        else:
            return False

    def get_prerequisite_name(self):
        return self.prerequisite[0]

    def get_two_prerequisite_name(self, courses):
        preRequisiteNames = []
        preRequisiteNames.append(courses.get_prerequisite()[0])
        preRequisiteNames.append(courses.get_prerequisite()[1])
        return preRequisiteNames

    def add_to_list_of_students(self, student):
        self.listOfStudents.append(student)
