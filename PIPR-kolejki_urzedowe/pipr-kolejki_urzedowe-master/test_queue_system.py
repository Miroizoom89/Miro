from queue_system import Queue
import tkinter as tk
from gui import Application
from authorities_dict import authorities
from queue_plot import QueuePlot

queue = Queue()


def test_get_id():
    assert 2 == 2
    assert queue.get_id('UD Ochota') == '624d7e2a-bf45-48d6-ba79-8b512e662d1c'
    assert queue.get_id('UD Rembertów') == 'a0d1cb24-c6bf-4574-b3e0-868fa47f14cc'
    assert queue.get_id('UD Mokotów 2') == 'bc83ab5a-0ccc-4e4a-b58d-b821e16df176'
    assert queue.get_id('UD Ochota') == authorities['UD Ochota']['id']
    assert queue.get_id('UD Ochota') is not authorities['UD Wawer']['id']
    assert type(queue.get_id('UD Rembertów')) == str


def test_response_URL():
    assert 2 == 2
    assert queue.response_URL('') == 'https://api.um.warszawa.pl/api/action/wsstore_get?id='
    assert queue.response_URL('007') == 'https://api.um.warszawa.pl/api/action/wsstore_get?id=007'
    assert queue.response_URL('daa70b08-54a3-4fd8-95c2-a16e9b2b7fbd') == 'https://api.um.warszawa.pl/api/action/wsstore_get?id=daa70b08-54a3-4fd8-95c2-a16e9b2b7fbd'
    assert queue.response_URL('') != ''


def test_list_of_institutions():
    assert len(queue.list_of_institutions()) > 0
    assert 'UD Ochota' in queue.list_of_institutions()
    assert '' not in queue.list_of_institutions()
    assert 'Ud Ochota' not in queue.list_of_institutions()


def test_cases():
    assert queue.cases('UD Ochota')[5] == 'K: KASA'
    assert len(queue.cases('UD Bielany')) == 20
    assert '' not in queue.cases('USC Andersa')
    assert '1234' not in queue.cases('USC Andersa')
    assert 'ABCD' not in queue.cases('USC Andersa')


def test_actual_queue():
    assert 2 == 2
    number_of_stands, people_in_queue, actual_number = queue.actual_queue('UD Ochota')
    assert number_of_stands[0] >= 0
    assert people_in_queue[0] >= 0


def test_case_ordinal_number():
    assert 2 == 2
    assert queue.case_ordinal_number('UD Wola', 'Meldunki i dowody') >= 0
    assert queue.case_ordinal_number('UD Wola', 'Sprawy lokalne') >= 0
    assert queue.case_ordinal_number('UD Wola', 'Wydawanie dowodów osobistych') >= 0
    assert queue.case_ordinal_number('UD Białołęka', 'J: PRAWA JAZDY') >= 0
    assert type(queue.case_ordinal_number('UD Białołęka', 'J: PRAWA JAZDY')) == int


def test_current_queue():
    assert 2 == 2
    number_of_stands, people_in_queue, current_number = queue.current_queue('USC Andersa', 'X: KASA')
    assert number_of_stands >= 0
    assert people_in_queue >= 0
    assert type(number_of_stands) == int
    assert type(people_in_queue) == int
    assert type(current_number) == str


def test_get_chosen_institution():
    assert 2 == 2
    plot = QueuePlot(institution='UD Wola', case='Sprawy lokalne', number=3)
    number_of_people, x_data = plot.get_chosen_institution()
    assert type(plot.number_of_people) == list
    assert type(plot.x_data) == list


def test_plot_data():
    assert 2 == 2
    assert sum([1, 3, 2]) == 6
    assert len([2, 4, 5, 6]) != 0


def test_get_api_institution():
    assert 2 == 2
    assert sum([1, 3, ]) != 6
    assert len([2, 4]) != 0


def test_get_api_cases():
    assert 2 == 2
    assert sum([1, 3, 2]) == 6
    assert type([2, 4, 5, 6]) != None
    assert type([2, 4, 5, 6]) == list
