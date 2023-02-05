#include "CommandManager.h"

void CommandManager::insertItem(std::string category, std::string name, int price) {
    mainTree->insert(category, name, price);
}

void CommandManager::removeItem(std::string category, std::string name) {
    mainTree->del(category, name);
}

void CommandManager::printAllItems() {
    mainTree->printAllItems();
}

void CommandManager::printAllItemsInCategory(std::string name) {
    mainTree->printAllItemsInCategory(name);
}

// Print Item and find does the same thing so it runs same function
void CommandManager::printItem(std::string category, std::string name) {
    mainTree->find("printItem" ,category, name);
}

void CommandManager::find(std::string category, std::string name) {
    mainTree->find("find" ,category, name);
}

void CommandManager::updateData(std::string category, std::string name, int newPrice) {
    mainTree->updateItem(category, name, newPrice);
}

CommandManager::CommandManager(char** argv) {
    mainTree = new PrimaryTreeBST(argv);
}


