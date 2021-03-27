#include <iostream>
#include "department.cpp"

using namespace std;

/*
valid Department Code
CSE 0
EEE 1
ME 2
*/

//There can be at most 3 departments
class University
{
private:
    Department departments[3];

public:
    University()
    {
        //set all department capacity 120
        for (int i = 0; i < 3; i++)
        {
            departments[i].setCapacity(120);
        }
    }
    University(int CSEcapacity, int EEEcapacity, int MEcapacity)
    {
        departments[0].setCapacity(CSEcapacity);
        departments[1].setCapacity(EEEcapacity);
        departments[2].setCapacity(MEcapacity);
    }

    bool addStudentToDepartment(Student student, int deptCode)
    {
        return departments[deptCode].addStudent(student);
    }

    bool removeStudentFromDepartment(int id, int deptCode)
    {
        return departments[deptCode].removeStudent(id);
    }

    Student getStudentFromDepartment(int id, int deptCode)
    {

        Student student = departments[deptCode].getStudent(id);
        return student;
    }
    void printUniversity()
    {
        cout << "Printing the information of students from all departments of this university: " << endl;
        for (int i = 0; i < 3; i++)
        {
            cout << "Department: ";
            switch (i)
            {
            case 0:
                cout << "CSE";
                break;
            case 1:
                cout << "EEE";
                break;

            default:
                cout << "ME";
                break;
            }
            cout << endl;
            departments[i].printDepartment();
        }
    }
};

int main()
{

    University buet(2, 3, 4);
    Student rahim(1, 2, 1, 0);
    buet.addStudentToDepartment(rahim, 0);
    Student karim(1, 2, 1, 1);
    buet.addStudentToDepartment(karim, 1);
    Student salim(1, 2, 1, 2);
    buet.addStudentToDepartment(salim, 2);
    Student farid(1, 2, 1, 1);
    buet.addStudentToDepartment(farid, 1);
    buet.printUniversity();

    return 0;
}
