
#ifndef ASS4_AVLNODE_H
#define ASS4_AVLNODE_H


#include <string>
#include <iostream>

// AVLNode class represents a node in an AVL tree
// It stores a price and name as data, and pointers to its left and right children

class AVLNode {
private:
    // Data stored in the node
    int price;

    // it is sorted by name
    std::string name;

    // Pointers to left and right children
    AVLNode* leftChild;
    AVLNode* rightChild;

    // Height of the node in the tree
    int height;


public:
    AVLNode(int, std::string);     // Constructor to create a new AVLNode with the given price and name

    // Getters for the node's data and children
    int getPrice() const;
    int getHeight() const;
    const std::string &getName() const;
    AVLNode *getLeftChild() const;
    AVLNode *getRightChild() const;


    // Setters for the node's data and children
    void setPrice(int price);
    void setHeight(int height);
    void setName(const std::string &name);
    void setLeftChild(AVLNode *leftChild);
    void setRightChild(AVLNode *rightChild);



};


#endif //ASS4_AVLNODE_H
