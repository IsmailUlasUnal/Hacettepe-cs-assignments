
#include "CashierManager.h"
#include <iostream> //todo sil bunu

CashierManager::CashierManager(CashierQueue &inputQueue, int &numOfCashiers, int numOfCustomers) {
    this->numOfCustomers = numOfCustomers;
    this->numOfCashiers = numOfCashiers;
    cashierArray = new Cashier[numOfCashiers];
    this->inputQueue = inputQueue;
    numOfBaristas = numOfCashiers / 3;

    cashierQueue = CashierQueue(numOfCashiers);

    for (int i = 0; i < numOfCashiers; i++) {
        cashierArray[i] = Cashier(i);
    }

    run();
}

void CashierManager::run() {
    while (!inputQueue.isEmpty()) {
        Customer currentCustomer = inputQueue.dequeue();

        addFromQueue(currentCustomer.getArrivalTime());
        cashierQueue.enqueue(currentCustomer);
        addFromQueue(currentCustomer.getArrivalTime());
    }

    addFromQueue(-1);


    baristaManager = new BaristaManager(baristaInput, numOfBaristas, numOfCustomers);

}

Cashier *CashierManager::findAvaible(double time) {
    double avaibleTime = cashierArray[0].getAvaibleTime();
    int cashierID = 0;

    if (time >= avaibleTime) {
        return &cashierArray[cashierID];
    }

    for (int i = 1; i < numOfCashiers; i++) {
        Cashier currentCashier = cashierArray[i];
        if (currentCashier.getAvaibleTime() < avaibleTime) {
            cashierID = i;
            avaibleTime = currentCashier.getAvaibleTime();
        }

        if (time >= avaibleTime) {
            return &cashierArray[cashierID];
        }
    }

    return &cashierArray[cashierID];
}

void CashierManager::addFromQueue(double time) {
    while (!cashierQueue.isEmpty()) {
        Customer currentCustomer = cashierQueue.peek();
        Cashier *avaibleCashier = findAvaible(currentCustomer.getArrivalTime());

        if (time != -1 && time < avaibleCashier->getAvaibleTime()) {
            break;
        }

        double startTime = std::max(avaibleCashier->getAvaibleTime(), currentCustomer.getArrivalTime());
        double newAvaibleTime = startTime + currentCustomer.getOrderTime();
        avaibleCashier->setAvaibleTime(newAvaibleTime);
        avaibleCashier->setBusyTime(currentCustomer.getOrderTime());

        currentCustomer.setCashierID(avaibleCashier->getID());
        currentCustomer.setReadyForBrewTime(avaibleCashier->getAvaibleTime());


        baristaInput.insert(currentCustomer);
        cashierQueue.dequeue();
    }

}

CashierManager::~CashierManager() {
    //delete cashierArray;
    //delete baristaManager;
}

BaristaManager *CashierManager::getBaristaManager() {
    return baristaManager;
}

CashierQueue CashierManager::getCashierQueue() {
    return cashierQueue;
}

Cashier *CashierManager::getCashierArray() {
    return cashierArray;
}

