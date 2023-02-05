//
// double linked list
//

#ifndef ASS2_FLATS_H
#define ASS2_FLATS_H

#include "Flat.h"


class Flats {
public:
    Flat* head;
    int inititalSumBw;
    int maxBandwidth;

    // this constructor creates a doubly linked list
    // parameters takes max bandwidth from apartment node and assign it.
    explicit Flats(int);

    // it finds flat from its index, indexes starts from 0.
    Flat* findFlatByIndex(int &index) const;

    //it removes every flat in apartment
    void removeFlats();

    // it makes flat's isempty value as 0 at the same time it changes sum of initial bandwidths in flats and apartment
    void makeFlatEmpty(int);

    // it finds flat from flat's id and returns it as a pointer
    Flat *findFlatByID(int flatID) const;
};


#endif //ASS2_FLATS_H
