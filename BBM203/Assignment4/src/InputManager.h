
#ifndef ASS4_INPUTMANAGER_H
#define ASS4_INPUTMANAGER_H


#include <fstream>
#include <iostream>
#include <sstream>
#include <map>
#include "CommandManager.h"

class InputManager {
private:
    std::map<std::string, int> commandMap;
    // this is a map for running switch case
    /*
    insert = 1
    remove = 2;
    printAllItems = 3;
    printAllItemsInCategory = 4;
    printItem = 5;
    updateData = 6;
    find = 7;
     */


public:
    // it takes an filename and opens a file with this name reads file from given file name
    // and takes command manager for running given commands
    void processInputFile(const std::string& fileName, CommandManager&);
    InputManager(); // constructor for initilazing map for switch case
};


#endif //ASS4_INPUTMANAGER_H
