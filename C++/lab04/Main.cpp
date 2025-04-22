#include "SqStack.h"
#include "LinkStack.h"

//int main()
//{
//  ElemType result;
//
//  //顺序存储结构实现实验
//  SqStack my_stack1;
//  InitStack(my_stack1);
//
//  for (int i = 1; i <= 10; i++)
//    Push(my_stack1, i); //数据入栈，断点①
//
//  TraverseStack(my_stack1);
//
//  while (!StackEmpty(my_stack1))
//    Pop(my_stack1, result); //数据出栈，断点②
//
//  //链式存储结构实现实验
//  LinkStNode *my_stack2;
//  InitStack(my_stack2);
//
//  for (int i = 1; i <= 10; i++)
//    Push(my_stack2, i); //数据入栈，断点③
//
//  TraverseStack(my_stack2);
//
//  while (!StackEmpty(my_stack2))
//    Pop(my_stack2, result); //数据出栈，断点④
//
//  Destroy(my_stack2);
//
//  return 0;
//}

//

bool IsValid(char* s) {
    SqStack S;
    InitStack(S);
    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] == '(' || s[i] == '[' || s[i] == '{') {
            Push(S, s[i]);
        }
        else {
            ElemType top;
            if (!GetTop(S, top)) {
                return false;
            }
            if ((s[i] == ')' && top == '(') || (s[i] == ']' && top == '[') || (s[i] == '}' && top == '{')) {
                Pop(S, top);
            }
            else {
                return false;
            }
        }
    }
    return StackEmpty(S);
}
bool IsValid(char* s);
int main() {
    char s1[] = "{[()]}()";
    char s2[] = "[(]){";
    std::cout << "字符串 " << s1 << " 的括号是否匹配: " << (IsValid(s1) ? "true" : "false") << std::endl;
    std::cout << "字符串 " << s2 << " 的括号是否匹配: " << (IsValid(s2) ? "true" : "false") << std::endl;
    return 0;
}