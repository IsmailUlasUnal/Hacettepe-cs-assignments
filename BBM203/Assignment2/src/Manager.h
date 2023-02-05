//
// this part is the main part that we will read inputs
//

#ifndef ASS2_MANAGER_H
#define ASS2_MANAGER_H

#include <iostream>
#include<fstream>
#include "Street.h"

class Manager {
public:
    std::ifstream inputFile;

    Street *apartmentLinkedList; // this is circular linked list for every apartment

    //it creates a apartment Linked List and opens given input file for reading,
    // in the end it closes input file and deletes every apartment and flats in apartment Linked list
    explicit Manager(char**);

private:
    // read command that given as argv[1]
    void readCommand(std::string& command);

    void add_apartment();

    void add_flat();

    void remove_apartment();

    void merge_two_apartments();

    void find_sum_of_max_bandwidths() const;

    void list_apartments() const;

    void make_flat_empty();

    void relocate_flats_to_same_apartment();
};


#endif //ASS2_MANAGER_H
