#pragma once
#include <string>
#include <iostream>

using namespace std;

class Character
{
	string name, lastname;

public:
	Character(string n, string l);
	string get_name();
	string get_lastname();
	void set_name(string new_name);
	void set_lastname(string new_lastname);

	friend
		ostream& operator<<(ostream& os, Character& ch);
};
