import logging
from typing import List, Tuple

class CalculateAvailables:
    logger = logging.getLogger(__name__)
    semester_one_courses_names = []
    semester_two_courses_names = []
    semester_three_courses_names = []
    semester_four_courses_names = []
    semester_five_courses_names = []
    semester_six_courses_names = []
    semester_seven_courses_names = []
    semester_eight_courses_names = []
    calculated_semester_two_course_names = []
    calculated_semester_three_course_names = []
    calculated_semester_four_course_names = []
    calculated_semester_five_course_names = []
    calculated_semester_six_course_names = []
    calculated_semester_seven_course_names = []
    calculated_semester_eight_course_names = []
    student_courses_took = []
    course_name = ""
    course_grade = ""
    prerequisite = ""
    max_number_of_selection_for_courses = 0

    def set_max_number_of_selection_for_courses(self, max_number_of_selection_for_courses: int) -> None:
        self.max_number_of_selection_for_courses = max_number_of_selection_for_courses

    def get_max_number_of_selection_for_courses(self) -> int:
        return self.max_number_of_selection_for_courses