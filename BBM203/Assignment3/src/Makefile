OBJS	= main.o Customer.o CashierQueue.o Cashier.o Employee.o Barista.o FileManager.o CashierManager.o BaristaQueue.o BaristaManager.o Comparator.o BaristaQueueManager.o
SOURCE	= main.cpp Customer.cpp CashierQueue.cpp Cashier.cpp Employee.cpp Barista.cpp FileManager.cpp CashierManager.cpp BaristaQueue.cpp BaristaManager.cpp Comparator.cpp BaristaQueueManager.cpp
HEADER	= Customer.h CashierQueue.h Cashier.h Employee.h Barista.h FileManager.h CashierManager.h BaristaQueue.h BaristaManager.h BaristaQueueManager.h
OUT	= Assignment3
CC	 = g++
FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp -std=c++11

Customer.o: Customer.cpp
	$(CC) $(FLAGS) Customer.cpp -std=c++11

CashierQueue.o: CashierQueue.cpp
	$(CC) $(FLAGS) CashierQueue.cpp -std=c++11

Cashier.o: Cashier.cpp
	$(CC) $(FLAGS) Cashier.cpp -std=c++11

Employee.o: Employee.cpp
	$(CC) $(FLAGS) Employee.cpp -std=c++11

Barista.o: Barista.cpp
	$(CC) $(FLAGS) Barista.cpp -std=c++11

FileManager.o: FileManager.cpp
	$(CC) $(FLAGS) FileManager.cpp -std=c++11

CashierManager.o: CashierManager.cpp
	$(CC) $(FLAGS) CashierManager.cpp -std=c++11

BaristaQueue.o: BaristaQueue.cpp
	$(CC) $(FLAGS) BaristaQueue.cpp -std=c++11

BaristaManager.o: BaristaManager.cpp
	$(CC) $(FLAGS) BaristaManager.cpp -std=c++11

Comparator.o: Comparator.cpp
	$(CC) $(FLAGS) Comparator.cpp -std=c++11

BaristaQueueManager.o: BaristaQueueManager.cpp
	$(CC) $(FLAGS) BaristaQueueManager.cpp -std=c++11


clean:
	rm -f $(OBJS) $(OUT)