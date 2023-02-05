//
// This is a node for street circular linked list
//

#ifndef ASS2_APARTMENT_H
#define ASS2_APARTMENT_H

#include <iostream>
#include "Flats.h"

class Apartment {
public:
    std::string name;
    int maxBandwidth;
    Apartment* next;
    Flats* flats;

    // this is a constructor for apartment node it takes input as name and max bandwidth and assign them.
    // it's next and flats becomes null when creating a apartment at the beginning
    Apartment(std::string, int);
};


#endif //ASS2_APARTMENT_H
