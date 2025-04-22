#include <iostream>
using namespace std;

#define MaxSize 5
typedef char ElemType;

typedef struct {
  ElemType data[MaxSize];
  int top;
} SqStack;

//初始化空顺序栈
void InitStack(SqStack &S);

//判顺序栈空
bool StackEmpty(SqStack S);

//入栈
bool Push(SqStack &S, ElemType item);

//出栈
bool Pop(SqStack &S, ElemType &item);

//取栈顶
bool GetTop(SqStack S, ElemType &item);

//遍历栈
void TraverseStack(SqStack S);