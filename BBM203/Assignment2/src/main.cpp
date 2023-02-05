#include <iostream>
#include "Manager.h"

using namespace std;

int main(int argc, char** argv) {

    Manager* gameManager = new Manager(argv);

    delete gameManager;
    return 0;
}
