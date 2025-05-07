#include <iostream>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    int coins[1001] = {0};  // 初始化为全0

    // 读取硬币并填充桶数组
    for (int i = 0; i < n; ++i) {
        int value;
        cin >> value;
        if (value <= 1000) {  // 过滤超范围值（题目保证面值<=500）
            coins[value]++;
        }
    }

    // 遍历所有可能的硬币面值
    for (int v1 = 0; v1 <= 1000; ++v1) {
        if (coins[v1] > 0) {
            coins[v1]--;  // 尝试使用当前硬币
            int v2 = m - v1;

            // 检查v2是否合法且存在可用硬币
            if (v2 >= 0 && v2 <= 1000 && coins[v2] > 0) {
                cout << v1 << " " << v2 << endl;
                return 0;  // 找到解立即退出
            }
            coins[v1]++;  // 回溯硬币使用
        }
    }

    cout << "No Solution" << endl;
    return 0;
}