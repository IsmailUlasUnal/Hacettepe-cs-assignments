//
// This is an apartment circular singly linked list
//

#include <sstream>
#include <fstream>
#include "Street.h"

Street::Street(const std::string& outputFileName) {
    outputFile.open(outputFileName, std::ofstream::out | std::ofstream::trunc);

}

Apartment* Street::findBeforeApt(const std::string& name) const  {
    Apartment* current = head;

    if (head == nullptr) {
        return nullptr;
    }


    do {
        if ((current->next)->name == name) {
            return current;
        }
        current = current->next;
    } while (current != head);

    return nullptr;
}

void Street::addApartment(std::string name, const std::string& position, int maxBandwidth) {
    Apartment* newApartment = new Apartment(name, maxBandwidth); // creates new apartment

    if (position == "head") {
        head = newApartment;
        newApartment->next = head;
    } else {
        std::stringstream info(position);

        std::string pos;
        std::string aptName;
        std::getline(info, pos, '_');
        std::getline(info, aptName);


        //curr apt is the aparment that we will add our new aparment after this apartment
        Apartment* currApt = findBeforeApt(aptName);

        if (pos == "after") {
            currApt = currApt->next;
        }

        if (pos == "before" && aptName == head->name) { // make new apt as a head
            newApartment->next = head;
            currApt->next = newApartment;
            head = newApartment;
        } else {
            newApartment->next = currApt->next;
            currApt->next = newApartment;
        }
    }

}


void Street::addFlat(const std::string& aptName, int index, int initialBandwidth, int flatID) {
    //find right apartment
    Apartment* aptToAdd = findBeforeApt(aptName)->next;
    if (aptToAdd->flats == nullptr) {
       aptToAdd->flats = new Flats(aptToAdd->maxBandwidth);
    }

    // it calculates max number of bandwidth that flat can have
    initialBandwidth = std::min(aptToAdd->maxBandwidth - aptToAdd->flats->inititalSumBw, initialBandwidth);

    aptToAdd->flats->inititalSumBw += initialBandwidth;
    Flat* newFlat;
    if (initialBandwidth == 0) { // creates flat as empty
        newFlat = new Flat(flatID, initialBandwidth, 1);
    } else {
        newFlat = new Flat(flatID, initialBandwidth, 0);
    }

    if (aptToAdd->flats->head == nullptr) { // if there is no flat it adds as head
        aptToAdd->flats->head = newFlat;
    } else {
        if (index != 0) {
            Flat* currFlat = aptToAdd->flats->findFlatByIndex(index);

            newFlat->next = currFlat->next;
            newFlat->prev = currFlat;

            if (currFlat->next != nullptr) {
                currFlat->next->prev = newFlat;
            }

            currFlat->next = newFlat;
        } else {
            // add as a first element
            newFlat->next = aptToAdd->flats->head;
            aptToAdd->flats->head->prev = newFlat;
            aptToAdd->flats->head = newFlat;
        }
    }




}

Apartment* Street::removeApt(const std::string& aptName) {
    Apartment* beforeDel = findBeforeApt(aptName);
    Apartment* aptToDel = beforeDel->next;

    if (beforeDel == aptToDel) {
        if (aptToDel->flats != nullptr) aptToDel->flats->removeFlats();
        delete aptToDel;
        head = nullptr;
        return head;
    }

    if (aptToDel == head) {
        head = head->next;
    }
    beforeDel->next = aptToDel->next;

    if (aptToDel->flats != nullptr) aptToDel->flats->removeFlats();
    delete aptToDel;
    return head;
}

void Street::makeFlatEmpty(const std::string& aptName, int flatID) {
    Apartment* apt = findBeforeApt(aptName)->next;

    Flat* flat = apt->flats->findFlatByID(flatID);
    apt->flats->inititalSumBw -= flat->initialBandwidth;
    flat->initialBandwidth = 0;
    flat->isEmpty = 1;
}

int Street::findSumMaxBw() {
    Apartment* currApt = head;
    int sum = 0;

    if (head != nullptr) {
        do {
            currApt = currApt->next;
            sum += currApt->maxBandwidth;
        } while (currApt != head);
    }
    printVal("sum of bandwidth", sum);
    return sum;
}

void Street::printVal(const std::string& text, int num) {

    if (outputFile.is_open()) {
        outputFile << text << ": " << num << "\n\n";
    }
}


