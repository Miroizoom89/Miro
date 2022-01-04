#include "Shopping_cart.h"
using namespace std;

Shopping_cart::Shopping_cart() { list<Item> m_shopping_cart; }

Shopping_cart::Shopping_cart(list<Item> shopping_cart)
{
	m_shopping_cart = shopping_cart;
}

Shopping_cart Shopping_cart::getShoppingCart_cart()
{
	return *this;
}

list<Item> Shopping_cart::getShoppingCart_list()
{
	return m_shopping_cart;
}

void Shopping_cart::setShoppingCart(list<Item> shopping_cart)
{
	m_shopping_cart = shopping_cart;
}

void Shopping_cart::add_element(Item element)
{
	m_shopping_cart.push_back(element);
}

void Shopping_cart::remove_element(Item element)
{
	if (std::find(std::begin(m_shopping_cart), std::end(m_shopping_cart), element) != std::end(m_shopping_cart))
	{
		m_shopping_cart.remove(element);
	}
	else
	{
		throw element;
	}
}

ostream& operator<<(ostream& os, Shopping_cart cart)
{
	os << "Cart content:" << endl;
	for (auto i : cart.getShoppingCart_list())
	{
		os << i << endl;
	}
	return os;
}

