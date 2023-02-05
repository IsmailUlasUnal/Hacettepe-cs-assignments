//
// Created by Ulaş Ünal on 16.11.2022.
//

#include "Flats.h"

Flats::Flats(int maxBandwidth) {
    head = nullptr; // it means our doubly linked list has 0 element
    inititalSumBw = 0;
    this->maxBandwidth = maxBandwidth;
}

Flat *Flats::findFlatByIndex(int& index) const {
    Flat* ptr = head;
    while(--index) {
        ptr = ptr->next;
    }

    return ptr;
}

Flat *Flats::findFlatByID(int flatID) const {
    if (head == nullptr) { // it means we don't have any flat in apartment
        return nullptr;
    }

    Flat* current = head; // assign our first flat as head and look every flat with do-while


    do {
        if (current->id == flatID) {
            return current;
        }
        current = current->next;
    } while (current != nullptr);

    return nullptr; // if it can't find any it returns null pointer
}

// this is for removing apartment method it removes every single flat in flats doubly linked list
void Flats::removeFlats() {
    while (head != nullptr) {
        Flat* currFlat = head;
        head = head->next;
        delete currFlat;
    }
}

void Flats::makeFlatEmpty(int flatID) {
    Flat* flat = findFlatByID(flatID);
    inititalSumBw -= flat->initialBandwidth;
    flat->initialBandwidth = 0;
    flat->isEmpty = 1;
}


