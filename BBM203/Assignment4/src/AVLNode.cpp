
#include "AVLNode.h"

AVLNode::AVLNode(int price, std::string name) {
    this->price = price;
    this->name = name;
    height = 1; // initially height is 1
    leftChild = nullptr;
    rightChild = nullptr;
}

int AVLNode::getHeight() const {
    return height;
}

AVLNode *AVLNode::getLeftChild() const {
    return leftChild;
}

AVLNode *AVLNode::getRightChild() const {
    return rightChild;
}

void AVLNode::setLeftChild(AVLNode *leftChild) {
    AVLNode::leftChild = leftChild;
}

void AVLNode::setRightChild(AVLNode *rightChild) {
    AVLNode::rightChild = rightChild;
}

void AVLNode::setHeight(int height) {
    AVLNode::height = height;
}

const std::string &AVLNode::getName() const {
    return name;
}

void AVLNode::setName(const std::string &name) {
    AVLNode::name = name;
}

void AVLNode::setPrice(int price) {
    AVLNode::price = price;
}

int AVLNode::getPrice() const {
    return price;
}
