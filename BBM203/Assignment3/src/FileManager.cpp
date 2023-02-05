

#include <iostream>
#include "FileManager.h"

FileManager::FileManager(char **argv) {
    inputFile.open(argv[1], std::ios::in);
    freopen(argv[2], "w", stdout);
    readInputFile();
    writeOutputFile();
}

void FileManager::readInputFile() {
    if (inputFile.is_open()) {
        int numOfCashiers, numOfOrders;
        inputFile >> numOfCashiers >> numOfOrders;
        orderNum = numOfOrders;
        cashierNum = numOfCashiers;
        baristaNum = cashierNum / 3;

        customerQueue = new CashierQueue(numOfOrders);

        for (int i = 0; i < numOfOrders; i++) {
            readOrders(i);
        }
        cashierManager = new CashierManager(*customerQueue, numOfCashiers, numOfOrders);
    }
}

void FileManager::readOrders(int customerID) {
    double arrivalTime, orderTime, brewTime, priceOfOrder;
    inputFile >> arrivalTime >> orderTime >> brewTime >> priceOfOrder;
    newCustomer = Customer(arrivalTime, orderTime, brewTime, priceOfOrder, customerID);
    customerQueue->enqueue(newCustomer);
}

FileManager::~FileManager() {
    inputFile.close();
    //delete cashierManager;
    //delete customerQueue;
}

void FileManager::writeOutputFile() {

    totalRunTimeFor1 = cashierManager->getBaristaManager()->getTotalRunningTimeFor1();
    totalRunTimeFor2 = cashierManager->getBaristaManager()->getTotalRunningTimeFor2();


    printf("%.2lf\n", totalRunTimeFor1);

    printf("%d\n", cashierManager->getCashierQueue().getMaxSize());

    printf("%d\n", cashierManager->getBaristaManager()->getModel1()->getQueue().getMaxSize());

    for (int i = 0; i < cashierNum; i++) {
        printf("%.2lf\n", cashierManager->getCashierArray()[i].getBusyTime() / totalRunTimeFor1);
    }

    for (int i = 0; i < baristaNum; i++) {
        printf("%.2lf\n", cashierManager->getBaristaManager()->getModel1()->getBaristaArray()[i].getBusyTime() / totalRunTimeFor1);
    }


    for (int i = 0 ; i < orderNum; i++) {
        Customer currCustomer = cashierManager->getBaristaManager()->getCustomer1()[i];
        printf("%.2lf\n", currCustomer.getEndTime() - currCustomer.getArrivalTime());
    }

    std::cout << "\n";

    printf("%.2lf\n", totalRunTimeFor2);
    printf("%d\n", cashierManager->getCashierQueue().getMaxSize());


    for (int i = 0; i < baristaNum; i++) {
        printf("%d\n", cashierManager->getBaristaManager()->getModel2Arr()[i]->getQueue().getMaxSize());
    }

    for (int i = 0; i < cashierNum; i++) {
        printf("%.2lf\n", cashierManager->getCashierArray()[i].getBusyTime() / totalRunTimeFor2);
    }

    for (int i = 0; i < baristaNum; i++) {
        printf("%.2lf\n", cashierManager->getBaristaManager()->getModel2Arr()[i]->getBaristaArray()[0].getBusyTime() / totalRunTimeFor2);
    }


    for (int i = 0 ; i < orderNum; i++) {
        Customer currCustomer = cashierManager->getBaristaManager()->getCustomer2()[i];
        printf("%.2lf\n", currCustomer.getEndTime() - currCustomer.getArrivalTime());
    }






}
