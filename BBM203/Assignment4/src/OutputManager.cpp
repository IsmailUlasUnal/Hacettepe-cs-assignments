
#include "OutputManager.h"

OutputManager::OutputManager(const std::string& outputFileName1, const std::string& outputFileName2) {
    // Store the names of the output files in the output1 and output2 member variables
    output1 = outputFileName1;
    output2 = outputFileName2;
}

void OutputManager::printItemModel1(std::vector<std::string> commands ,AVLNode *nodeToPrint) {
    outputFile1.open(output1, std::ios::app);     // Open the first output file in append mode

    outputFile1 << "command:";

    for (auto & command : commands) {
        outputFile1 << command << "\t";
    }
    outputFile1 << "\n{";
    if (nodeToPrint != nullptr) {
        outputFile1 << "\n";
        outputFile1 << '"' << commands[1] << '"' << ":\n";
        outputFile1 << '\t' <<'"' << nodeToPrint->getName() << '"' << ':' << '"' << nodeToPrint->getPrice() << '"';
        outputFile1 << "\n";

    }
    outputFile1 << "}\n";

    outputFile1.close();
}

void OutputManager::printItemModel2(std::vector<std::string> commands ,LLRBNode *nodeToPrint) {
    outputFile2.open(output2, std::ios::app);     // Open the second output file in append mode

    outputFile2 << "command:";

    for (auto & command : commands) {
        outputFile2 << command << "\t";
    }
    outputFile2 << "\n{";
    if (nodeToPrint != nullptr) {
        outputFile2 << "\n";
        outputFile2 << '"' << commands[1] << '"' << ":\n";
        outputFile2 << '\t' <<'"' << nodeToPrint->getName() << '"' << ':' << '"' << nodeToPrint->getPrice() << '"';
        outputFile2 << "\n";


    }
    outputFile2 << "}\n";

    outputFile2.close();
}


void OutputManager::printAllItemsInCategoryModel1(std::vector<std::string> commands, PrimaryTreeNode * BSTNodeToPrint) {
    outputFile1.open(output1, std::ios::app);     // Open the first output file in append mode

    outputFile1 << "command:";

    for (auto & command : commands) {
        outputFile1 << command << "\t";
    }
    outputFile1 << "\n{\n";

    if (BSTNodeToPrint != nullptr) {
        outputFile1 << '"' << commands[1] << '"' << ":\n";

        std::queue<AVLNode*> queue;

        // start by adding the root node to the queue
        if (BSTNodeToPrint->avlNode != nullptr) {
            queue.push(BSTNodeToPrint->avlNode);
        } else {
            outputFile1 << "{}\n";
        }

        // continue processing nodes until the queue is empty
        while (!queue.empty()) {
            // keep track of the number of nodes at the current level
            int levelSize = (int)queue.size();
            outputFile1 << "\t";
            // process all the nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // remove the next node from the front of the queue
                AVLNode* node = queue.front();
                queue.pop();

                outputFile1 << '"' << node->getName() << '"' << ':';
                outputFile1 << '"' <<  node->getPrice() << '"';



                if (i != levelSize - 1) {
                    outputFile1 << ',';
                }


                // add the left and right children of the node to the queue, if they exist
                if (node->getLeftChild() != nullptr) {
                    queue.push(node->getLeftChild());
                }
                if (node->getRightChild() != nullptr) {
                    queue.push(node->getRightChild());
                }
            }

            outputFile1 << "\n";
            // print a newline after processing all the nodes at the current level
        }
    }
    outputFile1 << "}\n";

    outputFile1.close();
}

void OutputManager::printAllItemsInCategoryModel2(std::vector<std::string> commands, PrimaryTreeNode *BSTNodeToPrint) {
    outputFile2.open(output2, std::ios::app);     // Open the second output file in append mode

    outputFile2 << "command:";


    for (auto & command : commands) {
        outputFile2 << command << "\t";
    }
    outputFile2 << "\n{\n";

    if (BSTNodeToPrint != nullptr) {
        outputFile2 << '"' << commands[1] << '"' << ":\n";

        std::queue<LLRBNode*> queue;

        // start by adding the root node to the queue
        if (BSTNodeToPrint->rbNode != nullptr) {
            queue.push(BSTNodeToPrint->rbNode);
        } else {
            outputFile2 << "{}\n";
        }

        // continue processing nodes until the queue is empty
        while (!queue.empty()) {
            // keep track of the number of nodes at the current level
            int levelSize = (int)queue.size();
            outputFile2 << "\t";
            // process all the nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // remove the next node from the front of the queue
                LLRBNode* node = queue.front();
                queue.pop();

                outputFile2 << '"' << node->getName() << '"' << ':';
                outputFile2 << '"' <<  node->getPrice() << '"';



                if (i != levelSize - 1) {
                    outputFile2 << ',';
                }


                // add the left and right children of the node to the queue, if they exist
                if (node->getLeftChild() != nullptr) {
                    queue.push(node->getLeftChild());
                }
                if (node->getRightChild() != nullptr) {
                    queue.push(node->getRightChild());
                }
            }

            outputFile2 << "\n";
            // print a newline after processing all the nodes at the current level
        }
    }
    outputFile2 << "}\n";

    outputFile2.close();
}

