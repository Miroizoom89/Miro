#pragma once
#include <iostream>
#include "Shop_assistant.h"

using namespace std;

class Shop_assistantInterface {
public:
	
	virtual int get_id()=0;
	virtual int get_stance() = 0;
	virtual void set_stance(int s) = 0;

};
