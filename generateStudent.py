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

    def selectCoursesForSemester(self, student: Student, completedCourses: List[str], currentSemesterCourses: Dict[str, List[str]]) -> List[str]:
        selectedCourses = []
        for courseCode, prerequisites in currentSemesterCourses.items():
            if prerequisites is None or all(prereq in completedCourses for prereq in prerequisites):
                selectedCourses.append(courseCode)
        return selectedCourses

    def isFF(self, student: Student) -> bool:
        return student.getCourseFFRate() >= self.courseFFRate

    def checkCourseLimit(self, selectedCourses: List[str]) -> bool:
        return len(selectedCourses) > self.maxNumberOfSelectionForCourses

    def generateRandom(self, student: Student) -> List[str]:
        completedCourses = student.getCompletedCourses()
        selectedCourses = []
        if self.semester == "First":
            selectedCourses = random.sample(self.firstSemesterCourses, self.maxNumberOfSelectionForCourses)
        elif self.semester == "Second":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.secondSemesterCoursesHash)
        elif self.semester == "Third":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.thirdSemesterCoursesHash)
        elif self.semester == "Fourth":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.fourthSemesterCoursesHash)
        elif self.semester == "Fifth":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.fifthSemesterCourses)
        elif self.semester == "Sixth":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.sixthSemesterCoursesHash)
        elif self.semester == "Seventh":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.seventhSemesterCoursesHash)
        elif self.semester == "Eighth":
            selectedCourses = self.selectCoursesForSemester(student, completedCourses, self.eighthSemesterCoursesHash)
        if self.checkCourseLimit(selectedCourses):
            return []
        return selectedCourses

    def generateYear(student: Student):
        number = student.getStudentId()
        yearNumber = (number // 1000) - 150000
        if yearNumber == 116:
            student.setCurrentYear(4)
        elif yearNumber == 117:
            student.setCurrentYear(3)
        elif yearNumber == 118:
            student.setCurrentYear(2)
        else:
            student.setCurrentYear(1)

    def semesterSetter(student: Student, semester=None):
        if semester == "Fall":
            if student.getCurrentYear() == 1:
                student.setCurrentSemester(1)
            elif student.getCurrentYear() == 2:
                student.setCurrentSemester(3)
            elif student.getCurrentYear() == 3:
                student.setCurrentSemester(5)
            elif student.getCurrentYear() == 4:
                student.setCurrentSemester(7)
        elif semester == "Spring":
            if student.getCurrentYear() == 1:
                student.setCurrentSemester(2)
            elif student.getCurrentYear() == 2:
                student.setCurrentSemester(4)
            elif student.getCurrentYear() == 3:
                student.setCurrentSemester(6)
            elif student.getCurrentYear() == 4:
                student.setCurrentSemester(8)





