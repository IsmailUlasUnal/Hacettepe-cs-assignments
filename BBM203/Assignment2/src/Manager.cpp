#include "Manager.h"

Manager::Manager(char ** argv) {

    apartmentLinkedList = new Street(argv[2]);

    inputFile.open(argv[1], std::ios::in);


    if (inputFile.is_open()) {
        std::string command;

        while (inputFile >> command) {
            readCommand(command);
        }
        inputFile.close();
        delete apartmentLinkedList;
    }
}
void Manager::readCommand(std::string& command) {


    if (command == "add_apartment") {

        add_apartment();

    } else if (command == "add_flat") {

        add_flat();

    } else if (command == "remove_apartment") {

        remove_apartment();

    } else if (command == "merge_two_apartments") {

        merge_two_apartments();

    } else if (command == "find_sum_of_max_bandwidths") {

        find_sum_of_max_bandwidths();

    } else if (command == "list_apartments") {

        list_apartments();

    } else if (command == "make_flat_empty") {

        make_flat_empty();

    } else if (command == "relocate_flats_to_same_apartment") {

        relocate_flats_to_same_apartment();

    }
}

void Manager::add_apartment() {
    std::string aptName;
    std::string position;
    int maxBandwidths;

    inputFile >> aptName >> position >> maxBandwidths;
    apartmentLinkedList->addApartment(aptName, position, maxBandwidths);
}

void Manager::add_flat() {
    std::string aptName;
    int index;
    int initialBandwidth;
    int flatID;

    inputFile >> aptName >> index >> initialBandwidth >> flatID;
    apartmentLinkedList->addFlat(aptName, index, initialBandwidth, flatID);
}

void Manager::remove_apartment() {
    std::string aptName;

    inputFile >> aptName;
    apartmentLinkedList->removeApt(aptName);
}

void Manager::make_flat_empty() {
    std::string aptName;
    int flatID;

    inputFile >> aptName >> flatID;
    apartmentLinkedList->makeFlatEmpty(aptName, flatID);
}

void Manager::find_sum_of_max_bandwidths() const {
    apartmentLinkedList->findSumMaxBw();
}

void Manager::merge_two_apartments() {
    std::string firstAptName;
    std::string secondAptName;

    inputFile >> firstAptName >> secondAptName;
    apartmentLinkedList->mergeTwoApartments(firstAptName, secondAptName);

}

void Manager::relocate_flats_to_same_apartment() {
    std::string newAptName;
    int flatIDToShift;
    std::string flatIDList;

    inputFile >> newAptName >> flatIDToShift >> flatIDList;
    apartmentLinkedList->relocateFlatsToSameApt(newAptName, flatIDToShift, flatIDList);
}

void Manager::list_apartments() const {
    apartmentLinkedList->listApartment();
}
