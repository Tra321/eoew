//#include <iostream>
//using namespace std;
//
////typedef char ElemType; //ElemType为数据元素的数据类型
//typedef int ElemType;
//
//typedef struct LNode //LNode为结点类型名
//{
//  ElemType data; //data代表数据元素
//  struct LNode *next; //next为指向下一结点的指针
//} LinkNode; // 单链表结点类型
//
////初始化单链表
//void InitList(LinkNode *&L);
//
////判表空
//bool ListEmpty(LinkNode *L);
//
////求单链表中当前元素的个数
//int ListLength(LinkNode *L);
//
////遍历单链表
//void TraverseList(LinkNode *L);
//
////返回第一个与指定值匹配的元素位置
//int Find_item(LinkNode *L, ElemType item);
//
////获取单链表中指定位置上的数据元素
//bool Find_pos(LinkNode *L, int pos, ElemType &item);
//
////向线性表指定位置插入一个新元素
//bool ListInsert(LinkNode *L, int pos, ElemType item);
//
////从线性表中删除指定位置的元素
//bool ListDelete(LinkNode *L, int pos, ElemType &item);
//
////从线性表中删除第一个与指定值匹配的元素
//bool ListDelete(LinkNode *L, ElemType item);
//
////撤销单链表
//void DestroyList(LinkNode *&L);
//
////有序插入
//bool ListInsert_order(LinkNode* L, ElemType item);
//
//// 删除最大值
//bool Delete_max(LinkNode* L, ElemType& item);

#include <iostream>
using namespace std;

// 重新定义 ElemType 为结构体，存储学号和成绩
typedef struct {
    int id;
    double score;
} ElemType;

typedef struct LNode {
    ElemType data;
    struct LNode* next;
} LinkNode;

// 初始化单链表
void InitList(LinkNode*& L);

// 判表空
bool ListEmpty(LinkNode* L);

// 求单链表中当前元素的个数
int ListLength(LinkNode* L);

// 遍历单链表
void TraverseList(LinkNode* L);

// 返回第一个与指定值匹配的元素位置
int Find_item(LinkNode* L, ElemType item);

// 获取单链表中指定位置上的数据元素
bool Find_pos(LinkNode* L, int pos, ElemType& item);

// 向线性表指定位置插入一个新元素
bool ListInsert(LinkNode* L, int pos, ElemType item);

// 从线性表中删除指定位置的元素
bool ListDelete(LinkNode* L, int pos, ElemType& item);

// 从线性表中删除学号为 number 的结点
bool ListDelete(LinkNode* L, int number);

// 撤销单链表
void DestroyList(LinkNode*& L);