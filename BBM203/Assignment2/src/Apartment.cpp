#include "Apartment.h"

Apartment::Apartment(std::string name, int maxBandwidth) {
    this->name = name;
    this->maxBandwidth = maxBandwidth;
    next = nullptr;
    flats = nullptr;
}

