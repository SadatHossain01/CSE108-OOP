#include <iostream>
using namespace std;


///Copy the modified inventory class from the solved Problem 4 program

class inventory
{
protected:
    static int count;
public:
    inventory() { cout << "Parameter-less constructor for inventory class object" << endl; }
    inventory(int c) { cout << "Parameterized constructor for inventory class object" << endl; count = c; }
    int get_count() { return count; }
    int set_count(int c) { count = c; }
    void changeInventory(int c) { cout << "To be changed by the derived classes"; }
    void show() { cout << "To be changed by the derived classes"; }
    ~inventory() { cout << "Destructor for inventory class object" << endl; }
};
int inventory::count = 0;

///Write an appropriate definition of buyer class.

class buyer :public inventory {
    static int bought;
public:
    buyer() { cout << "Parameter-less constructor for buyer class object" << endl; }
    void changeInventory(int quantity) {
        bought += quantity;
        count += quantity;
    }
    void show() {
        cout << "So far bought quantity: " << bought << endl;
        cout << "Available in inventory: " << count << endl;
    }
    ~buyer() {
        cout << "Destructor for buyer class object" << endl;
    }
};
int buyer::bought = 0;
///Write an appropriate definition of seller class.
class seller :public inventory {
    static int sold;
public:
    seller() { cout << "Parameter-less constructor for seller class object" << endl; }
    void changeInventory(int quantity) {
        sold += quantity;
        count -= quantity;
    }
    void show() {
        cout << "So far sold quantity: " << sold << endl;
        cout << "Available in inventory: " << count << endl;
    }
    ~seller() {
        cout << "Destructor for seller class object" << endl;
    }
};
int seller::sold = 0;

int main()
{
    int option, quantity, person;
    buyer b1, b2;
    seller s1, s2;
    cout << "Enter option 1 to buy, option 2 to sell, and other to exit" << endl;

    while (1)
    {
        cout << "Option: ";
        cin >> option;
        if (option == 1)
        {
            cout << "Quantity: ";
            cin >> quantity;
            cout << "Person (1 or 2): ";
            cin >> person;
            if (person == 1)
            {
                b1.changeInventory(quantity);
                b1.show();
            }
            else
            {
                b2.changeInventory(quantity);
                b2.show();
            }

        }
        else if (option == 2)
        {
            cout << "Quantity: ";
            cin >> quantity;
            cout << "Person (1 or 2): ";
            cin >> person;
            if (person == 1)
            {
                s1.changeInventory(quantity);
                s1.show();
            }
            else
            {
                s2.changeInventory(quantity);
                s2.show();
            }
        }
        else
            break;
    }

    return 0;
}
