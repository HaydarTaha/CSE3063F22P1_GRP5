import logging
import random
from person import Person
from transcript import Transcript
from failedCourses import FailedCourses
from advisor import Advisor


class Student(Person):
    def __init__(self, data):
        super().__init__(data['fName'], data['lName'])
        self.__advisor = None
        self.__advisor: Advisor
        self.__student_id = data['studentId']
        self.__total_credit = None
        self.__gpa = None
        self.__current_year = None
        self.__current_semester = None
        self.__current_selected_courses = []
        self.__completed_courses = []
        self.__available_courses = []
        self.__failed_courses = [FailedCourses]
        self.__transcript = None
        self.__logger = logging.getLogger(self.__class__.__name__)

    def get_student_id(self):
        return self.__student_id

    def get_current_year(self):
        return self.__current_year

    def set_current_year(self, current_year):
        self.__current_year = current_year

    def set_current_semester(self, current_semester):
        self.__current_semester = current_semester

    def get_current_semester(self):
       return self.__current_semester

    def set_completed_courses(self, completed_courses):
        self.__completed_courses = completed_courses

    def set_failed_courses(self, failed_courses):
        self.__failed_courses = failed_courses

    def get_failed_courses(self):
        return self.__failed_courses

    def get_available_courses(self):
        return self.__available_courses

    def set_current_selected_courses(self, current_selected):
        self.__current_selected_courses = current_selected

    # This method selects courses which are in availableCourses
    def select_from_available_courses(self, max_number_of_selection_for_courses):
        courses_add = []
        # Here if we have more than 10 available courses we first check failed courses
        # and add them first, after that we randomly select other classes until its size is 10
        if len(self.available_courses) > max_number_of_selection_for_courses:
            for s in self.available_courses:
                if self.check_if_course_failed(s):
                    courses_add.append(s)
                    self.__logger.info(str("{} {} Prioritized choosing: {} Because of failing before because he had more than 10 courses available for choosing".format(super().f_name, super().l_name, s)))
            if len(courses_add) < max_number_of_selection_for_courses:
                for s in self.available_courses:
                    if s not in courses_add:
                        courses_add.append(s)
                    if len(courses_add) == max_number_of_selection_for_courses:
                        break
                self.__current_selected_courses.extend(courses_add)
            elif len(courses_add) > max_number_of_selection_for_courses:
                while len(courses_add) != max_number_of_selection_for_courses:
                    courses_add.pop(max_number_of_selection_for_courses)
                self.__current_selected_courses.extend(courses_add)
            else:
                self.__current_selected_courses.extend(self.available_courses)

    def choose_from_elective_courses(self, ue, fte, nte, te):
        for i in range(len(self.__current_selected_courses)):
            value = random.randint(0, 4)
            if "UE" in self.__current_selected_courses[i]:
                self.__current_selected_courses[i] = ue[value].course_code
            elif "FTE" in self.__current_selected_courses[i]:
                self.__current_selected_courses[i] = fte[value].course_code
            elif "NTE" in self.__current_selected_courses[i]:
                self.__current_selected_courses[i] = nte[value].course_code
            elif "TE" in self.__current_selected_courses[i]:
                self.__current_selected_courses[i] = te[value].course_code

    # This checks if the course is failed for this student
    # and returns true if failed or false if passed
    def check_if_course_failed(self, course_code):
        check = False
        for completed_course in self.__completed_courses:
            if completed_course.course_name == course_code and completed_course.course_grade == "FF":
                return True
        return False

    # This method sends current_selected_courses to the corresponding advisor for this student
    def send_to_advisor_selected_classes(self):
        print(self.__advisor)

    # This is a method the advisor calls
    # it changes selected courses depending on if each course is accepted or rejected and then updates it
    def change_selected_courses(self, advisor_approved_courses, advisor_rejected_courses_and_reasons, advisor_name):
        self.__current_selected_courses.clear()
        self.__current_selected_courses.extend(advisor_approved_courses)
        self.__logger.info(str("For student: {} {} \n{} approved: {} \nrejected: {}".format(super().f_name, super().l_name, advisor_name, advisor_approved_courses, advisor_rejected_courses_and_reasons)))

# this returns how many courses this student finished.
    def get_completed_course_number(self):
        return len(self.__completed_courses)

    def gpa_calculator(self, courses):
        aa = 4.00
        ba = 3.50
        bb = 3.00
        cb = 2.50
        cc = 2.00
        dc = 1.50
        dd = 1.00
        fd = 0.50
        ff = 0.00

        credit = 0
        sum_ = 0
        credit_sum = 0
        transcript_credit_sum = 0
        for completed_course in self.__completed_courses:
            for course in courses:
                if completed_course.get_course_name() == course.get_course_code():
                    credit = course.get_credit()
                    break
            if completed_course.get_course_grade() == "AA":
                sum_ += aa * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "BA":
                sum_ += ba * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "BB":
                sum_ += bb * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "CB":
                sum_ += cb * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "CC":
                sum_ += cc * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "DC":
                sum_ += dc * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "DD":
                sum_ += dd * credit
                credit_sum += credit
                transcript_credit_sum += credit
            elif completed_course.get_course_grade() == "FD":
                sum_ += fd * credit
                credit_sum += credit
            elif completed_course.get_course_grade() == "FF":
                sum_ += ff * credit
                credit_sum += credit
            else:
                print("Invalid input.")

        gpa = round(sum_ / credit_sum, 2)
        self.__gpa = gpa
        self.__total_credit = transcript_credit_sum

    def get_advisor_name(self):
        return str("{} {}".format(self.__advisor.super().f_name, self.__advisor.super.l_name))

    def generate_transcript(self):
        transcript = Transcript(self.__completed_courses, self.__failed_courses, self.__gpa, self.__total_credit, self.get_current_selected_courses(), self.__class__)
        self.transcript = transcript
        transcript.print_transcript_specific_student(self)

    def get_completed_courses(self):
        return self.__completed_courses

    def set_available_courses(self, available_courses):
        self.available_courses = available_courses

    def get_full_name(self):
        return super().get_f_name() + " " + super().get_l_name()

    def get_advisor(self):
        return self.advisor

    def get_current_selected_courses(self):
        return self.__current_selected_courses