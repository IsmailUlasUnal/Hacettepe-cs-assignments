#ifndef ASS3YENIDEN_CUSTOMER_H
#define ASS3YENIDEN_CUSTOMER_H


class Customer {
private:
    double arrivalTime;
    double orderTime;
    double brewTime;
    double priceOfOrder;

    int cashierID;
    double readyForBrewTime;
    double endTime;
    int id;

public:
    Customer();

    Customer(double, double, double, double, int);

    double getArrivalTime() const;

    double getOrderTime() const;

    double getBrewTime() const;

    double getPriceOfOrder() const;

    void setCashierID(int);

    int getCashierID() const;

    void setReadyForBrewTime(double);

    double getReadyForBrewTime() const;

    void setEndTime(double);

    double getEndTime() const;

    int getID();
};


#endif //ASS3YENIDEN_CUSTOMER_H
