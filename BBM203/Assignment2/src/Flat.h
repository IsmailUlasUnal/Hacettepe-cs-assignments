//
// Node of Flats which is a doubly linked list
//

#ifndef ASS2_FLAT_H
#define ASS2_FLAT_H

#include <iostream>

class Flat {
public:
    int id;
    int initialBandwidth;
    int isEmpty;
    Flat* next;
    Flat* prev;

    // flat constructor takes an id, initial bandwidth and isempty and assign them
    // next and prev becomes null at the beginning
    Flat(int id, int initialBandwidth, int isEmpty);
};


#endif //ASS2_FLAT_H
