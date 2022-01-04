#ifndef LOYALTY_CARD_H
#define LOYALTY_CARD_H
#include <iostream>
#include <string>
using namespace std;

class Loyalty_card
{
public:
	Loyalty_card();

	Loyalty_card(int card_number, string card_expiration_date);

	int getcardnumber();
	void setcardnumber(int card_number);

	string getcardexpirationdate();
	void setcardexpirationdate(string date);

	friend ostream& operator<<(ostream& os, Loyalty_card & card);

private:
	int m_card_number;
	string m_card_expiration_date;
};
#endif
