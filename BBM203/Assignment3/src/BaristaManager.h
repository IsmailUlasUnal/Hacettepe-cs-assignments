
#ifndef ASS3YENIDEN_BARISTAMANAGER_H
#define ASS3YENIDEN_BARISTAMANAGER_H


#include <set>
#include "Customer.h"
#include "BaristaQueueManager.h"

class BaristaManager {
private:
    int numOfBaristas;
    int numOfCustomers;

    BaristaQueueManager *model1; // system class, 1 system
    BaristaQueueManager **model2Arr; // system class, n/3 system

    std::vector<Customer> vecCustomer1;
    std::vector<Customer> vecCustomer2;


    Barista *firstBaristaArray; // every barista on one array
    Barista **secondBaristaArray; // every barista on different array

    CashierQueue customerQueueFor1; // input which includes every customer
    CashierQueue *customerQueueFor2; // input which includes id / 3 array

    double totalRunningTimeFor1;
    double totalRunningTimeFor2;

    void setTotalRunningTimeFor1();

    void setTotalRunningTimeFor2();

public:
    BaristaManager(std::set<Customer, CompareByTime>, int, int);

    void runModel1();

    void runModel2();

    double getTotalRunningTimeFor1() const;

    double getTotalRunningTimeFor2() const;

    BaristaQueueManager *getModel1();

    BaristaQueueManager **getModel2Arr();

    std::vector<Customer> getCustomer1();

    std::vector<Customer> getCustomer2();

};


#endif //ASS3YENIDEN_BARISTAMANAGER_H
