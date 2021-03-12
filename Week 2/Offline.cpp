#include <iostream>
using namespace std;
class Point
{
    // x and y coordinate
    int x, y;

public:
    Point()
    {
        x = 0;
        y = 0;
    }
    Point(int a, int b)
    {
        x = a;
        y = b;
    }
    void set(int a, int b)
    {
        x = a;
        y = b;
    }
    void update(int dx, int dy)
    {
        x += dx;
        y += dy;
    }
    //Add necessary constructor(s) to initialize x and y
    //Add your set and get functions for x and y

    void print()
    {
        cout << "Coordinate: " << x << ", " << y << endl;
    }
};
class Circle
{
    Point p;
    int radius;

public:
    // Add necessary constructor(s) to initialize p and radius
    // Overload “upload” functions
    Circle(int x, int y, int r)
    {
        p.set(x, y);
        radius = r;
    }
    void print()
    {
        cout << "Center ";
        p.print();
        cout << "Radius: " << radius << endl;
    }
    void update(int dx, int dy)
    {
        p.update(dx, dy);
    }
    void update(int dr)
    {
        radius += dr;
    }
    void update(int dx, int dy, int dr)
    {
        p.update(dx, dy);
        radius += dr;
    }
};
class Line
{
    Point p1, p2;

public:
    Line(int a1, int b1, int a2, int b2)
    {
        p1.set(a1, b1);
        p1.set(a2, b2);
    }
    void print()
    {
        return sqrt();
    }
};
int main()
{
    Point p(5, 5);
    Circle c(2, 3, 5);
    cout << endl
         << "Point Display" << endl;
    p.print();
    cout << endl
         << "Circle Display" << endl;
    c.print();
    cout << endl;

    //First update
    cout << "First Update" << endl;
    // call “update” function of Circle class to increase the center’s x coordinate by 5 and y coordinate by 5;
    c.update(5, 5);
    c.print();
    cout << endl;

    //Second update
    cout << "Second Update" << endl;

    // call “update” function of Circle class to increase the radius by 6;
    c.update(6);
    c.print();
    cout << endl;

    //Third update
    cout << "Third Update" << endl;

    // call “update” function of Circle class to increase the center’s x coordinate by 2 and y coordinate by 2 and the radius by 2;
    c.update(2, 2, 2);
    c.print();
    cout << endl;
    return 0;
}