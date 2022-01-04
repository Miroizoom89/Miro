#pragma once
#include <iostream>
#include <exception>
#include "Products.h"

using namespace std;

class Essentials :public Products {
	double vat;

public:
	Essentials(string file) :Products(file) {
		double vat = 0.1;
	};
	virtual double get_price(int pos) {
		if (pos < this->get_amount()) {
			return this->get_items()[pos].get_price()*(1 + vat);
		}
		else {
			throw "Wrong index";
		}
	};
	virtual double get_price(string s) {
		double price = 0;

		if (this->is_avaliable(s)) {
			for (int i = 0; i < this->get_amount(); i++) {
				if (this->get_items()[i].get_name() == s) {
					return this->get_items()[i].get_price()*(1 + vat);
				}
			}
		}
		else {
			cout << "Unavaliable, default value (-1)" << endl;
			return -1;
		}
	};
	Item get_product(int pos) {
		try {
			return this->get_items()[pos];
		}
		catch (...) {
			cout << "Wrong index" << endl;
			throw -1;
		}
	};
};
