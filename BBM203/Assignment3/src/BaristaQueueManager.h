
#ifndef ASS3YENIDEN_BARISTAQUEUEMANAGER_H
#define ASS3YENIDEN_BARISTAQUEUEMANAGER_H


#include "Barista.h"
#include "BaristaQueue.h"
#include "CashierQueue.h"
#include <vector>

class BaristaQueueManager {
private:
    Barista *baristaArray;
    BaristaQueue queue;

    std::vector<Customer> customerVec;

    CashierQueue customerQueue;
    int size;

    Barista *findAvaible(double);
    void addFromQueue(double); // time

public:
    BaristaQueueManager(); // dummy constructor

    BaristaQueueManager(Barista*, CashierQueue, int);

    BaristaQueue getQueue();

    Barista *getBaristaArray();

    std::vector<Customer> getCustomers();


    void run();
};


#endif //ASS3YENIDEN_BARISTAQUEUEMANAGER_H
