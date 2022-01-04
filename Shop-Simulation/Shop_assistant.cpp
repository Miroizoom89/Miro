#include "Shop_assistant.h"

using namespace std;

Shop_assistant::Shop_assistant(string n, string ln, int st)
	: Character(n, ln)
{
	static int _id = 1;
	id = _id++;
	stance = st;
};

int Shop_assistant::get_id() {
	return id;
}

int Shop_assistant::get_stance() {
	return stance;
}

void Shop_assistant::set_stance(int s) {
	stance = s;
}

bool Shop_assistant::operator == (Shop_assistant& worker2) {
	return this->get_name() == worker2.get_name() && this->get_lastname() == worker2.get_lastname()
		&& this->stance == worker2.stance && this->id == worker2.id;
}

bool  Shop_assistant::operator != (Shop_assistant& worker2) {
	return !(this == &worker2);
}

Shop_assistant& Shop_assistant::operator = (Shop_assistant& right) {
	if (this == &right) { 
		return *this; 
	}
	this->set_name(right.get_name());
	this->set_lastname(right.get_lastname());
	this->set_stance(right.stance);
	this->id = right.id;
	return *this;
}

ostream& operator<<(ostream& os, Shop_assistant& sp) {
	os <<"Shop_assistant "<< sp.id << " " << sp.get_name() << " " << sp.get_lastname() << " [Stance: " << sp.stance << "]" << endl;
	return os;
}
