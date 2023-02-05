
#ifndef ASS4_OUTPUTMANAGER_H
#define ASS4_OUTPUTMANAGER_H

#include <iostream>
#include <fstream>
#include <vector>
#include "AVLNode.h"
#include "LLRBNode.h"
#include "PrimaryTreeNode.h"
#include <queue>


class OutputManager {
private:
    // Declare two ofstream objects for outputting to two separate files
    std::ofstream outputFile1;
    std::ofstream outputFile2;

    // Two separate input txt file's names
    std::string output1;
    std::string output2;

public:
    // Constructor for initializing the output1 and output2 with the names of the two output files for using other files
    OutputManager(const std::string&, const std::string&);

    // Function for printing an item's information to the first output file using a given AVLNode
    void printItemModel1(std::vector<std::string>, AVLNode*);

    // Function for printing an item's information to the second output file using a given LLRBNode
    void printItemModel2(std::vector<std::string>, LLRBNode*);

    // Function for printing all items(AVL Tree Nodes) in a given category to the first output file using a given PrimaryTreeNode
    void printAllItemsInCategoryModel1(std::vector<std::string>, PrimaryTreeNode*);

    // Function for printing all items(Red Black Tree nodes) in a given category to the second output file using a given PrimaryTreeNode
    void printAllItemsInCategoryModel2(std::vector<std::string>, PrimaryTreeNode*);

    // Function for printing all item(AVL Tree Nodes)s in the inventory to the first output file using a given PrimaryTreeNode
    void printAllItemsModel1(std::vector<std::string>, PrimaryTreeNode*);

    // Function for printing all items(Red Black Tree Nodes) in the inventory to the second output file using a given PrimaryTreeNode
    void printAllItemsModel2(std::vector<std::string>, PrimaryTreeNode*);

};


#endif //ASS4_OUTPUTMANAGER_H
