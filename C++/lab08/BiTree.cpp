#include "BiTree.h"

//初始化空二叉树
void InitBTree(BTNode *&root)
{
  root = NULL;
}

//按照前序遍历序列建立二叉树
void CreateBTree_Pre(BTNode *&root, ElemType Array[])
{
  static int count = 0; //静态变量count
  char item = Array[count]; //读取Array[]数组中的第count个元素
  count++;
  if (item == '#') { //如果读入#字符，创建空树
    root = NULL;
  } else {
    root = new BTNode; //建根结点
    root->data = item;
    CreateBTree_Pre(root->left, Array); //建左子树
    CreateBTree_Pre(root->right, Array); //建右子树
  }
}

//前序遍历二叉树
//void PreOrder(BTNode *root)



//计算二叉树深度
int BTreeDepth(BTNode *root)
{
  static int CALL = 0; //记录调用次数
  static int RETURN = 0; //记录返回次数

  CALL++; //调用次数加一

  if (root == NULL) {
    RETURN++; //返回次数加一
    return 0;
  } else {
    int depl = BTreeDepth(root->left);
    int depr = BTreeDepth(root->right);
    if (depl > depr) {
      RETURN++; //返回次数加一
      return depl + 1;
    } else {
      RETURN++; //返回次数加一
      return depr + 1;
    }
  }
}

//释放二叉树中所有结点
void ClearBTree(BTNode *&root)
{
  if (root != NULL) {
    ClearBTree(root->left);
    ClearBTree(root->right);
    delete root;
    root = NULL;
  }
}