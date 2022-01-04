#include "Loyalty_card.h"
using namespace std;

Loyalty_card::Loyalty_card() { m_card_number = 0; m_card_expiration_date = " "; }


Loyalty_card::Loyalty_card(int card_number, string card_expiration_date)
{
	m_card_number = card_number;
	m_card_expiration_date = card_expiration_date;
}

int Loyalty_card::getcardnumber() { return m_card_number; }

void Loyalty_card::setcardnumber(int card_number) { m_card_number = card_number; }

string Loyalty_card::getcardexpirationdate() { return m_card_expiration_date; }

void Loyalty_card::setcardexpirationdate(string date) { m_card_expiration_date = date; }

ostream& operator<<(ostream& os, Loyalty_card & card)
{
	os << "Card number: " << card.getcardnumber() << ", Card expiration date: " << card.getcardexpirationdate() << endl;
	return os;
}