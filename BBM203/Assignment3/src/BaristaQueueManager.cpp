
#include "BaristaQueueManager.h"
#include <iostream>

BaristaQueueManager::BaristaQueueManager(Barista *baristaArray, CashierQueue customerQueue, int size) {
    this->baristaArray = baristaArray;
    this->customerQueue = customerQueue;
    queue = BaristaQueue();
    this->size = size;
}

void BaristaQueueManager::run() {
    while (!customerQueue.isEmpty()) {
        Customer currentCustomer = customerQueue.dequeue();

        addFromQueue(currentCustomer.getReadyForBrewTime());
        queue.enqueue(currentCustomer);
        addFromQueue(currentCustomer.getReadyForBrewTime());
    }
    addFromQueue(-1);
}

Barista *BaristaQueueManager::findAvaible(double time) {
    double avaibleTime = baristaArray[0].getAvaibleTime();
    int baristaID = 0;

    if (time >= avaibleTime) {
        return &baristaArray[baristaID];
    }

    for (int i = 1; i < size; i++) {
        Barista currentBarista = baristaArray[i];
        if (currentBarista.getAvaibleTime() < avaibleTime) {
            baristaID = i;
            avaibleTime = currentBarista.getAvaibleTime();
        }

        if (time >= avaibleTime) {
            return &baristaArray[baristaID];
        }
    }

    return &baristaArray[baristaID];
}

void BaristaQueueManager::addFromQueue(double time) {
    while (!queue.isEmpty()) {
        Customer currentCustomer = queue.peek();
        Barista *avaibleBarista = findAvaible(currentCustomer.getReadyForBrewTime());
        if (time != -1 && time < avaibleBarista->getAvaibleTime()) {
            break;
        }

        double startTime = std::max(avaibleBarista->getAvaibleTime(), currentCustomer.getReadyForBrewTime());
        double newAvaibleTime = startTime + currentCustomer.getBrewTime();
        avaibleBarista->setAvaibleTime(newAvaibleTime);
        avaibleBarista->setBusyTime(currentCustomer.getBrewTime());

        currentCustomer.setEndTime(avaibleBarista->getAvaibleTime());


        customerVec.push_back(currentCustomer);

        queue.dequeue();
    }
}

BaristaQueueManager::BaristaQueueManager() {}

BaristaQueue BaristaQueueManager::getQueue() {
    return queue;
}

Barista *BaristaQueueManager::getBaristaArray() {
    return baristaArray;
}

std::vector<Customer> BaristaQueueManager::getCustomers() {
    return customerVec;
}
