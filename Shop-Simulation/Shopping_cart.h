#ifndef SHOPPING_CART_H
#define SHOPPING_CART_H
#include <iostream>
#include <list>
#include <string>
#include <algorithm>
#include "Item.h"
using namespace std;

class Shopping_cart
{
public:
	Shopping_cart();

	Shopping_cart(list<Item> shopping_cart);

	list<Item> getShoppingCart_list();
	Shopping_cart getShoppingCart_cart();

	void setShoppingCart(list<Item> shopping_cart);

	void add_element(Item element);

	void remove_element(Item element);

	Shopping_cart& operator = (Shopping_cart& right)
	{
		if (this == &right) { return *this; }
		this->setShoppingCart(right.getShoppingCart_list());
		return *this;

	}

	friend ostream& operator<<(ostream& os, Shopping_cart cart);

private:
	list<Item> m_shopping_cart;
};
#endif