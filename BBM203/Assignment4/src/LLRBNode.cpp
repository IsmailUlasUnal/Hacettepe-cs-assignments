
#include "LLRBNode.h"

LLRBNode::LLRBNode(int price, std::string name) {
    this->price = price;
    this->name = name;
    is_red = true; // initially is red is true because every node is added by red colour
    leftChild = nullptr;
    rightChild = nullptr;
}

void LLRBNode::setName(const std::string &name) {
    LLRBNode::name = name;
}

void LLRBNode::setLeftChild(LLRBNode *leftChild) {
    LLRBNode::leftChild = leftChild;
}

void LLRBNode::setRightChild(LLRBNode *rightChild) {
    LLRBNode::rightChild = rightChild;
}

const std::string &LLRBNode::getName() const {
    return name;
}

LLRBNode *LLRBNode::getLeftChild() const {
    return leftChild;
}

LLRBNode *LLRBNode::getRightChild() const {
    return rightChild;
}

int LLRBNode::getPrice() const {
    return price;
}

bool LLRBNode::isRed() const {
    return is_red;
}

void LLRBNode::setPrice(int price) {
    LLRBNode::price = price;
}

void LLRBNode::setIsRed(bool isRed) {
    is_red = isRed;
}
