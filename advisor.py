class Advisor(Person):

    def __init__(self):
        self._advisorId = ""
        self._studentsList = []

    def set_advisor(self, advisorId):
        self._advisorId = advisorId

    def set_fName(self, fName):
        super().set_fName(fName)

    def set_lName(self, lName):
        super().set_lName(lName)

