#include <iostream>
using namespace std;

typedef char ElemType;

typedef struct node {
  ElemType data; //数据域
  struct node *left;
  struct node *right; //结点的左右子树指针
} BTNode; //二叉树结点类型

//初始化空二叉树
void InitBTree(BTNode *&root);

//按照课件示意图建立一棵二叉树
void CreateBTree(BTNode *&root);

//计算二叉树叶子结点个数的函数
int N0(BTNode *root);

//计算二叉树双分支结点个数的函数
int N2(BTNode *root);