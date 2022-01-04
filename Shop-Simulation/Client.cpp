#include "Client.h"
using namespace std;


Client::Client(string name, string lastname, int age = 0) : Character(name, lastname)
{
	static int _id = 1;
	m_age = age;
	m_client_number = _id++;
	m_client_id = "Client";
}

void Client::setClientNumber(int number) { m_client_number = number; }
int Client::getClientNumber() { return m_client_number; }

string Client::getId() { return m_client_id; }
void Client::setId(string id) { m_client_id = id; }

int Client::getAge() { return m_age; }
void Client::setAge(int age) { m_age = age; }

void Client::setShopCart(list<Item> items) { m_shopping_cart.setShoppingCart(items); }
void Client::setShopCart(Shopping_cart cart) { m_shopping_cart = cart; }
list<Item> Client::getShopCart() { return m_shopping_cart.getShoppingCart_list(); }
Shopping_cart Client::getShopCart_cart() { return m_shopping_cart; }

void Client::add_item_to_cart(Item item) { m_shopping_cart.add_element(item); }
void Client::remove_item_from_cart(Item item) { m_shopping_cart.remove_element(item); }

void Client::show_cart_content()
{
	list<Item> cart = this->getShopCart();
	cout << "Items in the cart:\n";
	for (Item item : cart)
	{
		cout << item << endl;
	}
}

bool Client::operator== (Client& client2)
{
	return this->get_name() == client2.get_name() && this->get_lastname() == client2.get_lastname()
		&& this->getAge() == client2.getAge();
}

bool Client::operator!= (Client& client2)
{
	return !(this->get_name() == client2.get_name() && this->get_lastname() == client2.get_lastname()
		&& this->getAge() == client2.getAge());
}

Client& Client::operator = (Client& right)
{
	if (this == &right) { return *this; }
	this->set_name(right.get_name());
	this->set_lastname(right.get_lastname());
	this->setAge(right.getAge());
	this->setClientNumber(right.getClientNumber());
	this->setShopCart(right.getShopCart_cart());
	return *this;
}

Client& Client::operator = (Client right)
{
	if (this == &right) { return *this; }
	this->set_name(right.get_name());
	this->set_lastname(right.get_lastname());
	this->setAge(right.getAge());
	this->setClientNumber(right.getClientNumber());
	this->setShopCart(right.getShopCart_cart());
	return *this;

}

ostream& operator<<(ostream& os, Client& client)
{
	os << client.getId() << " Number:" << client.getClientNumber() << " Name:" << client.get_name() << " Lastname:" << client.get_lastname()
		<< " Age:" << client.getAge() << endl;
	return os;
}
