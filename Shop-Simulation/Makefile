# Mój plik makefile - wpisz 'make all' aby skompilowaæ ca³y program
# (w³aœciwie wystarczy wpisaæ 'make' - all jest domyœlny jako pierwszy cel)
#CC = gcc

output : main.o animal.o testclass.o
	g++ main.o animal.o testclass.o -o output

main.o: main.cpp testclass.hpp
	g++ -c main.cpp

animal.o: animal.cpp animal.hpp
	g++ -c animal.cpp

testclass.o: testclass.cpp testclass.hpp linkedlist.hpp animal.hpp
	g++ -c testclass.cpp

clean:
	rm  *.o output
