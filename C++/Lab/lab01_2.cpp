#include <iostream>
using namespace std;
void swap1(int x, int y);//ֵ����
void swap2(int* px, int* py);//ָ�봫��
void swap3(int& x, int& y);//���ô���

int main()
{
	int a = 1, b = 2;
	int* pa = &a, * pb = &b;

	swap1(a, b); //�ϵ��
	cout << "After calling swap1:a=" << a << ", b=" << b << endl;

	a = 1, b = 2;
	swap2(pa, pb); //�ϵ��
	cout << "After calling swap2:a=" << a << ", b=" << b << endl;

	a = 1, b = 2;
	swap3(a, b); //�ϵ�� 
	cout << "After calling swap3:a=" << a << ", b=" << b << endl;

	return 0;
}
//ֵ����
void swap1(int x, int y)
{
	int t;
	t = x;  x = y;  y = t; //�ϵ��
	cout << "After calling swap1:x=" << &x << ", y=" << &y << endl;
}

//ָ�봫��
void swap2(int* px, int* py)
{
	int t;
	t = *px;  *px = *py;  *py = t; //�ϵ��
}

//���ô���
void swap3(int& x, int& y)
{
	int t;
	t = x;  x = y;  y = t; //�ϵ��
	cout << "After calling swap1:x=" << &x << ", y=" << &y << endl;
}