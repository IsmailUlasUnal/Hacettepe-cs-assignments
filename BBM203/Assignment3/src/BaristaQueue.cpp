
#include "BaristaQueue.h"
#include <iostream>

BaristaQueue::BaristaQueue() {
    maxSize = 0;
}

int BaristaQueue::size() {
    return (int)set.size();
}

bool BaristaQueue::isEmpty() {
    return set.empty();
}

Customer BaristaQueue::peek() {
    return *set.begin();
}

void BaristaQueue::enqueue(Customer customerToAdd) {
    set.insert(customerToAdd);
    maxSize = std::max(maxSize, size());
}

Customer BaristaQueue::dequeue() {
    Customer temp = peek();
    set.erase(set.begin());
    return temp;
}

int BaristaQueue::getMaxSize() {
    return maxSize;
}
