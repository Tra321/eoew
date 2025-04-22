#include <iostream>
using namespace std;

#define MaxSize 5
typedef char ElemType;

typedef struct
{
  ElemType data[MaxSize];
  int front;
  int rear;
} SqQueue;

//初始化空循环队列
bool InitQueue(SqQueue &Q);

//求循环队列长度
int QueueLength(SqQueue Q);

//判循环队列空
bool QueueEmpty(SqQueue Q);

//循环队列入队
bool EnQueue(SqQueue &Q, ElemType item);

//循环队列出队
bool DeQueue(SqQueue &Q, ElemType &item);

//取队头
bool GetFront(SqQueue Q, ElemType &item);

//遍历队列
bool TraverseQueue(SqQueue Q);