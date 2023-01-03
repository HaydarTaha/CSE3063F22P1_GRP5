import random

from person import Person


class Advisor(Person):

    def __init__(self):
        self._advisorId = None
        self._studentsList = []

    def set_fName(self, fName):
        super().set_fName(fName)

    def set_lName(self, lName):
        super().set_lName(lName)

    def set_advisor(self, advisorId):
        self._advisorId = advisorId

    def advisor_control(self, chosenClasses, student):
        stringArray = []
        stringArray.append('Rejected because quota is full')
        stringArray.append('Rejected because the timing is not appropriate')
        stringArray.append('Rejected because student cannot take many lessons')

        rejectedList = []
        acceptedList = []

        courseSize = len(chosenClasses)

        for i in range(courseSize):
            randomNumber = random.randint(0, 3)
            randomNumber2 = random.randint(0, 3)
            randomNumber3 = random.randint(0, 3)

            if randomNumber == 0:
                if randomNumber2 == 0 and randomNumber3 == 0:
                    rejectedList.append(student.get_current_selected_courses()[i])
                    rejectedList.append(stringArray[0])
                else:
                    acceptedList.append(student.get_current_selected_courses()[i])
            elif randomNumber == 1:
                if randomNumber2 == 1 and randomNumber3 == 1:
                    rejectedList.append(student.get_current_selected_courses()[i])
                    rejectedList.append(stringArray[1])
                else:
                    acceptedList.append(student.get_current_selected_courses()[i])
            elif randomNumber == 2:
                if randomNumber2 == 2 and randomNumber3 == 2:
                    rejectedList.append(student.get_current_selected_courses([i]))
                    rejectedList.append(stringArray[2])
                else:
                    acceptedList.append(student.get_current_selected_courses()[i])

        student.change_selected_courses(acceptedList, rejectedList, self.get_fName() + ' ' + self.get_lName)

    def add_advisors_looking_list(self, std):
        self._studentsList.append(std)

    def get_students_list(self):
        return self._studentsList
