

#include <iostream>
#include "PrimaryTreeBST.h"

PrimaryTreeBST::PrimaryTreeBST(char** argv) {
    root = nullptr;
    AVLOperations = new AVLTreeManager();
    RBTOperations = new LLRBTreeManager();
    outputOperations = new OutputManager(argv[2], argv[3]);
}

void PrimaryTreeBST::insert(std::string category, std::string itemName, int price) {
    // Create a new PrimaryTreeNode for the given category
    PrimaryTreeNode* newNode = new PrimaryTreeNode(category);

    // If the primary tree is empty, set the root to the new node and insert the item into the AVL and LLRB trees of the new node
    if (root == nullptr) {
        root = newNode;
        AVLOperations->insert(newNode->avlNode, price, itemName);
        RBTOperations->insert(newNode->rbNode, price, itemName);
        return;
    }

    // Set the current node to the root of the primary tree
    PrimaryTreeNode* current_node = root;

    // it is an iterative method for inserting an item in binary search tree
    // it loops until null child is found
    while (true) {
        if (category < current_node->getCategoryName()) {
            // go left
            if (current_node->getLeftChild() == nullptr) {
                current_node->setLeftChild(newNode);
                AVLOperations->insert(newNode->avlNode, price, itemName);
                RBTOperations->insert(newNode->rbNode, price, itemName);

                return;
            }
            current_node = current_node->getLeftChild();
        }
        else if (category > current_node->getCategoryName()) {
            // go right
            if (current_node->getRightChild() == nullptr) {
                // If there is no left child, set the left child to the new node and insert the item into the AVL and LLRB trees of the new node
                current_node->setRightChild(newNode);
                AVLOperations->insert(newNode->avlNode, price, itemName);
                RBTOperations->insert(newNode->rbNode, price, itemName);

                return;
            }
            current_node = current_node->getRightChild();
        }
        else {
            // category already exists, do some operation on the existing node
            AVLOperations->insert(current_node->avlNode, price, itemName);
            RBTOperations->insert(current_node->rbNode, price, itemName);

            return;
        }
    }
}

PrimaryTreeNode *PrimaryTreeBST::findCategory(const std::string& categoryName) {
    PrimaryTreeNode *current = root; // Start at the root of the tree
    while (current != nullptr) {
        if (categoryName == current->getCategoryName()) {
            return current; // Found the category, return the node
        } else if (categoryName < current->getCategoryName()) {
            current = current->getLeftChild(); // Search the left subtree
        } else {
            current = current->getRightChild(); // Search the right subtree
        }
    }
    return nullptr; // Category not found
}

void PrimaryTreeBST::del(const std::string& categoryName, const std::string& itemName) {
    PrimaryTreeNode *categoryToDelete = findCategory(categoryName);

    if (categoryToDelete == nullptr) {
        return; // node can't found so it can't run delete function
    }

    AVLOperations->del(categoryToDelete->avlNode, itemName);
    RBTOperations->del(categoryToDelete->rbNode, itemName);

}

void PrimaryTreeBST::find(std::string printName, std::string categoryName, std::string itemName) {
    PrimaryTreeNode *categoryToPrint = findCategory(categoryName);

    std::vector<std::string> commandPrint;

    commandPrint.push_back(printName);
    commandPrint.push_back(categoryName);
    commandPrint.push_back(itemName);

    if (categoryToPrint == nullptr) {
        outputOperations->printItemModel1(commandPrint, nullptr);
        outputOperations->printItemModel2(commandPrint, nullptr);
        return;
    }

    AVLNode* itemToPrintModel1 = AVLOperations->find(categoryToPrint->avlNode, itemName);
    LLRBNode* itemToPrintModel2 = RBTOperations->find(categoryToPrint->rbNode, itemName);

    outputOperations->printItemModel1(commandPrint, itemToPrintModel1);
    outputOperations->printItemModel2(commandPrint, itemToPrintModel2);
}

void PrimaryTreeBST::printAllItemsInCategory(std::string categoryName) {
    PrimaryTreeNode *categoryToPrint = findCategory(categoryName);

    std::vector<std::string> commandPrint;
    commandPrint.push_back("printAllItemsInCategory");
    commandPrint.push_back(categoryName);


    if (categoryToPrint == nullptr) {
        outputOperations->printAllItemsInCategoryModel1(commandPrint, categoryToPrint);
        outputOperations->printAllItemsInCategoryModel2(commandPrint, categoryToPrint);
        return;
    }

    outputOperations->printAllItemsInCategoryModel1(commandPrint, categoryToPrint);
    outputOperations->printAllItemsInCategoryModel2(commandPrint, categoryToPrint);
}

void PrimaryTreeBST::printAllItems() {
    std::vector<std::string> commandPrint;
    commandPrint.push_back("printAllItems");

    outputOperations->printAllItemsModel1(commandPrint, root);
    outputOperations->printAllItemsModel2(commandPrint, root);
}

void PrimaryTreeBST::updateItem(std::string category, std::string name, int newPrice) {
    PrimaryTreeNode *categoryNode = findCategory(category);

    if (categoryNode == nullptr) {
        return;
    }

    AVLNode* avlToUpdate = AVLOperations->find(categoryNode->avlNode, name);
    LLRBNode* LLRBToUpdate = RBTOperations->find(categoryNode->rbNode, name);

    avlToUpdate->setPrice(newPrice);
    LLRBToUpdate->setPrice(newPrice);
}


