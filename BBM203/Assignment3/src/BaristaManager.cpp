
#include "BaristaManager.h"

BaristaManager::BaristaManager(std::set<Customer, CompareByTime> baristaInput, int numOfBaristas, int numOfCustomers) {
    this->numOfBaristas = numOfBaristas;
    customerQueueFor1 = CashierQueue(numOfCustomers);
    customerQueueFor2 = new CashierQueue[this->numOfBaristas];

    for (int i = 0 ; i < numOfBaristas; i++) {
        customerQueueFor2[i] = CashierQueue(numOfCustomers);
    }

    for (auto it : baristaInput) {
        customerQueueFor1.enqueue(it);

        customerQueueFor2[it.getCashierID() / 3].enqueue(it); // todo

    }


    firstBaristaArray = new Barista[numOfBaristas];
    secondBaristaArray = new Barista *[numOfBaristas];

    for (int i = 0; i < numOfBaristas; i++) {
        secondBaristaArray[i] = new Barista[1];

        secondBaristaArray[i][0] = Barista(i);
        firstBaristaArray[i] = Barista(i);
    }
    model1 = new BaristaQueueManager(firstBaristaArray, customerQueueFor1, numOfBaristas);

    model2Arr = new BaristaQueueManager *[numOfBaristas];
    for (int i = 0; i < numOfBaristas; i++) {
        model2Arr[i] = new BaristaQueueManager(secondBaristaArray[i], customerQueueFor2[i], 1);
    }


    totalRunningTimeFor1 = 0;
    totalRunningTimeFor2 = 0;

    runModel1();

    vecCustomer1 = model1->getCustomers();

    std::sort(vecCustomer1.begin(), vecCustomer1.end(), CompareByID());





    runModel2();

    for (int i = 0 ; i < numOfBaristas; i++) {
        std::vector<Customer> vec = model2Arr[i]->getCustomers();

        for (const auto & a : vec) {
            vecCustomer2.push_back(a);
        }
    }

    std::sort(vecCustomer2.begin(), vecCustomer2.end(), CompareByID());




    setTotalRunningTimeFor1();
    setTotalRunningTimeFor2();


}

void BaristaManager::runModel1() {
    model1->run();
}

void BaristaManager::runModel2() {
    for (int i = 0; i < numOfBaristas; i++) {
        model2Arr[i]->run();
    }
}

double BaristaManager::getTotalRunningTimeFor1() const {
    return totalRunningTimeFor1;
}

double BaristaManager::getTotalRunningTimeFor2() const {
    return totalRunningTimeFor2;
}

void BaristaManager::setTotalRunningTimeFor1() {
    for (int i = 0; i < numOfBaristas; i++) {
        totalRunningTimeFor1 = std::max(totalRunningTimeFor1, firstBaristaArray[i].getAvaibleTime());
    }
}

void BaristaManager::setTotalRunningTimeFor2() {
    for (int i = 0; i < numOfBaristas; i++) {
        totalRunningTimeFor2 = std::max(totalRunningTimeFor2, secondBaristaArray[i][0].getAvaibleTime());
    }
}

BaristaQueueManager* BaristaManager::getModel1() {
    return model1;
}

BaristaQueueManager **BaristaManager::getModel2Arr() {
    return model2Arr;
}

std::vector<Customer> BaristaManager::getCustomer1() {
    return vecCustomer1;
}

std::vector<Customer> BaristaManager::getCustomer2() {
    return vecCustomer2;
}




