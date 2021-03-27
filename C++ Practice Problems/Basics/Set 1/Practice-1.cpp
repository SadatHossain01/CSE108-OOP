#include <iostream>
using namespace std;
class Point
{
private:
    double x, y;

public:
    Point(double x = 0, double y = 0)
    {
        this->x = x;
        this->y = y;
    }
    double getX() { return x; }
    double getY() { return y; }
    void setX(double x) { this->x = x; }
    void setY(double y) { this->y = y; }
};
class Circle
{
private:
    Point Center;
    double radius;

public:
    Circle(double x = 0, double y = 0, double radius = 5)
    {
        Center = Point(x, y);
        this->radius = radius;
    }
    void setX(double x) { Center.setX(x); }
    void setY(double y) { Center.setY(y); }
    void setRadius(double radius) { this->radius = radius; }
    double getX() { return Center.getX(); }
    double getY() { return Center.getY(); }
    double getRadius() { return radius; }
};
class Rectangle
{
private:
    Point BottomLeft, TopRight;

public:
    //not that difficult, you can complete that I guess, no more wasting time on it
};
int main()
{
    return 0;
}