#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

class Vector
{
    char *name;
    int x, y, z;

public:
    Vector(char *n = "")
    {
        int l = strlen(n);
        name = new char[l + 1];
        strcpy(name, n);
        x = 0;
        y = 0;
        z = 0;
    }
    Vector(char *n, int a, int b, int c)
    {
        int l = strlen(n);
        name = new char[l + 1];
        strcpy(name, n);
        x = a;
        y = b;
        z = c;
    }

    int setX(int a)
    {
        x = a;
        return a;
    }
    int setY(int b)
    {
        y = b;
        return b;
    }
    int setZ(int c)
    {
        z = c;
        return c;
    }

    void setName(char *n)
    {
        int l = strlen(n);
        name = new char[l + 1];
        strcpy(name, n);
    }

    int getX() { return x; }
    int getY() { return y; }
    int getZ() { return z; }

    Vector &operator=(const Vector &anotherVector)
    {
        x = anotherVector.x;
        y = anotherVector.y;
        z = anotherVector.z;
        return *this;
    }
    friend bool operator==(const Vector &firstVector, const Vector &secondVector);
    friend Vector operator*(const Vector &GivenVector, int scalarMulitiplier);
    friend Vector operator*(int scalarMultiplier, const Vector &GivenVector);
    friend Vector operator*(const Vector &firstVector, const Vector &secondVector);
    friend Vector operator^(const Vector &firstVector, const Vector &secondVector);
    char *getName() { return name; }
    void print()
    {
        cout << name << ": " << (x >= 0 ? "" : "-") << abs(x) << "x" << (y >= 0 ? "+" : "-") << abs(y) << "y" << (z >= 0 ? "+" : "-") << abs(z) << "z" << endl;
    }
    ~Vector()
    {
        delete[] name;
    }
};
Vector operator*(const Vector &GivenVector, int scalarMulitiplier)
{
    Vector ans("", GivenVector.x * scalarMulitiplier, GivenVector.y * scalarMulitiplier, GivenVector.z * scalarMulitiplier);
    return ans;
}
Vector operator*(int scalarMultiplier, const Vector &GivenVector)
{
    return GivenVector * scalarMultiplier;
}
Vector operator*(const Vector &firstVector, const Vector &secondVector)
{
    Vector ans("", firstVector.x * secondVector.x, firstVector.y * secondVector.y, firstVector.z * secondVector.z);
    return ans;
}
Vector operator^(const Vector &firstVector, const Vector &secondVector)
{
    Vector ans("Result", 1, 1, 1);
    ans.x = firstVector.y * secondVector.z - firstVector.z * secondVector.y;
    ans.y = -(firstVector.x * secondVector.z - firstVector.z * secondVector.x);
    ans.z = firstVector.x * secondVector.y - firstVector.y * secondVector.x;
    return ans;
}
bool operator==(const Vector &firstVector, const Vector &secondVector)
{
    return (firstVector.x == secondVector.x && firstVector.y == secondVector.y && firstVector.z == secondVector.z);
}
int main()
{
    Vector v1("v1", 1, 2, 3), v2("v2", 4, 5, -6), v3("Result1"), v4("Result2", -27, 18, -3);

    v1.print(); ///Print the components of vector v1
    v2.print(); ///Print the components of vector v2

    v3 = v1 ^ v2; ///Calculate the cross product of vector v1 and vector v2 (Consider ^ as cross product for this assignment)
    v3.print();   ///Print the modified components of vector v3 (Name: Result1)

    if (v3 == v4) ///Check for equality; if two vectors contain equal component values (x, y, z), then they are equal.
        cout << "Vectors are equal" << endl;
    else
        cout << "Vectors are not equal" << endl;

    v1 = v1 * 2; ///Multiply each component of vector v1 with the given value
    v1.print();  ///Print the modified components of vector v1

    v2 = 2 * v2; ///Multiply each component of vector v2 with the given value
    v2.print();  ///Print the modified components of vector v2

    v3 = v1 * v2; ///Multiply each component of vector v1 with the corresponding component of vector v2.
    v3.print();   ///Print the modified components of vector v3 (Name: Result1)

    if (v3 == v4) ///Check for equality; if two vectors contain equal component values (x, y, z), then they are equal.
        cout << "Vectors are equal" << endl;
    else
        cout << "Vectors are not equal" << endl;
    return 0;
}
