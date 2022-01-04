#include "Item.h"

using namespace std;

void Item::set_name(string item_name) {
	name = item_name;
}

void Item::set_price(double item_price) {
	price = item_price;
}

string Item::get_name() {
	return name;
}

double Item::get_price() {
	return price;
}

bool Item::operator == (Item& item) {
	return (this->name == item.name && this->price == item.price);
}

bool Item::operator == (Item item) {
	return (this->name == item.name && this->price == item.price);
}

bool Item::operator != (Item& item) {
	return !(this == &item);
}

Item& Item::operator = (const Item& item2) {
	this->name = item2.name;
	this->price = item2.price;
	return *this;
};