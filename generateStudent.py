import random

from failedCourses import FailedCourses
from completedCourses import CompletedCourses
from transcript import Transcript
from calculateAvailables import CalculateAvailables


class GenerateStudent:
    def __init__(self, student, courses, ue, te, nte, fte, advisors, course_ff_rate, max_number_of_selection_for_courses, semester):
        self.__course_ff_rate = course_ff_rate
        self.__max_number_of_selection_for_courses = max_number_of_selection_for_courses
        self.__semester = semester
        self.__student = student
        self.__courses = courses
        self.__advisors = advisors
        self.__ue = ue
        self.__te = te
        self.__nte = nte
        self.__fte = fte
        self.__first_semester_courses = []
        self.__second_semester_courses_hash = {}
        self.__third_semester_courses_hash = {}
        self.__fourth_semester_courses_hash = {}
        self.__fifth_semester_courses_hash = {}
        self.__sixth_semester_courses_hash = {}
        self.__seventh_semester_courses_hash = {}
        self.__eighth_semester_courses_hash = {}
        self.__prerequisite_list = {}

    def add_course_names(self):
        for course in self.__courses:
            if course.get_semester() == 1:
                self.__first_semester_courses.append(course.get_course_code())
            elif course.get_semester() == 2:
                self.__second_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 3:
                self.__third_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 4:
                self.__fourth_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 5:
                self.__fifth_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 6:
                self.__sixth_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 7:
                self.__seventh_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            elif course.get_semester() == 8:
                self.__eighth_semester_courses_hash[course.get_course_code()] = course.get_prerequisite()
            if course.get_prerequisite() is not None:
                for prerequisite in course.get_prerequisite():
                    self.__prerequisite_list[prerequisite] = course.get_course_code()

    def generate_year(self, student):
        number = student.get_student_id()
        year_number = (number // 1000) - 150000
        if year_number == 116:
            student.set_current_year(4)
        elif year_number == 117:
            student.set_current_year(3)
        elif year_number == 118:
            student.set_current_year(2)
        else:
            student.set_current_year(1)

    def semester_setter(self, s):
        if self.__semester == "Fall":
            if s.get_current_year() == 1:
                s.set_current_semester(1)
            elif s.get_current_year() == 2:
                s.set_current_semester(3)
            elif s.get_current_year() == 3:
                s.set_current_semester(5)
            elif s.get_current_year() == 4:
                s.set_current_semester(7)
        elif self.__semester == "Spring":
            if s.get_current_year() == 1:
                s.set_current_semester(2)
            elif s.get_current_year() == 2:
                s.set_current_semester(4)
            elif s.get_current_year() == 3:
                s.set_current_semester(6)
            elif s.get_current_year() == 4:
                s.set_current_semester(8)

    def set_courses_list(self, s):
        completed_courses = []
        failed_courses = []
        available_courses = []
        s.set_completed_courses(completed_courses)
        s.set_failed_courses(failed_courses)
        s.set_available_courses(available_courses)

    def assign_failed_courses(self, current_semester_failed, course_code):
        failed_courses = FailedCourses()
        failed_courses.set_course_grade("FF")
        failed_courses.set_course_name(course_code)
        current_semester_failed.append(failed_courses)

    def prerequisite_control_and_lock(self, course_code, locked_courses):
        for course_name, prerequisite in self.__prerequisite_list.items():
            if course_code in prerequisite:
                failed_prerequisite = [course_code]
                locked_courses[course_name] = failed_prerequisite

    def print_transcript(self):
        transcript = Transcript
        self.generate_available_courses(self.__student, self.__advisors, self.__courses)
        print(
            "----------------------------------------------------------------------------------------------------------------------------")
        for std in self.__student:
            std.generate_transcript()

    def add_completed_courses(self, current_semester_completed, course_code, grade, finished_semester):
        completed_courses = CompletedCourses()
        completed_courses.set_course_name(course_code)
        completed_courses.set_course_grade(grade)
        completed_courses.set_given_semester(finished_semester)
        current_semester_completed.append(completed_courses)

    def simulate_failed_courses(self, s, current_semester_completed, current_semester):
        failed_courses_size = len(s.get_failed_courses())
        if failed_courses_size > 0:
            for i in range(failed_courses_size):
                course_code = str("")
                if course_code not in current_semester_completed:
                    if self.course_is_given_already(s, course_code):
                        continue
                    else:
                        grade = None
                        try:
                            grade = self.assign_random_grades()
                        except Exception as e:
                            raise RuntimeError(e)
                        if grade != "FF":
                            if not self.course_is_given_already(s, course_code) != False:
                                self.add_completed_courses(current_semester_completed, course_code, grade, current_semester)
                                index = 0
                                for code in s.get_failed_courses():
                                    if code.get_course_name() == course_code:
                                        break
                                    index = index + 1
                                # s.get_failed_courses().pop(index)
                                failed_courses_size -= 1

    def unlock_locked_courses_and_set_available(self, s, completed_courses, locked_courses):
        if len(completed_courses) > 0:
            if len(locked_courses) > 0:
                for i in range(len(completed_courses)):
                    locked_check = False
                    for course_name, prerequisite in locked_courses.items():
                        for prerequisite_code in prerequisite:
                            if prerequisite_code == completed_courses[i].get_course_name():
                                s.get_available_courses().append(course_name)
                                locked_check = True
                    if locked_check:
                        pass
                        # del locked_courses[completed_courses[i].get_course_name()]

    def check_available_courses(self, s, semester, current_semester_completed, current_semester_failed, locked_courses,
                                current_semester_courses):
        for course_list in self.__courses:
            if len(s.get_available_courses()) > 0:
                if len(s.get_available_courses()) != 0:
                    size = len(s.get_available_courses())
                    for i in range(size):
                        course_code = s.get_available_courses()[i]
                        if course_list.get_course_code() == course_code and course_list.get_semester() == semester - 1:
                            if self.course_is_given_already(s, course_code):
                                continue
                            else:
                                if self.check_course_has_prerequisite(course_code):
                                    if self.check_prerequisite_course_is_given(s, course_code, i):
                                        grade = self.assign_random_grades()
                                        if grade == "FF":
                                            self.assign_failed_courses(current_semester_failed, course_code)
                                            self.prerequisite_control_and_lock(course_code, locked_courses)
                                        else:
                                            self.add_completed_courses(current_semester_completed, course_code, grade,
                                                                       semester)
                                            s.get_available_courses().pop(course_code)
                                            size -= 1
                                else:
                                    grade = self.assign_random_grades()
                                    if grade == "FF":
                                        self.assign_failed_courses(current_semester_failed, course_code)
                                        self.prerequisite_control_and_lock(course_code, locked_courses)
                                    else:
                                        self.add_completed_courses(current_semester_completed, course_code, grade,
                                                                   semester)
                                        s.get_available_courses().pop(course_code)
                                        size -= 1

    def check_course_given(self, s):
        for completed in s.get_completed_courses():
            course_code = completed.get_course_name()
            size = len(s.get_failed_courses())
            for i in range(size):
                if s.get_failed_courses()[i].get_course_name() == course_code:
                    s.get_failed_courses().pop(i)
                    size -= 1

    def check_course_has_prerequisite(self, course_code):
        is_course_prerequisite = False
        for course_name, prerequisite in self.__prerequisite_list.items():
            if course_name == course_code and prerequisite:
                is_course_prerequisite = True
                break
        return is_course_prerequisite

    def check_prerequisite_course_is_given(self, s, course_code, semester):
        prerequisite_courses = []
        for course_name, prerequisite in self.__prerequisite_list.items():
            if course_code == course_name:
                for prerequisite_code in prerequisite:
                    prerequisite_courses.append(prerequisite_code)

        if not prerequisite_courses:
            return True
        if not s.get_completed_courses():
            return False

        for completed_course in s.get_completed_courses():
            for prerequisite in prerequisite_courses:
                if completed_course.get_course_name() == prerequisite:
                    return True

        return False

    def course_is_given_already(self, s, course_code):
        for completed_courses in s.get_completed_courses():
            if completed_courses.get_course_name() == course_code:
                return True
        return False

    def set_student_advisor(self, s):
        number = random.randint(0, 5)
        s.advisor = self.__advisors[number]

    def generate_available_courses(self, students, advisors, courses):
        calculate_available = CalculateAvailables()
        calculate_available.set_available_courses_for_each_student(students, courses, advisors, self.__ue, self.__te, self.__fte, self.__nte, self.__max_number_of_selection_for_courses)

    def case_two(self, current_semester_completed, s, i, current_semester_failed, locked_courses):
        for course_code in self.__first_semester_courses:
            if course_code not in current_semester_completed:
                grade = self.assign_random_grades()
                if not self.course_is_given_already(s, course_code):
                    if grade == "FF":
                        self.assign_failed_courses(current_semester_failed, course_code)
                        self.prerequisite_control_and_lock(course_code, locked_courses)
                    else:
                        self.add_completed_courses(current_semester_completed, course_code, grade, i)

        current_semester_completed_size = len(current_semester_completed)
        current_semester_failed_size = len(current_semester_failed)
        for j in range(current_semester_completed_size):
            s.get_completed_courses().append(current_semester_completed[j])
        for j in range(current_semester_failed_size):
            s.get_failed_courses().append(current_semester_failed[j])
        current_semester_completed.clear()
        current_semester_failed.clear()

    def case_three(self, current_semester_completed, s, i, current_semester_failed, locked_courses):
        final_i = i
        for course_code, prerequisite in self.__second_semester_courses_hash.items():
            if course_code not in current_semester_completed:
                if course_code not in locked_courses:
                    if not self.check_course_has_prerequisite(course_code):
                        grade = self.assign_random_grades()
                        if not self.course_is_given_already(s, course_code):
                            if grade == "FF":
                                self.assign_failed_courses(current_semester_failed, course_code)
                                self.prerequisite_control_and_lock(course_code, locked_courses)
                            else:
                                self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
                    else:
                        if self.check_prerequisite_course_is_given(s, course_code, final_i):
                            grade = self.assign_random_grades()
                            if not self.course_is_given_already(s, course_code):
                                if grade == "FF":
                                    self.assign_failed_courses(current_semester_failed, course_code)
                                    self.prerequisite_control_and_lock(course_code, locked_courses)
                                else:
                                    self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
        self.simulate_failed_courses(s, current_semester_completed, final_i)
        current_semester_completed_size = len(current_semester_completed)
        current_semester_failed_size = len(current_semester_failed)
        for j in range(current_semester_completed_size):
            s.get_completed_courses().append(current_semester_completed[j])
        for j in range(current_semester_failed_size):
            s.get_failed_courses().append(current_semester_failed[j])
        current_semester_completed.clear()
        current_semester_failed.clear()
        self.unlock_locked_courses_and_set_available(s, s.get_completed_courses(), locked_courses)

    def case_four(self, current_semester_completed, s, i, current_semester_failed, locked_courses):
        final_i = i
        self.check_available_courses(s, final_i, current_semester_completed, current_semester_failed, locked_courses, self.__third_semester_courses_hash)
        for course_code, prerequisite in self.__third_semester_courses_hash.items():
            if course_code not in current_semester_completed:
                if course_code not in locked_courses:
                    if not self.check_course_has_prerequisite(course_code):
                        grade = self.assign_random_grades()
                        if not self.course_is_given_already(s, course_code):
                            if grade == "FF":
                                self.assign_failed_courses(current_semester_failed, course_code)
                                self.prerequisite_control_and_lock(course_code, locked_courses)
                            else:
                                self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
                    else:
                        if self.check_prerequisite_course_is_given(s, course_code, final_i):
                            grade = self.assign_random_grades()
                            if not self.course_is_given_already(s, course_code):
                                if grade == "FF":
                                    self.assign_failed_courses(current_semester_failed, course_code)
                                    self.prerequisite_control_and_lock(course_code, locked_courses)
                                else:
                                    self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
        self.simulate_failed_courses(s, current_semester_completed, final_i)
        current_semester_completed_size = len(current_semester_completed)
        current_semester_failed_size = len(current_semester_failed)
        for j in range(current_semester_completed_size):
            s.get_completed_courses().append(current_semester_completed[j])
        for j in range(current_semester_failed_size):
            s.get_failed_courses().append(current_semester_failed[j])
        current_semester_completed.clear()
        current_semester_failed.clear()
        self.unlock_locked_courses_and_set_available(s, s.get_completed_courses(), locked_courses)

    def other_cases(self, current_semester_completed, s, i, current_semester_failed, locked_courses, semester_courses):
        final_i = i
        self.check_available_courses(s, final_i, current_semester_completed, current_semester_failed, locked_courses, semester_courses)
        for course_code, prerequisite in semester_courses.items():
            if course_code not in current_semester_completed:
                if course_code not in locked_courses:
                    if not self.check_course_has_prerequisite(course_code):
                        grade = self.assign_random_grades()
                        if not self.course_is_given_already(s, course_code):
                            if grade == "FF":
                                self.assign_failed_courses(current_semester_failed, course_code)
                                self.prerequisite_control_and_lock(course_code, locked_courses)
                            else:
                                self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
                    else:
                        if self.check_prerequisite_course_is_given(s, course_code, final_i):
                            grade = self.assign_random_grades()
                            if not self.course_is_given_already(s, course_code):
                                if grade == "FF":
                                    self.assign_failed_courses(current_semester_failed, course_code)
                                    self.prerequisite_control_and_lock(course_code, locked_courses)
                                else:
                                    self.add_completed_courses(current_semester_completed, course_code, grade, final_i)
        self.simulate_failed_courses(s, current_semester_completed, final_i)
        current_semester_completed_size = len(current_semester_completed)
        current_semester_failed_size = len(current_semester_failed)
        for j in range(current_semester_completed_size):
            s.get_completed_courses().append(current_semester_completed[j])
        for j in range(current_semester_failed_size):
            s.get_failed_courses().append(current_semester_failed[j])
        current_semester_completed.clear()
        current_semester_failed.clear()
        self.unlock_locked_courses_and_set_available(s, s.get_completed_courses(), locked_courses)

    def simulate_semester(self, s):
        self.semester_setter(s)
        current_semester_completed = []
        current_semester_failed = []
        locked_courses = {}
        for i in range(1, s.get_current_semester() + 1):
            if i == 1:
                self.set_courses_list(s)
            elif i == 2:
                self.case_two(current_semester_completed, s, i, current_semester_failed, locked_courses)
            elif i == 3:
                self.case_three(current_semester_completed, s, i, current_semester_failed, locked_courses)
            elif i == 4:
                self.case_four(current_semester_completed, s, i, current_semester_failed, locked_courses)
            elif i == 5:
                self.other_cases(current_semester_completed, s, i, current_semester_failed, locked_courses, self.__fourth_semester_courses_hash)
            elif i == 6:
                self.other_cases(current_semester_completed, s, i, current_semester_failed, locked_courses, self.__fifth_semester_courses_hash)
            elif i == 7:
                self.other_cases(current_semester_completed, s, i, current_semester_failed, locked_courses, self.__sixth_semester_courses_hash)
            elif i == 8:
                self.other_cases(current_semester_completed, s, i, current_semester_failed, locked_courses, self.__seventh_semester_courses_hash)
            self.check_course_given(s)

    def remove_unnamed_courses(self, s):
        duplicate_courses = []
        duplicate_times = []
        for i in range(len(s.get_completed_courses())):
            count = 0
            course_code = s.get_completed_courses()[i].get_course_name()
            for j in range(i, len(s.get_completed_courses())):
                check_code = s.get_completed_courses()[j].get_course_name()
                if course_code == check_code:
                    count += 1
            if count >= 2:
                duplicate_courses.append(course_code)
                duplicate_times.append(count)
        if len(duplicate_courses) > 0:
            dup_size = len(duplicate_courses)
            course_size = len(s.get_completed_courses())
            for i in range(dup_size):
                dup_position = []
                for j in range(course_size):
                    code = s.get_completed_courses()[j].get_course_name()
                    if code == duplicate_courses[i]:
                        dup_position.append(j)
                dup_pos_size = len(dup_position) - 1
                for j in range(dup_pos_size):
                    position = dup_position[j]
                    s.get_completed_courses().pop(j)
                    course_size -= 1
        for completed_course in s.get_completed_courses():
            value = random.randint(0, 4)
            if "UE" in completed_course.get_course_name():
                completed_course.set_course_name(self.__ue[value].get_course_code())
            elif "FTE" in completed_course.get_course_name():
                completed_course.set_course_name(self.__fte[value].get_course_code())
            elif "NTE" in completed_course.get_course_name():
                completed_course.set_course_name(self.__nte[value].get_course_code())
            elif "TE" in completed_course.get_course_name():
                completed_course.set_course_name(self.__te[value].get_course_code())

    def simulate(self):
        self.add_course_names()
        for s in self.__student:
            self.generate_year(s)
            self.simulate_semester(s)
            self.remove_unnamed_courses(s)
            self.set_student_advisor(s)
            available_courses_size = len(s.get_available_courses())
            for i in range(available_courses_size):
                # s.get_available_courses().pop(i)
                available_courses_size = available_courses_size - 1
            current_selected = []
            s.set_current_selected_courses(current_selected)
            if len(s.get_failed_courses()) > 0:
                for failed_courses in s.get_failed_courses():
                    completed_courses = CompletedCourses()
                    completed_courses.set_course_name(failed_courses.get_course_name())
                    completed_courses.set_course_grade(failed_courses.get_course_grade())
                    # s.completed_courses.append(completed_courses)
        self.print_transcript()

    def assign_random_grades(self):
        ff_rate = self.__course_ff_rate / 100.0
        other_grades_rate = (1 - ff_rate) / 7
        random_number = random.random()
        random_letter = ""
        if random_number < ff_rate:
            random_letter = "FF"
        elif random_number < other_grades_rate + ff_rate:
            random_letter = "DD"
        elif random_number < other_grades_rate * 2 + ff_rate:
            random_letter = "DC"
        elif random_number < other_grades_rate * 3 + ff_rate:
            random_letter = "CC"
        elif random_number < other_grades_rate * 4 + ff_rate:
            random_letter = "CB"
        elif random_number < other_grades_rate * 5 + ff_rate:
            random_letter = "BB"
        elif random_number < other_grades_rate * 6 + ff_rate:
            random_letter = "BA"
        else:
            random_letter = "AA"

        return random_letter
