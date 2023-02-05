

#ifndef ASS4_COMMANDMANAGER_H
#define ASS4_COMMANDMANAGER_H


#include <string>
#include "PrimaryTreeBST.h"

class CommandManager {
private:
    PrimaryTreeBST *mainTree;


public:
    CommandManager(char**);  //this constructor creates a primary BST for making operations

    // inserts a new item into the mainTree
    void insertItem(std::string, std::string, int);

    //removes an item from the mainTree
    void removeItem(std::string, std::string);

    // prints all items in the mainTree with level order
    void printAllItems();

    //prints all items in a specific category in level order
    void printAllItemsInCategory(std::string);

    // prints a specific item from the spesific category mainTree
    void printItem(std::string, std::string);

    //searches for an item in tree and prints this item
    void find(std::string, std::string);

    // updates the price for an item in the mainTree
    void updateData(std::string, std::string, int);

};


#endif //ASS4_COMMANDMANAGER_H
