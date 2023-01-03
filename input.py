class Input:
    def __init__(self, data):
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