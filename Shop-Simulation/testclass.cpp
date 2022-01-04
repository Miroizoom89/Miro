#include <iostream>
#include <cassert>
#include "testclass.hpp"
#include "linkedlist.hpp"
#include "animal.hpp"

using namespace std;


void TestClass::int_end_additionTest()
{
    LinkedList<int> l1;
    l1.insert_end(1);
    l1.insert_end(2);
    l1.insert_end(3);
    l1.insert_end(4);
    l1.insert_end(5);
    l1.insert_end(6);
    int expected=6;
    assert(l1.get_position(5)==expected);
}

void TestClass::int_start_additionTest()
{
    LinkedList<int> l1;
    l1.insert_end(1);
    l1.insert_end(2);
    l1.insert_end(3);
    l1.insert_end(4);
    l1.insert_end(5);
    l1.insert_start(6);
    int expected=6;
    assert(l1.get_position(0)==expected);
}

void TestClass::int_get_elementTest()
{
    LinkedList<int> l1;
    l1.insert_start(3);
    int expected=3;
    assert(l1.get_position(0)==expected);
}

void TestClass::int_delete_elementTest()
{
    LinkedList<int> l1;
    l1.insert_end(1);
    l1.insert_end(2);
    l1.insert_end(3);
    l1.insert_end(4);
    l1.insert_end(5);
    l1.insert_start(6);
    l1.delete_position(3);
    int expected=5;
    assert(l1.get_position(4)==expected);
}

void TestClass::int_get_next_elementTest()
{
    LinkedList<int> l1;
    l1.insert_end(1);
    l1.insert_end(2);
    l1.insert_end(3);
    l1.insert_end(4);
    l1.insert_end(5);
    l1.insert_start(6);
    int pos=2;
    assert(l1.get_next_position(pos)==l1.get_position(pos+1));
}
void TestClass::int_is_emptyTest()
{
    LinkedList<int> l1;
    l1.insert_end(1);
    assert(l1.check_if_empty()==false);
}
void TestClass::int_get_sizeTest()
{
    LinkedList<int> l1;
    int len=50;
    for(int i=0;i<len;i++)
    {
        l1.insert_start(i);
    }
    assert(l1.get_size()==len);
}
void TestClass::int_compareTest()
{
    LinkedList<int> l1;
    LinkedList<int> l2;

    for(int i=0;i<50;i++)
    {
        l1.insert_end(i);
        l2.insert_start(i);
    }
    assert((l1==l2)==false);
}
void TestClass::int_wrong_index_Test()
{
    try
    {
        LinkedList<int> l1;
        l1.insert_start(3);
        l1.get_position(2);
    }
    catch(const char* msg)
    {
        assert(msg="Podano nieprawidlowa pozycje \n");
    }
}
 void TestClass::animal_end_additionTest()
{
    LinkedList<Animal> l1;
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_end(a);
    l1.insert_end(b);
    l1.insert_end(c);
    l1.insert_end(d);
    Animal expected=c;
    assert(l1.get_position(2)==expected);
 }
void TestClass::animal_start_additionTest()
{
    LinkedList<Animal> l1;
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_start(a);
    l1.insert_start(b);
    l1.insert_start(c);
    l1.insert_start(d);
    Animal expected=d;
    assert(l1.get_position(0)==expected);
}
void TestClass::animal_get_elementTest()
{
    LinkedList<Animal> l1;
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_end(a);
    l1.insert_end(b);
    l1.insert_end(c);
    l1.insert_end(d);
    string expected="dinozaur";
    assert(l1.get_position(3).get_species()==expected);
}
void TestClass::animal_delete_elementTest()
{
    LinkedList<Animal> l1;
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_end(a);
    l1.insert_end(b);
    l1.insert_end(c);
    l1.insert_end(d);
    string expected="dinozaur";
    assert(l1.get_position(3).get_species()==expected);
}
void TestClass::animal_get_next_elementTest()
{
    LinkedList<Animal> l1;
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_end(a);
    l1.insert_end(b);
    l1.insert_end(c);
    l1.insert_end(d);
    int pos=2;
    assert(l1.get_next_position(pos)==l1.get_position(pos+1));
}
void TestClass::animal_is_emptyTest()
{
    LinkedList<Animal> l1;
    assert(l1.check_if_empty()==true);
    Animal a("anakonda");
    Animal b("byk");
    Animal c("chomik");
    Animal d("dinozaur");
    l1.insert_end(a);
    l1.insert_end(b);
    l1.insert_end(c);
    l1.insert_end(d);
    assert(l1.check_if_empty()==false);
}
void TestClass::animal_get_sizeTest()
{
    LinkedList<Animal> l1;
    int len=101;
    Animal a("antylopa");
    for(int i=0;i<len;i++)
    {
        l1.insert_end(a);
    }
    assert(l1.get_size()==len);
}
void TestClass::animal_compareTest()
{
    LinkedList<Animal> l1;
    LinkedList<Animal> l2;
    Animal b("bobr");
    Animal c("czapla");
    Animal d("daniel");
    for(int i=0;i<45;i++)
    {
        l1.insert_end(b);
        l1.insert_end(c);
        l1.insert_end(d);
        l2.insert_end(b);
        l2.insert_end(c);
        l2.insert_end(b);
    }
    assert((l1==l2)==false);
}
void TestClass::animal_wrong_index_Test()
{
    try
    {
        LinkedList<Animal> l1;
        Animal a("anakonda");
        Animal b("byk");
        Animal c("chomik");
        Animal d("dinozaur");
        l1.insert_end(a);
        l1.insert_end(b);
        l1.insert_end(c);
        l1.insert_end(d);
        l1.get_position(7);
    }
    catch(const char* msg)
    {
        assert(msg="Podano nieprawidlowa pozycje \n");
    }
}