void Street::mergeTwoApartments(const std::string& apt1, const std::string& apt2) {
    Apartment* firstApt = findBeforeApt(apt1)->next;
    Apartment* secondApt = findBeforeApt(apt2)->next;


    if (secondApt->flats != nullptr) {
        if (firstApt->flats == nullptr) { // if first apartment is null but second is not
            firstApt->flats = secondApt->flats;
        } else { // if both apartment is not null
            Flat* temp = firstApt->flats->head;
            while (temp->next != nullptr) { // temp becomes last flat
                temp = temp->next;
            }

            temp->next = secondApt->flats->head;
            secondApt->flats->head->prev = temp;

        }
    }

    firstApt->maxBandwidth += secondApt->maxBandwidth;

    if (firstApt->flats != nullptr) {
        firstApt->flats->maxBandwidth += secondApt->maxBandwidth;
        if (secondApt->flats != nullptr) {
            firstApt->flats->inititalSumBw += secondApt->flats->inititalSumBw;

        }
    }

    if (secondApt->flats != nullptr)  secondApt->flats = nullptr;

    removeApt(apt2); // removes second apartment because we merge it into an another aparment

}

void Street::listApartment() {
    if (outputFile.is_open()) {
        Apartment* temp = head;
        if (head != nullptr) {
            do {
                outputFile << temp->name << '(' << temp->maxBandwidth << ')' << "\t";

                printFlat(temp);
                temp = temp->next;
            } while (head != temp);
            outputFile << "\n";

        } else {
            outputFile << "There is no apartment" << "\n";

        }

    }
}

void Street::printFlat(Apartment* apt) {

    if (apt->flats != nullptr) {
        Flat* tempFlat = apt->flats->head;
        while (tempFlat != nullptr) {
            outputFile << "Flat" << tempFlat->id << '(' << tempFlat->initialBandwidth << ')' << "\t";

            tempFlat = tempFlat->next;
        }
    }
    outputFile << "\n";
}


void Street::relocateFlatsToSameApt(const std::string& aptName, int flatID, std::string aptList) {
    aptList = aptList.substr(1, aptList.length() - 2); // for removing [, ]

    std::stringstream ss(aptList);

    // find flat to add
    Apartment* givenApt = findBeforeApt(aptName)->next; // find right apt
    Flat* givenFlat = givenApt->flats->findFlatByID(flatID); // find flat for adding prev of this flat


    while (ss.good()) {
        std::string numString; // for every flat id
        std::getline(ss, numString, ',');
        int idFromList = stoi(numString);
        Apartment* aptFromList = head;
        if (head != nullptr) { // if there is an at least one apartment this need to run
            do {
                Flat *flatFromList = nullptr;
                if (aptFromList->flats != nullptr) {
                    flatFromList = aptFromList->flats->findFlatByID(idFromList);
                }
                if (flatFromList == nullptr) {
                    aptFromList = aptFromList->next;
                } else {
                    if (aptFromList->flats->head == flatFromList) { // from head
                        aptFromList->flats->head = aptFromList->flats->head->next;
                        aptFromList->flats->head->prev = nullptr;
                    } else {
                        if (flatFromList->next != nullptr) { // middle
                            flatFromList->next->prev = flatFromList->prev;
                        }
                        flatFromList->prev->next = flatFromList->next;
                    }

                    aptFromList->maxBandwidth -= flatFromList->initialBandwidth;
                    aptFromList->flats->maxBandwidth -= flatFromList->initialBandwidth;
                    aptFromList->flats->inititalSumBw -= flatFromList->initialBandwidth;

                    givenApt->maxBandwidth += flatFromList->initialBandwidth;
                    givenApt->flats->maxBandwidth += flatFromList->initialBandwidth;
                    givenApt->flats->inititalSumBw += flatFromList->initialBandwidth;




                    flatFromList->prev = givenFlat->prev;
                    givenFlat->prev = flatFromList;
                    flatFromList->next = givenFlat;

                    if (givenFlat == givenApt->flats->head) { // move it's flat
                        givenApt->flats->head = flatFromList;
                    } else {
                        givenFlat->prev->prev->next = flatFromList;
                    }
                    break;
                }

            } while (aptFromList != head);
        }

    }

}

Street::~Street() {
    // if head is not null it runs while condition and remove apartment until it returns nullptr
    if (head != nullptr) {
        while (removeApt(head->name));
    }
    outputFile.close();
}
