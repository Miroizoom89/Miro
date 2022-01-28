import matplotlib.pyplot as plt
import datetime


class QueuePlot():

    def __init__(self, institution, case, number):
        '''This function creates an object of the QueuePlot class
            and attach attributes to it.'''
        self.institution = institution
        self.case = case
        self.number = number
        self.number_of_people = []
        self.x_data = []

        plt.clf()

    def get_chosen_institution(self, institution_name=None, case_name=None, number=None):
        '''This function returns updated data in order to create a plot.'''

        if self.institution != institution_name:
            if self.case != case_name:
                self.institution = institution_name
                self.case = case_name
                self.number_of_people.clear()
                plt.clf()

        self.number_of_people.append(number)
        self.x_data.append(datetime.datetime.now().strftime("%H:%M"))
        return self.number_of_people, self.x_data

    def plot_data(self, institution_name=None, case_name=None, number=None):
        '''This function creates and edits a plot.'''
        y_data, x_data = self.get_chosen_institution(institution_name, case_name, number)
        plt.plot(x_data, y_data, '.-', label=self.case)
        plt.title('Aktywne monitorowanie stanu kolejki')
        plt.legend(bbox_to_anchor=(1, 1), loc=2, prop={'size': 3})
        plt.xlabel('Aktualny czas')
        plt.ylim(ymin=0)
        plt.ylabel('Liczba osób w kolejce')
        plt.tight_layout(rect=[0, 0, 1.02, 1])
        plt.show()
