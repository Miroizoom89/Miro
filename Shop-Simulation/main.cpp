#include <iostream>
#include <string>
#include <list>
#include <vector>
#include"Shop.h"


using namespace std;



int main()
{
	string file_to_write_to = "list_of_activities.txt";
	Shop shop(file_to_write_to);
	shop.run_a_simulation();
	
}