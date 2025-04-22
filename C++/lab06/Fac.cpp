#include <iostream>
using namespace std;

int fac(int n)
{
  int result = 1;
  for (int i = 1; i <= n; i++) {
    result *= i;
  }
  return result;
}

// 递归计算数组前n个元素的平均值
double avg(int A[], int n) {
    if (n == 1) {
        return A[0];
    }
    return (avg(A, n-1) * (n-1) + A[n-1]) / n;
}

int main()
{
  // 测试阶乘
  cout << "阶乘测试：" << endl;
  cout << "结果：fac(5)=" << fac(5) << endl;
  
  // 测试平均值
  cout << "\n平均值测试：" << endl;
  int A[] = {1, 2, 3, 4, 5};
  int n = sizeof(A) / sizeof(A[0]);
  
  cout << "数组元素：";
  for (int i = 0; i < n; i++) {
      cout << A[i] << " ";
  }
  cout << endl;
  
  cout << "平均值 = " << avg(A, n) << endl;
  
  return 0;
}