#pragma once
#include <iostream>
#include "Shop_assistant.h"
#include "Client.h"

using namespace std;

class CounterInterface {
public:

	virtual int get_id()=0;
	virtual bool counter_is_open()=0;
	virtual void set_assistant(Shop_assistant* assistant)=0;
	virtual Shop_assistant& get_shop_assistant()=0;
	virtual void add_to_queue(Client& client)=0;
	virtual void remove_from_queue(int pos)=0;
	virtual void remove_from_queue()=0;
	virtual int get_queue_size()=0;
	virtual void print_queue()=0;
	virtual Client get_client(int pos)=0;
	virtual double get_payment(Client c)=0;
	virtual string create_receipt(Client c)=0;
	virtual string track_operations(Client c)=0;

};
