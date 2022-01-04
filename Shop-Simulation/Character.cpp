#include "Character.h"

using namespace std;

Character::Character(string n, string l)
{
	name = n;
	lastname = l;
}

string Character::get_name() {
	return name;
}

string Character::get_lastname() {
	return lastname;
}

void Character::set_name(string new_name) {
	name = new_name;
}

void Character::set_lastname(string new_lastname) {
	lastname = new_lastname;
}

ostream& operator<<(ostream& os, Character& ch) {
	os << ch.name << " " << ch.lastname << endl;
	return os;
};
