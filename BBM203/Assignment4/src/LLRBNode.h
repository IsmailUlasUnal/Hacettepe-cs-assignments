
#ifndef ASS4_LLRBNODE_H
#define ASS4_LLRBNODE_H


#include <string>

// this is a node for Left Leaned Red Black Tree

class LLRBNode {
private:
    int price; // data value
    bool is_red; // initially it is true

    std::string name; // sort by this

    // left and right child node
    LLRBNode* leftChild;
    LLRBNode* rightChild;

public:
    LLRBNode(int, std::string);

    // getters of this node
    int getPrice() const;
    bool isRed() const;
    const std::string &getName() const;
    LLRBNode *getLeftChild() const;
    LLRBNode *getRightChild() const;

    // setters of this node
    void setPrice(int price);
    void setIsRed(bool isRed);
    void setName(const std::string &name);
    void setLeftChild(LLRBNode *leftChild);
    void setRightChild(LLRBNode *rightChild);
};


#endif //ASS4_LLRBNODE_H
