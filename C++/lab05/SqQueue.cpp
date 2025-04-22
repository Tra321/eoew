#include "SqQueue.h"

//初始化空循环队列
bool InitQueue(SqQueue &Q)
{
  Q.front = Q.rear = 0;
  return true;
}

//求循环队列长度
int QueueLength(SqQueue Q)
{
  return (Q.rear - Q.front + MaxSize) % MaxSize;
}

//判循环队列空
bool QueueEmpty(SqQueue Q)
{
  if (Q.front == Q.rear)
    return true;
  else
    return false;
}

//循环队列入队
bool EnQueue(SqQueue &Q, ElemType item)
{
  if ((Q.rear + 1) % MaxSize == Q.front) {
    cout << "队满";
    return false;
  } //①判队满

  Q.data[Q.rear] = item; //②新元素放尾指针位置
  Q.rear = (Q.rear + 1) % MaxSize; //③尾指针后移
  return true;
}

//循环队列出队
bool DeQueue(SqQueue &Q, ElemType &item)
{
  if (Q.rear == Q.front) {
    cout << "队空";
    return false;
  } //①判队空

  item = Q.data[Q.front]; //②删除队头元素
  Q.front = (Q.front + 1) % MaxSize; //③头指针后移
  return true;
}

//取队头
bool GetFront(SqQueue Q, ElemType &item)
{
  if (Q.rear == Q.front) {
    cout << "队空";
    return false;
  } //①判队空

  item = Q.data[Q.front]; //②取队头元素
  return true;
}

//遍历队列
bool TraverseQueue(SqQueue Q)
{
  if (Q.rear == Q.front) {
    cout << "队空";
    return false;
  }
  int i = Q.front;
  while (i != Q.rear) {
    cout << Q.data[i] << " ";
    i = (i + 1) % MaxSize;
  }
  cout << endl;
  return true;
}