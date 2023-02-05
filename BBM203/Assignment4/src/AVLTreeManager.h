
#ifndef ASS4_AVLTREEMANAGER_H
#define ASS4_AVLTREEMANAGER_H


#include "AVLNode.h"
#include "OutputManager.h"
#include <queue>

// This class is responsible for managing the AVL tree data structure, it makes operations with given root
// It provides functions for inserting, deleting, and finding nodes in the tree

class AVLTreeManager {
private:
    // Recursive function to insert a new node into the AVL tree
    AVLNode* insertNode(AVLNode*, int, std::string);

    // Function to find the leftmost node in the subtree rooted at the given node
    AVLNode* mostLeftNode(AVLNode*);

    // Recursive function to delete a node with the given name from the AVL tree
    AVLNode* deleteNode(AVLNode*, std::string);

    // Function to get the height of the given node in the tree
    // if node is null height becomes 0
    int height(AVLNode*);

    // Function to perform a right rotation on the given node
    AVLNode* rightRotate(AVLNode*);

    // Function to perform a left rotation on the given node
    AVLNode* leftRotate(AVLNode*);

    // Function to get the balance factor (difference between left and right children's heights) of the given node
    int getBalanceFactor(AVLNode*);


public:
    // Function to insert a new node with the given price and name into the AVL tree
    // it calls insertNode function for making this operations
    void insert(AVLNode*&, int, std::string);

    // Function to delete the node with the given name from the AVL tree
    // it calls deleteNode function for making this operations
    void del(AVLNode*&, std::string);

    // Function to find the node with the given name in the AVL tree
    AVLNode* find(AVLNode*, std::string);

};


#endif //ASS4_AVLTREEMANAGER_H
