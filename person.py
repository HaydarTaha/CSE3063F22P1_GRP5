from abc import ABC


class Person(ABC):
    def __init__(self, f_name, l_name):
        self.f_name = f_name
        self.l_name = l_name

    def get_f_name(self):
        return self.f_name

    def get_l_name(self):
        return self.l_name