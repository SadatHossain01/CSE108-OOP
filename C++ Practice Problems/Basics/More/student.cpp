#include <iostream>
#include <cstdlib>
using namespace std;

class Student
{
private:
    int id;
    int level;
    int term;
    double cgpa;
    int departmentCode;

public:
    Student()
    {
        level = 1;
        term = 1;
        cgpa = (rand() % 201 + 200) / (double)100;
        departmentCode = rand() % 3;
    }

    Student(int sId, int deptCode)
    {
        //default will be level1 term1
        id = sId;
        departmentCode = deptCode;
    }
    Student(int sId, int sLevel, int sTerm, int deptCode)
    {
        id = sId;
        level = sLevel;
        term = sTerm;
        departmentCode = deptCode;
        cgpa = (rand() % 201 + 200) / (double)100;
    }
    Student &operator=(Student &anotherStudent)
    {
        id = anotherStudent.id;
        level = anotherStudent.level;
        term = anotherStudent.term;
        cgpa = anotherStudent.cgpa;
        departmentCode = anotherStudent.departmentCode;
        return *this;
    }
    void printStudent()
    {
        cout << "ID: " << id << " Level: " << level << " Term: " << term << " CGPA: " << cgpa << " Dept. Code: " << departmentCode << endl;
    }
    //setter getter for all private variables
    int getID() { return id; }
    int getLevel() { return level; }
    int getTerm() { return term; }
    double getCGPA() { return cgpa; }
    int getDepartmentCode() { return departmentCode; }
};
