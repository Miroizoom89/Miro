#include "Counter.h"

using namespace std;

Counter::Counter()
{
	static int _id = 1;
	id = _id++;
	sp = nullptr;
	clients_queue.clear();
}

int Counter::get_id() {
	return id;
}
bool Counter::counter_is_open() {
	if (sp != nullptr) return true;
	else return false;
}

void Counter::set_assistant(Shop_assistant* assistant)
{
	sp = assistant;
}

Shop_assistant& Counter::get_shop_assistant()
{
	return *sp;
}

void Counter::add_to_queue(Client& client)
{
	clients_queue.push_back(client);
}

void  Counter::remove_from_queue(int pos)
{
	clients_queue.erase(clients_queue.begin() + (pos - 1));
}

void Counter::remove_from_queue()
{
	clients_queue.erase(clients_queue.begin());
}

int Counter::get_queue_size()
{
	return clients_queue.size();
}

void Counter::print_queue()
{
	if (get_queue_size() == 0)
	{
		cout << "Queue is empty" << endl;
	}
	else
	{
		cout << "Queue:" << endl;
		for (int i = 0; i < get_queue_size(); i++)
		{
			cout << clients_queue[i] << endl;
		}
	}
}

Client Counter::get_client(int pos) {
	return clients_queue[pos];
}

double Counter::get_payment(Client c) {
	double amount_to_pay = 0;

	for (Item & item : c.getShopCart()) {
		amount_to_pay += item.get_price();
	}
	return amount_to_pay;
}

string Counter::create_receipt(Client c) {
	string bill = "Receipt for client " + to_string(c.getClientNumber()) + "\n";
	for (Item & item : c.getShopCart()) {
		bill += item.get_name() + "........." + to_string(item.get_price()) + "\n";
	}
	bill += "Payment total: " + to_string(get_payment(c)) + "\n";

	return bill;
}

string Counter::track_operations(Client c) {

	string operations = "";
	string client_id = to_string(c.getClientNumber());
	string shopassistant_id = to_string(get_shop_assistant().get_id());
	
	operations = "Client " + client_id + ":" + "\n";

	for (Item & item : c.getShopCart()) {
		string item_name = item.get_name();
		operations += "Client " + client_id + " buys a/an " + item_name + " from shop assistant " + shopassistant_id + "\n";
	}

	return operations;
}
