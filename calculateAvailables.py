import logging


class CalculateAvailables:

    def __init__(self):
        self.logger = logging.getLogger(self.__class__.__name__)
        self.semester_one_courses_names = []
        self.semester_two_courses_names = []
        self.semester_three_courses_names = []
        self.semester_four_courses_names = []
        self.semester_five_courses_names = []
        self.semester_six_courses_names = []
        self.semester_seven_courses_names = []
        self.semester_eight_courses_names = []
        self.calculated_semester_two_course_names = []
        self.calculated_semester_three_course_names = []
        self.calculated_semester_four_course_names = []
        self.calculated_semester_five_course_names = []
        self.calculated_semester_six_course_names = []
        self.calculated_semester_seven_course_names = []
        self.calculated_semester_eight_course_names = []
        self.student_courses_took = []
        self.course_name = None
        self.course_grade = None
        self.prerequisite = None
        self.max_number_of_selection_for_courses = None

    def set_max_number_of_selection_for_courses(self, max_number_of_selection_for_courses):
        self.max_number_of_selection_for_courses = max_number_of_selection_for_courses

    def get_max_number_of_selection_for_courses(self):
        return self.max_number_of_selection_for_courses

    def set_attributes(self, courses):
        # Everytime i call this method i need to update these lists again
        for course in courses:
            if course.get_semester() == 1:
                self.semester_one_courses_names.append(course.get_course_code())
            elif course.get_semester() == 2:
                self.semester_two_courses_names.append(course.get_course_code())
            elif course.get_semester() == 3:
                self.semester_three_courses_names.append(course.get_course_code())
            elif course.get_semester() == 4:
                self.semester_four_courses_names.append(course.get_course_code())
            elif course.get_semester() == 5:
                self.semester_five_courses_names.append(course.get_course_code())
            elif course.get_semester() == 6:
                self.semester_six_courses_names.append(course.get_course_code())
            elif course.get_semester() == 7:
                self.semester_seven_courses_names.append(course.get_course_code())
            elif course.get_semester() == 8:
                self.semester_eight_courses_names.append(course.get_course_code())

    def put_available_courses_case_two(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i+1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_two_course_names.remove(courses.get_course_code())
                        self.calculated_semester_two_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_two_course_names.append(self.course_name)
        return self.calculated_semester_two_course_names

    def put_available_courses_case_three(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_three_course_names.remove(courses.get_course_code())
                        self.calculated_semester_three_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_three_course_names.append(self.course_name)
        return self.calculated_semester_three_course_names

    def put_available_courses_case_four(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_four_course_names.remove(courses.get_course_code())
                        self.calculated_semester_four_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_four_course_names.append(self.course_name)
        return self.calculated_semester_four_course_names

    def put_available_courses_case_five(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite,
                                                                                        courses.get_course_code())))
                        self.calculated_semester_five_course_names.remove(courses.get_course_code())
                        self.calculated_semester_five_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_five_course_names.append(self.course_name)
        return self.calculated_semester_five_course_names

    def put_available_courses_case_six(self ,courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_six_course_names.remove(courses.get_course_code())
                        self.calculated_semester_six_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_six_course_names.append(self.course_name)
        return self.calculated_semester_six_course_names

    def put_available_courses_case_seven(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_seven_course_names.remove(courses.get_course_code())
                        self.calculated_semester_seven_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_seven_course_names.append(self.course_name)
        return self.calculated_semester_seven_course_names

    def put_available_courses_case_eigth(self, courses, student_courses_took, std_name):
        for i in range(0, len(student_courses_took), 2):
            self.course_name = student_courses_took[i]
            self.course_grade = student_courses_took[i + 1]
            if self.course_grade == "FF":
                test = False
                for courses in courses:
                    if self.course_name == courses.get_prerequisite_name():
                        prerequisite = courses.get_prerequisite_name()
                        self.logger.info(str("{} Failed: {} He cannot choose {}".format(std_name, prerequisite, courses.get_course_code())))
                        self.calculated_semester_eight_course_names.remove(courses.get_course_code())
                        self.calculated_semester_eight_course_names.append(prerequisite)
                        test = True
                        break
                if not test:
                    self.calculated_semester_eight_course_names.append(self.course_name)
        return self.calculated_semester_eight_course_names

    def calculated_courses_re_setter(self):
        self.calculated_semester_two_course_names = []
        self.calculated_semester_three_course_names = []
        self.calculated_semester_four_course_names = []
        self.calculated_semester_five_course_names = []
        self.calculated_semester_six_course_names = []
        self.calculated_semester_seven_course_names = []
        self.calculated_semester_eight_course_names = []
        self.student_courses_took = []

    def set_available_courses_for_each_student(self, students, courses, advisors, UE, TE, FTE, NTE, max_number_of_selection_for_courses):
        self.set_attributes(courses)
        for student in students:
            self.calculated_courses_re_setter()
            for completedCourses in student.get_completed_courses():
                self.student_courses_took.append(completedCourses.get_course_name())
                self.student_courses_took.append(completedCourses.get_course_grade())
            if student.get_current_semester() == 1:
                student.set_available_courses(self.semester_one_courses_names)
            elif student.get_current_semester() == 2:
                student.set_available_courses(
                    self.put_available_courses_case_two(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 3:
                student.set_available_courses(
                    self.put_available_courses_case_three(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 4:
                student.set_available_courses(
                    self.put_available_courses_case_four(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 5:
                student.set_available_courses(
                    self.put_available_courses_case_five(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 6:
                student.set_available_courses(
                    self.put_available_courses_case_six(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 7:
                student.set_available_courses(
                    self.put_available_courses_case_seven(courses, self.student_courses_took, student.get_full_name()))
            elif student.get_current_semester() == 8:
                student.set_available_courses(
                    self.put_available_courses_case_eigth(courses, self.student_courses_took, student.get_full_name()))

        self.set_student_for_each_advisor(students, advisors)
        for student in students:
            student.select_from_available_courses(max_number_of_selection_for_courses)
            student.choose_from_elective_courses(UE, FTE, NTE, TE)
            student.send_to_advisor_selected_classes()
            student.gpa_calculator(courses)
        self.set_students_for_each_courses(students, courses)


    def set_student_for_each_advisor(self, students, advisors):
        for advisor in advisors:
            for student in students:
                if student.get_advisor() == advisor:
                    advisor.add_advisors_looking_list(student)

    def set_students_for_each_courses(self, students, courses):
        for student in students:
            for s in student.get_current_selected_courses():
                for course in courses:
                    if s == course.get_course_code():
                        course.add_to_list_of_students(student)