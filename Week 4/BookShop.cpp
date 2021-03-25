#include <iostream>
#include <cstring>
#include <cmath>
#include "Book.cpp"

using namespace std;

class BookShop
{
	char name[100];
	Book *books; //List of books in the shop
	int* testNumbers;
	int size;	 //maximum number of books the shop can keep
	int count;	 //tracks currently how many numbers in the shop
public:
	void setName(const char *name)
	{
		/*Set the name of the bookshop to the given name*/
		strcpy(this->name, (char *)name);
	}
	int getCount() { return count; }
	Book getBook(int index) { if (index < count) return books[index]; }
	BookShop()
	{ //Default constructor
		/*Initialize with size 5 and name to empty string ""*/
		this->size = 5;
		this->count = 0;
		this->books = new Book[this->size];
		strcpy(this->name, "");
	}
	BookShop(const char *name, int size)
	{ //Parameterized constructor
		/*Initialize with the given name and size*/
		strcpy(this->name, (char*)name);
		this->size = size;
		this->books = new Book[this->size];
		this->count = 0;
	}
	BookShop(const BookShop &bs)
	{ //Copy constructor
		/*Write necessary code for the copy constructor*/
		strcpy(this->name, bs.name);
		this->size = bs.size;
		this->count = bs.count;
		this->books = new Book[this->size];
		for (int i = 0; i < bs.count; i++)
		{
			this->books[i] = bs.books[i];
		}
	}
	~BookShop()
	{ //Destructor
		/*Free memory as applicable*/
		delete[] books;
	}
	void addBook(Book b)
	{
		/*Add book b to the end of the list*/
		books[count] = b;
		count++;
	}
	void addBook(Book *ba, int count)
	{
		/*Add the given array of books ba to the end of the list*/
		for (int i = 0; i < count; i++)
		{
			addBook(ba[i]);
		}
	}
	Book getBookInfo(const char *title)
	{
		/*Return the **first** book that has the given title. Assume there is atleast one book in the list with the given title*/
		Book wantedBook;
		for (int i = 0; i < count; i++)
		{
			if (strcmp(books[i].getTitle(), (char*)title) == 0)
			{
				wantedBook = books[i];
				break;
			}
		}
		return wantedBook;
	}
	void updateBookPrice(int isbn, int price)
	{
		/*Update the price of the book with given isbn to the given price*/
		for (int i = 0; i < count; i++)
		{
			if (books[i].getISBN() == isbn)
			{
				books[i].setPrice(price);
			}
		}
	}
	void removeBook(int isbn)
	{
		/*Remove the book with the given isbn from the list. After removing a book all the books below the removed book will be shifted up, i.e, there will be no hole in the list*/
		for (int i = 0; i < count; i++)
		{
			if (books[i].getISBN() == isbn)
			{
				for (int j = i + 1; j < count; j++)
				{
					books[j - 1] = books[j];
				}
				count--;
			}
		}
	}
	int totalPrice()
	{
		/*Return the sum of the prices of all the books in the list*/
		int totalPrice = 0;
		for (int i = 0; i < count; i++)
		{
			totalPrice += books[i].getPrice();
		}
		return totalPrice;
	}
	void print()
	{
		/*Print the bookshop's name followed by information of all the books in the bookshop*/
		cout << "Bookshop Name: " << name << endl;
		for (int i = 0; i < count; i++)
		{
			books[i].print();
		}
	}
	BookShop mergeShop(BookShop b)
	{
		/* Return a new bookshop object that will contain all the books of this bookshop and the given bookshop b*/ /* Use the title **MergedShop** of the new bookshop*/
		BookShop mergedBookShop;
		mergedBookShop.setName("MergedShop");
		mergedBookShop.count = 0;
		mergedBookShop.size = this->size + b.size;
		mergedBookShop.books = new Book[mergedBookShop.size];
		mergedBookShop.addBook(this->books, this->count);
		mergedBookShop.addBook(b.books, b.count);
		return mergedBookShop;
	}
};

int main()
{

	BookShop bs1;
	bs1.setName("Boimela");
	Book b1(101, "Teach Yourself C++", 100);
	Book b2(102, "Teach Yourself C", 200);
	Book b3(103, "Java For Dummies", 300);
	bs1.addBook(b1);
	bs1.addBook(b2);
	bs1.addBook(b3);
	bs1.print();												  
	/*Output:
		Bookshop Name: Boimela
		ISBN: 101, Title: Teach Yourself C++, Edition: 100
		ISBN: 102, Title: Teach Yourself C, Edition: 200
		ISBN: 103, Title: Java For Dummies, Edition: 300
	*/
	cout << "Total price of books: " << bs1.totalPrice() << endl; /*Output:
															   Total price of books: 600
															*/

	bs1.removeBook(102);
	bs1.print(); /*Output:
					Bookshop Name: Boimela
					ISBN: 101, Title: Teach Yourself C++, Edition: 100
					ISBN: 103, Title: Java For Dummies, Edition: 300
				 */

	bs1.updateBookPrice(101, 500);
	bs1.print(); /*Output:
					Bookshop Name: Boimela
					ISBN: 101, Title: Teach Yourself C++, Edition: 500
					ISBN: 103, Title: Java For Dummies, Edition: 300
				 */

	Book jfd = bs1.getBookInfo("Java For Dummies");
	jfd.print(); /*Output:
					 ISBN: 103, Title: Java For Dummies, Edition: 300
				 */

	cout << endl;
	Book ba[3] = {Book(201, "Operating Systems", 1000), Book(202, "Compilers", 2000), Book(203, "Computer Networks", 1500)};
	BookShop bs2("Puthighor", 5);
	bs2.addBook(ba, 3);
	bs2.print(); /*Output:
					 Bookshop Name: Puthighor
					 ISBN: 201, Title: Operating Systems, Edition: 1000
					 ISBN: 202, Title: Compilers, Edition: 2000
					 ISBN: 203, Title: Computer Networks, Edition: 1500
				 */

	cout << endl;
	BookShop bs3 = bs1.mergeShop(bs2);
	bs3.setName("Mullick Brothers");
	bs3.print(); /*Output: Mullick Brothers
					ISBN: 101, Title: Teach Yourself C++, Edition: 500
					ISBN: 103, Title: Java For Dummies, Edition: 300
					ISBN: 201, Title: Operating Systems, Edition: 1000
					ISBN: 202, Title: Compilers, Edition: 2000
					ISBN: 203, Title: Computer Networks, Edition: 1500
				 */
}
