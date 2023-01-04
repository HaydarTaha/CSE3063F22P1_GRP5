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

    def set_attributes(courses):
        # Everytime i call this method i need to update these lists again
        semester_one_courses_names = []
        semester_two_courses_names = []
        semester_three_courses_names = []
        semester_four_courses_names = []
        semester_five_courses_names = []
        semester_six_courses_names = []
        semester_seven_courses_names = []
        semester_eight_courses_names = []

        for course in courses:
            if course.get_semester() == 1:
                semester_one_courses_names.append(course.get_course_code())
            elif course.get_semester() == 2:
                semester_two_courses_names.append(course.get_course_code())
            elif course.get_semester() == 3:
                semester_three_courses_names.append(course.get_course_code())
            elif course.get_semester() == 4:
                semester_four_courses_names.append(course.get_course_code())
            elif course.get_semester() == 5:
                semester_five_courses_names.append(course.get_course_code())
            elif course.get_semester() == 6:
                semester_six_courses_names.append(course.get_course_code())
            elif course.get_semester() == 7:
                semester_seven_courses_names.append(course.get_course_code())
            elif course.get_semester() == 8:
                semester_eight_courses_names.append(course.get_course_code())

    def put_available_courses_case_two(courses, student_courses_took, std_name):
        calculated_semester_two_course_names = []
        for i in range(0, len(student_courses_took), 2):
            course_name = student_courses_took[i]
            course_grade = student_courses_took[i + 1]
            if course_grade == "FF":
                test = False
                # Here we iterate every courses available until we find the same course with the completed one of the student.
                for courses1 in courses:
                    # If a failed course is a prerequisite to another course
                    # We delete that course in calculated list and add the failed course back to the calculatedlist
                    if course_name == courses1.get_prerequisite_name():
                        prerequisite = courses1.get_prerequisite_name()
                        print(f"{std_name} Failed: {prerequisite} He cannot choose {courses1.get_course_code()}")
                        calculated_semester_two_course_names.remove(courses1.get_course_code())
                        calculated_semester_two_course_names.add(prerequisite)
                        test = True
                        break
                # If he failed but there is not any prerequisite needed for above courses
                # we add the same course again but we do not remove anything.