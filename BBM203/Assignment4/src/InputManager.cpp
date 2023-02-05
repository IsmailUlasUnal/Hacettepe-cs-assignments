

#include "InputManager.h"

void InputManager::processInputFile(const std::string &fileName, CommandManager& commandManager) {
    std::ifstream inputFile(fileName);

    if (!inputFile.is_open()) {
        std::cout << "file is not open";
        return;
    }

    std::string line;

    while (std::getline(inputFile, line, '\n')) {
        std::istringstream lineStream(line);
        std::string command;
        std::string commandArr[4]; // it has 4 elements because comment can be at most 4 element

        int commandIndex = 0; // for puting in vector

        while (std::getline(lineStream, command, '\t')) {
            commandArr[commandIndex++] = command;
        }

        int commandValue = commandMap[commandArr[0]];  // with this value it goes to an spesific switch case and makes operaitons with it

        switch (commandValue) {
            case 1: {
                // Insert the item into the appropriate category with the given price
                commandManager.insertItem(commandArr[1], commandArr[2], stoi(commandArr[3]));
                break;
            }
            case 2: {
                commandManager.removeItem(commandArr[1], commandArr[2]);
                // Remove the item from the appropriate category
                break;
            }
            case 3: {
                commandManager.printAllItems();
                // Print all the items and their prices
                break;
            }
            case 4: {
                commandManager.printAllItemsInCategory(commandArr[1]);
                // Print all the items and their prices in the given category
                break;
            }
            case 5: {
                commandManager.printItem(commandArr[1], commandArr[2]);
                // Print the price of the item in the given category
                break;
            }
            case 6: {
                 commandManager.updateData(commandArr[1], commandArr[2], stoi(commandArr[3]));
                // Update the price of the item in the given category
                break;
            }
            case 7: {
                commandManager.find(commandArr[1], commandArr[2]);
                // Find the item in the given category
                break;
            }
            default: {
                std::cout << "Error: Unknown command '" << command << "'." << std::endl;
            }

            inputFile.close();
        }
    }

}

InputManager::InputManager() {
    commandMap["insert"] = 1;
    commandMap["remove"] = 2;
    commandMap["printAllItems"] = 3;
    commandMap["printAllItemsInCategory"] = 4;
    commandMap["printItem"] = 5;
    commandMap["updateData"] = 6;
    commandMap["find"] = 7;
}
