
#ifndef ASS3YENIDEN_EMPLOYEE_H
#define ASS3YENIDEN_EMPLOYEE_H


class Employee {
private:
    double busyTime;
    double avaibleTime;
    int id;

public:
    Employee();

    explicit Employee(int);

    double getBusyTime() const;

    double getAvaibleTime() const;

    int getID() const;

    void setBusyTime(double);

    void setAvaibleTime(double &);
};


#endif //ASS3YENIDEN_EMPLOYEE_H
