
#include "LLRBTreeManager.h"

bool LLRBTreeManager::isRed(LLRBNode *node) {
    return node != nullptr && node->isRed();
}

LLRBNode *LLRBTreeManager::rotateLeft(LLRBNode *node) {
    LLRBNode *node2 = node->getRightChild();
    node->setRightChild(node2->getLeftChild());
    node2->setLeftChild(node);
    node2->setIsRed(node->isRed());
    node->setIsRed(true);
    return node2;
}

LLRBNode *LLRBTreeManager::rotateRight(LLRBNode *node) {
    LLRBNode *leftNode = node->getLeftChild();
    node->setLeftChild(leftNode->getRightChild());
    leftNode->setRightChild(node);
    leftNode->setIsRed(node->isRed());
    node->setIsRed(true);
    return leftNode;
}

void LLRBTreeManager::flipColors(LLRBNode *node) {
    node->setIsRed(!node->isRed());
    node->getLeftChild()->setIsRed(!node->getLeftChild()->isRed());
    node->getRightChild()->setIsRed(!node->getRightChild()->isRed());
}

LLRBNode *LLRBTreeManager::insertNode(LLRBNode *node, int price, std::string name) {
    if (node == nullptr)
        return new LLRBNode(price, name);

    if (name < node->getName())
        node->setLeftChild(insertNode(node->getLeftChild(), price, name));
    else if (name > node->getName())
        node->setRightChild(insertNode(node->getRightChild(), price, name));
    else {
        node->setName(name);
        node->setPrice(price);
    }

    if (isRed(node->getRightChild()) && !isRed(node->getLeftChild())){
        node = rotateLeft(node);
    }

    if (isRed(node->getLeftChild()) && isRed(node->getLeftChild()->getLeftChild()))
        node = rotateRight(node);
    if (isRed(node->getRightChild()) && isRed(node->getLeftChild()))
        flipColors(node);

    return node;
}

void LLRBTreeManager::insert(LLRBNode *& root, int price, std::string name) {
    root = insertNode(root, price, name);
    root->setIsRed(false);
}

LLRBNode *LLRBTreeManager::deleteNode(LLRBNode *node, std::string name) {
    if (name < node->getName()) {
        if (!isRed(node->getLeftChild()) && !isRed(node->getLeftChild()->getLeftChild())) node = moveRedLeft(node);
        node->setLeftChild(deleteNode(node->getLeftChild(), name));
    } else {
        if (isRed(node->getLeftChild())) node = rotateRight(node);
        if (name == node->getName() && node->getRightChild() == nullptr) return nullptr;
        if (!isRed(node->getRightChild()) && !isRed(node->getRightChild()->getLeftChild())) node = moveRedRight(node);
        if (name == node->getName()) {
            LLRBNode* x = min(node->getRightChild());
            node->setName(x->getName());
            node->setRightChild(deleteMin(node->getRightChild()));
        } else {
            node->setRightChild(deleteNode(node->getRightChild(), name));
        }
    }
    return balance(node);
}

void LLRBTreeManager::del(LLRBNode *& root, std::string name) {
    if (!isRed(root->getLeftChild()) && !isRed(root->getRightChild())) root->setIsRed(true);
    root = deleteNode(root, name);
    if (root != nullptr) root->setIsRed(false);
}

LLRBNode *LLRBTreeManager::balance(LLRBNode *node) {
    if (isRed(node->getRightChild()))
        node = rotateLeft(node);
    if (isRed(node->getLeftChild()) && isRed(node->getLeftChild()->getLeftChild()))
        node = rotateRight(node);
    if (isRed(node->getLeftChild()) && isRed(node->getRightChild()))
        flipColors(node);

    return node;
}

LLRBNode *LLRBTreeManager::min(LLRBNode *node) {
    if (node->getLeftChild() == nullptr)
        return node;
    return min(node->getLeftChild());
}

LLRBNode *LLRBTreeManager::deleteMin(LLRBNode *node) {
    if (node->getLeftChild() == nullptr)
        return nullptr;

    if (!isRed(node->getLeftChild()) && !isRed(node->getLeftChild()->getLeftChild())) node = moveRedLeft(node);


    node->setLeftChild(deleteMin(node->getLeftChild()));
    return balance(node);
}

LLRBNode* LLRBTreeManager::find(LLRBNode *root, std::string name) {
    // start from the root of the tree
    LLRBNode* cur = root;

    // iterate until the node is found or the tree is exhausted
    while (cur != nullptr && cur->getName() != name) {
        // search left or right subtree depending on the name
        if (name < cur->getName()) {
            cur = cur->getLeftChild();
        } else {
            cur = cur->getRightChild();
        }
    }

    // return the found node (or nullptr if not found)
    return cur;


}

LLRBNode *LLRBTreeManager::moveRedLeft(LLRBNode *node) {
    flipColors(node);
    if (isRed(node->getRightChild()->getLeftChild())) {
        node->setRightChild(rotateRight(node->getRightChild()));
        node = rotateLeft(node);
        flipColors(node);
    }
    return node;

}

LLRBNode *LLRBTreeManager::moveRedRight(LLRBNode *node) {
    flipColors(node);
    if (isRed(node->getLeftChild()->getLeftChild())) {
        node = rotateRight(node);
        flipColors(node);
    }
    return node;

}




