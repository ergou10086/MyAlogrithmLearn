#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// 封闭图形个数打表
const int di[10] = {1, 0, 0, 0, 1, 0, 1, 0, 2, 1};

// 拆位求和
int getSum(int a) {
    int sum = 0;
    while (a > 0) {
        sum += di[a % 10];
        a /= 10;
    }
    return sum;
}

// 自定义比较函数
bool compare(int a, int b) {
    int sumA = getSum(a);
    int sumB = getSum(b);

    // a的封闭数更少，a应该排在b前面（升序）
    if (sumA < sumB) {
        return true;
    } else if (sumA > sumB) {
        return false;
    } else {
        return a < b;
    }
}

int main() {
    int n;
    cin >> n;

    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    // 自定义排序，从小到大排序
    sort(arr.begin(), arr.end(), compare);

    for (int num : arr) {
        cout << num << " ";
    }

    return 0;
}