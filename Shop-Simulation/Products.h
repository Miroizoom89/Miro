#pragma once
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Item.h"

using namespace std;

class Products:public Item {

	int amount=0;
	vector <Item> items;

public:

	Products(string text_doc);
	string get_name(int pos);
	int get_amount();
	bool is_avaliable(string s);
	vector <Item> get_items();
	virtual double get_price(int pos)=0;
	virtual double get_price(string s)=0;
	virtual Item get_product(int pos)=0;
	
};