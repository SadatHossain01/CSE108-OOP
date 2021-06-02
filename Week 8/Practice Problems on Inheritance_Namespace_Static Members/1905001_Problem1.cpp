#include <iostream>
#include <cstring>
using namespace std;

namespace infrastructure
{
    class pool {
    private:
        int height, width, depth;
        char painted_color[10];
    public:
        void set_properties(int h, int w, int d, char* pc) {
            height = h, width = w, depth = d;
            strcpy(this->painted_color, pc);
        }
        void show() {
            cout << height << " x " << width << " x " << depth << endl;
            cout << "Painted Color: " << painted_color << endl;
        }
    };
}

namespace sports
{
    class pool {
    private:
        char table_ingredients[20], table_color[10];
    public:
        void set_properties(char* ti, char* tc) {
            strcpy(table_ingredients, ti);
            strcpy(table_color, tc);
        }
        void show() {
            cout << "Table Ingredient: " << table_ingredients << endl;
            cout << "Table Color: " << table_color << endl;
        }
    };
}

int main()
{
    infrastructure::pool P1;
    P1.set_properties(1, 2, 3, "Blue");
    P1.show();
    sports::pool P2;
    P2.set_properties("Wood", "Red");
    P2.show();
    return 0;
}