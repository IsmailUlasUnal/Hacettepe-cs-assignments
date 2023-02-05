
#ifndef ASS3YENIDEN_CASHIERMANAGER_H
#define ASS3YENIDEN_CASHIERMANAGER_H


#include <set>
#include "Cashier.h"
#include "CashierQueue.h"
#include "BaristaManager.h"

class CashierManager {
private:

    Cashier *cashierArray;
    CashierQueue inputQueue;
    std::set<Customer, CompareByTime> baristaInput;
    CashierQueue cashierQueue;
    int numOfCashiers;
    int numOfBaristas;
    int numOfCustomers;
    BaristaManager *baristaManager;

    void run();
    Cashier *findAvaible(double);

    void addFromQueue(double);

public:
    CashierManager(CashierQueue &, int &, int);
    ~CashierManager();

    BaristaManager *getBaristaManager();

    CashierQueue getCashierQueue();

    Cashier *getCashierArray();

};


#endif //ASS3YENIDEN_CASHIERMANAGER_H
