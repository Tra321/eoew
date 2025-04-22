#include "LinkQueue.h"
#include "SqQueue.h"

#include "SqStack.h"
#include "LinkStack.h"

void Reverse(LinkQueue Q)
{
  ElemType t;
  LinkStNode *S;
  InitStack(S);
  while (!QueueEmpty(Q)) {
    DeQueue(Q, t);
    Push(S, t);
  }
  while (!StackEmpty(S)) {
    Pop(S, t);
    EnQueue(Q, t);
  }
}

int main()
{
  //链式队列实验 开始
  ElemType temp;
  LinkQueue Q1;
  InitQueue(Q1);

  EnQueue(Q1, 'a'); //断点①
  EnQueue(Q1, 'b');
  EnQueue(Q1, 'c');

  DeQueue(Q1, temp); //断点②
  DeQueue(Q1, temp);
  DeQueue(Q1, temp);
  //链式队列实验 结束

  //循环队列实验 开始
  SqQueue Q2;
  InitQueue(Q2);

  EnQueue(Q2, 'a'); //断点③
  EnQueue(Q2, 'b');
  EnQueue(Q2, 'c');
  EnQueue(Q2, 'd');

  EnQueue(Q2, 'e'); //此处入队是否成功？

  DeQueue(Q2, temp); //断点④
  DeQueue(Q2, temp);
  DeQueue(Q2, temp);
  DeQueue(Q2, temp);

  for (int i = 0; i < 4; i++)
    EnQueue(Q2, 'f' + i);
  EnQueue(Q2, 'j'); //断点⑤ 此处入队是否成功？
  //循环队列实验 结束

  /**
  LinkQueue Q;
  InitQueue(Q);
  for (int i = 0; i < 4; i++)
    EnQueue(Q, 'a' + i);
  TraverseQueue(Q);
  Reverse(Q);
  TraverseQueue(Q);
  /**/

  return 0;
}
