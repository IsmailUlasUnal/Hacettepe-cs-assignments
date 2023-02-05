

#ifndef ASS3YENIDEN_FILEMANAGER_H
#define ASS3YENIDEN_FILEMANAGER_H


#include <fstream>
#include "Customer.h"
#include "CashierManager.h"
#include "CashierQueue.h"

class FileManager {
private:
    std::ifstream inputFile;

    Customer newCustomer;
    CashierManager *cashierManager;
    CashierQueue *customerQueue;

    int cashierNum;
    int baristaNum;
    int orderNum;

    void readInputFile();

    void writeOutputFile();

    void readOrders(int);

    double totalRunTimeFor1;
    double totalRunTimeFor2;

public:
    explicit FileManager(char **argv);

    ~FileManager();




};


#endif //ASS3YENIDEN_FILEMANAGER_H
