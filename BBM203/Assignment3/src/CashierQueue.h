
#ifndef ASS3YENIDEN_CASHIERQUEUE_H
#define ASS3YENIDEN_CASHIERQUEUE_H


#include "Customer.h"

class CashierQueue {
private:
    Customer *queue;
    int capacity;
    int front;
    int rear;
    int currentSize;
    int maxSize;

public:
    CashierQueue();

    explicit CashierQueue(int);

    Customer peek();

    int size() const;

    bool isEmpty() const;

    bool isFull() const;

    Customer dequeue();

    void enqueue(Customer);

    ~CashierQueue();

    int getMaxSize();
};


#endif //ASS3YENIDEN_CASHIERQUEUE_H
