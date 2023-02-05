
#include "Employee.h"
#include <iostream>

Employee::Employee() {

}

Employee::Employee(int id) {
    this->id = id;
    busyTime = 0;
    avaibleTime = 0;
}

double Employee::getBusyTime() const {
    return busyTime;
}

double Employee::getAvaibleTime() const {
    return avaibleTime;
}

int Employee::getID() const {
    return id;
}

void Employee::setBusyTime(double timeToAdd) {
    busyTime += timeToAdd;
}

void Employee::setAvaibleTime(double &avaibleTime) {
    this->avaibleTime = avaibleTime;
}
