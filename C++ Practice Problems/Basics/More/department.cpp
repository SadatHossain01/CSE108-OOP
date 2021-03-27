#include <iostream>
#include "student.cpp"

using namespace std;

class Department
{
private:
    int capacity;
    int level;
    int term;
    Student *students;
    int departmentCode;
    int noOfStudents;

public:
    Department()
    {
        capacity = 120;
        students = new Student[capacity];
        noOfStudents = 0;
    }

    Department(int level, int term, int capacity, int deptCode)
    {
        //allocate students array by the capacity
        this->level = level;
        this->term = term;
        this->capacity = capacity;
        this->departmentCode = deptCode;
        noOfStudents = 0;
        students = new Student[capacity];
    }

    bool addStudent(Student student)
    {
        //add student information into the array
        if (noOfStudents < capacity)
        {
            students[noOfStudents++] = student;
            return true;
        }
        return false;
    }

    bool removeStudent(int byId)
    {
        for (int i = 0; i < noOfStudents; i++)
        {
            Student &thisStudent = students[i];
            if (thisStudent.getID() == byId)
            {
                for (int j = i + 1; j < noOfStudents; j++)
                {
                    students[j - 1] = students[j];
                }
                noOfStudents--;
                return true;
            }
        }
        return false;
    }

    Student getStudent(int byId)
    {
        //check if that id exists
        for (int i = 0; i < noOfStudents; i++)
        {
            Student &thisStudent = students[i];
            if (thisStudent.getID() == byId)
            {
                return thisStudent;
            }
        }
        return students[0];
    }
    int getCapacity() { return capacity; }
    void setCapacity(int capacity)
    {
        this->capacity = capacity;
        delete[] students;
        students = new Student[capacity];
    }
    void printDepartment()
    {
        cout << "Capacity: " << capacity << " Number of Students: " << noOfStudents << endl;
        for (int i = 0; i < noOfStudents; i++)
        {
            students[i].printStudent();
        }
    }
};
