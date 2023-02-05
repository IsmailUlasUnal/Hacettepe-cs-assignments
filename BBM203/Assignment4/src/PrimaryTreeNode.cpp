
#include "PrimaryTreeNode.h"

PrimaryTreeNode::PrimaryTreeNode(std::string categoryName) {
    this->categoryName = categoryName;
    rbNode = nullptr;
    avlNode = nullptr;
    leftChild = nullptr;
    rightChild = nullptr;

}

const std::string &PrimaryTreeNode::getCategoryName() const {
    return categoryName;
}

PrimaryTreeNode *PrimaryTreeNode::getLeftChild() const {
    return leftChild;
}

PrimaryTreeNode *PrimaryTreeNode::getRightChild() const {
    return rightChild;
}

void PrimaryTreeNode::setLeftChild(PrimaryTreeNode *leftChild) {
    PrimaryTreeNode::leftChild = leftChild;
}

void PrimaryTreeNode::setRightChild(PrimaryTreeNode *rightChild) {
    PrimaryTreeNode::rightChild = rightChild;
}
