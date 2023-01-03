import json
from courses import Courses


class Input:
    def __init__(self, data):
        self.courses = None
        self.nte = None
        self.fte = None
        self.ue = None
        self.fte = None
        self.te = None
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

    def create_objects(self):
        with open(self.coursesJsonName, 'r') as f:
            coursesData = json.load(f)
        self.courses = Courses(coursesData)
