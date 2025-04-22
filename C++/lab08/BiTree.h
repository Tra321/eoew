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

//按照前序遍历序列建立二叉树
void CreateBTree_Pre(BTNode *&root, ElemType Array[]);

//前序遍历二叉树
void PreOrder(BTNode *root);

//计算二叉树深度
int BTreeDepth(BTNode *root);

//释放二叉树中所有结点
void ClearBTree(BTNode *&root);