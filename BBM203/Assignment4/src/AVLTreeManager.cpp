
#include "AVLTreeManager.h"

int AVLTreeManager::height(AVLNode* node) {
    if (node == nullptr) {  // If the node is null, return 0 as the height
        return 0;
    }
    return node->getHeight(); // If node is not null return the stored height information
}

AVLNode *AVLTreeManager::rightRotate(AVLNode *y) {
    AVLNode *x = y->getLeftChild();     // Set x to be y's left child
    AVLNode *T2 = x->getRightChild();     // Set T2 to be x's right child

    // Perform the rotation
    x->setRightChild(y);
    y->setLeftChild(T2);

    // Update the heights of the rotated nodes
    y->setHeight(std::max(height(y->getLeftChild()), height(y->getRightChild())) + 1);
    x->setHeight(std::max(height(x->getLeftChild()), height(x->getRightChild())) + 1);

    return x; // this is the newRoot of rotated subtree
}

AVLNode *AVLTreeManager::leftRotate(AVLNode *x) {
    AVLNode* y = x->getRightChild();     // Set y to be x's right child
    AVLNode* T2 = y->getLeftChild();     // Set T2 to be y's left child

    // Perform the rotation
    y->setLeftChild(x);
    x->setRightChild(T2);

    // Update the heights of the rotated nodes
    x->setHeight(std::max(height(x->getLeftChild()), height(x->getRightChild())) + 1);
    y->setHeight(std::max(height(y->getLeftChild()), height(y->getRightChild())) + 1);

    return y; // this is the newRoot of rotated subtree
}

// this function get's the balance factor (difference between left and right children's heights) of the given node
int AVLTreeManager::getBalanceFactor(AVLNode *N) {
    if (N == nullptr) {
        return 0;
    }

    return height(N->getLeftChild()) - height(N->getRightChild());
}

AVLNode *AVLTreeManager::insertNode(AVLNode *node, int price, std::string name) {
    if (node == nullptr) {     // If the current node is null, create a new node and return it
        return new AVLNode(price, name);
    }

    if (name < node->getName()) { // go left
        node->setLeftChild(insertNode(node->getLeftChild(), price, name));
    } else if (name > node->getName()) { // go right
        node->setRightChild(insertNode(node->getRightChild(), price, name));
    } else {
        return node;
    }

    // Update the height of the current node
    node->setHeight(1 + std::max(height(node->getLeftChild()), height(node->getRightChild())));

    int balanceFactor = getBalanceFactor(node);

    // If the balance factor is greater than 1, perform a rotation
    if (balanceFactor > 1) {
        if (name < node->getLeftChild()->getName()) {
            return rightRotate(node);
        } else if (name > node->getLeftChild()->getName()) {
            node->setLeftChild(leftRotate(node->getLeftChild()));
            return rightRotate(node);
        }
    }

    // If the balance factor is less than -1, perform a rotation
    if (balanceFactor < -1) {
        if (name > node->getRightChild()->getName()) {
            return leftRotate(node);
        } else if (name < node->getRightChild()->getName()) {
            node->setRightChild(rightRotate(node->getRightChild()));
            return leftRotate(node);
        }
    }

    return node;
}

AVLNode *AVLTreeManager::mostLeftNode(AVLNode *node) {
    AVLNode* curr = node;
    while (curr->getLeftChild() != nullptr) {
        curr = curr->getLeftChild();
    }

    return curr;
}

AVLNode *AVLTreeManager::deleteNode(AVLNode *root, std::string name) {
    if (root == nullptr) {
        return root;
    }

    if (name < root->getName()) {     // If the name to delete is less than the root's name, delete it from the left subtree
        root->setLeftChild(deleteNode(root->getLeftChild(), name));
    } else if (name > root->getName()) {     // If the name to delete is greater than the root's name, delete it from the right subtree
        root->setRightChild(deleteNode(root->getRightChild(), name));
    } else {     // If the name to delete is equal to the root's name, delete the root
        // this is the main part that deletion is handled
        if ((root->getLeftChild() == nullptr) || (root->getRightChild() == nullptr)) {
            AVLNode *temp = root->getLeftChild() ? root->getLeftChild() : root->getRightChild();
            if (temp == nullptr) {
                temp = root;
                root = nullptr;
            } else {
                *root = *temp;
            }
            delete temp;

        } else {
            AVLNode *temp = mostLeftNode(root->getRightChild());
            root->setName(temp->getName());
            root->setRightChild(deleteNode(root->getRightChild(), temp->getName()));
        }
    }

    if (root == nullptr) {
        return root;
    }

    root->setHeight(1 + std::max(height(root->getLeftChild()), height(root->getRightChild())));

    int balanceFactor = getBalanceFactor(root);

    if (balanceFactor > 1) {
        if (getBalanceFactor(root->getLeftChild()) >= 0) {
            return rightRotate(root);
        } else {
            root->setLeftChild(leftRotate((root->getLeftChild())));
            return rightRotate(root);
        }
    }

    if (balanceFactor < -1) {
        if (getBalanceFactor(root->getRightChild()) <= 0) {
            return leftRotate(root);
        } else {
            root->setRightChild(rightRotate(root->getRightChild()));
            return leftRotate(root);
        }
    }

    return root;
}

void AVLTreeManager::insert(AVLNode *& root, int price, std::string name) {
    root = insertNode(root, price, name);
}

void AVLTreeManager::del(AVLNode *&root, std::string name) {
    root = deleteNode(root, name);
}

AVLNode* AVLTreeManager::find(AVLNode* root, std::string name) {
    // start from the root of the tree
    AVLNode* cur = root;

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

