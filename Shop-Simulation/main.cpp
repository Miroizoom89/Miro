#include <iostream>
#include "testclass.hpp"


using namespace std;

int main()
{

    TestClass t;
    cout<<"TESTY DLA WBUDOWANEGO TYPU INT:"<<endl;
    t.int_end_additionTest();
    cout<<"Test dodawania elementu na koniec listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_start_additionTest();
    cout<<"Test dodawania elementu na poczatek listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_delete_elementTest();
    cout<<"Test usuwania elementu z wyznaczonej pozycji listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_get_elementTest();
    cout<<"Test pobierania elementu z danej pozycji listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_get_next_elementTest();
    cout<<"Test pobierania elementu z nastepnej co do podanej pozycji z listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_is_emptyTest();
    cout<<"Test funkcji, sprawdzajacej czy lista jest pusta, zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_get_sizeTest();
    cout<<"Test pobierania rozmaru listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_compareTest();
    cout<<"Test porownania list zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.int_wrong_index_Test();
    cout<<"Test sprawdzajacy dzilanie wyjatku (niepoprawny indeks) zakonczyl sie pomyslnie."<<endl;
    cout<<endl;

    cout<<"TESTY DLA ZDEFINIOWANEJ KLASY ANIMAL:"<<endl;

    t.animal_end_additionTest();
    cout<<"Test dodawania elementu na koniec listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_start_additionTest();
    cout<<"Test dodawania elementu na poczatek listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_delete_elementTest();
    cout<<"Test usuwania elementu z wyznaczonej pozycji listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_get_elementTest();
    cout<<"Test pobierania elementu z danej pozycji listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_get_next_elementTest();
    cout<<"Test pobierania elementu z nastepnej co do podanej pozycji z listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_is_emptyTest();
    cout<<"Test funkcji, sprawdzajacej czy lista jest pusta, zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_get_sizeTest();
    cout<<"Test pobierania rozmaru listy zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_compareTest();
    cout<<"Test porownania list zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    t.animal_wrong_index_Test();
    cout<<"Test sprawdzajacy dzilanie wyjatku (niepoprawny indeks) zakonczyl sie pomyslnie."<<endl;
    cout<<endl;
    cout<<endl;
    cout<<"Program przeszedl wszystkie testy poprawnie."<<endl;

    return 0;
}
