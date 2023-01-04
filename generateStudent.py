import random
from typing import List, Dict

from Student import Student
from courses import Courses
from advisor import Advisor


class GenerateStudent:
    def __init__(self, student: List[Student], courses: List[Courses], UE: List[Courses], TE: List[Courses], NTE: List[Courses], FTE: List[Courses], advisors: List[Advisor], courseFFRate: int, maxNumberOfSelectionForCourses: int, semester: str):
        self.courseFFRate = courseFFRate
        self.maxNumberOfSelectionForCourses = maxNumberOfSelectionForCourses
        self.semester = semester
        self.student = student
        self.courses = courses
        self.advisors = advisors
        self.UE = UE
        self.TE = TE
        self.NTE = NTE
        self.FTE = FTE
        self.firstSemesterCourses = []
        self.secondSemesterCoursesHash = {}
        self.thirdSemesterCoursesHash = {}
        self.fourthSemesterCoursesHash = {}
        self.fifthSemesterCourses = {}
        self.sixthSemesterCoursesHash = {}
        self.seventhSemesterCoursesHash = {}
        self.eighthSemesterCoursesHash = {}
        self.prerequisiteList = {}

    def addCourseNames(self):
        for course in self.courses:
            if course.getSemester() == 1:
                self.firstSemesterCourses.append(course.getCourseCode())
            elif course.getSemester() == 2:
                self.secondSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 3:
                self.thirdSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 4:
                self.fourthSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 5:
                self.fifthSemesterCourses[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 6:
                self.sixthSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 7:
                self.seventhSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            elif course.getSemester() == 8:
                self.eighthSemesterCoursesHash[course.getCourseCode()] = course.getPrerequisite()
            if course.getPrerequisite() is not None:
                for prerequisite in course.getPrerequisite():
                    self.prerequisiteList[prerequisite] = course.getCourseCode()

    def addPrerequisite(self, courseCode: str, prerequisite: str):
        self.prerequisiteList[prerequisite] = courseCode

    def getAdvisor(self) -> Advisor:
        return random.choice(self.advisors)
