#ifndef CLIENT_H
#define CLIENT_H
#include <string>
#include <list>
#include "Character.h"
#include "Shopping_cart.h"
#include "ClientInterface.h"
using namespace std;

class Client : public Character, public ClientInterface
{

private:
	int m_client_number;
	string m_client_id;
	int m_age;
	Shopping_cart m_shopping_cart;

public:
	Client(string name, string lastname, int age);

	void setClientNumber(int number);
	int getClientNumber();

	string getId();
	void setId(string  id);

	int getAge();
	void setAge(int age);

	void setShopCart(list<Item> items);
	void setShopCart(Shopping_cart cart);
	list<Item> getShopCart();
	Shopping_cart getShopCart_cart();

	void add_item_to_cart(Item item);
	void remove_item_from_cart(Item item);

	void show_cart_content();

	bool operator == (Client& client2);

	bool operator != (Client& client2);

	Client& operator = (Client& right);

	Client& operator = (Client right);

	friend ostream& operator<<(ostream& os, Client& client);
};
#endif