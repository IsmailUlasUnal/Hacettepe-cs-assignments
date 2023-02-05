#include "Customer.h"

struct CompareByTime {
    bool operator ()(Customer a, Customer b) const {
        if (a.getReadyForBrewTime() != b.getReadyForBrewTime()) {
            return (a.getReadyForBrewTime() < b.getReadyForBrewTime());
        } else {
            return (a.getPriceOfOrder() > b.getPriceOfOrder());
        }
    }
};

struct CompareByPrice {
    bool operator ()(Customer a, Customer b) const {
        return a.getPriceOfOrder() > b.getPriceOfOrder();
    }
};

struct CompareByID {
    bool operator ()(Customer a, Customer b) const {
        return a.getID() < b.getID();
    }
};
