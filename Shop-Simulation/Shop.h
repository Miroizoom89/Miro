#pragma once
#include <iostream>
#include <vector>
#include <string>
#include "Counter.h"
#include <random>
#include <chrono>
#include <fstream>
#include <thread>
#include <sstream>
#include "Fruits.h"
#include "Essentials.h"
#include "Vegetables.h"


using namespace std;

class Shop {
	vector <Counter> counters;
	vector <Shop_assistant> assistants;
	vector <Client> clients;
	fstream m_file;
	int opening_hours;
	int open_counters = 0;
	bool is_natural_number(string number);
	void create_assistants_objects(int number);
	void create_counters_objects(int number);
	void create_clients_objects(int number);

public:
	Shop(string file);

	vector<string> get_parameters(const string & filename);
	vector<int> get_user_parameters(const string& filename);
	void set_opening_hours();
	void set_assistants();
	void set_counters();
	void set_clients();
	void set_opening_hours(int amount);
	void set_assistants(int amount);
	void set_counters(int amount);
	void set_clients(int amount);
	void setting_user_presets();
	void setting_user_presets(vector<int> file);
	void map_assistants_and_counters();
	int generate_random_int(int from, int to);
	int get_open_counters();
	void map_clients_and_counters();
	void generate_purchases();
	
	void run_a_simulation();
	~Shop();
};
