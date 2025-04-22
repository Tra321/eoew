#include "LinkStack.h"

//初始化空链栈
bool InitStack(LinkStNode *&S)
{
  S = new LinkStNode;
  if (S == NULL) {
    cout << "初始化错误";
    return false;
  }
  S->next = NULL;
  return true;
}

//判栈空
bool StackEmpty(LinkStNode *S)
{
  if (S->next == NULL)
    return true;
  else
    return false;
}

//入栈
bool Push(LinkStNode *S, ElemType item)
{ //带头结点单链表的表头插入法
  LinkStNode *t = new LinkStNode; //①生成新结点
  if (t == NULL) {
    cout << "出错";
    return false;
  }
  t->data = item;

  //②在栈顶插入新结点
  t->next = S->next;
  S->next = t;
  return true;
}

//出栈
bool Pop(LinkStNode *S, ElemType &item)
{ //删除单链表的第一个元素结点
  //①判断栈是否为空
  if (S->next == NULL) {
    cout << "栈空";
    return false;
  }

  //②删除栈顶元素
  LinkStNode *t = S->next;
  S->next = t->next;
  item = t->data;
  delete t;
  return true;
}

//取栈顶
bool GetTop(LinkStNode *S, ElemType &item)
{
  //①判断栈是否为空
  if (S->next == NULL) {
    cout << "栈空";
    return false;
  }

  //②取栈顶元素
  item = S->next->data;
  return true;
}

//释放链栈
void Destroy(LinkStNode *&S)
{
  LinkStNode *p;
  while (S) {
    p = S;
    S = S->next;
    delete p;
  }
}

void TraverseStack(LinkStNode* S) {
    if (StackEmpty(S))
    {
        cout << "栈空";
        return;
    }
    LinkStNode* p;
    p = S->next;
    while (p)
    {
        cout << p->data << " ";
        p = p->next;
    }
    cout << endl;
}