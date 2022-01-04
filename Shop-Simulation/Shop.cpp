#include "Shop.h"

using namespace std;

Shop::Shop(string file) {
	counters.clear();
	assistants.clear();
	clients.clear();
	opening_hours = 0;
	m_file.open(file, ofstream::out | ofstream::trunc);
}
Shop::~Shop() {
	m_file.close();
}

vector<string> Shop::get_parameters(const string& filename)
{
	ifstream source;
	source.open(filename);
	vector<string> lines;
	string line;
	while (getline(source, line))
	{
		lines.push_back(line);
	}
	return lines;
}

vector<int> Shop::get_user_parameters(const string& filename)
{
	ifstream source;
	source.open(filename);
	vector<int> lines;
	string line;
	while (getline(source, line))
	{
		stringstream str(line);
		int x = 0;
		str >> x;
		lines.push_back(x);
	}
	return lines;
}

bool Shop::is_natural_number(string number) {
	unsigned int check = 0;
	for (unsigned int i = 0;i < number.length();i++) {
		char c = number[i];
		if (c >= '0' && c <= '9') {
			check++;
		}
	}
	if (check == number.length()) {
		return true;
	}
	else {
		return false;
	}
}

void Shop::create_assistants_objects(int number) {
	for (int i = 1;i <= number;i++) {
		Shop_assistant assistant("none", "none");
		assistants.push_back(assistant);
	}
}
void Shop::create_counters_objects(int number) {
	for (int i = 1;i <= number;i++) {
		Counter counter;
		counters.push_back(counter);
	}
}
void Shop::create_clients_objects(int number) {
	for (int i = 1;i <= number;i++) {
		Client client("none", "none", 0);
		clients.push_back(client);
	}
}

void Shop::set_opening_hours() {
	string userinput = "";
	cout << "How much time the simulation should last?" << endl;
	getline(cin, userinput);
	try {
		opening_hours = stof(userinput);
	}
	catch (...){
		cout << "Invalid data" << endl;
		set_opening_hours();
	}
}

void Shop::set_opening_hours(int amount)
{
	opening_hours = amount;
}

void Shop::set_assistants() {
	string userinput = "";
	cout << "How many employees should work in the store?" << endl;
	getline(cin, userinput);
	try {
		this->create_assistants_objects(stof(userinput));
	}
	catch (...) {
		cout << "Invalid data" << endl;
		set_assistants();
	}
}

void Shop::set_assistants(int amount)
{
	this->create_assistants_objects(amount);
}

void Shop::set_counters() {
	string userinput = "";
	cout << "How many cash registers are to be in the store?" << endl;
	getline(cin, userinput);
	try {
		this->create_counters_objects(stof(userinput));
	}
	catch (...) {
		cout << "Invalid data" << endl;
		set_counters();
	}
}

void Shop::set_counters(int amount)
{
	this->create_counters_objects(amount);
}

void Shop::set_clients() {
	string userinput = "";
	cout << "How many customers will visit the store" << endl;
	getline(cin, userinput);
	try {
		this->create_clients_objects(stof(userinput));
	}
	catch (...) {
		cout << "Invalid data" << endl;
		set_clients();
	}
}

void Shop::set_clients(int amount)
{
	this->create_clients_objects(amount);
}

void Shop::setting_user_presets() {
	this->set_opening_hours();
	this->set_assistants();
	this->set_counters();
	this->set_clients();
}

void Shop::setting_user_presets(vector<int> file)
{
	this->set_opening_hours(file[0]);
	this->set_assistants(file[1]);
	this->set_counters(file[2]);
	this->set_clients(file[3]);
}

void Shop::map_assistants_and_counters()
{
	if (counters.size() <= assistants.size())
	{
		for (unsigned int i = 0; i < counters.size(); i++)
		{
			counters[i].set_assistant(&assistants[i]);
			assistants[i].set_stance(counters[i].get_id());
			open_counters = open_counters++;
		}
	}

	else
	{
		for (unsigned int i = 0; i < assistants.size(); i++)
		{
			counters[i].set_assistant(&assistants[i]);
			assistants[i].set_stance(counters[i].get_id());
			open_counters = open_counters++;
		}
	}
}

int Shop::generate_random_int(int from, int to)
{
	unsigned seed = chrono::system_clock::now().time_since_epoch().count();
	default_random_engine generator(seed);
	uniform_int_distribution<int> distribution(from, to);
	return distribution(generator);
}

int Shop::get_open_counters()
{
	return open_counters;
}

void Shop::map_clients_and_counters()
{
	for (unsigned int i = 0; i < clients.size(); i++)
	{
		int r = generate_random_int(0, open_counters - 1);
		Client & ref = clients[i];
		counters[r].add_to_queue(ref);
	}
}

void Shop::generate_purchases()
{
	
	for (Client & client : clients){
		Essentials essental_products(get_parameters("parameters.txt")[0]);
		Fruits fruits(get_parameters("parameters.txt")[1]);
		Vegetables vegetables(get_parameters("parameters.txt")[2]);
		Products *point = nullptr;
		switch (generate_random_int(0, 2)) {
		case(0):
			point = &essental_products;
			break;
		case(1):
			point = &fruits;
			break;
		case(2):
			point = &vegetables;
			break;
		}
		point = &essental_products;
		client.add_item_to_cart(point->get_product(generate_random_int(0, 9)));
	}
}

void Shop::run_a_simulation() {
	setting_user_presets(get_user_parameters("user_parameters.txt"));
	map_assistants_and_counters();
	for (int i = 0; i < opening_hours;i++) {
		generate_purchases();
	}
	map_clients_and_counters();
	
	for (int i = 0; i < counters.size(); i++) {
		for (int j = 0; j < counters[i].get_queue_size(); j++) {
			if (m_file.good() && counters[i].counter_is_open() && counters[i].get_queue_size() >= 1) {
				m_file << counters[i].track_operations(counters[i].get_client(j));
				m_file << counters[i].create_receipt(counters[i].get_client(j));
				cout << counters[i].track_operations(counters[i].get_client(j)) << endl;
				cout << counters[i].create_receipt(counters[i].get_client(j)) << endl;
				this_thread::sleep_for(chrono::milliseconds(1500));
			}	
		}
	}
}