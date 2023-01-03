class Courses:
    def __init__(self, data):
        self.listOfStudents = None
        self.quota = None
        self.name = data['name']
        self.courseCode = data['courseCode']
        self.prerequisite = data['prerequisite']
        self.credit = data['credit']
        self.courseType = data['courseType']
        self.semester = data['semester']
        self.courseYear = data['courseYear']
        self.theoreticalCourseHour = data['theoreticalCourseHour']
        self.practicalLessonHour = data['practicalLessonHour']

    def set_quota(self, quota):
        self.quota = quota

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
        preRequisiteNames = [courses.get_prerequisite()[0], courses.get_prerequisite()[1]]
        return preRequisiteNames

    def add_to_list_of_students(self, student):
        self.listOfStudents.append(student)