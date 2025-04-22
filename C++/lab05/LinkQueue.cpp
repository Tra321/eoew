#include "LinkQueue.h"

//初始化带头结点的链队列
bool InitQueue(LinkQueue &Q)
{ //构造只有一个空头结点的链式队列
  //头、尾指针均指向头结点
  Q.front = Q.rear = new QNode;
  if (Q.front == NULL) {
    cout << "出错";
    return false;
  }
  Q.front->next = NULL;
  return true;
}

//判链队列空
bool QueueEmpty(LinkQueue Q)
{
  if (Q.front == Q.rear)
    return true;
  else
    return false;
}

//入队
bool EnQueue(LinkQueue &Q, ElemType item)
{ //单链表的尾插法
  QNode *t = new QNode; //①生成新结点
  if (t == NULL) {
    cout << "出错";
    return false;
  }
  t->data = item;
  t->next = NULL;

  Q.rear->next = t; //②在尾结点后插入新结点
  Q.rear = t; //③更新尾指针
  return true;
}

//出队
bool DeQueue(LinkQueue &Q, ElemType &item)
{ //删除单链表的表头元素
  //①判空
  if (Q.front == Q.rear) {
    cout << "队空";
    return false;
  }
  QNode *t = Q.front->next;
  Q.front->next = t->next; //②删除队头结点

  if (Q.rear == t) //③若删除尾结点须更新尾指针
    Q.rear = Q.front;

  item = t->data;
  delete t;
  return true;
}

//取队头元素
bool GetFront(LinkQueue Q, ElemType &item)
{
  if (Q.front == Q.rear) {
    cout << "队空";
    return false;
  }
  item = Q.front->next->data;
  return true;
}

//遍历队列
bool TraverseQueue(LinkQueue Q)
{
  if (Q.front == Q.rear) {
    cout << "队空";
    return false;
  }
  QNode *p = Q.front->next;
  while (p) {
    cout << p->data << " ";
    p = p->next;
  }
  cout << endl;
  return true;
}

//释放链队列
void Destroy(LinkQueue &Q)
{
  while (Q.front) {
    Q.rear = Q.front;
    Q.front = Q.front->next;
    delete Q.rear;
  }
}