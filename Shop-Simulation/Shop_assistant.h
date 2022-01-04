#pragma once
#include <iostream>
#include <vector>
#include <string>
#include "Character.h"
#include "Shop_assistantInterface.h"

using namespace std;

class Shop_assistant : public Character, public Shop_assistantInterface
{
	int id;
	int stance;

public:
	Shop_assistant(string n, string ln, int st = -1);
	
	int get_id();
	int get_stance();
	void set_stance(int s);

	bool operator == (Shop_assistant& client2);

	bool operator != (Shop_assistant& client2);

	Shop_assistant& operator = (Shop_assistant& right);

	friend
		ostream& operator<<(ostream& os, Shop_assistant& sp);

};
