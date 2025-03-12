#include "LinkedList.h"
int main()
{
	/*LinkNode* L;
	InitList(L);

	for(int i=0; i<4; i++)
		ListInsert(L, 1, 'a'+i);
	TraverseList (L);

	if (!ListEmpty(L))
	{
		cout << "表的长度为：" << ListLength(L) << endl;
		TraverseList(L);
	}
	ElemType item;
	Find_pos(L, 3, item);
	cout << "第3个元素为：" << item << endl;
	cout << "a的位置为：" << Find_item(L, 'a') << endl;

	ListInsert(L, 3, 'f');
	TraverseList(L);

	ListDelete(L, 2, item);
	cout << "删除的第2个元素为：" << item << endl;
	TraverseList(L);
	
	cout << "请输入你要删除的元素：";
	cin >> item;
	if (ListDelete(L, item))
	{
		cout << "删除的元素为：" << item << endl;
		TraverseList(L);
	}
	DestroyList(L);
	
	return 0;

	LinkNode* L;
	InitList(L);

	ListInsert_order(L, '1');
	ListInsert_order(L, '3');
	ListInsert_order(L, '5');

	cout << "插入前的链表: ";
	TraverseList(L);

	//插入新元素
	ListInsert_order(L, '2');

	cout << "插入后的链表: ";
	TraverseList(L);

	DestroyList(L);
	return 0;

	LinkNode* L;
	InitList(L);

	// 插入一些元素
	ListInsert(L, 1, 1);
	ListInsert(L, 2, 3);
	ListInsert(L, 3, 2);

	cout << "删除前的链表: ";
	TraverseList(L);

	ElemType max;
	if (Delete_max(L, max)) {
		cout << "删除的最大值是: " << max << endl;
		cout << "删除后的链表: ";
		TraverseList(L);
	}
	else {
		cout << "链表为空，无法删除最大值。" << endl;
	}

	DestroyList(L);
	return 0;*/

	LinkNode* L;
	InitList(L);
	ElemType A[] = { {101,85}, {103,90.5}, {104,73}, {105,55} };

	for (int i = 0; i < 4; i++)
		ListInsert(L, i + 1, A[i]);
	cout << "学号 成绩" << endl;
	TraverseList(L);

	ListDelete(L, 103);
	cout << "学号 成绩" << endl;
	TraverseList(L);
	DestroyList(L);
	return 0;
}