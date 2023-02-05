#include <iostream>
#include "InputManager.h"

int main(int argc, char **argv) {
    CommandManager commandManager = CommandManager(argv); // for running command, argv for opening output files
    InputManager * inputManager = new InputManager(); // for reading input file
    inputManager->processInputFile(argv[1], commandManager); // it reads input file with given name
}
