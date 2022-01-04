#include <iostream>
#include "animal.hpp"

using namespace std;

Animal::Animal(string s="brak")
{
    species=s;
}

Animal::Animal(const Animal& a) {
    species = a.species;
}

string Animal::get_species()
{
    return this->species;
}

ostream & operator<< (ostream &output,const Animal &a)
{
    return output<<"("<<a.species<<")"<<endl;
}

bool Animal::operator== (Animal a1)
{
    return this->species==a1.species;
}

bool Animal::operator!= (Animal a1)
{
    return !(this->species==a1.species);
}


