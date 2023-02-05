
#ifndef ASS4_LLRBTREEMANAGER_H
#define ASS4_LLRBTREEMANAGER_H

#include "LLRBNode.h"
#include <queue>

class LLRBTreeManager {
private:
    // Returns true if the given node is red, false otherwise
    bool isRed(LLRBNode*);

    // Rotates the given node to the left and returns the new root node
    LLRBNode* rotateLeft(LLRBNode*);

    // Rotates the given node to the right and returns the new root node
    LLRBNode* rotateRight(LLRBNode*);

    // Flips the colors of the given node and its children
    void flipColors(LLRBNode*);

    // Inserts a new node with the given key and value into the tree rooted at the given node
    LLRBNode* insertNode(LLRBNode*, int, std::string);

    // Deletes a node with the given value from the tree rooted at the given node
    LLRBNode* deleteNode(LLRBNode*, std::string);

    // Balances the tree rooted at the given node and returns the new root node
    LLRBNode* balance(LLRBNode*);

    // Finds the minimum node in the tree rooted at the given node and returns it
    LLRBNode* min(LLRBNode*);

    // Deletes the minimum node in the tree rooted at the given node and returns the new root node
    LLRBNode* deleteMin(LLRBNode*);

    // Moves a red node down to the left and balances the tree rooted at the given node
    LLRBNode* moveRedLeft(LLRBNode*);

    // Moves a red node down to the right and balances the tree rooted at the given node
    LLRBNode* moveRedRight(LLRBNode*);

public:
    // Inserts a new node with the given key and value into the tree rooted at the given node
    // it calls the insertNode
    void insert(LLRBNode *&, int, std::string);

    // Deletes a node with the given value from the tree rooted at the given node
    // it calls the deleteNode
    void del(LLRBNode *&, std::string);

    // Finds a node with the given value in the tree rooted at the given node and returns it
    LLRBNode* find(LLRBNode*, std::string);
};


#endif //ASS4_LLRBTREEMANAGER_H
