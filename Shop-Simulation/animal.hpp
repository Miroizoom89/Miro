#pragma once
#include <iostream>

using namespace std;

class Animal
{
    string species;
public:
    Animal(string s);

    Animal(const Animal& a);

    string get_species();

    friend
    ostream & operator<< (ostream &output,const Animal &a);

    bool operator== (Animal a1);

    bool operator!= (Animal a1);
};

