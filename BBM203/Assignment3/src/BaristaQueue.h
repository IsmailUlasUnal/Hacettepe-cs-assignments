

#ifndef ASS3YENIDEN_BARISTAQUEUE_H
#define ASS3YENIDEN_BARISTAQUEUE_H


#include <set>
#include "Customer.h"
#include "Comparator.cpp"


class BaristaQueue {
private:
    std::set<Customer, CompareByPrice> set;
    int maxSize;

public:
    BaristaQueue();
    int size();
    bool isEmpty();
    Customer peek();
    void enqueue(Customer);
    Customer dequeue();

    int getMaxSize();
};


#endif //ASS3YENIDEN_BARISTAQUEUE_H