void OutputManager::printAllItemsModel1(std::vector<std::string> commands, PrimaryTreeNode* root) {
    outputFile1.open(output1, std::ios::app);

    outputFile1 << "command:";

    for (auto & command : commands) {
        outputFile1 << command << "\t";
    }
    outputFile1 << "\n{";

    // Create a queue to hold nodes as we visit them
    std::queue<PrimaryTreeNode*> queue;

    // Enqueue the root node
    if (root != nullptr) {
        queue.push(root);
    }
    // While the queue is not empty
    while (!queue.empty()) {
        // Dequeue the front node
        PrimaryTreeNode* node = queue.front();
        queue.pop();




        outputFile1 << "\n" << '"' << node->getCategoryName() << '"' << ":\n";

        std::queue<AVLNode*> secondQ;

        // start by adding the root node to the queue
        if (node->avlNode != nullptr) {
            secondQ.push(node->avlNode);
        } else {
            outputFile1 << "{}\n";
        }

        // continue processing nodes until the queue is empty
        while (!secondQ.empty()) {
            // keep track of the number of nodes at the current level
            int levelSize = (int)secondQ.size();
            outputFile1 << "\t";
            // process all the nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // remove the next node from the front of the queue
                AVLNode* nodeItem = secondQ.front();
                secondQ.pop();

                outputFile1 << '"' << nodeItem->getName() << '"' << ':';
                outputFile1 << '"' <<  nodeItem->getPrice() << '"';



                if (i != levelSize - 1) {
                    outputFile1 << ',';
                }


                // add the left and right children of the node to the queue, if they exist
                if (nodeItem->getLeftChild() != nullptr) {
                    secondQ.push(nodeItem->getLeftChild());
                }
                if (nodeItem->getRightChild() != nullptr) {
                    secondQ.push(nodeItem->getRightChild());
                }
            }

            outputFile1 << "\n";
            // print a newline after processing all the nodes at the current level
        }
        // Enqueue the left and right children of the node, if they exist
        if (node->getLeftChild() != nullptr) {
            queue.push(node->getLeftChild());
        }
        if (node->getRightChild() != nullptr) {
            queue.push(node->getRightChild());
        }
    }
    outputFile1 << "}\n";

    outputFile1.close();
}


void OutputManager::printAllItemsModel2(std::vector<std::string> commands, PrimaryTreeNode* root) {
    outputFile2.open(output2, std::ios::app);

    outputFile2 << "command:";

    for (auto & command : commands) {
        outputFile2 << command << "\t";
    }
    outputFile2 << "\n{";

    // Create a queue to hold nodes as we visit them
    std::queue<PrimaryTreeNode*> queue;

    // Enqueue the root node
    if (root != nullptr) {
        queue.push(root);
    }
    // While the queue is not empty
    while (!queue.empty()) {
        // Dequeue the front node
        PrimaryTreeNode* node = queue.front();
        queue.pop();




        outputFile2 << "\n" << '"' << node->getCategoryName() << '"' << ":\n";

        std::queue<LLRBNode*> secondQ;

        // start by adding the root node to the queue
        if (node->rbNode != nullptr) {
            secondQ.push(node->rbNode);
        } else {
            outputFile2 << "{}\n";
        }

        // continue processing nodes until the queue is empty
        while (!secondQ.empty()) {
            // keep track of the number of nodes at the current level
            int levelSize = (int)secondQ.size();
            outputFile2 << "\t";
            // process all the nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // remove the next node from the front of the queue
                LLRBNode* nodeItem = secondQ.front();
                secondQ.pop();

                outputFile2 << '"' << nodeItem->getName() << '"' << ':';
                outputFile2 << '"' <<  nodeItem->getPrice() << '"';



                if (i != levelSize - 1) {
                    outputFile2 << ',';
                }


                // add the left and right children of the node to the queue, if they exist
                if (nodeItem->getLeftChild() != nullptr) {
                    secondQ.push(nodeItem->getLeftChild());
                }
                if (nodeItem->getRightChild() != nullptr) {
                    secondQ.push(nodeItem->getRightChild());
                }
            }

            outputFile2 << "\n";
            // print a newline after processing all the nodes at the current level
        }



        // Enqueue the left and right children of the node, if they exist
        if (node->getLeftChild() != nullptr) {
            queue.push(node->getLeftChild());
        }
        if (node->getRightChild() != nullptr) {
            queue.push(node->getRightChild());
        }
    }

    outputFile2 << "}\n";

    outputFile2.close();
}







