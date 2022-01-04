#pragma once
#include <iostream>
#include <string>
#include <iostream>

using namespace std;

struct Item {
	string name = "";
	double price = 0;
public:

	void set_name(string item_name);
	void set_price(double item_price);
	string get_name();
	double get_price();

	bool operator == (Item& item);
	bool operator == (Item item);

	bool operator != (Item& item);

	Item& operator = (const Item& item2);

	friend
		ostream& operator<<(ostream& os, Item& item) {
		os << item.name << " " << item.price << endl;
		return os;
	};
};