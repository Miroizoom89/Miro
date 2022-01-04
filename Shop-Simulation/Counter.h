#pragma once
#include <vector>
#include <fstream>
#include <string>
#include <functional>
#include "Shop_assistant.h"
#include "Client.h"
#include "CounterInterface.h"

using namespace std;

class Counter: public CounterInterface {

	int id;
	Shop_assistant* sp;
	vector <reference_wrapper<Client>> clients_queue;

public:
	Counter();

	int get_id();
	bool counter_is_open();
	void set_assistant(Shop_assistant* assistant);
	Shop_assistant& get_shop_assistant();
	void add_to_queue(Client& client);
	void remove_from_queue(int pos);
	void remove_from_queue();
	int get_queue_size();
	void print_queue();
	Client get_client(int pos);
	double get_payment(Client c);
	string create_receipt(Client c);
	string track_operations(Client c);

};
