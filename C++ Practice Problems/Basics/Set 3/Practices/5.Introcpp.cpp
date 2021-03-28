#include <stdio.h>

namespace A
{

    void print()
    {
        printf("This is in A");
    }

}

namespace B
{

    void print()
    {
        printf("This is in B");
    }

}

int main()
{

    A::print();
    B::print();

    return 0;
}
