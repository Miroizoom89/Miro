CC = g++

CFLAGS = -c -g -Wall

all: project_1

project_1: main.o Character.o Client.o Counter.o Item.o Products.o Shop.o Shop_assistant.o Shopping_cart.o
		$(CC) main.o Character.o Client.o Counter.o Item.o Products.o Shop.o Shop_assistant.o Shopping_cart.o -o project_1

main.o: main.cpp Shop.h 
		$(CC) $(CFLAGS) main.cpp

Character.o: Character.h
		$(CC) $(CFLAGS) Character.cpp

Client.o: Client.cpp Character.h Shopping_cart.h
		$(CC) $(CFLAGS) Client.cpp

Counter.o: Counter.cpp Shop_assistant.h Client.h
		$(CC) $(CFLAGS) Counter.cpp

Item.o: Item.h
		$(CC) $(CFLAGS) Item.cpp

Products.o: Products.cpp Item.h
		$(CC) $(CFLAGS) Products.cpp

Shop.o: Shop.cpp Counter.h Fruits.h Essentials.h Vegetables.h
		$(CC) $(CFLAGS) Shop.cpp

Shop_assistant.o: Shop_assistant.cpp Character.h
		$(CC) $(CFLAGS) Shop_assistant.cpp

Shopping_cart.o: Shopping_cart.cpp Item.h
		$(CC) $(CFLAGS) Shopping_cart.cpp

clean: 
		rm -rf *o project_1
