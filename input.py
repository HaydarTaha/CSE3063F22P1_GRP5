import json
from courses import Courses


class Input:
    def __init__(self, data):
        self.courses = []
        self.nte = []
        self.fte = []
        self.ue = []
        self.te = []
        self.semester = data['semester']
        self.courseFFRate = data['courseFFRate']
        self.quotaForElectives = data['quotaForElectives']
        self.quotaForMandatory = data['quotaForMandatory']
        self.maxNumberOfSelectionForCourses = data['maxNumberOfSelectionForCourses']
        self.coursesJsonName = data['coursesJsonName']
        self.electiveNTEJsonFileName = data['electiveNTEJsonFileName']
        self.electiveFTEJsonFileName = data['electiveFTEJsonFileName']
        self.electiveUEJsonFileName = data['electiveUEJsonFileName']
        self.electiveTEJsonFileName = data['electiveTEJsonFileName']
        self.advisorsJsonName = data['advisorsJsonName']
        self.studentsJsonName = data['studentsJsonName']

    def create_courses(self):
        with open(self.coursesJsonName, 'r') as f:
            coursesData = json.load(f)
        for data in coursesData:
            tempCourse = Courses(data)
            self.courses.append(tempCourse)
        with open(self.electiveNTEJsonFileName, 'r') as f:
            nteData = json.load(f)
        for data in nteData:
            tempCourse = Courses(data)
            self.nte.append(tempCourse)
        with open(self.electiveFTEJsonFileName, 'r') as f:
            fteData = json.load(f)
        for data in fteData:
            tempCourse = Courses(data)
            self.fte.append(tempCourse)
        with open(self.electiveUEJsonFileName, 'r') as f:
            ueData = json.load(f)
        for data in ueData:
            tempCourse = Courses(data)
            self.ue.append(tempCourse)
        with open(self.electiveTEJsonFileName, 'r') as f:
            teData = json.load(f)
        for data in ueData:
            tempCourse = Courses(data)
            self.te.append(tempCourse)

    def create_objects(self):
        self.create_courses()