OBJS	= main.o AVLNode.o LLRBNode.o PrimaryTreeNode.o PrimaryTreeBST.o AVLTreeManager.o LLRBTreeManager.o InputManager.o CommandManager.o OutputManager.o
SOURCE	= main.cpp AVLNode.cpp LLRBNode.cpp PrimaryTreeNode.cpp PrimaryTreeBST.cpp AVLTreeManager.cpp LLRBTreeManager.cpp InputManager.cpp CommandManager.cpp OutputManager.cpp
HEADER	= AVLNode.h LLRBNode.h PrimaryTreeNode.h PrimaryTreeBST.h AVLTreeManager.h LLRBTreeManager.h InputManager.h CommandManager.h OutputManager.h
OUT	= webstore
CC	 = g++
FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp -std=c++11

AVLNode.o: AVLNode.cpp
	$(CC) $(FLAGS) AVLNode.cpp -std=c++11

LLRBNode.o: LLRBNode.cpp
	$(CC) $(FLAGS) LLRBNode.cpp -std=c++11

PrimaryTreeNode.o: PrimaryTreeNode.cpp
	$(CC) $(FLAGS) PrimaryTreeNode.cpp -std=c++11

PrimaryTreeBST.o: PrimaryTreeBST.cpp
	$(CC) $(FLAGS) PrimaryTreeBST.cpp -std=c++11

AVLTreeManager.o: AVLTreeManager.cpp
	$(CC) $(FLAGS) AVLTreeManager.cpp -std=c++11

LLRBTreeManager.o: LLRBTreeManager.cpp
	$(CC) $(FLAGS) LLRBTreeManager.cpp -std=c++11

InputManager.o: InputManager.cpp
	$(CC) $(FLAGS) InputManager.cpp -std=c++11

CommandManager.o: CommandManager.cpp
	$(CC) $(FLAGS) CommandManager.cpp -std=c++11

OutputManager.o: OutputManager.cpp
	$(CC) $(FLAGS) OutputManager.cpp -std=c++11


clean:
	rm -f $(OBJS) $(OUT)