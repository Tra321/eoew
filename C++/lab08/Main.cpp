#include "BiTree.h"

int main()
{
  BTNode *root;
  ElemType A[] = ""; //以"#"补全空分支后的前序遍历序列

   //初始化空二叉树
   //用前序遍历序列建立二叉树




  //调用前序遍历函数，输出前序遍历序列

  //调用中序遍历函数，输出中序遍历序列

//调用后序遍历函数，输出后序遍历序列

  cout << "深度：" << BTreeDepth(root) << endl; //计算二叉树深度

  ClearBTree(root);
  return 0;
}