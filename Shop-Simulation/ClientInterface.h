#pragma once
#include <iostream>
#include "Client.h"
#include "Shopping_cart.h"

using namespace std;

class ClientInterface{
public:
	
	virtual void setClientNumber(int number)=0;
	virtual int getClientNumber()=0;

	virtual string getId()=0;
	virtual void setId(string  id)=0;

	virtual int getAge()=0;
	virtual void setAge(int age)=0;

	virtual void setShopCart(list<Item> items) = 0;
	virtual void setShopCart(Shopping_cart cart) = 0;
	virtual list<Item> getShopCart() = 0;
	virtual Shopping_cart getShopCart_cart() = 0;

	virtual void add_item_to_cart(Item item) = 0;
	virtual void remove_item_from_cart(Item item) = 0;

	virtual void show_cart_content() = 0;

};