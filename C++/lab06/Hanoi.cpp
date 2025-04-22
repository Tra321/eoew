//#include <iostream>
//using namespace std;
//
//void Hanoi(int n, char X, char Y, char Z)
//{ //n表示需要移动盘子的数量，X表示源塔，Y表示借用塔，Z表示目标塔
//  if (n == 1) //只有一个盘子时，将其从X塔移动到Z塔
//    cout << X << "->" << Z << "\t";
//  else {
//    //①借助Z塔,将前n-1个盘子从X塔移动到Y塔
//    Hanoi(n - 1, X, Z, Y);
//    //②将X塔上剩下的1个盘子移到Z塔
//    cout << X << "->" << Z << "\t";
//    //③借助X塔,将前n-1个盘子从Y塔移动到Z塔
//    Hanoi(n - 1, Y, X, Z);
//  }
//}
//
////int main()
//{
//    Hanoi(3, 'A', 'B', 'C');
//    //Hanoi(6, 'A', 'B', 'C');
//    //Hanoi(9, 'A', 'B', 'C');
//    /*int n;
//    cout << "请给一个整数：";
//    cin >> n;
//    Hanoi(n, 'A', 'B', 'C');*/
//    return 0;