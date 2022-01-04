#include "Products.h"

using namespace std;

Products::Products(string text_doc) {

	ifstream file;
	file.open(text_doc, ios::out);
	if (!file.good()) {
		cout << "Wrong text document" << endl;
	}
	else {
		std::string row;
		while (getline(file, row)) {
			Item i;
			size_t pos = row.find(';');
			i.name = row.substr(0, pos);
			i.price = stod(row.substr(pos + 1));
			items.push_back(i);
			amount++;
		}
		file.close();
	}
}

string Products::get_name(int pos) {
	if (pos < amount) {
		return items[pos].name;
	}
	else {
		throw "Wrong index";
	}
}

int Products::get_amount() {
	return amount;
}

vector <Item> Products::get_items() {
	return items;
};

bool Products::is_avaliable(string s) {
	bool found = false;
	for (int i = 0;i < amount;i++) {
		if (items[i].name == s && found == false) {
			return true;
		}
	}
	return false;
}