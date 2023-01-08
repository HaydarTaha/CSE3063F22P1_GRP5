import json
from courses import Courses
from student import Student
from advisor import Advisor
from generateStudent import GenerateStudent

class Input:
    def __init__(self, data):
        self.__courses = []
        self.__nte = []
        self.__fte = []
        self.__ue = []
        self.__te = []
        self.__advisors = []
        self.__students = []
        self.__semester = data['semester']
        self.__course_ff_rate = data['courseFFRate']
        self.__quota_for_electives = data['quotaForElectives']
        self.__quota_for_mandatory = data['quotaForMandatory']
        self.__max_number_of_selection_for_courses = data['maxNumberOfSelectionForCourses']
        self.__courses_json_name = data['coursesJsonName']
        self.__elective_nte_json_file_name = data['electiveNTEJsonFileName']
        self.__elective_fte_json_file_name = data['electiveFTEJsonFileName']
        self.__elective_ue_json_file_name = data['electiveUEJsonFileName']
        self.__elective_te_json_file_name = data['electiveTEJsonFileName']
        self.__advisors_json_name = data['advisorsJsonName']
        self.__students_json_name = data['studentsJsonName']

    def set_quota(self):
        length = len(self.__courses)
        for i in range(length):
            self.__courses[i].quota = self.__quota_for_mandatory
        length = len(self.__nte)
        for i in range(length):
            self.__nte[i].quota = self.__quota_for_electives
        length = len(self.__fte)
        for i in range(length):
            self.__fte[i].quota = self.__quota_for_electives
        length = len(self.__ue)
        for i in range(length):
            self.__ue[i].quota = self.__quota_for_electives
        length = len(self.__te)
        for i in range(length):
            self.__te[i].quota = self.__quota_for_electives

    def create_courses(self):
        with open(self.__courses_json_name, 'r') as f:
            courses_data = json.load(f)
        for data in courses_data:
            temp_course = Courses(data)
            self.__courses.append(temp_course)

        with open(self.__elective_nte_json_file_name, 'r') as f:
            nte_data = json.load(f)
        for data in nte_data:
            temp_course = Courses(data)
            self.__nte.append(temp_course)

        with open(self.__elective_fte_json_file_name, 'r') as f:
            fte_data = json.load(f)
        for data in fte_data:
            temp_course = Courses(data)
            self.__fte.append(temp_course)

        with open(self.__elective_ue_json_file_name, 'r') as f:
            ue_data = json.load(f)
        for data in ue_data:
            temp_course = Courses(data)
            self.__ue.append(temp_course)

        with open(self.__elective_te_json_file_name, 'r') as f:
            te_data = json.load(f)
        for data in te_data:
            temp_course = Courses(data)
            self.__te.append(temp_course)
        self.set_quota()

    def create_objects(self):
        self.create_student()
        self.create_advisor()
        self.create_courses()

    def create_advisor(self):
        with open(self.__advisors_json_name, 'r') as f:
            advisor_data = json.load(f)
        for data in advisor_data:
            temp_advisor = Advisor(data)
            self.__advisors.append(temp_advisor)

    def create_student(self):
        with open(self.__students_json_name, 'r') as f:
            student_data = json.load(f)
        for data in student_data:
            temp_student = Student(data)
            self.__students.append(temp_student)

    def start_simulation_with_inputs(self):
        generate_student = GenerateStudent(self.__students, self.__courses, self.__ue, self.__te, self.__nte, self.__fte, self.__advisors, self.__course_ff_rate, self.__max_number_of_selection_for_courses, self.__semester)
        generate_student.simulate()