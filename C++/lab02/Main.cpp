#include "ArrayList.h"

//int main() {
//    SqList my_list;
//    InitList(my_list);
//
//    char elements[] = { 'a', 'b', 'c', 'd', 'e' };
//    for (int i = 0; i < 5; i++) {
//        ListInsert(my_list, 1, elements[i]);
//    }
//    cout << "my_list的长度为" << ListLength(my_list) << endl;
//    cout << "my_list中的元素为：";
//    TraverseList(my_list);
//    if (ListEmpty(my_list)) {
//        cout << "my_list为空。" << endl;
//    }
//    else {
//        cout << "my_list不为空。" << endl;
//    }
//    ListInsert(my_list, 4, 'f');
//    cout << "在第4个位置插入 'f' 后，my_list为：";
//    TraverseList(my_list);
//    ElemType deletedItem;
//    if (ListDelete(my_list, 3, deletedItem)) {
//        cout << "被删除的元素是" << deletedItem << endl;
//        cout << "删除第3个元素后，my_list为：";
//        TraverseList(my_list);
//    }
//    ElemType secondItem;
//    if (GetElem(my_list, 2, secondItem)) {
//        cout << "my_list的第2个元素是：" << secondItem << endl;
//    }
//    int position = Find(my_list, 'b');
//    if (position != 0) {
//        cout << "元素 'b' 的位置是：" << position << endl;
//    }
//    else {
//        cout << "未找到元素 'b'。" << endl;
//    }
//
//    return 0;
//}

int main() {
	SqList L1, L2;

	// 初始化两个顺序表
	InitList(L1);
	InitList(L2);

	// 向L1中插入数据 {1,2,3,4,5}
	char data1[] = { '1', '2', '3', '4', '5' };
	for (int i = 0; i < 5; i++) {
		ListInsert(L1, i + 1, data1[i]);
	}

	// 向L2中插入数据 {6,7,8,9,10}
	char data2[] = { '6', '7', '8', '9', '10' };
	for (int i = 0; i < 5; i++) {
		ListInsert(L2, i + 1, data2[i]);
	}

	cout << "线性表m_List1中有数据：" << endl; 
	TraverseList(L1);
	cout << "线性表m_List2中有数据：" << endl;
	TraverseList(L2);

	// 合并L2到L1的尾部
	MergeList(L1, L2);

	cout << "合并后的线性表m_List1中有数据：" << endl;
	TraverseList(L1);

	return 0;
}