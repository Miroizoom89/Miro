import tkinter as tk
from queue_system import Queue
from queue_plot import QueuePlot
import datetime


class Application(tk.Frame):

    def __init__(self, master):
        '''This function creates an interface object and calls
            function create_widgets() to set widgets on it.'''
        tk.Frame.__init__(self, master)
        self.grid()
        self.create_widgets()

    def get_api_institutions(self):
        '''This function returns list of institutions,
            it uses a method list_of_institutions from Queue class. '''
        q = Queue()
        institutions = q.list_of_institutions()
        return institutions

    def get_api_cases(self, user_choice):
        '''This function returns a list of cases in chosen institution.'''
        cases = []
        self.listbox2.delete(0, tk.END)
        q = Queue()
        cases = q.cases(user_choice)
        return cases

    def entry_get(self, evt=None):
        '''This event function prints each case
            from get_api_cases to listbox widgets.'''
        try:
            self.listbox2.delete(0, tk.END)
            self.label8['text'] = '_'
            self.label10['text'] = '_'
            self.label12['text'] = '_'
            value = ''
            value = str((self.entry.get()))
            cases = self.get_api_cases(value)
            self.listbox2.insert(tk.END, *cases)
        except TypeError:
            self.entry.delete(0, tk.END)
            self.entry.insert(0, f'Brak danych dla {value}')

    def click_entry(self, evt=None):
        '''This event function clears the interface while switching choice.'''
        self.listbox2.delete(0, tk.END)
        self.label8['text'] = '_'
        self.label10['text'] = '_'
        self.label12['text'] = '_'

    def cursor_selection1(self, evt=None):
        '''This event function allows user to chose the institution
            double_clicking on item in a listbox.'''
        value = ''
        value = str((self.listbox1.get(tk.ANCHOR)))
        self.entry.delete(0, tk.END)
        self.entry.insert(tk.END, value)

    def get_api_queue(self, value, chosen_case):
        '''This function returns current state of a queue for chosen case.'''
        queue = []
        q = Queue()
        queue = q.current_queue(value, chosen_case)
        return queue

    def cursor_selection2(self, evt=None):
        '''This event function prints information about the queue on interface
            and shows a plot.'''
        chosen_case = ''
        chosen_case = str((self.listbox2.get(tk.ANCHOR)))
        value = self.entry.get()
        number_of_stands, people_in_queue, current_number = \
            self.get_api_queue(value, chosen_case)
        self.label8['text'] = number_of_stands
        self.label10['text'] = people_in_queue
        self.label12['text'] = current_number
        self.label14['text'] = datetime.datetime.now().strftime("%H:%M:%S")
        self.after(30000, func=self.cursor_selection2)

    def data_from_gui(self):
        '''This function collects data from guidepending on the user choice.'''
        institution_name = str((self.entry.get()))
        case_name = str((self.listbox2.get(tk.ANCHOR)))
        number_of_people = self.label10['text']
        return institution_name, case_name, number_of_people

    def make_plot(self, evt=None):
        '''This function is responsible for creating a plot.'''
        try:
            institution_name, case_name, number_of_people = self.data_from_gui()
            p = QueuePlot(institution_name, case_name, number_of_people)
            p.plot_data(institution_name, case_name, number_of_people)
        except ValueError:
            print('Nie można stworzyć wykresu - brak danych')

    def show_plot(self, evt=None):
        '''This event function prints out a plot.'''
        self.make_plot()

    def create_widgets(self):
        '''This function creates and sets in a right place all widgets
            placed on interface.'''

        institutions = self.get_api_institutions()

        self.label1 = tk.Label(self, text='Podaj nazwę urzędu:\
            ', font=("Helvetica", 12))
        self.label1.grid(row=0, column=0, sticky=tk.W, pady=2)

        self.entry = tk.Entry(self, width=30)
        self.entry.grid(row=1, column=0, sticky=tk.W, pady=2)

        self.label2 = tk.Label(self, text='Obsługiwane urzędy:\
            ', font=("Helvetica", 12))
        self.label2.grid(row=2, column=0, sticky=tk.W, pady=2)

        self.scrollbar1 = tk.Scrollbar(self, orient=tk.VERTICAL)
        self.scrollbar1.grid(row=3, column=0, sticky=tk.E+tk.N+tk.S, rowspan=5)

        self.listbox1 = tk.Listbox(self, width=30, yscrollcommand=self.scrollbar1.set)
        self.listbox1.insert(tk.END, *institutions)
        self.listbox1.grid(row=3, column=0, columnspan=10, rowspan=5, sticky=tk.N+tk.W)

        self.button = tk.Button(self, text='Zatwierdź', command=self.entry_get)
        self.button.grid(row=1, column=1, sticky=tk.W, pady=2)

        self.label3 = tk.Label(self, text='       ->       ')
        self.label3.grid(row=0, column=2, sticky=tk.W, pady=2)

        self.label4 = tk.Label(self, text='Wybierz sprawę:', font=("Helvetica\
            ", 12))
        self.label4.grid(row=0, column=3, sticky=tk.W, pady=2)

        self.scrollbar2 = tk.Scrollbar(self, orient=tk.HORIZONTAL)
        self.scrollbar2.grid(row=6, column=3, rowspan=5, sticky=tk.N+tk.W+tk.E)

        self.scrollbar3 = tk.Scrollbar(self, orient=tk.VERTICAL)
        self.scrollbar3.grid(row=1, column=4, rowspan=5, sticky=tk.W+tk.N+tk.S)

        self.listbox2 = tk.Listbox(self, xscrollcommand=self.scrollbar2.set, yscrollcommand=self.scrollbar3.set)
        self.listbox2.grid(row=1, column=3, columnspan=10, rowspan=10, sticky=tk.N+tk.W)

        self.label5 = tk.Label(self, text='           ->          ')
        self.label5.grid(row=0, column=4, sticky=tk.W, pady=2)

        self.label6 = tk.Label(self, text='Aktulany stan kolejki:', font=("\
            Helvetica", 12))
        self.label6.grid(row=0, column=5, sticky=tk.W, pady=2)

        self.label7 = tk.Label(self, text='Liczba stanowisk:', font=("\
            Helvetica", 10))
        self.label7.grid(row=1, column=5, sticky=tk.W, pady=2)

        self.label8 = tk.Label(self, text='_', font=("Helvetica", 10))
        self.label8.grid(row=1, column=6, sticky=tk.W, pady=2)

        self.label9 = tk.Label(self, text='Licba osób w kolejce:', font=("\
            Helvetica", 10))
        self.label9.grid(row=2, column=5, sticky=tk.W, pady=2)

        self.label10 = tk.Label(self, text='_', font=("Helvetica", 10))
        self.label10.grid(row=2, column=6, sticky=tk.W, pady=2)

        self.label11 = tk.Label(self, text='Aktualny numer:', font=("\
            Helvetica", 10))
        self.label11.grid(row=3, column=5, sticky=tk.NW, pady=2)

        self.label12 = tk.Label(self, text='_', font=("Helvetica", 10))
        self.label12.grid(row=3, column=6, sticky=tk.NW, pady=2)

        self.button2 = tk.Button(self, text='Pokaż wykres', command=self.show_plot)
        self.button2.grid(row=3, column=5, sticky=tk.W, rowspan=3, pady=2)

        self.label13 = tk.Label(self, text='Ostatnia aktualizacja:', font=("\
            Helvetica", 10))
        self.label13.grid(row=8, column=5, sticky=tk.NW, pady=2)

        self.label14 = tk.Label(self, text='', font=("Helvetica", 10))
        self.label14.grid(row=8, column=6, sticky=tk.NW, pady=2)

        self.scrollbar1.config(command=self.listbox1.yview)
        self.scrollbar2.config(command=self.listbox2.xview)
        self.scrollbar3.config(command=self.listbox2.yview)
        self.entry.bind('<Return>', self.entry_get)
        self.entry.bind('<Button>', self.click_entry)
        self.listbox1.bind('<Double-1>', self.cursor_selection1)
        self.listbox2.bind('<<ListboxSelect>>', self.cursor_selection2)


if __name__ == '__main__':

    root = tk.Tk()
    root.title('Systemy kolejkowe')
    root.geometry('730x300')

    app = Application(root)

    root.mainloop()
