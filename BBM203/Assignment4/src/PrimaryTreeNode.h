

#ifndef ASS4_PRIMARYTREENODE_H
#define ASS4_PRIMARYTREENODE_H


#include "LLRBNode.h"
#include "AVLNode.h"

class PrimaryTreeNode {
private:
    std::string categoryName;     // Declare a member variable for storing the category name

    // Declare pointers to the left and right children of the node
    PrimaryTreeNode* leftChild;
    PrimaryTreeNode* rightChild;

public:
    //     // Constructor for initializing a PrimaryTreeNode with a given category name
    PrimaryTreeNode(std::string);

    // Declare pointers to an AVLNode and an LLRBNode, which will be used to store items in the category
    AVLNode* avlNode;
    LLRBNode* rbNode;

    // Getter functions for accessing the category name, left child, and right child of the node
    const std::string &getCategoryName() const;
    PrimaryTreeNode *getLeftChild() const;
    PrimaryTreeNode *getRightChild() const;

    // Setter functions for setting the left child and right child of the node
    void setLeftChild(PrimaryTreeNode *leftChild);
    void setRightChild(PrimaryTreeNode *rightChild);
};


#endif //ASS4_PRIMARYTREENODE_H
