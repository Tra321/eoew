#include <iostream>
using namespace std;

#define MaxSize 100

typedef char ElemType; //声明元素类型

typedef struct {
  ElemType data[MaxSize]; //存放线性表数据
  int length;
} SqList; // SqList为用户定义的线性表类型

//初始化空线性表
void InitList(SqList &L);

//判断线性表是否为空
bool ListEmpty(SqList L);

//求出线性表长度
int ListLength(SqList L);

//向线性表指定位置插入一个新元素
bool ListInsert(SqList &L, int pos, ElemType item);

//从线性表中删除第一个与指定值匹配的元素
bool ListDelete(SqList &L, int pos, ElemType &item);

//获取顺序表中指定位置上的数据元素
bool GetElem(SqList L, int pos, ElemType &item);

//从线性表中查找元素，返回第一个与指定值匹配元素位置
int Find(SqList L, ElemType item);

//遍历输出线性表
void TraverseList(SqList L);

// 合并函数声明
void MergeList(SqList& L1, SqList& L2);