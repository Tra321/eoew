﻿//#include "LinkedList.h"
//
////初始化单链表
//void InitList(LinkNode *&L)
//{ //L为指向单链表的头指针
//  L = new LinkNode; //分配空间，作为头结点
//  L->next = NULL;
//}
//
////判表空
//bool ListEmpty(LinkNode *L)
//{ //L为指向单链表的头指针
//  if (L->next == NULL)
//    return true; //头结点指针域为空
//  else
//    return false;
//}
//
////求单链表中当前元素的个数
//int ListLength(LinkNode *L)
//{ //L为指向单链表的头指针
//  int n = 0;
//  LinkNode *p = L->next;
//  while (p) {
//    n++; //计数器+1
//    p = p->next; //指针后移
//  }
//  return n;
//}
//
////遍历单链表
//void TraverseList(LinkNode *L)
//{ //L为指向单链表的头指针
//  LinkNode *p = L->next;
//  while (p) {
//    cout << p->data << "  ";
//    p = p->next;
//  }
//  cout << endl;
//}
//
////返回第一个与指定值匹配的元素位置
//int Find_item(LinkNode *L, ElemType item)
//{ //L为指向单链表的头指针
//  LinkNode *p = L->next;
//  int pos = 1; //结点位序
//  while (p && p->data != item) { //从单链表第一个结点开始顺序查找所有结点
//    p = p->next;
//    pos++;
//  }
//  if (p)
//    return pos; //返回位置编号
//  else
//    return 0; //查找失败
//}
//
////获取单链表中指定位置上的数据元素
//bool Find_pos(LinkNode *L, int pos, ElemType &item)
//{ //L为指向单链表的头指针
//  LinkNode *p = L->next;
//  int i = 1; //结点位序
//  while (p && i != pos) {
//    p = p->next;
//    i++;
//  }
//  if (p == NULL) { //查找不成功，退出运行
//    cout << "位置无效" << endl;
//    return false;
//  }
//  item = p->data;
//  return true;
//}
//
////向线性表指定位置插入一个新元素
//bool ListInsert(LinkNode *L, int pos, ElemType item)
//{
//  LinkNode *p = L;
//  int i = 0;
//  while (p && i != pos - 1) { //查找pos的前驱
//    p = p->next;
//    i++;
//  }
//  if (p == NULL) { //查找不成功，退出运行
//    cout << "插入位置无效" << endl;
//    return false;
//  }
//  LinkNode *t = new LinkNode;
//  t->data = item; //①
//  t->next = p->next; //②
//  p->next = t; //③
//  return true;
//}
//
////从线性表中删除指定位置的元素
//bool ListDelete(LinkNode *L, int pos, ElemType &item)
//{
//  LinkNode *p = L, *t;
//  int i = 0;
//  while (p->next && i != pos - 1) { //查找pos的前驱
//    p = p->next;
//    i++;
//  }
//  if (p->next == NULL) { //查找不成功，退出运行
//    cout << "删除位置无效" << endl;
//    return false;
//  }
//  t = p->next; //①t为被删除结点
//  p->next = t->next; //②删除t的链接关系
//  item = t->data; //保存被删除结点的值
//  delete t; //③释放被删结点
//  return true;
//}
//
////从线性表中删除第一个与指定值匹配的元素
//bool ListDelete(LinkNode *L, ElemType item)
//{
//  LinkNode *p = L, *t;
//  while (p->next && p->next->data != item) {
//    p = p->next;
//  }
//  if (p->next == NULL) { //查找不成功，退出运行
//    cout << "删除元素不存在" << endl;
//    return false;
//  }
//  t = p->next; //①t为被删除结点
//  p->next = t->next; //②删除t的链接关系
//  delete t; //③释放被删结点
//  return true;
//}
//
////撤销单链表
//void DestroyList(LinkNode *&L)
//{ //L为指向单链表的头指针
//  LinkNode *p;
//  while (L) {
//    p = L;
//    L = L->next; // 使用L指针保存下一结点的位置
//    delete p;
//  }
//}
//
//bool ListInsert_order(LinkNode* L, ElemType item)
//{
//    LinkNode* p = L;
//
//    while (p->next && p->next->data < item) {
//        p = p->next;
//    }
//
//    LinkNode* t = new LinkNode;
//    t->data = item;
//    t->next = p->next;
//    p->next = t;
//    return true;
//}
//
//bool Delete_max(LinkNode* L, ElemType& item) {
//    if (L->next == NULL) {
//        return false; // 链表为空，返回 false
//    }
//
//    LinkNode* prevMax = L; // 最大值结点的前驱结点
//    LinkNode* maxNode = L->next; // 最大值结点
//    LinkNode* prev = L; // 当前结点的前驱结点
//    LinkNode* current = L->next; // 当前结点
//
//    // 遍历链表找到最大值结点及其前驱结点
//    while (current != NULL) {
//        if (current->data > maxNode->data) {
//            maxNode = current;
//            prevMax = prev;
//        }
//        prev = current;
//        current = current->next;
//    }
//
//    // 删除最大值结点
//    prevMax->next = maxNode->next;
//    item = maxNode->data;
//    delete maxNode;
//
//    return true;
//}

