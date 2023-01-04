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


