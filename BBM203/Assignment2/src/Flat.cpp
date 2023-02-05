#include "Flat.h"

Flat::Flat(int id, int initialBandwidth, int isEmpty) {
    this->id = id;
    this->initialBandwidth = initialBandwidth;
    this->isEmpty = isEmpty;
    next = nullptr;
    prev = nullptr;
}


