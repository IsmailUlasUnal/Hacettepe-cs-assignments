#include "Customer.h"


Customer::Customer() {
    // this is a dummy constructor
}

Customer::Customer(double arrivalTime, double orderTime, double brewTime, double priceOfOrder, int id) {
    this->arrivalTime = arrivalTime;
    this->orderTime = orderTime;
    this->brewTime = brewTime;
    this->priceOfOrder = priceOfOrder;
    this->id = id;
}

double Customer::getArrivalTime() const {
    return arrivalTime;
}

double Customer::getOrderTime() const {
    return orderTime;
}

double Customer::getBrewTime() const {
    return brewTime;
}

double Customer::getPriceOfOrder() const {
    return priceOfOrder;
}

void Customer::setCashierID(int cashierID) {
    this->cashierID = cashierID;
}

int Customer::getCashierID() const {
    return cashierID;
}

void Customer::setReadyForBrewTime(double readyForBrewTime) {
    this->readyForBrewTime = readyForBrewTime;
}

double Customer::getReadyForBrewTime() const {
    return readyForBrewTime;
}

void Customer::setEndTime(double endTime) {
    this->endTime = endTime;
}

double Customer::getEndTime() const {
    return endTime;
}

int Customer::getID() {
    return id;
}
