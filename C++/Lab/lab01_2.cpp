#include <iostream>
using namespace std;
void swap1(int x, int y);//值传递
void swap2(int* px, int* py);//指针传递
void swap3(int& x, int& y);//引用传递

int main()
{
	int a = 1, b = 2;
	int* pa = &a, * pb = &b;

	swap1(a, b); //断点①
	cout << "After calling swap1:a=" << a << ", b=" << b << endl;

	a = 1, b = 2;
	swap2(pa, pb); //断点③
	cout << "After calling swap2:a=" << a << ", b=" << b << endl;

	a = 1, b = 2;
	swap3(a, b); //断点⑤ 
	cout << "After calling swap3:a=" << a << ", b=" << b << endl;

	return 0;
}
//值传递
void swap1(int x, int y)
{
	int t;
	t = x;  x = y;  y = t; //断点②
	cout << "After calling swap1:x=" << &x << ", y=" << &y << endl;
}

//指针传递
void swap2(int* px, int* py)
{
	int t;
	t = *px;  *px = *py;  *py = t; //断点④
}

//引用传递
void swap3(int& x, int& y)
{
	int t;
	t = x;  x = y;  y = t; //断点⑥
	cout << "After calling swap1:x=" << &x << ", y=" << &y << endl;
}