#ifndef ASS2_STREET_H
#define ASS2_STREET_H

//
//This is a circular linked list for every apartment on the street
//

#include "Apartment.h"


class Street {
public:
    Street(const std::string&);

    // this constructor opens outputfile with given argv[2]
    ~Street();


    // this is node of this circular linked list
    Apartment* head{};

    std::ofstream outputFile;

    // it adds apartment with apartment name,
    // position of apartment(if it is head it means it is our first apartment, and before, after of an aparmtent),
    // and it asign max bandwidth of apartment node
    void addApartment(std::string, const std::string&, int);

    //it takes parameters as apartment name for putting in right apartment, index for new flat's index, initial bandwidth for flat's bandwidth, and flat's id
    void addFlat(const std::string&, int, int, int);

    // removes apartment as given name
    Apartment * removeApt(const std::string&);

    // it finds apartment at given apartment name and, int for flat id for finding right apartment
    void makeFlatEmpty(const std::string&, int);

    // find sum of bandwidts of every apartment
    int findSumMaxBw();

    // it connects two apartments
    // connects second apartment to first apartment
    void mergeTwoApartments(const std::string&, const std::string&);

    // it prints every apartment and every node on apartment linked list
    void listApartment();

    // parameters are string for apartment name for adding to this apartment, int for flatID, it adds every flat at previous of this flat
    // last parameter is flat list that we need to take and add given flat's prev
    void relocateFlatsToSameApt(const std::string&, int, std::string);

private:
    // it finds given aparment previous node
    Apartment* findBeforeApt( const std::string& name) const;

    //it prints every flat on given apartment pointer
    void printFlat(Apartment *apt);

    //it prints given string and given number with text: num format
    void printVal(const std::string& text, int num);
};


#endif //ASS2_STREET_H
