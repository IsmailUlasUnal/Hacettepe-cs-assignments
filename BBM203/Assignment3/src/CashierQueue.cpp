

#include <algorithm>
#include "CashierQueue.h"

CashierQueue::CashierQueue() {
    this->capacity = 100000;
    front = 0;
    rear = -1;
    currentSize = 0;
    maxSize = 0;
}

CashierQueue::CashierQueue(int capacity) {
    this->capacity = capacity;
    queue = new Customer[capacity];
    front = 0;
    rear = -1;
    currentSize = 0;
    maxSize = 0;
}

Customer CashierQueue::peek() {
    return queue[front];
}

int CashierQueue::size() const {
    return currentSize;
}

bool CashierQueue::isEmpty() const {
    return (size() == 0);
}

bool CashierQueue::isFull() const {
    return (size() == capacity);
}

Customer CashierQueue::dequeue() {
    Customer customerToReturn = queue[front];
    front = (front + 1) % capacity;
    currentSize--;

    return customerToReturn;
}

void CashierQueue::enqueue(Customer newCustomer) {
    rear = (rear + 1) % capacity;
    queue[rear] = newCustomer;
    currentSize++;
    maxSize = std::max(maxSize, size());
}

CashierQueue::~CashierQueue() {
    //delete[] queue;
}

int CashierQueue::getMaxSize() {
    return maxSize;
}
