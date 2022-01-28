import requests
from authorities_dict import authorities

general_URL_address = 'https://api.um.warszawa.pl/api/action/wsstore_get?id='


class Queue():

    def __init__(self):
        pass

    def get_id(self, institution):
        '''This function searches a authorities dictionary for
            an id number of chosen institution.'''
        institution_id = ''
        for item in authorities:
            if item == institution:
                institution_id = authorities[item]['id']
        return institution_id

    def response_URL(self, id):
        '''This funnction creates an individual url address
            for a chosen institution.'''
        response_adress = general_URL_address + id
        return response_adress

    def list_of_institutions(self):
        '''This function collects all institutions from
            authorities dictionary.'''
        institutions = []
        for item in authorities:
            institutions.append(item)
        return institutions

    def connection_to_server(self, url):
        '''This function sends request to API and returns data for
            chosen institution such as precise informations about each case.'''
        result = requests.get(url)
        return result.json()

    def cases(self, user_choice):
        '''This function delivers a list of cases feasible
            to take in chosen innstitution.'''
        try:
            id = self.get_id(user_choice)
            url = self.response_URL(id)
            connect = self.connection_to_server(url)
            dictionary = connect['result']['grupy']
            list_of_cases = []
            for item in dictionary:
                list_of_cases.append(item['nazwaGrupy'])
            return list_of_cases
        except TypeError:
            print(f"Brak danych dla {user_choice}")

    def actual_queue(self, user_choice):
        '''This function collects from API data such as number of stands at the office,
         amount of people in the queue and current number at the desk.'''
        id = self.get_id(user_choice)
        url = self.response_URL(id)
        connect = self.connection_to_server(url)
        dictionary = connect['result']['grupy']
        number_of_stands = []
        people_in_queue = []
        actual_number = []
        for item in dictionary:
            number_of_stands.append(item['liczbaCzynnychStan'])
            people_in_queue.append(item['liczbaKlwKolejce'])
            actual_number.append(item['aktualnyNumer'])
        return number_of_stands, people_in_queue, actual_number

    def case_ordinal_number(self, user_choice, case):
        '''This function returns an index of chosen case in a list of cases.'''
        index = 0
        for item in self.cases(user_choice):
            if case == item:
                index = self.cases(user_choice).index(item)
        return index

    def current_queue(self, user_choice, case):
        '''This function returns index position of current state of a queue
            for chosen case.'''
        number_of_stands, people_in_queue, current_number = \
            self.actual_queue(user_choice)
        index = self.case_ordinal_number(user_choice, case)
        return (number_of_stands[index], people_in_queue[index],
                current_number[index])
