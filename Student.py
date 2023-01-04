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



