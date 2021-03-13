#include <iostream>
#include <cmath>
using namespace std;

class Point
{
    // x and y coordinate
    int x, y;

public:
    //Added necessary constructor(s) to initialize x and y
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
    //Add your set and get functions for x and y
    void setX(int a)
    {
        x = a;
    }
    void setY(int b)
    {
        y = b;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    void update(int dx, int dy)
    {
        x += dx;
        y += dy;
    }
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
    // Added necessary constructor(s) to initialize p and radius
    Circle(int x, int y, int r)
    {
        p.setX(x);
        p.setY(y);
        radius = r;
    }
    void print()
    {
        cout << "Center ";
        p.print();
        cout << "Radius: " << radius << endl;
    }
    // Overload “update” functions
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
        p1.setX(a1);
        p1.setY(b1);
        p2.setX(a2);
        p2.setY(b2);
    }
    void print()
    {
        double x1, x2, y1, y2;
        x1 = p1.getX();
        x2 = p2.getX();
        y1 = p1.getY();
        y2 = p2.getY();
        double ans = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        cout << ans << endl;
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
    // called “update” function of Circle class to increase the center’s x coordinate by 5 and y coordinate by 5;
    c.update(5, 5);
    c.print();
    cout << endl;

    //Second update
    cout << "Second Update" << endl;
    // called “update” function of Circle class to increase the radius by 6;
    c.update(6);
    c.print();
    cout << endl;

    //Third update
    cout << "Third Update" << endl;
    // called “update” function of Circle class to increase the center’s x coordinate by 2 and y coordinate by 2 and the radius by 2;
    c.update(2, 2, 2);
    c.print();
    cout << endl;

    Line L1(0, 0, 5, 5);
    L1.print();
    return 0;
}