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
		cout << "��ĳ���Ϊ��" << ListLength(L) << endl;
		TraverseList(L);
	}
	ElemType item;
	Find_pos(L, 3, item);
	cout << "��3��Ԫ��Ϊ��" << item << endl;
	cout << "a��λ��Ϊ��" << Find_item(L, 'a') << endl;

	ListInsert(L, 3, 'f');
	TraverseList(L);

	ListDelete(L, 2, item);
	cout << "ɾ���ĵ�2��Ԫ��Ϊ��" << item << endl;
	TraverseList(L);
	
	cout << "��������Ҫɾ����Ԫ�أ�";
	cin >> item;
	if (ListDelete(L, item))
	{
		cout << "ɾ����Ԫ��Ϊ��" << item << endl;
		TraverseList(L);
	}
	DestroyList(L);
	
	return 0;

	LinkNode* L;
	InitList(L);

	ListInsert_order(L, '1');
	ListInsert_order(L, '3');
	ListInsert_order(L, '5');

	cout << "����ǰ������: ";
	TraverseList(L);

	//������Ԫ��
	ListInsert_order(L, '2');

	cout << "����������: ";
	TraverseList(L);

	DestroyList(L);
	return 0;

	LinkNode* L;
	InitList(L);

	// ����һЩԪ��
	ListInsert(L, 1, 1);
	ListInsert(L, 2, 3);
	ListInsert(L, 3, 2);

	cout << "ɾ��ǰ������: ";
	TraverseList(L);

	ElemType max;
	if (Delete_max(L, max)) {
		cout << "ɾ�������ֵ��: " << max << endl;
		cout << "ɾ���������: ";
		TraverseList(L);
	}
	else {
		cout << "����Ϊ�գ��޷�ɾ�����ֵ��" << endl;
	}

	DestroyList(L);
	return 0;*/

	LinkNode* L;
	InitList(L);
	ElemType A[] = { {101,85}, {103,90.5}, {104,73}, {105,55} };

	for (int i = 0; i < 4; i++)
		ListInsert(L, i + 1, A[i]);
	cout << "ѧ�� �ɼ�" << endl;
	TraverseList(L);

	ListDelete(L, 103);
	cout << "ѧ�� �ɼ�" << endl;
	TraverseList(L);
	DestroyList(L);
	return 0;
}