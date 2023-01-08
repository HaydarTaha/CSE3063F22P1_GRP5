import random
from person import Person


class Advisor(Person):

    def __init__(self, data):
        self.advisor_id = data['advisorId']
        super().__init__(data['fName'], data['lName'])
        self.students_list = []

    def advisor_control(self, chosen_classes, student):
        stringArray = ['Rejected because quota is full', 'Rejected because the timing is not appropriate',
                       'Rejected because student cannot take many lessons']
        rejectedList = []
        acceptedList = []

        courseSize = len(chosen_classes)

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

        student.change_selected_courses(acceptedList, rejectedList, super().f_name + ' ' + super().l_name)

    def add_advisors_looking_list(self, student):
        self.students_list.append(student)