#include "LinkedList.h"

// 初始化单链表
void InitList(LinkNode*& L) {
    L = new LinkNode;
    L->next = NULL;
}

// 判表空
bool ListEmpty(LinkNode* L) {
    if (L->next == NULL)
        return true;
    else
        return false;
}

// 求单链表中当前元素的个数
int ListLength(LinkNode* L) {
    int n = 0;
    LinkNode* p = L->next;
    while (p) {
        n++;
        p = p->next;
    }
    return n;
}

// 遍历单链表
void TraverseList(LinkNode* L) {
    LinkNode* p = L->next;
    while (p) {
        cout << p->data.id << " " << p->data.score << endl;
        p = p->next;
    }
}

// 返回第一个与指定值匹配的元素位置
int Find_item(LinkNode* L, ElemType item) {
    LinkNode* p = L->next;
    int pos = 1;
    while (p && (p->data.id != item.id || p->data.score != item.score)) {
        p = p->next;
        pos++;
    }
    if (p)
        return pos;
    else
        return 0;
}

// 获取单链表中指定位置上的数据元素
bool Find_pos(LinkNode* L, int pos, ElemType& item) {
    LinkNode* p = L->next;
    int i = 1;
    while (p && i != pos) {
        p = p->next;
        i++;
    }
    if (p == NULL) {
        cout << "位置无效" << endl;
        return false;
    }
    item = p->data;
    return true;
}

// 向线性表指定位置插入一个新元素
bool ListInsert(LinkNode* L, int pos, ElemType item) {
    LinkNode* p = L;
    int i = 0;
    while (p && i != pos - 1) {
        p = p->next;
        i++;
    }
    if (p == NULL) {
        cout << "插入位置无效" << endl;
        return false;
    }
    LinkNode* t = new LinkNode;
    t->data = item;
    t->next = p->next;
    p->next = t;
    return true;
}

// 从线性表中删除指定位置的元素
bool ListDelete(LinkNode* L, int pos, ElemType& item) {
    LinkNode* p = L, * t;
    int i = 0;
    while (p->next && i != pos - 1) {
        p = p->next;
        i++;
    }
    if (p->next == NULL) {
        cout << "删除位置无效" << endl;
        return false;
    }
    t = p->next;
    p->next = t->next;
    item = t->data;
    delete t;
    return true;
}

// 从线性表中删除学号为 number 的结点
bool ListDelete(LinkNode* L, int number) {
    LinkNode* p = L, * t;
    while (p->next && p->next->data.id != number) {
        p = p->next;
    }
    if (p->next == NULL) {
        cout << "删除元素不存在" << endl;
        return false;
    }
    t = p->next;
    p->next = t->next;
    delete t;
    return true;
}

// 撤销单链表
void DestroyList(LinkNode*& L) {
    LinkNode* p;
    while (L) {
        p = L;
        L = L->next;
        delete p;
    }
}