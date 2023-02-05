

#ifndef ASS4_PRIMARYTREEBST_H
#define ASS4_PRIMARYTREEBST_H


#include "PrimaryTreeNode.h"
#include "AVLTreeManager.h"
#include "LLRBTreeManager.h"
#include "OutputManager.h"
#include <queue>

class PrimaryTreeBST {
private:
    // Declare a pointer to the root node of the primary tree
    PrimaryTreeNode* root;

    // Declare pointers to AVLTreeManager and LLRBTreeManager objects for performing operations on the AVL and LLRB trees
    // these are just a manager classes.
    AVLTreeManager* AVLOperations;
    LLRBTreeManager* RBTOperations;
    OutputManager* outputOperations;

    // Function for finding a category in the primary tree by name
    PrimaryTreeNode* findCategory(const std::string&);



public:
    // Constructor for initializing the PrimaryTreeBST it opens AVL Tree Manager, Red Black Tree Manager,
    // and Output Manager with command line arguments
    PrimaryTreeBST(char**);

    // Function for inserting an item into the primary tree with given category name, item name, and price
    void insert(std::string, std::string, int);

    // Function for deleting an item from the primary tree
    void del(const std::string&, const std::string&);

    // Function for finding an item in the primary tree it calls output manager's find function
    void find(std::string ,std::string, std::string);

    // Function for printing all items in a given category it calls output manager's print all items in category function
    void printAllItemsInCategory(std::string);

    // Function for updating the price of an item in the primary tree
    void updateItem(std::string, std::string, int);

    // Fucntion for printing all items in this class, it calls output manager's print all items function
    void printAllItems();
};


#endif //ASS4_PRIMARYTREEBST_H
