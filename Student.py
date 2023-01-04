import logging
from random import random
from logger import logger
from person import Person
from transcript import Transcript


class Student(Person):
    logger = logging.getLogger(__name__)

    def __init__(self):
        self.student_id = 0
        self.total_credit = 0
        self.advisor = None
        self.gpa = 0.0
        self.current_year = 0
        self.current_semester = 0
        self.current_selected_courses = []
        self.completed_courses = []
        self.available_courses = []
        self.failed_courses = []
        self.transcript = None

    def set_fname(self, fname):
        super().set_fname(fname)

    def set_lname(self, lname):
        super().set_lname(lname)

    def get_transcript(self):
        return self.transcript

    def set_advisor(self, advisor):
        self.advisor = advisor

    def get_advisor(self):
        return self.advisor

    def get_current_year(self):
        return self.current_year

    def set_current_year(self, current_year):
        self.current_year = current_year

    def get_current_semester(self):
        return self.current_semester

    def set_current_semester(self, current_semester):
        self.current_semester = current_semester

    def set_student_id(self, student_id):
        self.student_id = student_id

    def get_student_id(self):
        return self.student_id

    def set_current_selected_courses(self, current_selected_courses):
        self.current_selected_courses = current_selected_courses

    def set_completed_courses(self, completed_courses):
        self.completed_courses = completed_courses

    def set_available_courses(self, mandatory_courses):
        self.available_courses = mandatory_courses

    def get_failed_courses(self):
        return self.failed_courses

    def set_failed_courses(self, failed_courses):
        self.failed_courses = failed_courses

    def get_gpa(self):
        return self.gpa

    def get_current_selected_courses(self):
        return self.current_selected_courses

    def get_completed_courses(self):
        return self.completed_courses


def select_from_available_courses(self, max_number_of_selection_for_courses):
    courses_add = []
    # Here if we have more than 10 available courses we first check failed courses
    # and add them first, after that we randomly select other classes until it's size is 10
    if len(self.available_courses) > max_number_of_selection_for_courses:
        for s in self.available_courses:
            if self.check_if_course_failed(s):
                courses_add.append(s)
                logger.info(f"{self.get_f_name()} {self.get_l_name()} Prioritized choosing: {s} Because of failing before because he had more than 10 courses available for choosing")
        # Here we check the list again and if it has less than 10 we add them until it becomes size 10
        # we add those to the list. until it's size is 10
        if len(courses_add) < max_number_of_selection_for_courses:
            for s in self.available_courses:
                if s not in courses_add:
                    courses_add.append(s)
                if len(courses_add) == max_number_of_selection_for_courses:
                    break
            # then we update selected_courses with this method
            self.current_selected_courses.extend(courses_add)
        # If however the size becomes more than 10
        # we remove some until it goes back to size 10 again.
        elif len(courses_add) > max_number_of_selection_for_courses:
            while len(courses_add) != max_number_of_selection_for_courses:
                courses_add.pop(max_number_of_selection_for_courses)
            self.current_selected_courses.extend(courses_add)
    # If the size is not more than 10 we add every available course to selected_courses attr.
    else:
        self.current_selected_courses.extend(self.available_courses)






def choose_from_elective_courses(self, ue, fte, nte, te):
    for i in range(len(self.current_selected_courses)):
        value = random.randint(5)
        if "UE" in self.current_selected_courses[i]:
            self.current_selected_courses[i] = ue[value].course_code
        elif "FTE" in self.current_selected_courses[i]:
            self.current_selected_courses[i] = fte[value].course_code
        elif "NTE" in self.current_selected_courses[i]:
            self.current_selected_courses[i] = nte[value].course_code
        elif "TE" in self.current_selected_courses[i]:
            self.current_selected_courses[i] = te[value].course_code






def get_available_courses(self):
    return self.available_courses

# This checks if the course is failed for this student
# and returns true if failed or false if passed
def check_if_course_failed(self, course_code):
    for completed_course in self.completed_courses:
        if completed_course.course_name == course_code and completed_course.course_grade == "FF":
            return True
    return False

# This method sends current_selected_courses to the corresponding advisor for this student
def send_to_advisor_selected_classes(self):
    self.advisor.advisor_control(self.current_selected_courses, self)

# This is a method the advisor calls
# it changes selected courses depending on if each course is accepted or rejected and then updates it
def change_selected_courses(self, advisor_approved_courses, advisor_rejected_courses_and_reasons, advisor_name):
    self.current_selected_courses.clear()
    self.current_selected_courses.extend(advisor_approved_courses)
    logger.info(f"For student: {self.get_f_name()} {self.get_l_name()}\n{advisor_name} approved: {advisor_approved_courses}\nrejected: {advisor_rejected_courses_and_reasons}")

# this returns how many courses this student finished.
def get_completed_course_number(self):
    return len(self.completed_courses)



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
    for completed_course in self.completed_courses:
        for course in courses:
            if completed_course.course_name == course.course_code:
                credit = course.credit
                break
        if completed_course.course_grade == "AA":
            sum_ += aa * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "BA":
            sum_ += ba * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "BB":
            sum_ += bb * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "CB":
            sum_ += cb * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "CC":
            sum_ += cc * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "DC":
            sum_ += dc * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "DD":
            sum_ += dd * credit
            credit_sum += credit
            transcript_credit_sum += credit
        elif completed_course.course_grade == "FD":
            sum_ += fd * credit
            credit_sum += credit
        elif completed_course.course_grade == "FF":
            sum_ += ff * credit
            credit_sum += credit
        else:
            print("Hatali giris yaptiniz.")

    gpa = round(sum_ / credit_sum, 2)
    self.gpa = gpa
    self.total_credit = transcript_credit_sum






def get_advisor_name(self):
    return f"{self.advisor.get_f_name()} {self.advisor.get_l_name()}"

def generate_transcript(self):
    transcript = Transcript(self.completed_courses, self.failed_courses, self.gpa, self.total_credit, self.get_current_selected_courses(), self)
    self.transcript = transcript
    transcript.print_transcript_specific_student(self)





