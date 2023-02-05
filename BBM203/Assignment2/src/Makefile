OBJS	= Apartment.o Flat.o Flats.o main.o Manager.o Street.o
SOURCE	= Apartment.cpp Flat.cpp Flats.cpp main.cpp Manager.cpp Street.cpp
HEADER	= Apartment.h Flat.h Flats.h Manager.h Street.h
OUT	= networkmap
CC	 = g++
FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

Apartment.o: Apartment.cpp
	$(CC) $(FLAGS) Apartment.cpp -std=c++11

Flat.o: Flat.cpp
	$(CC) $(FLAGS) Flat.cpp -std=c++11

Flats.o: Flats.cpp
	$(CC) $(FLAGS) Flats.cpp -std=c++11

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp -std=c++11

Manager.o: Manager.cpp
	$(CC) $(FLAGS) Manager.cpp -std=c++11

Street.o: Street.cpp
	$(CC) $(FLAGS) Street.cpp -std=c++11


clean:
	rm -f $(OBJS) $(OUT)